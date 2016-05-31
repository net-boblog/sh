package com.qccr.sh.external.orderCenter.impl;

import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import com.qccr.ordercenter.facade.entity.kefu.KefuServerOrderRo;
import com.qccr.ordercenter.facade.entity.order.ServerOrderQueryRo;
import com.qccr.ordercenter.facade.service.merchant.StoreManageFacade;
import com.qccr.ordercenter.facade.service.order.ServerOrderFacade;
import com.qccr.sh.external.crm.bo.ProductBO;
import com.qccr.sh.external.orderCenter.ServerOrderExt;
import com.qccr.sh.external.orderCenter.bo.PromotionSourceEnum;
import com.qccr.sh.external.orderCenter.bo.ServerOrderBO;
import com.qccr.sh.external.orderCenter.bo.ServerOrderQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.qccr.sh.util.BeanCopierUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dongxc on 2015/12/1.
 */
@Service("serverOrderExt")
public class ServerOrderExtImpl implements ServerOrderExt {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServerOrderFacade serverOrderFacade;
    @Autowired
    private StoreManageFacade storeManageFacade;

    @Override
    public Response<Pagination<ServerOrderBO>> queryByStoreIdAndSmscode(ServerOrderQuery serverOrderQuery) {
        try {
            ServerOrderQueryRo serverOrderQueryRo = new ServerOrderQueryRo();
            if(serverOrderQuery !=null){
                serverOrderQuery.setPageNo(serverOrderQuery.getPageNo().intValue()+1);
                BeanCopierUtils.copy(serverOrderQuery, serverOrderQueryRo);
            }else{
                serverOrderQueryRo.setPageNo(1);
                serverOrderQueryRo.setPageSize(15);
            }
            Result<List<KefuServerOrderRo>> serverOrderRoResult = serverOrderFacade.queryByStoreIdAndSmscode(serverOrderQueryRo);
            if(serverOrderRoResult!=null && serverOrderRoResult.isSuccess() && serverOrderRoResult.getData()!=null) {
                String totalCount = (String) serverOrderRoResult.getExtData().get("totalCount");
                if (serverOrderQuery != null && serverOrderQueryRo != null) {
                    Pagination<ServerOrderBO> pagination = new Pagination<ServerOrderBO>(serverOrderQuery.getPageSize()
                            , serverOrderQueryRo.getPageNo(), new Integer(totalCount));
                    List<ServerOrderBO> serverOrderBOs = new LinkedList<ServerOrderBO>();
                    List<KefuServerOrderRo> kefuServerOrderRos = serverOrderRoResult.getData();
                    if (kefuServerOrderRos != null && kefuServerOrderRos.size() > 0) {
                        for (KefuServerOrderRo orderRo : kefuServerOrderRos) {
                            ServerOrderBO orderBO = new ServerOrderBO();
                            BeanCopierUtils.copy(orderRo, orderBO);
                            if (orderBO.getSaleNum() != null && orderBO.getSprice() != null) {
                                orderBO.setSaleSprice(orderBO.getSprice().doubleValue() * orderBO.getSaleNum());
                            } else {
                                orderBO.setSaleSprice(new Double(0));
                            }
                            //订单参加的活动
                            if(orderRo.getSource() != null && Integer.valueOf(orderRo.getSource()) == 21){
                                //美团的活动
                                orderBO.setPromotionSource(PromotionSourceEnum.MEITUAN.getValue());
                                orderBO.setPromotionName("美团订单");
                            }else if(orderRo.getPromotionType() != null && Integer.valueOf(orderRo.getPromotionType()) == 2){
                                //促销类型为服务,细分平台活动和商家自建活动

                                int sponsor = -1;
                                if(orderRo.getSponsor()!=null){
                                    sponsor = Integer.valueOf(orderRo.getSponsor());
                                }
                                if(sponsor == 1){//平台活动
                                    orderBO.setPromotionSource(PromotionSourceEnum.CU.getValue());
                                }else if(sponsor == 2){//商家活动
                                    orderBO.setPromotionSource(PromotionSourceEnum.SHANG.getValue());
                                }

                            }
                            serverOrderBOs.add(orderBO);
                        }
                    }
                    pagination.setDataList(serverOrderBOs);
                    return ResponseUtil.success(pagination);
                }
                return null;
            }else if(!serverOrderRoResult.isSuccess() && serverOrderRoResult != null && serverOrderRoResult.getStateCode() != null){
                logger.error("查询已验证订单列表时异常了", serverOrderRoResult.getStateCode().getDesc());
                return ResponseUtil.error(serverOrderRoResult.getStateCode().getDesc());
            }else{
                logger.error("查询已验证订单列表时返回空");
                return ResponseUtil.error("查询已验证订单列表时返回空");
            }
        } catch (Exception ex) {
            logger.error("查询已验证订单列表时异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }
    @Override
    public Response<List<ServerOrderBO>> queryByStoreIdAndOrderids(Long storeId,Long[] orderIdArr){
        try {
            List<ServerOrderBO> serverOrderBOList = new ArrayList<ServerOrderBO>();
            Result<List<KefuServerOrderRo>> serverOrderResult = serverOrderFacade.queryByStoreIdAndOrderids(storeId,orderIdArr);
            if(serverOrderResult!=null && serverOrderResult.isSuccess() && serverOrderResult.getData()!=null){
                List<KefuServerOrderRo> serverOrderRoList = serverOrderResult.getData();
                if(serverOrderBOList.size()>0){
                    for(KefuServerOrderRo serverOrderRo : serverOrderRoList){
                        ServerOrderBO serverOrderBO = new ServerOrderBO();
                        BeanCopierUtils.copy(serverOrderRo, serverOrderBO);
                        serverOrderBOList.add(serverOrderBO);
                    }
                }
            }
            return ResponseUtil.success(serverOrderBOList);
        } catch (Exception ex) {
            logger.error("查询已验证订单列表时异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }
    @Override
    public Result<String> verifyInit(String smsCode, long serverOrderId, int storeId) {
        if(smsCode==null || smsCode.equals("") || storeId == 0){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "参数不能为空");
        }
        try {
            logger.info("调用ordercenter的StoreManageFacade.verifyInit接口，根据验证码和门店ID进行核销，smsCode={},storeId={}",smsCode, storeId);
            return storeManageFacade.verifyInit(smsCode,serverOrderId,storeId);
        } catch (RuntimeException ex) {
            logger.error("调用ordercenter的StoreManageFacade.verifyInit接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }

    /**
     * 获取服务订单数
     * @param listResult
     * @return
     */
    public Response<List<ProductBO>> countOrder(List<ProductBO> listResult){
        if(listResult ==null ){
            return  ResponseUtil.error("参数不能为空");
        }
        for (ProductBO productBO:listResult){
            try {
                logger.info("调用ordercenter的serverOrderFacade.countOrderByServerStoreId接口，根据服务id获取服务的订单数，ProductId={}",productBO.getProductId());
                Result<Integer> countOrder= serverOrderFacade.countOrderByServerStoreId(Integer.parseInt(productBO.getProductId()));
                if(countOrder.isSuccess()){
                    productBO.setCountOrder(countOrder.getData() == null ? "0" : countOrder.getData()+"");
                }else{
                    productBO.setCountOrder("获取失败");
                }
            }catch (Exception ex){
                logger.error("调用ordercenter的serverOrderFacade.countOrderByServerStoreId接口异常,ex={}", ex);
                productBO.setCountOrder("获取失败");
            }
        }
        return  ResponseUtil.success(listResult);
    }
}
