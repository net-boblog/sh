package com.qccr.sh.external.carman.bo;

import java.util.Date;

/**
 * Created by xianchao.yan on 2015/11/2.
 */
public class DrawRecordQueryBO {

    private int storeId;//门店id

    private Date startDate;//提款开始时间

    private Date endDate;//提款结束时间

    private Integer status;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

