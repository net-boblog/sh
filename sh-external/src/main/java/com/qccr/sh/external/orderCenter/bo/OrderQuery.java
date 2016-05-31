package com.qccr.sh.external.orderCenter.bo;

import java.io.Serializable;

/**
 * Created by dongxc on 2015/12/1.
 */
public class OrderQuery implements Serializable {
    private static final long serialVersionUID = 4891552239531141842L;
    /**
     * 订单编号
     */
    private String			 no;
    /**
     * 送货方式
     */
    private Integer			 sendType;
    /**
     * 地址
     */
    private Long			 addressId;
    /**
     * 订单类型
     */
    private Long			 type;
    /**
     * 状态
     */
    private Integer          status;
    /**
     * 每页记录数
     */
    private Integer          pageSize;
    /**
     * 当前页码
     */
    private Integer          pageNumber;

    /**
     * 门店ID
     */
    private Integer           storeId;

    /**
     * 订单ID
     */
    private Long              orderId;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
