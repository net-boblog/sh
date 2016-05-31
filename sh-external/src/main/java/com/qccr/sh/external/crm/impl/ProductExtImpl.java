package com.qccr.sh.external.crm.impl;

import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import com.qccr.sh.enums.Constants;
import com.qccr.sh.external.crm.ProductExt;
import com.qccr.sh.external.crm.bo.ItemAttrBo;
import com.qccr.sh.external.crm.bo.ItemAttrValueBo;
import com.qccr.sh.external.crm.bo.ProductBO;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.qccr.sh.util.BeanCopierUtils;
import com.toowell.crm.facade.product.entity.ItemAttrRo;
import com.toowell.crm.facade.product.entity.ItemAttrValueRo;
import com.toowell.crm.facade.product.entity.ProductRo;
import com.toowell.crm.facade.product.service.ProductFacade;
import com.toowell.crm.facade.store.entity.StoreRo;
import com.toowell.crm.facade.store.service.StoreFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dongxc on 2015/12/2.
 */
@Service("productExt")
public class ProductExtImpl implements ProductExt{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductFacade productFacade;

    @Autowired
    private StoreFacade storeFacade;

    @Autowired
    private UserMerchantExt userMerchantExt;


    @Override
    public Response<List<ProductBO>> getProductsByStoreId(int storeId) {
        try {
            if(storeId==0){
                return ResponseUtil.error("门店ID不能为空");
            }
            //根据门店ID查询门店名称
            String storeName = "";
            //com.toowell.crm.facade.base.Result<StoreRo> storeRoResult = storeFacade.getStoreByStoreId(storeId+"", RemoteProjectEnum.SH);
            com.qccr.knife.result.Result<StoreRo> storeRoResult = storeFacade.getStoreById(storeId);
            if(storeRoResult!=null && storeRoResult.isSuccess() && storeRoResult.getData()!=null){
                storeName = storeRoResult.getData().getStoreName();
            }

            Result<List<ProductRo>> productResult = productFacade.getProductsByStoreId(storeId);
            if(productResult!=null && productResult.isSuccess() && productResult.getData()!=null) {
                List<ProductRo> productRoList = productResult.getData();
                List<ProductBO> productBOList = new ArrayList<ProductBO>();
                for(ProductRo productRo:productRoList){
                    ProductBO productBO = new ProductBO();
                    BeanCopierUtils.copy(productRo, productBO);
                    productBO.setStoreName(storeName);
                    if(productRo.getClearAmt()!=null){
                        productBO.setClearAmt(new Double(productRo.getClearAmt()).doubleValue()/100);
                    }
                    if(Constants.CATEGORY_CODE.getCode().equals(productBO.getCategoryCode())){
                        if(!"2".equals(productRo.getProductStatus()) && !"3".equals(productRo.getProductStatus()) && !"6".equals(productRo.getProductStatus())){
                            productBO.setEditable(true);
                        }

                        if(!"4".equals(productBO.getProductStatus())){
                            productBO.setDelete(true);
                        }

                        productBO.setMyProduct(true);
                    }
                    if("0".equals(productRo.getIsOnline()) && "4".equals(productRo.getProductStatus())) {
                        productBO.setProductStatus(productRo.getIsOnline());//下线
                    }
                    productBOList.add(productBO);
                }
                return ResponseUtil.success(productBOList);
            }else if(productResult!=null){
                logger.error("查询门店提供的服务列表失败了", productResult.getStatusText());
                return ResponseUtil.error(productResult.getStatusText());
            }else{
                return ResponseUtil.error("接口查询结果为空");
            }
        } catch (Exception ex) {
            logger.error("查询门店提供的服务列表异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }

    @Override
    public Result<Double> getProductContractPrice(String userName,String categoryId){
        if(userName==null || userName.equals("") || categoryId==null || categoryId.equals("")){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "参数均不能为空");
        }
        try{
            Result<UserMerchantBO> userMerchantBOResult = userMerchantExt.queryByUsername(userName);
            if(userMerchantBOResult!=null && userMerchantBOResult.isSuccess() && userMerchantBOResult.getData()!=null){
                int storeId = userMerchantBOResult.getData().getStoreId();
                Result<List<ProductRo>> listResult = productFacade.getProductsByStoreIdAndSecondCategory(storeId, new Integer(categoryId));
                if(listResult!=null && listResult.isSuccess() && listResult.getData()!=null && !listResult.getData().isEmpty()){
                    List<ProductRo> productRoList = listResult.getData();
                    Double contractPrice = new Double(productRoList.get(0).getClearAmt());
                    if(contractPrice.doubleValue()>0){  //返回的单位是分，转换成元
                        contractPrice = new Double(contractPrice.doubleValue()/100);
                    }
                    return Results.newSuccessResult(contractPrice,listResult.getStatusText());
                }
            }
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.queryPromotionConsumeHistory接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
        return Results.newFailedResult(CommonStateCode.FAILED,"未查到改服务的结算价");
    }

    public  Result<String> addProduct(ProductBO productBO){
        if (productBO==null){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "参数均不能为空");
        }
        try {
            ProductRo productRo=new ProductRo();
            BeanCopierUtils.copy(productBO,productRo);
            Double marketAmt=productBO.getMarketAmt()*100;
            productRo.setMarketAmt(marketAmt.intValue());
            List<ItemAttrBo> itemAttrBoList= productBO.getItemAttrList();
            ItemAttrBo itemAttrBo1=itemAttrBoList.get(0);
            ItemAttrValueBo itemAttrValueBo1=itemAttrBo1.getItemAttrValues().get(0);
            ItemAttrBo itemAttrBo2=itemAttrBoList.get(1);
            ItemAttrValueBo itemAttrValueBo2=itemAttrBo2.getItemAttrValues().get(0);

            ItemAttrValueRo itemAttrValueRo1=new ItemAttrValueRo();
            ItemAttrValueRo itemAttrValueRo2=new ItemAttrValueRo();
            BeanCopierUtils.copy(itemAttrValueBo1,itemAttrValueRo1);
            BeanCopierUtils.copy(itemAttrValueBo2,itemAttrValueRo2);

            List<ItemAttrValueRo> itemAttrValueRoList1=new LinkedList<ItemAttrValueRo>();
            List<ItemAttrValueRo> itemAttrValueRoList2=new LinkedList<ItemAttrValueRo>();
            itemAttrValueRoList1.add(itemAttrValueRo1);
            itemAttrValueRoList2.add(itemAttrValueRo2);

            List<ItemAttrRo> itemAttrRoList=new LinkedList<ItemAttrRo>();
            ItemAttrRo itemAttrRo1=new ItemAttrRo();
            ItemAttrRo itemAttrRo2=new ItemAttrRo();
            itemAttrRo1.setItemAttrValues(itemAttrValueRoList1);
            itemAttrRo2.setItemAttrValues(itemAttrValueRoList2);
            itemAttrRoList.add(itemAttrRo1);
            itemAttrRoList.add(itemAttrRo2);
            productRo.setItemAttrList(itemAttrRoList);
            return  productFacade.addProduct(productRo);
        }catch (Exception ex){
            logger.error("调用crm的productFacade.addProduct接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"服务添加失败");
        }
    }

    public Result<Boolean> deleteByProductId(String productId,String storeId){
        if(productId==null || productId.equals("") || storeId==null || storeId.equals("")){
            return  Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER,"参数不正确");
        }
        try {
            return productFacade.deleteByProductId(productId,storeId);
        }catch (Exception ex){
            logger.error("调用crm的productFacade.deleteByProductId接口异常,ex={}",ex);
            return  Results.newFailedResult(CommonStateCode.FAILED,"服务删除失败");
        }
    }

    //必填项productId,productName,discount,storeId,categoryCode 二级服务code,updateUser
    public Result<Integer> modifyProduct(ProductBO productBO){
        if(productBO==null || productBO.getProductId()==null ){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "参数均不能为空");
        }
        try {
            Result<ProductRo> productRoResult= productFacade.getProductById(Integer.parseInt(productBO.getProductId()));
            if(productRoResult==null || productRoResult.getData()==null || !productRoResult.isSuccess()){
                return Results.newFailedResult(CommonStateCode.FAILED,"修改服务失败");
            }
            ProductRo productRo=productRoResult.getData();
            Double marketAmt=productBO.getMarketAmt()*100;
            productRo.setMarketAmt( marketAmt.intValue());
            productRo.setUpdateUser(productBO.getUpdateUser());
            productRo.setDiscount(productBO.getDiscount());
            return productFacade.modifyProduct(productRo);
        }catch (Exception ex){
            logger.error("调用crm的productFacade.modifyProduct接口异常,ex={}",ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"修改服务失败");
        }
    }
}
