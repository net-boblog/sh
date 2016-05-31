/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.impl;

import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import com.qccr.marketcenter.facade.ro.PagedDataRO;
import com.qccr.marketcenter.facade.ro.promotion.*;
import com.qccr.marketcenter.facade.service.promotion.ServicePromotionFacade;
import com.qccr.sh.external.marketcenter.MerchantBuildPromotionExt;
import com.qccr.sh.external.marketcenter.bo.*;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.util.BaseConvert;
import com.qccr.sh.util.BeanCopierUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 商户自建活动服务实现类
 * @author dongxuechai
 * @date 2016年3月01日 上午10:12
 */
@Service("merchantBuildPromotionExt")
public class MerchantBuildPromotionExtImpl implements MerchantBuildPromotionExt{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServicePromotionFacade servicePromotionFacade;
    /**
     * 分页查询商家自建的活动列表
     * @param merchantBuildPromotionQuery 商家自建活动查询对象
     * @return
     */
    @Override
    public Result<Pagination<MerchantBuildPromotionBO>> pageQueryMerchantPromotion(MerchantBuildPromotionQuery merchantBuildPromotionQuery){
        //门店ID不能为空
        if(merchantBuildPromotionQuery==null||merchantBuildPromotionQuery.getStoreId()==null
                ||merchantBuildPromotionQuery.getStoreId().intValue()==0){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "登录商户不能为空");
        }
        try{
            MerchantBuildPromotionQueryRO promotionQueryRO = BaseConvert.beanConvert(merchantBuildPromotionQuery,MerchantBuildPromotionQueryRO.class);
            promotionQueryRO.setSponsor(2);  //商户
            Result<PagedDataRO<MerchantBuildPromotionRO>> pagedDataROResult =
                    servicePromotionFacade.pageQueryMerchantPromotion(promotionQueryRO);
            if(pagedDataROResult!=null && pagedDataROResult.isSuccess()){
                PagedDataRO<MerchantBuildPromotionRO> pagedDataRO = pagedDataROResult.getData();
                int totalSize = 0;
                if(pagedDataRO!=null){
                    totalSize = pagedDataRO.getTotalSize();
                }
                Pagination<MerchantBuildPromotionBO> pagination = new Pagination<MerchantBuildPromotionBO>
                        (merchantBuildPromotionQuery.getPageSize(),merchantBuildPromotionQuery.getPageNo(), totalSize);
                List<MerchantBuildPromotionBO> promotionBOList = new ArrayList<MerchantBuildPromotionBO>();
                if(pagedDataRO!=null){
                    promotionBOList = BaseConvert.listBeanConvert(pagedDataRO.getResultList(),MerchantBuildPromotionBO.class);
                }
                Date now = new Date(); //当期时间
                int curPromotionStatus;
                for(MerchantBuildPromotionBO promotionBO:promotionBOList){
                    curPromotionStatus = promotionBO.getEnrollPromotionStatus().intValue();
                    //转换活动状态的名称
                    promotionBO.setPromotionStatusName(StoreActivityStatusEnum.getNameByValue(curPromotionStatus));
                    //判断是否可编辑
                    if(promotionBO.getStartTime().getTime()>now.getTime() &&
                            curPromotionStatus==StoreActivityStatusEnum.WAIT_START.getValue()){
                         promotionBO.setEditable(true);
                    }
                    //判断是否可下线
                    if(curPromotionStatus==StoreActivityStatusEnum.WAIT_START.getValue() ||
                            curPromotionStatus==StoreActivityStatusEnum.STARTED.getValue()){
                        promotionBO.setAllowOffline(true);
                    }
                }
                pagination.setDataList(promotionBOList);
                return Results.newSuccessResult(pagination,pagedDataROResult.getStatusText());
            }else if(pagedDataROResult!=null){
                logger.error("查询商家自建的活动列表时返回失败",pagedDataROResult.getStatusText());
                return Results.newFailedResult(pagedDataROResult.getStateCode(), pagedDataROResult.getStatusText());
            }else{
                return Results.newFailedResult(CommonStateCode.DATA_EMPTY, "接口返回为空");
            }
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.pageQueryMerchantPromotion接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"查询我发布的活动列表时异常了");
        }
    }

    /**
     * 商家自建活动下线
     * @param promotionId 活动id
     * @param storeId     门店id
     * @return
     */
    @Override
    public Result<Boolean> offlineMerchantPromotion(Long promotionId,Integer storeId,String userName){
        if(promotionId==null || promotionId.longValue()==0l || storeId==null || storeId.intValue()==0){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "参数均不能为空");
        }
        //验证是否可下线
        Result<MerchantPromotionDetailBO> boResult = this.queryMerchantPromotionDetail(promotionId);
        if(boResult!=null && boResult.isSuccess() && boResult.getData()!=null){
            if(!boResult.getData().isAllowOffline()){
                return Results.newFailedResult(CommonStateCode.ERROR_REQUEST,"该活动的状态不允许下线");
            }else if(boResult.getData().getStoreId().intValue()!=storeId.intValue()){
                return Results.newFailedResult(CommonStateCode.ERROR_REQUEST,"该活动的状态不允许被当前用户下线");
            }
        }else{
            return Results.newFailedResult(CommonStateCode.ERROR_REQUEST,"该活动不允许下线");
        }
        try{
            Result<Boolean> result = servicePromotionFacade.offlineMerchantPromotion(promotionId,userName);
            return result;
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.offlineMerchantPromotion接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"下线自建活动时异常了");
        }
    }

    /**
     * 查询商家自建活动详情
     * @param promotionId 活动id
     * @return
     */
    @Override
    public Result<MerchantPromotionDetailBO> queryMerchantPromotionDetail(Long promotionId){
        if(promotionId==null || promotionId.longValue()==0l){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动ID不能为空");
        }
        try{
            Result<ServicePromotionRO> servicePromotionROResult = servicePromotionFacade.queryMerchantPromotionDetail(promotionId);
            if(servicePromotionROResult!=null && servicePromotionROResult.isSuccess()){
                ServicePromotionRO servicePromotionRO = servicePromotionROResult.getData();
                if(servicePromotionRO!=null){
                    MerchantPromotionDetailBO promotionDetailBO = BaseConvert.beanConvert(servicePromotionRO,MerchantPromotionDetailBO.class);
                    if(servicePromotionRO.getSponsor()!=null && servicePromotionRO.getSponsor().intValue()==2){  //商家活动
                        BeanCopierUtils.copy(servicePromotionRO.getServicePromotionRelaRO(),promotionDetailBO);
                    }
                    Date now = new Date();
                    int curPromotionStatus = promotionDetailBO.getEnrollPromotionStatus();
                    //判断是否可编辑
                    if(promotionDetailBO.getStartTime().getTime()>now.getTime() &&
                            curPromotionStatus==StoreActivityStatusEnum.WAIT_START.getValue()){
                        promotionDetailBO.setEditable(true);
                    }
                    //判断是否可下线
                    if(curPromotionStatus==StoreActivityStatusEnum.WAIT_START.getValue() ||
                            curPromotionStatus==StoreActivityStatusEnum.STARTED.getValue()){
                        promotionDetailBO.setAllowOffline(true);
                    }
                    //促销频次处理
                    String[] cycleArr = promotionDetailBO.getCycle().split(":");
                    if(cycleArr.length==2){
                        promotionDetailBO.setCycleDayes(new Integer(cycleArr[0]));
                        promotionDetailBO.setCycleTimes(new Integer(cycleArr[1]));
                    }
                    promotionDetailBO.setPromotionStatusName(StoreActivityStatusEnum.getNameByValue(curPromotionStatus));
                    return Results.newSuccessResult(promotionDetailBO,servicePromotionROResult.getStatusText());
                }else{
                    return Results.newFailedResult(CommonStateCode.DATA_EMPTY, "接口返回活动详情为空");
                }
            }else if(servicePromotionROResult!=null){
                logger.error("查询商家自建活动详情时返回失败",servicePromotionROResult.getStatusText());
                return Results.newFailedResult(servicePromotionROResult.getStateCode(), servicePromotionROResult.getStatusText());
            }else{
                return Results.newFailedResult(CommonStateCode.DATA_EMPTY, "接口返回为空");
            }
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.queryMerchantPromotionDetail接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"查询自建活动详情时异常了");
        }
    }

    /**
     * 商家自建活动创建
     * @param merchantPromotionAddBO 商家自建活动新增对象
     * @return
     */
    @Override
    public Result<Long> createMerchantPromotion(MerchantPromotionAddBO merchantPromotionAddBO){
        //活动各参数校验
        if(merchantPromotionAddBO==null||
                merchantPromotionAddBO.getName()==null||merchantPromotionAddBO.getName().equals("")||
                merchantPromotionAddBO.getFirstCategoryId()==null||merchantPromotionAddBO.getFirstCategoryId().equals("")||
                merchantPromotionAddBO.getFirstCategoryName()==null||merchantPromotionAddBO.getFirstCategoryName().equals("")||
                merchantPromotionAddBO.getSecondCategoryId()==null||merchantPromotionAddBO.getSecondCategoryId().equals("")||
                merchantPromotionAddBO.getSecondCategoryName()==null||merchantPromotionAddBO.getSecondCategoryName().equals("")||
                merchantPromotionAddBO.getStartTime()==null||merchantPromotionAddBO.getEndTime()==null||
                merchantPromotionAddBO.getSaleNumber()==null||merchantPromotionAddBO.getMerchantAllowance()==null||
                merchantPromotionAddBO.getCycleDays()==null||merchantPromotionAddBO.getCycleTimes()==null||
                merchantPromotionAddBO.getStoreId()==null||merchantPromotionAddBO.getStoreId().intValue()<=0) {
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "各个参数均不能为空");
        }else if(merchantPromotionAddBO.getCycleDays().intValue()<=0||merchantPromotionAddBO.getCycleTimes().intValue()<=0) {
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "促销频次不正确");
        }else if(merchantPromotionAddBO.getSaleNumber().intValue()<=0||merchantPromotionAddBO.getMerchantAllowance().intValue()<=0){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "服务数量与商家补贴金额必须要大于0");
        }else if(merchantPromotionAddBO.getStartTime().getTime()>merchantPromotionAddBO.getEndTime().getTime()){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动结束时间必须要大于活动开始时间");
        }else{
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String todayStr = dateFormat.format(new Date());
                Date today =  dateFormat.parse(todayStr) ;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);
                calendar.add(Calendar.DATE, 1);
                if(merchantPromotionAddBO.getStartTime().getTime()<calendar.getTime().getTime()) {
                    return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动开始时间必须要从当前的第二天开始");
                }
            }catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动开始时间必须要从当前的第二天开始");
            }
        }
        //创建活动
        try{
            MerchantPromotionAddRO promotionAddRO = BaseConvert.beanConvert(merchantPromotionAddBO, MerchantPromotionAddRO.class);
            promotionAddRO.setCycle(merchantPromotionAddBO.getCycleDays() + ":" + merchantPromotionAddBO.getCycleTimes());
            Result<Long> result = servicePromotionFacade.createMerchantPromotion(promotionAddRO);
            return result;
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.createMerchantPromotion接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"创建自建活动时异常了");
        }
    }

    /**
     * 商家自建活动修改
     * @param merchantPromotionUpdateBO 商家自建活动修改对象
     * @param storeId 门店ID
     * @return
     */
    @Override
    public Result<Long> updateMerchantPromotion(MerchantPromotionUpdateBO merchantPromotionUpdateBO,Integer storeId){
        //活动各参数校验
        if(merchantPromotionUpdateBO==null||
                merchantPromotionUpdateBO.getName()==null||merchantPromotionUpdateBO.getName().equals("")||
                merchantPromotionUpdateBO.getFirstCategoryId()==null||merchantPromotionUpdateBO.getFirstCategoryId().equals("")||
                merchantPromotionUpdateBO.getFirstCategoryName()==null||merchantPromotionUpdateBO.getFirstCategoryName().equals("")||
                merchantPromotionUpdateBO.getSecondCategoryId()==null||merchantPromotionUpdateBO.getSecondCategoryId().equals("")||
                merchantPromotionUpdateBO.getSecondCategoryName()==null||merchantPromotionUpdateBO.getSecondCategoryName().equals("")||
                merchantPromotionUpdateBO.getStartTime()==null||merchantPromotionUpdateBO.getEndTime()==null||
                merchantPromotionUpdateBO.getSaleNumber()==null||merchantPromotionUpdateBO.getMerchantAllowance()==null||
                merchantPromotionUpdateBO.getCycleDays()==null||merchantPromotionUpdateBO.getCycleTimes()==null||
                merchantPromotionUpdateBO.getPromotionId()==null) {
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "各个参数均不能为空");
        }else if(merchantPromotionUpdateBO.getCycleDays().intValue()<=0||merchantPromotionUpdateBO.getCycleTimes().intValue()<=0) {
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "促销频次不正确");
        }else if(merchantPromotionUpdateBO.getSaleNumber().intValue()<=0||merchantPromotionUpdateBO.getMerchantAllowance().intValue()<=0){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "服务数量与商家补贴金额必须要大于0");
        }else if(merchantPromotionUpdateBO.getStartTime().getTime()>merchantPromotionUpdateBO.getEndTime().getTime()){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动结束时间必须要大于活动开始时间");
        }else{
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String todayStr = dateFormat.format(new Date());
                Date today =  dateFormat.parse(todayStr) ;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);
                calendar.add(Calendar.DATE, 1);
                if(merchantPromotionUpdateBO.getStartTime().getTime()<calendar.getTime().getTime()) {
                    return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动开始时间必须要从当前的第二天开始");
                }
            }catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动开始时间必须要从当前的第二天开始");
            }
        }
        //验证是否可编辑
        Result<MerchantPromotionDetailBO> boResult = this.queryMerchantPromotionDetail(merchantPromotionUpdateBO.getPromotionId());
        if(boResult!=null && boResult.isSuccess() && boResult.getData()!=null){
            if(!boResult.getData().isEditable()){
                return Results.newFailedResult(CommonStateCode.ERROR_REQUEST,"该活动的状态不允许编辑");
            }else if(boResult.getData().getStoreId().intValue()!=storeId.intValue()){
                return Results.newFailedResult(CommonStateCode.ERROR_REQUEST,"该活动的状态不允许被当前用户编辑");
            }
        }else{
            return Results.newFailedResult(CommonStateCode.ERROR_REQUEST,"该活动不允许编辑");
        }
        //修改活动
        try{
            MerchantPromotionUpdateRO promotionUpdateRO = BaseConvert.beanConvert(merchantPromotionUpdateBO, MerchantPromotionUpdateRO.class);
            promotionUpdateRO.setCycle(merchantPromotionUpdateBO.getCycleDays()+":"+merchantPromotionUpdateBO.getCycleTimes());
            Result<Long> result = servicePromotionFacade.updateMerchantPromotion(promotionUpdateRO);
            return result;
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.updateMerchantPromotion接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"修改自建活动时异常了");
        }
    }

    /**
     * 活动消耗历史记录
     * <pre>
     * 查询活动近几天的消耗情况，包括近几天内的售出总数、剩余单数、每日售出数、每日收益
     * </pre>
     * @param promotionId 活动id
     * @param dayRange 日期范围，近几天。如：输入7表示近7天
     * @return
     */
    @Override
    public Result<PromotionConsumeHistoryBO> queryPromotionConsumeHistory(Long promotionId,Integer dayRange,Integer productId){
        if(promotionId==null || promotionId.longValue()==0l){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "活动ID不能为空");
        }else if(dayRange==null || dayRange.intValue()<=0){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "日期范围必须要大于0");
        }
        try{
            Result<PromotionConsumeHistoryRO> result = servicePromotionFacade.queryPromotionConsumeHistory(promotionId,productId,dayRange);
            if(result!=null&&result.isSuccess()){
                PromotionConsumeHistoryRO historyRO = result.getData();
                if(historyRO!=null){
                    PromotionConsumeHistoryBO historyBO = BaseConvert.beanConvert(historyRO,PromotionConsumeHistoryBO.class);
                    List<DailyPromotionConsumeRO> consumeROList = historyRO.getDailyPromotionConsumeROs();
                    List<DailyPromotionConsumeBO> consumeBOList = BaseConvert.listBeanConvert(consumeROList,DailyPromotionConsumeBO.class);
                    historyBO.setDailyPromotionConsumeBOs(consumeBOList);
                    return Results.newSuccessResult(historyBO,result.getStatusText());
                }else{
                    return Results.newFailedResult(CommonStateCode.DATA_EMPTY, "接口返回活动消耗历史记录为空");
                }
            }else if(result!=null){
                logger.error("查询活动消耗历史记录时返回失败",result.getStatusText());
                return Results.newFailedResult(result.getStateCode(), result.getStatusText());
            }else{
                return Results.newFailedResult(CommonStateCode.DATA_EMPTY, "接口返回为空");
            }
        }catch (Exception ex){
            logger.error("调用marketcenter的ServicePromotionFacade.queryPromotionConsumeHistory接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,"活动消耗历史记录时异常了");
        }
    }
}
