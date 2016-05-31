/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家自建活动修改BO
 * @author dongxuechai
 * @date 2016年3月01日 下午3:39
 */
public class MerchantPromotionUpdateBO implements Serializable {
    private static final long serialVersionUID = -4219119224486832156L;
    /** 门店id */
    private Integer           storeId;
    /** 活动id */
    private Long              promotionId;
    /** 活动名称 */
    private String            name;
    /** 活动开始时间 */
    private Date              startTime;
    /** 活动结束时间 */
    private Date              endTime;
    /** 一级服务分类id */
    private String            firstCategoryId;
    /** 一级服务分类名称 */
    private String            firstCategoryName;
    /** 二级服务分类id */
    private String            secondCategoryId;
    /** 二级服务分类名称 */
    private String            secondCategoryName;
    /** 销售服务数量 */
    private Integer           saleNumber;
    /** 促销频次，n:m（N天M次） */
    private String            cycle;
    /** 促销频次，N天 */
    private Integer           cycleDays;
    /** 促销频次，M次 */
    private Integer           cycleTimes;
    /** 商家补贴（分为单位） */
    private Integer           merchantAllowance;
    /** 修改人 */
    private String            updatePerson;
    /** 门店名称 */
    private String            storeName;
    /** 门店所属省份 */
    private Integer           provinceId;
    /** 门店所属城市 */
    private Integer           cityId;
    /** 促销价格（分为单位） */
    private Integer           promotionAmt;
    /** 促销结算价（分为单位） */
    private Integer           promotionClearAmt;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getCycleDays() {
        return cycleDays;
    }

    public void setCycleDays(Integer cycleDays) {
        this.cycleDays = cycleDays;
    }

    public Integer getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(Integer cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    public Integer getMerchantAllowance() {
        return merchantAllowance;
    }

    public void setMerchantAllowance(Integer merchantAllowance) {
        this.merchantAllowance = merchantAllowance;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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
}
