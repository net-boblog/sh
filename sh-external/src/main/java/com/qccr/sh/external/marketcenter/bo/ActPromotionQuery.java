/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 超人活动查询类
 * @author zhangzhonghua
 * @date 2016年3月01日 上午10:12
 */
public class ActPromotionQuery implements Serializable {
    /** uid */
    private static final long serialVersionUID = 7955236863225915738L;
    /** 门店id */
    private Integer           storeId;
    /** 活动名称 */
    private String            name;
    /** 一级服务分类id */
    private String            firstCategoryId;
    /** 二级服务分类id */
    private String            secondCategoryId;
    /** 活动时间开始 */
    private Date              promotionTimeFrom;
    /** 活动时间结束 */
    private Date              promotionTimeTo;
    /** 报名活动状态（1.审核中；2.等待开始；3.已开始；4.已结束；5.审批失败），参考EnrollPromotionStatus常量 */
    private Integer           enrollPromotionStatus;

    /** 每页显示的记录数 */
    private Integer           pageSize;
    /** 当前页，从1开始 */
    private Integer           pageNo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getPromotionTimeFrom() {
        return promotionTimeFrom;
    }

    public void setPromotionTimeFrom(Date promotionTimeFrom) {
        this.promotionTimeFrom = promotionTimeFrom;
    }

    public Date getPromotionTimeTo() {
        return promotionTimeTo;
    }

    public void setPromotionTimeTo(Date promotionTimeTo) {
        this.promotionTimeTo = promotionTimeTo;
    }

    public String getSecondCategoryId() {
        return secondCategoryId;
    }

    public void setSecondCategoryId(String secondCategoryId) {
        this.secondCategoryId = secondCategoryId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
