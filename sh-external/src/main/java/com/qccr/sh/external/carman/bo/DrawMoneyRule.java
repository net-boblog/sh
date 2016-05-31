package com.qccr.sh.external.carman.bo;

/**
 * Created by xianchao.yan on 2015/11/5.
 */
public class DrawMoneyRule {

    private long id;
    private long storeId;
    private int province;
    private int city;
    private int priceCondition;
    private int timeCondition;
    private int pickLimit;
    private int status;
    private int firstAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getPriceCondition() {
        return priceCondition;
    }

    public void setPriceCondition(int priceCondition) {
        this.priceCondition = priceCondition;
    }

    public int getTimeCondition() {
        return timeCondition;
    }

    public void setTimeCondition(int timeCondition) {
        this.timeCondition = timeCondition;
    }

    public int getPickLimit() {
        return pickLimit;
    }

    public void setPickLimit(int pickLimit) {
        this.pickLimit = pickLimit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(int firstAmount) {
        this.firstAmount = firstAmount;
    }
}
