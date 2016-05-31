package com.qccr.sh.external.carman.bo;

import com.qccr.sh.util.BT;

import java.util.Date;

/**
 * Created by xianchao.yan on 2015/11/2.
 */
public class ServiceDetailBO {

    private String orderNo;
    private String serviceName;
    private int count;
    private double money;
    private String smsCode;
    private Date smsTime;
    private int orderStatus;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Date getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusDesc() {
        if (this.orderStatus == 1) {
            return "初始创建";
        } else if (this.orderStatus == 3) {
            return "已核销";
        } else if (this.orderStatus == 3) {
            return "结算中";
        } else if (this.orderStatus == 3) {
            return "已结算";
        } else if (this.orderStatus == 3) {
            return "已申请退款";
        } else if (this.orderStatus == 3) {
            return "退款中";
        } else if (this.orderStatus == 3) {
            return "已退款";
        } else {
            return "";
        }
    }

    public String getSmsTimeStr() {
        if (this.smsTime == null) {
            return "";
        }
        return BT.dateFormat(this.smsTime, "yyyy-MM-dd HH:mm:ss");
    }

}
