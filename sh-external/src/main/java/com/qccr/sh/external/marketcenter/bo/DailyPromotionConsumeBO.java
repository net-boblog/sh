/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 每日活动消耗信息对象
 * @author dongxuechai
 * @date   2016年3月01日 下午4:09
 */
public class DailyPromotionConsumeBO implements Serializable {
    private static final long serialVersionUID = -2174705416729222051L;

    /** 活动id */
    private Long              promotionId;
    /** 单日售出数量 */
    private Integer           dailySoldNumber;
    /** 单日收益（分为单位） */
    private Integer           dailyIncome;
    /** 记录日期 */
    private Date              recordDate;

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getDailySoldNumber() {
        return dailySoldNumber;
    }

    public void setDailySoldNumber(Integer dailySoldNumber) {
        this.dailySoldNumber = dailySoldNumber;
    }

    public Integer getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(Integer dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
