package com.qccr.sh.biz;

import com.qccr.sh.dao.StoreDao;
import com.qccr.sh.external.memberCenter.UserExt;
import com.qccr.sh.external.memberCenter.bo.UserBO;
import com.qccr.sh.response.Response;
import com.toowell.crm.facade.base.BatchResult;
import com.toowell.crm.facade.base.RemoteProjectEnum;
import com.toowell.crm.facade.product.entity.ProductCategoryRo;
import com.toowell.crm.facade.store.entity.CategoryRo;
import com.toowell.crm.facade.store.service.StoreFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongxc on 2015/10/29.
 * 商户服务实现类
 */
@Service
public class ShopServiceBizImpl implements ShopServiceBiz{
    private static final Logger log = LoggerFactory.getLogger(ShopServiceBizImpl.class);
    @Autowired
    private StoreDao storeDao;
    @Resource(name = "storeFacade")
    private StoreFacade storeFacade;
    @Resource(name = "userExt")
    private UserExt userExt;

    /**
     *  查询门店的服务
     * @param storeId  门店ID
     * @param categoryId  一级服务类目ID，传空时表示查询该门店提供的一级服务
     * @return
     */
    @Override
    public List<CategoryRo> queryStoreService(Integer storeId,String categoryId){
        List<CategoryRo> list = new ArrayList<CategoryRo>();
        if(storeId!=null){
            try{
                BatchResult<ProductCategoryRo> batchResult = findServicesByStoreId(storeId);
                if(batchResult!=null && batchResult.isSuccess()){
                    List<ProductCategoryRo> productCategoryRos = batchResult.getData();
                    if(productCategoryRos!=null && productCategoryRos.size()>0){
                        if(categoryId==null || categoryId.equals("")){
                            CategoryRo categoryRo = new CategoryRo();
                            for(ProductCategoryRo ro:productCategoryRos){
                                categoryRo = new CategoryRo();
                                categoryRo = ro.getFirstProductCategory();
                                list.add(categoryRo);
                            }
                        }else{
                            for(ProductCategoryRo ro : productCategoryRos){
                                if(ro!=null && ro.getFirstProductCategory()!=null && ro.getFirstProductCategory().getCategoryId()!=null
                                         && ro.getFirstProductCategory().getCategoryId().equals(categoryId)){
                                    list = ro.getSecondProductCategory();
                                    break;
                                }
                            }
                        }
                    }
                }
            }catch (Exception ex){
                log.error("根据storeId查询该门店提供的服务类目时异常",ex);
            }
        }
        return  list;
    }


    // 根据storeId查询该门店提供的服务类目
    @Cacheable(value = "shopServiceCache", key="#storeId")
    @Override
    public BatchResult<ProductCategoryRo> findServicesByStoreId(Integer storeId){
        if(storeId == null){
            return null;
        }
        BatchResult<ProductCategoryRo> batchResult = new BatchResult<ProductCategoryRo>();
        batchResult = storeFacade.getStoreProductCategoryOrAllCategory(storeId.toString(), RemoteProjectEnum.SH);
        return batchResult;
    }

    /**
     * 查询商户的服务评价列表
     * @param map 查询条件 商店ID、服务项目、评价类型、下单开始时间、下单结束时间
     * @return
     */
    @Override
    public List<Map<String, Object>> queryCommentList(Map<String, Object> map){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> tmpList = storeDao.selectCommentList(map);
        if(tmpList!=null && tmpList.size()>0){
            int len = tmpList.size();
            Map<String,Object> obj = new HashMap<String,Object>();
            for(int i = 0;i<len; i++){
                obj = new HashMap<String,Object>();
                obj = tmpList.get(i);
                Integer userId = (Integer)obj.get("userId");
                if(userId!=null){
                    Response<UserBO> boResponse = userExt.queryByUserId(userId);
                    if(boResponse.isSuccess()&&boResponse.getData()!=null){
                        UserBO bo = boResponse.getData();
                        String userName = bo.getUsername();
                        if(userName!=null && !userName.equals("") && userName.length()>=7){
                            userName = userName.substring(0,3)+"****"+userName.substring(userName.length()-4);
                            obj.put("username", userName);
                        }
                    }
                }
                result.add(obj);
            }
        }
        return result;
    }

    /**
     * 查询商户的服务评价列表
     * @param map 查询条件 商店ID、服务项目、评价类型、下单开始时间、下单结束时间
     * @return
     */
    @Override
    public int countCommentList(Map<String, Object> map){
        int count = storeDao.countCommentList(map);
        return count;
    }
}
