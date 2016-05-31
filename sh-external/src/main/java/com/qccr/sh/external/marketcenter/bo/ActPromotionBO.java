/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户报名活动BO
 * @author zhangzhonghua
 * @date 2016年3月01日 上午10:12
 */
public class ActPromotionBO implements Serializable {

    private static final long serialVersionUID = 7622930569275382470L;

    /** 活动id */
    private Long              promotionId;
    /** 活动名称 */
    private String            name;
    /** 活动开始时间 */
    private Date              startTime;
    /** 活动结束时间 */
    private Date              endTime;
    /** 报名活动状态（1.审核中；2.等待开始；3.已开始；4.已结束；5.审批失败），参考EnrollPromotionStatus常量 */
    private Integer           enrollPromotionStatus;
    /** 审批备注。当enrollPromotionStatus=5时有值 */
    private String            approveRemark;
    /** 商家补贴（分为单位） */
    private Integer           merchantAllowance;
    /** 销售服务数量 */
    private Integer           saleNumber;
    /** 促销价（分为单位） */
    private Integer           promotionAmt;
    /** 促销结算价（分为单位） */
    private Integer           promotionClearAmt;
    /** 一级服务分类id */
    private String            firstCategoryId;
    /** 一级服务分类名称 */
    private String            firstCategoryName;
    /** 二级服务分类id */
    private String            secondCategoryId;
    /** 二级服务分类名称 */
    private String            secondCategoryName;
    /*状态名称*/
    private String           statusName;
    /** 报名开始时间 */
    private Date              startRegistrationTime;
    /***
     * 报名结束时间
     */
    private Date              endRegistrationTime;

    public Date getEndRegistrationTime() {
        return endRegistrationTime;
    }

    public void setEndRegistrationTime(Date endRegistrationTime) {
        this.endRegistrationTime = endRegistrationTime;
    }

    public Date getStartRegistrationTime() {
        return startRegistrationTime;
    }

    public void setStartRegistrationTime(Date startRegistrationTime) {
        this.startRegistrationTime = startRegistrationTime;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /** 是否可编辑 */
    private boolean           editable = false;
    /** 是否可下线*/
    private boolean           allowOffline = false;

    public boolean isAllowOffline() {
        return allowOffline;
    }

    public void setAllowOffline(boolean allowOffline) {
        this.allowOffline = allowOffline;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getEnrollPromotionStatus() {
        return enrollPromotionStatus;
    }

    public void setEnrollPromotionStatus(Integer enrollPromotionStatus) {
        this.enrollPromotionStatus = enrollPromotionStatus;
    }

    public String getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(String firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public Integer getMerchantAllowance() {
        return merchantAllowance;
    }

    public void setMerchantAllowance(Integer merchantAllowance) {
        this.merchantAllowance = merchantAllowance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPromotionAmt() {
        return promotionAmt;
    }

    public void setPromotionAmt(Integer promotionAmt) {
        this.promotionAmt = promotionAmt;
    }

    public Integer getPromotionClearAmt() {
        return promotionClearAmt;
    }

    public void setPromotionClearAmt(Integer promotionClearAmt) {
        this.promotionClearAmt = promotionClearAmt;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getSecondCategoryId() {
        return secondCategoryId;
    }

    public void setSecondCategoryId(String secondCategoryId) {
        this.secondCategoryId = secondCategoryId;
    }

    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
