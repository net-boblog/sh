/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */

package com.qccr.sh.external.marketcenter.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangzhonghua
 * @version 2016/3/9 23:41.
 */
public class StoreAchievementAnalysisBo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5378004693586543181L;
    //日期时间
    private Date date_time;
    //获得收益
    private Double profit;
    //正常订单数
    private Integer nomalCount;

    public Date getDate_time() {
        return date_time;
    }
    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }
    public Double getProfit() {
        return profit;
    }
    public void setProfit(Double profit) {
        this.profit = profit;
    }
    public Integer getNomalCount() {
        return nomalCount;
    }
    public void setNomalCount(Integer nomalCount) {
        this.nomalCount = nomalCount;
    }
    @Override
    public String toString() {
        return "StoreAchievementAnalysisBo [date_time=" + date_time
                + ", profit=" + profit + ", nomalCount=" + nomalCount + "]";
    }
}
