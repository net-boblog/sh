package com.qccr.sh.external.orderCenter.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by dongxc on 2015/12/1.
 * 服务订单请求对象
 */
public class ServerOrderQuery implements Serializable {
    private static final long serialVersionUID = -5532307068534403895L;
    /** 门店ID **/
    private Long              storeId;
    /** 验证码 **/
    private String            smsCode;
    /** 审核状态 **/
    private Integer           auditStatus;
    /** 订单编号 **/
    private String            orderNo;
    /** 买家姓名 **/
    private String            buyerName;
    /** 买家电话 **/
    private String            buyerPhone;
    /** 开始时间 **/
    private Timestamp         beginDate;
    /** 结束时间 **/
    private Timestamp         endDate;
    /** 服务列表 **/
    private Long[]            serverIdArr;
    /** 页号 **/
    private Integer           pageNo;
    /** 每页记录数 **/
    private Integer           pageSize;
    /** 按sms_time排序 0降序，1升序 **/
    private int               sortType;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long[] getServerIdArr() {
        return serverIdArr;
    }

    public void setServerIdArr(Long[] serverIdArr) {
        this.serverIdArr = serverIdArr;
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

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }
}
