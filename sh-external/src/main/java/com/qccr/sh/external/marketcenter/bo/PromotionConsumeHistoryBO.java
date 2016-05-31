/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;


import java.io.Serializable;
import java.util.List;

/**
 * 活动消耗历史记录对象
 * @author dongxuechai
 * @date 2016年3月01日 下午4:04
 */
public class PromotionConsumeHistoryBO implements Serializable {
    private static final long serialVersionUID = -6656088748961762389L;

    private Long                          promotionId;

    private Integer                       soldNumber;

    private Integer                       saleNumber;

    private List<DailyPromotionConsumeBO> dailyPromotionConsumeBOs;

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public List<DailyPromotionConsumeBO> getDailyPromotionConsumeBOs() {
        return dailyPromotionConsumeBOs;
    }

    public void setDailyPromotionConsumeBOs(List<DailyPromotionConsumeBO> dailyPromotionConsumeBOs) {
        this.dailyPromotionConsumeBOs = dailyPromotionConsumeBOs;
    }
}
