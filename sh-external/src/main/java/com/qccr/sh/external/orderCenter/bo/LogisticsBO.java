package com.qccr.sh.external.orderCenter.bo;

import java.io.Serializable;

/**
 * Created by dongxc on 2015/12/7.
 */
public class LogisticsBO implements Serializable {

    private static final long serialVersionUID = 614010000611210450L;

    private Long              id;
    /** 关联订单id */
    private Long              orderId;
    /** 类型 '1、物流 2、快递 3、ems' */
    private Integer               type;
    /** 物流费用 */
    private Double            cost;
    /** 用户支付费用 */
    private Double            userCost;
    /** 物流公司 */
    private String            company;
    /** 快递单号 */
    private String            no;
    /** * 物流详情 */
    private String            detail;
    /** 最近物流信息时间 */
    private String            date;
    /** 最近物流详情 */
    private String            logisticsDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getUserCost() {
        return userCost;
    }

    public void setUserCost(Double userCost) {
        this.userCost = userCost;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLogisticsDetail() {
        return logisticsDetail;
    }

    public void setLogisticsDetail(String logisticsDetail) {
        this.logisticsDetail = logisticsDetail;
    }
}
