package com.qccr.sh.biz;

import com.toowell.crm.facade.base.BatchResult;
import com.toowell.crm.facade.product.entity.ProductCategoryRo;
import com.toowell.crm.facade.store.entity.CategoryRo;

import java.util.List;
import java.util.Map;

/**
 * Created by dongxc on 2015/10/29.
 * 商户服务
 */
public interface ShopServiceBiz {

    /**
     *  查询门店的服务
     * @param storeId  门店ID
     * @param categoryId  一级服务类目ID，传空时表示查询该门店提供的一级服务
     * @return
     */
    public List<CategoryRo> queryStoreService(Integer storeId,String categoryId);

    /**
     * 根据storeId查询该门店提供的服务类目
     * @param storeId  门店ID
     * @return
     */
    public BatchResult<ProductCategoryRo> findServicesByStoreId(Integer storeId);

    /**
     * 查询商户的服务评价列表
     * @param map 查询条件 商店ID、服务项目、评价类型、下单开始时间、下单结束时间
     * @return
     */
    public List<Map<String, Object>> queryCommentList(Map<String, Object> map);

    /**
     * 查询商户的服务评价列表
     * @param map 查询条件 商店ID、服务项目、评价类型、下单开始时间、下单结束时间
     * @return
     */
    public int countCommentList(Map<String, Object> map);
}
