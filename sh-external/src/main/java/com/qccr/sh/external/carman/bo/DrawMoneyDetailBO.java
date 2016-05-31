package com.qccr.sh.external.carman.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xianchao.yan on 2015/11/6.
 */
public class DrawMoneyDetailBO {

    private BigDecimal sum;
    private BigDecimal orderSum;
    private BigDecimal rewardSum;
    private int status;
    private Date createTime;
    private Date clearTime;
    private List<ServiceDetailBO> serviceList;

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    public BigDecimal getRewardSum() {
        return rewardSum;
    }

    public void setRewardSum(BigDecimal rewardSum) {
        this.rewardSum = rewardSum;
    }

    public List<ServiceDetailBO> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceDetailBO> serviceList) {
        this.serviceList = serviceList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getClearTime() {
        return clearTime;
    }

    public void setClearTime(Date clearTime) {
        this.clearTime = clearTime;
    }

}
