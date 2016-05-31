package com.qccr.sh.external.orderCenter.impl;

import com.qccr.commons.money.Money;
import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import com.qccr.ordercenter.facade.entity.merchant.InsteadReceiveOrderRo;
import com.qccr.ordercenter.facade.entity.order.GoodsOrderRo;
import com.qccr.ordercenter.facade.entity.order.OrderInfoRo;
import com.qccr.ordercenter.facade.entity.order.OrderQueryRo;
import com.qccr.ordercenter.facade.entity.order.OrderRo;
import com.qccr.ordercenter.facade.entity.order.ServerOrderRo;
import com.qccr.ordercenter.facade.service.merchant.StoreServerFacade;
import com.qccr.ordercenter.facade.service.order.OrderFacade;
import com.qccr.ordercenter.facade.service.order.ServerOrderFacade;
import com.qccr.sh.external.orderCenter.OrderExt;
import com.qccr.sh.external.orderCenter.bo.GoodsOrderBO;
import com.qccr.sh.external.orderCenter.bo.OrderBO;
import com.qccr.sh.external.orderCenter.bo.OrderQuery;
import com.qccr.sh.external.orderCenter.bo.ServerOrderBO;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.qccr.sh.util.BaseConvert;
import com.qccr.sh.util.BeanCopierUtils;
import com.towell.carman.common.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dongxc on 2015/12/1.
 */
@Service("orderExt")
public class OrderExtImpl implements OrderExt {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private ServerOrderFacade serverOrderFacade;

    @Autowired
    private StoreServerFacade storeServerFacade;

    @Override
    public Response<Pagination<OrderBO>> queryDeliveryOrderList(OrderQuery orderQuery){
        try {
            OrderQueryRo orderQueryRo = new OrderQueryRo();
            if(orderQuery!=null){
                //待删除
                /*orderQuery.setAddressId(9767l);
                orderQuery.setStoreId(9767);*/
                orderQuery.setPageNumber(orderQuery.getPageNumber().intValue()+1);
                BeanCopierUtils.copy(orderQuery, orderQueryRo);
            }else{
                orderQueryRo.setPageSize(20);
                orderQueryRo.setPageNumber(1);
            }
            Result<List<InsteadReceiveOrderRo>> insteadReceiveOrderRoListResult = storeServerFacade.queryReceivedOrderIdListByStoreId(orderQueryRo);
            if(insteadReceiveOrderRoListResult!=null && insteadReceiveOrderRoListResult.isSuccess()){
                String totalCount = "0";
                Map<Long, InsteadReceiveOrderRo> receiveOrderRoMap = null;
                if(insteadReceiveOrderRoListResult.getData()!=null && insteadReceiveOrderRoListResult.getData().size()>0){
                    receiveOrderRoMap = new HashMap<>();
                    for(InsteadReceiveOrderRo orderRo : insteadReceiveOrderRoListResult.getData()){
                        receiveOrderRoMap.put(orderRo.getOrderId(), orderRo);
                    }
                }
                if(insteadReceiveOrderRoListResult.getExtData()!=null){
                    totalCount = (String)insteadReceiveOrderRoListResult.getExtData().get("totalCount");
                }
                if(receiveOrderRoMap!=null && receiveOrderRoMap.size()>0){
                    List<Long> orderIdList = new ArrayList<>(receiveOrderRoMap.keySet());
                    //根据orderId集合查询order对象
                    Result<List<OrderRo>> orderRoListResult = orderFacade.queryOrderDetailByOrderIdList(orderIdList);
                    if(orderRoListResult!=null && orderRoListResult.isSuccess() && orderRoListResult.getData()!=null){
                        Pagination<OrderBO> pagination = new Pagination<OrderBO>(orderQuery.getPageSize(),orderQuery.getPageNumber(), new Integer(totalCount));
                        List<OrderBO> orderBOList = new LinkedList<OrderBO>();
                        List<OrderRo> orderRoList = orderRoListResult.getData();
                        if(orderRoList!=null && orderRoList.size()>0){
                            for(OrderRo orderRo : orderRoList){
                                OrderBO orderBO = new OrderBO();
                                BeanCopierUtils.copy(orderRo, orderBO);
                                if(orderRo.getGoodsOrderRoList()!=null && orderRo.getGoodsOrderRoList().size()>0){
                                    List<GoodsOrderRo> goodsOrderRoList = orderRo.getGoodsOrderRoList();
                                    List<GoodsOrderBO> goodsOrderBOList = new ArrayList<GoodsOrderBO>();
                                    for(GoodsOrderRo goodsOrderRo:goodsOrderRoList){
                                        int goodsStatus = goodsOrderRo.getStatus();
                                        //退货的商品不展示
                                        if(goodsStatus!=41 && goodsStatus!=44 && goodsStatus!=46){
                                            GoodsOrderBO goodsOrderBO = new GoodsOrderBO();
                                            BeanCopierUtils.copy(goodsOrderRo, goodsOrderBO);
                                            goodsOrderBOList.add(goodsOrderBO);
                                        }
                                    }
                                    orderBO.setGoodsOrderBOList(goodsOrderBOList);
                                }
                                //订单状态是已收货待服务状态，但是要判断该订单下面的所有服务单是否都已经服务了，如果都为已服务则订单状态显示为已完成
                                boolean hasCheck = true;
                                if(orderRo.getServerOrderRoList()!=null && orderRo.getServerOrderRoList().size()>0){
                                    List<ServerOrderRo> serverOrderRoList = orderRo.getServerOrderRoList();
                                    List<ServerOrderBO> serverOrderBOList = new ArrayList<ServerOrderBO>();
                                    int sailPrice = 0;
                                    for(ServerOrderRo serverOrderRo : serverOrderRoList){
                                        ServerOrderBO serverOrderBO = new ServerOrderBO();
                                        BeanCopierUtils.copy(serverOrderRo, serverOrderBO);
                                        serverOrderBOList.add(serverOrderBO);
                                        if(serverOrderBO.getStatus()!=null && serverOrderBO.getStatus().intValue()==Status.ServerOrder.NEW_CREATE){
                                            hasCheck = false; //有一单未完成就判定为待服务
                                        }

                                        //计算服务费用
                                        Integer saleNum = serverOrderRo.getSaleNum() == null ? 0 : serverOrderRo.getSaleNum();
                                        Integer refundNum = serverOrderRo.getRefundNum() == null ? 0 : serverOrderRo.getRefundNum();
                                        Double sprice = serverOrderRo.getSprice();
                                        sailPrice += (saleNum - refundNum) * Money.convertYuanToCent(sprice);
                                    }
                                    orderBO.setPrice(Money.convertCentToYuan(sailPrice));
                                    orderBO.setServerOrderBOList(serverOrderBOList);
                                }
                                if(receiveOrderRoMap.get(orderBO.getId())!=null){
                                    InsteadReceiveOrderRo insteadReceiveOrderRo = receiveOrderRoMap.get(orderBO.getId());
                                    /*if(insteadReceiveOrderRo.isChangeStoreFlag()){
                                        orderBO.setOrderStatus(4); //"改派"
                                        orderBO.setChangeStoreDesc(insteadReceiveOrderRo.getChangeStoreDesc());
                                    }else if(orderBO.getStatus().equals(Status.Order.OUT_DONE)){
                                        orderBO.setOrderStatus(1);  //"待收货";
                                    }else{
                                        if(hasCheck){
                                            orderBO.setOrderStatus(3);  //"已完成";
                                        }else{
                                            orderBO.setOrderStatus(2);   //待服务
                                        }
                                    }*/
                                    if(insteadReceiveOrderRo.isChangeStoreFlag()){
                                        orderBO.setOrderStatus(4); //"改派"
                                        orderBO.setChangeStoreDesc(insteadReceiveOrderRo.getChangeStoreDesc());
                                    }else if(orderBO.getStatus() <= Status.Order.OUT_DONE){
                                        orderBO.setOrderStatus(1);  //"待收货";
                                    }else if(orderBO.getStatus() == Status.Order.CONFIRM){
                                        orderBO.setOrderStatus(2);   //待服务
                                    }else if(orderBO.getStatus() >= Status.Order.COMMENT_DONE){
                                        orderBO.setOrderStatus(3);  //"已完成";
                                    }
                                }

                                orderBOList.add(orderBO);
                            }
                        }
                        pagination.setDataList(orderBOList);
                        return ResponseUtil.success(pagination);
                    }else{
                        return ResponseUtil.error("在查询order对象时返回空");
                    }
                }else{
                    return ResponseUtil.success(new Pagination<OrderBO>(orderQuery.getPageSize(), orderQuery.getPageNumber(), 0));
                }
            }else if(insteadReceiveOrderRoListResult!=null){
                logger.error("查询代收订单列表时返回失败",insteadReceiveOrderRoListResult.getStatusText());
                return ResponseUtil.error(insteadReceiveOrderRoListResult.getStatusText());
            }else{
                return ResponseUtil.error("接口返回为空!");
            }
        } catch (Exception ex) {
            logger.error("查询代收订单列表时异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }


    @Override
    public Response<OrderBO> queryOrderDetail(OrderQuery orderQuery){
        try {
            OrderQueryRo orderQueryRo = new OrderQueryRo();
            if(orderQuery!=null){
                //待删除
                /*orderQuery.setAddressId(9767l);
                orderQuery.setStoreId(9767);*/
                BeanCopierUtils.copy(orderQuery, orderQueryRo);
            }
            if(orderQueryRo.getOrderId()==null){
                return ResponseUtil.error("订单ID不能为空");
            }
            List<Long> orderIdList = new ArrayList<Long>();
            orderIdList.add(orderQueryRo.getOrderId());
            //根据orderId集合查询order对象
            Result<List<OrderRo>> orderRoListResult = orderFacade.queryOrderDetailByOrderIdList(orderIdList);
            if(orderRoListResult!=null && orderRoListResult.isSuccess() && orderRoListResult.getData()!=null){
                List<OrderRo> orderRoList = orderRoListResult.getData();
                if (orderRoList != null && orderRoList.size() > 0) {
                    OrderBO orderBO = new OrderBO();
                    for (OrderRo orderRo : orderRoList) {
                        BeanCopierUtils.copy(orderRo, orderBO);
                        if (orderRo.getGoodsOrderRoList() != null && orderRo.getGoodsOrderRoList().size() > 0) {
                            List<GoodsOrderRo> goodsOrderRoList = orderRo.getGoodsOrderRoList();
                            List<GoodsOrderBO> goodsOrderBOList = new ArrayList<GoodsOrderBO>();
                            for (GoodsOrderRo goodsOrderRo : goodsOrderRoList) {
                                int goodsStatus = goodsOrderRo.getStatus();
                                //退货的商品不展示
                                if(goodsStatus!=41 && goodsStatus!=44 && goodsStatus!=46) {
                                    GoodsOrderBO goodsOrderBO = new GoodsOrderBO();
                                    BeanCopierUtils.copy(goodsOrderRo, goodsOrderBO);
                                    goodsOrderBOList.add(goodsOrderBO);
                                }
                            }
                            orderBO.setGoodsOrderBOList(goodsOrderBOList);
                        }
                        //订单状态是已收货待服务状态，但是要判断该订单下面的所有服务单是否都已经服务了，如果都为已服务则订单状态显示为已完成
                        boolean hasCheck = true;
                        if (orderRo.getServerOrderRoList() != null && orderRo.getServerOrderRoList().size() > 0) {
                            List<ServerOrderRo> serverOrderRoList = orderRo.getServerOrderRoList();
                            List<ServerOrderBO> serverOrderBOList = new ArrayList<ServerOrderBO>();
                            int sailPrice = 0;
                            for (ServerOrderRo serverOrderRo : serverOrderRoList) {
                                ServerOrderBO serverOrderBO = new ServerOrderBO();
                                BeanCopierUtils.copy(serverOrderRo, serverOrderBO);
                                int[] wash= {4,5,6,7,8,9,10,11,46,47,48,49};
                                int[] tires= {13,14,15,16,17,18};
                                int[] maintain= {12,19,20,21,22,50,51,52,53,54,55,56,57,58,59,60};
                                String pic = "http://static.qichechaoren.com/upload/2015/08/";
                                Integer serverId = serverOrderRo.getServerId();
                                if(serverId==null){
                                    serverId = new Integer(0);
                                }
                                if(serverId.equals(6)){//大保养
                                    pic += "webLMaintain.png";
                                }else if(serverId.equals(5)){//小保养
                                    pic += "webSMaintain.png";
                                }else if(Arrays.binarySearch(wash, serverId)>=0){
                                    pic += "webWash.png";
                                }else if(Arrays.binarySearch(tires, serverId)>=0){
                                    pic += "webTire.png";
                                }else if(Arrays.binarySearch(maintain, serverId)>=0){
                                    pic += "webSMaintain.png";
                                }else{
                                    pic = "http://static.qichechaoren.com/shop/images/2015/09/defaultPic.jpg";
                                }
                                serverOrderBO.setImg(pic);
                                serverOrderBOList.add(serverOrderBO);
                                if (serverOrderBO.getStatus() != null && serverOrderBO.getStatus().intValue() == Status.ServerOrder.NEW_CREATE) {
                                    hasCheck = false; //有一单未完成就判定为待服务
                                }

                                //计算服务费用
                                Integer saleNum = serverOrderRo.getSaleNum() == null ? 0 : serverOrderRo.getSaleNum();
                                Integer refundNum = serverOrderRo.getRefundNum() == null ? 0 : serverOrderRo.getRefundNum();
                                Double sprice = serverOrderRo.getSprice();
                                sailPrice += (saleNum - refundNum) * Money.convertYuanToCent(sprice);
                            }
                            orderBO.setServerOrderBOList(serverOrderBOList);
                            orderBO.setPrice(Money.convertCentToYuan(sailPrice));
                        }
                        //设置orderStatus
                        //只查一条（只有一条）
                        orderQueryRo.setPageSize(10);
                        orderQueryRo.setPageNumber(1);
//                        orderQueryRo.setOrderNo(orderBO.getNo());
                        orderQueryRo.setNo(orderBO.getNo());
                        Result<List<InsteadReceiveOrderRo>> insteadReceiveOrderRoListResult = storeServerFacade.queryReceivedOrderIdListByStoreId(orderQueryRo);
                        if(insteadReceiveOrderRoListResult != null && insteadReceiveOrderRoListResult.isSuccess()
                                && !CollectionUtils.isEmpty(insteadReceiveOrderRoListResult.getData())){
                            InsteadReceiveOrderRo insteadReceiveOrderRo = insteadReceiveOrderRoListResult.getData().get(0);
                            /*if(insteadReceiveOrderRo.isChangeStoreFlag()){
                                orderBO.setOrderStatus(4); //"改派"
                                orderBO.setChangeStoreDesc(orderBO.getChangeStoreDesc());
                            }else if(orderBO.getStatus().equals(Status.Order.OUT_DONE)){
                                orderBO.setOrderStatus(1);  //"待收货";
                            }else{
                                if(hasCheck){
                                    orderBO.setOrderStatus(3);  //"已完成";
                                }else{
                                    orderBO.setOrderStatus(2);   //待服务
                                }
                            }*/
                            if(insteadReceiveOrderRo.isChangeStoreFlag()){
                                orderBO.setOrderStatus(4); //"改派"
                                orderBO.setChangeStoreDesc(insteadReceiveOrderRo.getChangeStoreDesc());
                            }else if(orderBO.getStatus() <= Status.Order.OUT_DONE){
                                orderBO.setOrderStatus(1);  //"待收货";
                            }else if(orderBO.getStatus() == Status.Order.CONFIRM){
                                orderBO.setOrderStatus(2);   //待服务
                            }else if(orderBO.getStatus() >= Status.Order.COMMENT_DONE){
                                orderBO.setOrderStatus(3);  //"已完成";
                            }
                        }
                    }
                    return ResponseUtil.success(orderBO);
                }else{
                    return ResponseUtil.exception("查询代收订单详情时返回空");
                }
            }else{
                return ResponseUtil.exception("查询代收订单详情时返回空");
            }
        } catch (Exception ex) {
            logger.error("查询代收订单列表时异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }

    @Override
    public Response<OrderBO> queryOrderDetailBySmscodeAndStoreid(String smsCode,Long storeId){
        if(smsCode==null || smsCode.equals("") || storeId==null){
            return ResponseUtil.error("参数不能为空!");
        }
        try {
            OrderBO orderBO = new OrderBO();
            Result<OrderRo> orderRoResult = orderFacade.queryOrderDetailBySmscodeAndStoreid(smsCode, storeId);
            if(orderRoResult!=null && orderRoResult.isSuccess() && orderRoResult.getData()!=null) {
                OrderRo orderRo = orderRoResult.getData();
                BeanCopierUtils.copy(orderRo, orderBO);
                if(orderRo.getGoodsOrderRoList()!=null && orderRo.getGoodsOrderRoList().size()>0){
                    List<GoodsOrderRo> goodsOrderRoList = orderRo.getGoodsOrderRoList();
                    List<GoodsOrderBO> goodsOrderBOList = new ArrayList<GoodsOrderBO>();
                    for(GoodsOrderRo goodsOrderRo:goodsOrderRoList){
                        GoodsOrderBO goodsOrderBO = new GoodsOrderBO();
                        BeanCopierUtils.copy(goodsOrderRo, goodsOrderBO);
                        goodsOrderBOList.add(goodsOrderBO);
                    }
                    orderBO.setGoodsOrderBOList(goodsOrderBOList);
                }
                if(orderRo.getServerOrderRoList()!=null && orderRo.getServerOrderRoList().size()>0){
                    List<ServerOrderRo> serverOrderRoList = orderRo.getServerOrderRoList();
                    List<ServerOrderBO> serverOrderBOList = new ArrayList<ServerOrderBO>();
                    for(ServerOrderRo serverOrderRo : serverOrderRoList){
                        ServerOrderBO serverOrderBO = new ServerOrderBO();
                        BeanCopierUtils.copy(serverOrderRo, serverOrderBO);
                        serverOrderBOList.add(serverOrderBO);
                    }
                    orderBO.setServerOrderBOList(serverOrderBOList);
                }
                return ResponseUtil.success(orderBO);
            }else if(orderRoResult!=null && !orderRoResult.isSuccess()){
                logger.error("根据验证码查询服务接口查询失败", orderRoResult.getStatusText());
                return ResponseUtil.error(orderRoResult.getStatusText());
            }else{
                logger.error("根据验证码查询服务接口返回为空");
                return ResponseUtil.error("接口查询无数据");
            }
        } catch (Exception ex) {
            logger.error("根据验证码查询服务接口异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }

    @Override
    public Result<OrderBO> queryByOrderId(long orderId){
        if(orderId == 0){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "参数不能为空");
        }
        try {
            logger.info("调用ordercenter的OrderFacade.queryById接口，根据验证码和门店ID进行核销，orderId={}",orderId);
            Result<OrderInfoRo> orderInfoRoResult =  orderFacade.queryById(orderId);
            if(orderInfoRoResult!=null && orderInfoRoResult.isSuccess() && orderInfoRoResult.getData()!=null){
                OrderBO orderBo = BaseConvert.beanConvert(orderInfoRoResult.getData(),OrderBO.class);
                return Results.newSuccessResult(orderBo,orderInfoRoResult.getStatusText());
            }
            return Results.newFailedResult(CommonStateCode.DATA_EMPTY,orderInfoRoResult !=null ? orderInfoRoResult.getStatusText() :"查询失败");
        } catch (Exception ex) {
            logger.error("调用ordercenter的OrderFacade.queryById接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }


}
