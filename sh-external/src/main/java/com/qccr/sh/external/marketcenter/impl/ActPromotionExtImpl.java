/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.impl;

import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import com.qccr.marketcenter.facade.ro.PagedDataRO;
import com.qccr.marketcenter.facade.ro.promotion.MerchantEnrollPromotionQueryRO;
import com.qccr.marketcenter.facade.ro.promotion.MerchantEnrollPromotionRO;
import com.qccr.marketcenter.facade.service.promotion.ServicePromotionFacade;
import com.qccr.sh.external.marketcenter.ActPromotionExt;
import com.qccr.sh.external.marketcenter.bo.*;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.util.BaseConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 超人活动服务接口
 * @author zhangzhonghua
 * @date 2016年3月01日 上午10:12
 */
@Service("actPromotionExt")
public class ActPromotionExtImpl implements ActPromotionExt {
    @Resource(name="servicePromotionFacade")
    private ServicePromotionFacade servicePromotionFacade;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 分页查询商户报名的活动列表
     * @param actPromotionQuery 商户报名活动查询对象
     * @return
     */
    public Result<Pagination<ActPromotionBO>> pageQueryEnrollPlatformPromotion(ActPromotionQuery actPromotionQuery){

        try{
            MerchantEnrollPromotionQueryRO merchantEnrollPromotionQueryRO =
                    BaseConvert.beanConvert(actPromotionQuery,MerchantEnrollPromotionQueryRO.class);

            merchantEnrollPromotionQueryRO.setPageNo(actPromotionQuery.getPageNo());
            merchantEnrollPromotionQueryRO.setPageSize(actPromotionQuery.getPageSize());

            //我报名的超人活动列表查询
            Result<PagedDataRO<MerchantEnrollPromotionRO>> result =
                    servicePromotionFacade.pageQueryEnrollPlatformPromotion(merchantEnrollPromotionQueryRO);

            if(result!=null && result.isSuccess()){
                PagedDataRO<MerchantEnrollPromotionRO> pagedDataRO = result.getData();
                Pagination<ActPromotionBO> pagination = new Pagination<ActPromotionBO>
                        (pagedDataRO.getPageSize(),pagedDataRO.getPageNo(), pagedDataRO.getTotalSize());

                List<ActPromotionBO> promotionBOList = BaseConvert.listBeanConvert(pagedDataRO.getResultList(),ActPromotionBO.class);

                Date now = new Date(); //当期时间
                int curPromotionStatus;
                for(ActPromotionBO promotionBO:promotionBOList){
                    curPromotionStatus = promotionBO.getEnrollPromotionStatus().intValue();

                    //转换活动状态的名称
                    promotionBO.setStatusName(ActivityResultStatusEnum.getNameByValue(curPromotionStatus));

                    //报名截止之前都能编辑
                    if(promotionBO.getEndRegistrationTime().getTime()>now.getTime()){
                        promotionBO.setEditable(true);
                    }else{
                        promotionBO.setEditable(false);
                    }

                    //活动开始之前都能下线
                    if(promotionBO.getStartTime().getTime()>now.getTime()
                            && curPromotionStatus!= ActivityResultStatusEnum.APPROVE_FAILED.getValue()
                            && curPromotionStatus!= ActivityResultStatusEnum.QUIT_PROMOTION.getValue()){
                        promotionBO.setAllowOffline(true);
                    }else{
                        promotionBO.setAllowOffline(false);
                    }
                }
                pagination.setDataList(promotionBOList);
                return Results.newSuccessResult(pagination,result.getStatusText());
            }else if(result!=null){
                logger.error("查询商家报名的活动列表时返回失败",result.getStatusText());
                return Results.newFailedResult(result.getStateCode(), result.getStatusText());
            }else{
                return Results.newFailedResult(CommonStateCode.DATA_EMPTY, "接口返回为空");
            }
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.pageQueryEnrollPlatformPromotion接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }
}
