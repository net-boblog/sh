/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.marketcenter.bo.*;
import com.qccr.sh.page.Pagination;

/**
 * 商户自建活动服务接口
 * @author dongxuechai
 * @date 2016年3月01日 上午10:12
 */
public interface MerchantBuildPromotionExt {
    /**
     * 分页查询商家自建的活动列表
     * @param merchantBuildPromotionQuery 商家自建活动查询对象
     * @return
     */
    public Result<Pagination<MerchantBuildPromotionBO>> pageQueryMerchantPromotion(MerchantBuildPromotionQuery merchantBuildPromotionQuery);

    /**
     * 商家自建活动下线
     * @param promotionId 活动id
     * @param storeId     门店id
     * @return
     */
    public Result<Boolean> offlineMerchantPromotion(Long promotionId,Integer storeId,String userName);

    /**
     * 查询商家自建活动详情
     * @param promotionId 活动id
     * @return
     */
    public Result<MerchantPromotionDetailBO> queryMerchantPromotionDetail(Long promotionId);

    /**
     * 商家自建活动创建
     * @param merchantPromotionAddBO 商家自建活动新增对象
     * @return
     */
    public Result<Long> createMerchantPromotion(MerchantPromotionAddBO merchantPromotionAddBO);

    /**
     * 商家自建活动修改
     * @param merchantPromotionUpdateBO 商家自建活动修改对象
     * @param storeId 门店ID
     * @return
     */
    public Result<Long> updateMerchantPromotion(MerchantPromotionUpdateBO merchantPromotionUpdateBO,Integer storeId);

    /**
     * 活动消耗历史记录
     * <pre>
     * 查询活动近几天的消耗情况，包括近几天内的售出总数、剩余单数、每日售出数、每日收益
     * </pre>
     * @param promotionId 活动id
     * @param dayRange 日期范围，近几天。如：输入7表示近7天
     * @return
     */
    public Result<PromotionConsumeHistoryBO> queryPromotionConsumeHistory(Long promotionId,Integer dayRange,Integer productId);
}
