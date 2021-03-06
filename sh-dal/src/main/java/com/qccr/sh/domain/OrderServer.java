package com.qccr.sh.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OrderServer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.order_id
     *
     * @mbggenerated
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.order_goods_id
     *
     * @mbggenerated
     */
    private Long orderGoodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.store_id
     *
     * @mbggenerated
     */
    private Integer storeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.clear_id
     *
     * @mbggenerated
     */
    private Integer clearId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.server_store_id
     *
     * @mbggenerated
     */
    private Integer serverStoreId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.server_name
     *
     * @mbggenerated
     */
    private String serverName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.appoint_time
     *
     * @mbggenerated
     */
    private Date appointTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.orig_cost
     *
     * @mbggenerated
     */
    private BigDecimal origCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.real_cost
     *
     * @mbggenerated
     */
    private BigDecimal realCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.sms_code
     *
     * @mbggenerated
     */
    private String smsCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.sms_time
     *
     * @mbggenerated
     */
    private Date smsTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.refund_sum
     *
     * @mbggenerated
     */
    private BigDecimal refundSum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.kf_note
     *
     * @mbggenerated
     */
    private String kfNote;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.package_id
     *
     * @mbggenerated
     */
    private Integer packageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_server.package_info
     *
     * @mbggenerated
     */
    private String packageInfo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.id
     *
     * @return the value of order_server.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.id
     *
     * @param id the value for order_server.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.order_id
     *
     * @return the value of order_server.order_id
     *
     * @mbggenerated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.order_id
     *
     * @param orderId the value for order_server.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.order_goods_id
     *
     * @return the value of order_server.order_goods_id
     *
     * @mbggenerated
     */
    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.order_goods_id
     *
     * @param orderGoodsId the value for order_server.order_goods_id
     *
     * @mbggenerated
     */
    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.store_id
     *
     * @return the value of order_server.store_id
     *
     * @mbggenerated
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.store_id
     *
     * @param storeId the value for order_server.store_id
     *
     * @mbggenerated
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.clear_id
     *
     * @return the value of order_server.clear_id
     *
     * @mbggenerated
     */
    public Integer getClearId() {
        return clearId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.clear_id
     *
     * @param clearId the value for order_server.clear_id
     *
     * @mbggenerated
     */
    public void setClearId(Integer clearId) {
        this.clearId = clearId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.server_store_id
     *
     * @return the value of order_server.server_store_id
     *
     * @mbggenerated
     */
    public Integer getServerStoreId() {
        return serverStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.server_store_id
     *
     * @param serverStoreId the value for order_server.server_store_id
     *
     * @mbggenerated
     */
    public void setServerStoreId(Integer serverStoreId) {
        this.serverStoreId = serverStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.server_name
     *
     * @return the value of order_server.server_name
     *
     * @mbggenerated
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.server_name
     *
     * @param serverName the value for order_server.server_name
     *
     * @mbggenerated
     */
    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.appoint_time
     *
     * @return the value of order_server.appoint_time
     *
     * @mbggenerated
     */
    public Date getAppointTime() {
        return appointTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.appoint_time
     *
     * @param appointTime the value for order_server.appoint_time
     *
     * @mbggenerated
     */
    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.orig_cost
     *
     * @return the value of order_server.orig_cost
     *
     * @mbggenerated
     */
    public BigDecimal getOrigCost() {
        return origCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.orig_cost
     *
     * @param origCost the value for order_server.orig_cost
     *
     * @mbggenerated
     */
    public void setOrigCost(BigDecimal origCost) {
        this.origCost = origCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.real_cost
     *
     * @return the value of order_server.real_cost
     *
     * @mbggenerated
     */
    public BigDecimal getRealCost() {
        return realCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.real_cost
     *
     * @param realCost the value for order_server.real_cost
     *
     * @mbggenerated
     */
    public void setRealCost(BigDecimal realCost) {
        this.realCost = realCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.sms_code
     *
     * @return the value of order_server.sms_code
     *
     * @mbggenerated
     */
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.sms_code
     *
     * @param smsCode the value for order_server.sms_code
     *
     * @mbggenerated
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode == null ? null : smsCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.status
     *
     * @return the value of order_server.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.status
     *
     * @param status the value for order_server.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.update_time
     *
     * @return the value of order_server.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.update_time
     *
     * @param updateTime the value for order_server.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.sms_time
     *
     * @return the value of order_server.sms_time
     *
     * @mbggenerated
     */
    public Date getSmsTime() {
        return smsTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.sms_time
     *
     * @param smsTime the value for order_server.sms_time
     *
     * @mbggenerated
     */
    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.refund_sum
     *
     * @return the value of order_server.refund_sum
     *
     * @mbggenerated
     */
    public BigDecimal getRefundSum() {
        return refundSum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.refund_sum
     *
     * @param refundSum the value for order_server.refund_sum
     *
     * @mbggenerated
     */
    public void setRefundSum(BigDecimal refundSum) {
        this.refundSum = refundSum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.user_id
     *
     * @return the value of order_server.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.user_id
     *
     * @param userId the value for order_server.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.kf_note
     *
     * @return the value of order_server.kf_note
     *
     * @mbggenerated
     */
    public String getKfNote() {
        return kfNote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.kf_note
     *
     * @param kfNote the value for order_server.kf_note
     *
     * @mbggenerated
     */
    public void setKfNote(String kfNote) {
        this.kfNote = kfNote == null ? null : kfNote.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.package_id
     *
     * @return the value of order_server.package_id
     *
     * @mbggenerated
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.package_id
     *
     * @param packageId the value for order_server.package_id
     *
     * @mbggenerated
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_server.package_info
     *
     * @return the value of order_server.package_info
     *
     * @mbggenerated
     */
    public String getPackageInfo() {
        return packageInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_server.package_info
     *
     * @param packageInfo the value for order_server.package_info
     *
     * @mbggenerated
     */
    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo == null ? null : packageInfo.trim();
    }
}