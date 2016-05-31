package com.qccr.sh.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.no
     *
     * @mbggenerated
     */
    private String no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.type
     *
     * @mbggenerated
     */
    private Byte type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.address_id
     *
     * @mbggenerated
     */
    private Long addressId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.receipt_id
     *
     * @mbggenerated
     */
    private Long receiptId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.coupon_id
     *
     * @mbggenerated
     */
    private Long couponId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.source
     *
     * @mbggenerated
     */
    private Byte source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.pay_type
     *
     * @mbggenerated
     */
    private Byte payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.bill_no
     *
     * @mbggenerated
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.pay_time
     *
     * @mbggenerated
     */
    private Date payTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.send_type
     *
     * @mbggenerated
     */
    private Byte sendType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.original_cost
     *
     * @mbggenerated
     */
    private BigDecimal originalCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.real_cost
     *
     * @mbggenerated
     */
    private BigDecimal realCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.use_point
     *
     * @mbggenerated
     */
    private Integer usePoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.refund_mark
     *
     * @mbggenerated
     */
    private Byte refundMark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.buyer_name
     *
     * @mbggenerated
     */
    private String buyerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.buyer_phone
     *
     * @mbggenerated
     */
    private String buyerPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.channel
     *
     * @mbggenerated
     */
    private Byte channel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.wait_success_time
     *
     * @mbggenerated
     */
    private Date waitSuccessTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.buyer_note
     *
     * @mbggenerated
     */
    private String buyerNote;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.seller_note
     *
     * @mbggenerated
     */
    private String sellerNote;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.id
     *
     * @return the value of orders.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.id
     *
     * @param id the value for orders.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.no
     *
     * @return the value of orders.no
     *
     * @mbggenerated
     */
    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.no
     *
     * @param no the value for orders.no
     *
     * @mbggenerated
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.type
     *
     * @return the value of orders.type
     *
     * @mbggenerated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.type
     *
     * @param type the value for orders.type
     *
     * @mbggenerated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.user_id
     *
     * @return the value of orders.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.user_id
     *
     * @param userId the value for orders.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.address_id
     *
     * @return the value of orders.address_id
     *
     * @mbggenerated
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.address_id
     *
     * @param addressId the value for orders.address_id
     *
     * @mbggenerated
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.receipt_id
     *
     * @return the value of orders.receipt_id
     *
     * @mbggenerated
     */
    public Long getReceiptId() {
        return receiptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.receipt_id
     *
     * @param receiptId the value for orders.receipt_id
     *
     * @mbggenerated
     */
    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.coupon_id
     *
     * @return the value of orders.coupon_id
     *
     * @mbggenerated
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.coupon_id
     *
     * @param couponId the value for orders.coupon_id
     *
     * @mbggenerated
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.source
     *
     * @return the value of orders.source
     *
     * @mbggenerated
     */
    public Byte getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.source
     *
     * @param source the value for orders.source
     *
     * @mbggenerated
     */
    public void setSource(Byte source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.status
     *
     * @return the value of orders.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.status
     *
     * @param status the value for orders.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.pay_type
     *
     * @return the value of orders.pay_type
     *
     * @mbggenerated
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.pay_type
     *
     * @param payType the value for orders.pay_type
     *
     * @mbggenerated
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.bill_no
     *
     * @return the value of orders.bill_no
     *
     * @mbggenerated
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.bill_no
     *
     * @param billNo the value for orders.bill_no
     *
     * @mbggenerated
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.pay_time
     *
     * @return the value of orders.pay_time
     *
     * @mbggenerated
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.pay_time
     *
     * @param payTime the value for orders.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.send_type
     *
     * @return the value of orders.send_type
     *
     * @mbggenerated
     */
    public Byte getSendType() {
        return sendType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.send_type
     *
     * @param sendType the value for orders.send_type
     *
     * @mbggenerated
     */
    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.update_time
     *
     * @return the value of orders.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.update_time
     *
     * @param updateTime the value for orders.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.create_time
     *
     * @return the value of orders.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.create_time
     *
     * @param createTime the value for orders.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.original_cost
     *
     * @return the value of orders.original_cost
     *
     * @mbggenerated
     */
    public BigDecimal getOriginalCost() {
        return originalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.original_cost
     *
     * @param originalCost the value for orders.original_cost
     *
     * @mbggenerated
     */
    public void setOriginalCost(BigDecimal originalCost) {
        this.originalCost = originalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.real_cost
     *
     * @return the value of orders.real_cost
     *
     * @mbggenerated
     */
    public BigDecimal getRealCost() {
        return realCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.real_cost
     *
     * @param realCost the value for orders.real_cost
     *
     * @mbggenerated
     */
    public void setRealCost(BigDecimal realCost) {
        this.realCost = realCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.use_point
     *
     * @return the value of orders.use_point
     *
     * @mbggenerated
     */
    public Integer getUsePoint() {
        return usePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.use_point
     *
     * @param usePoint the value for orders.use_point
     *
     * @mbggenerated
     */
    public void setUsePoint(Integer usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.refund_mark
     *
     * @return the value of orders.refund_mark
     *
     * @mbggenerated
     */
    public Byte getRefundMark() {
        return refundMark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.refund_mark
     *
     * @param refundMark the value for orders.refund_mark
     *
     * @mbggenerated
     */
    public void setRefundMark(Byte refundMark) {
        this.refundMark = refundMark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.buyer_name
     *
     * @return the value of orders.buyer_name
     *
     * @mbggenerated
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.buyer_name
     *
     * @param buyerName the value for orders.buyer_name
     *
     * @mbggenerated
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.buyer_phone
     *
     * @return the value of orders.buyer_phone
     *
     * @mbggenerated
     */
    public String getBuyerPhone() {
        return buyerPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.buyer_phone
     *
     * @param buyerPhone the value for orders.buyer_phone
     *
     * @mbggenerated
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone == null ? null : buyerPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.channel
     *
     * @return the value of orders.channel
     *
     * @mbggenerated
     */
    public Byte getChannel() {
        return channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.channel
     *
     * @param channel the value for orders.channel
     *
     * @mbggenerated
     */
    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.wait_success_time
     *
     * @return the value of orders.wait_success_time
     *
     * @mbggenerated
     */
    public Date getWaitSuccessTime() {
        return waitSuccessTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.wait_success_time
     *
     * @param waitSuccessTime the value for orders.wait_success_time
     *
     * @mbggenerated
     */
    public void setWaitSuccessTime(Date waitSuccessTime) {
        this.waitSuccessTime = waitSuccessTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.buyer_note
     *
     * @return the value of orders.buyer_note
     *
     * @mbggenerated
     */
    public String getBuyerNote() {
        return buyerNote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.buyer_note
     *
     * @param buyerNote the value for orders.buyer_note
     *
     * @mbggenerated
     */
    public void setBuyerNote(String buyerNote) {
        this.buyerNote = buyerNote == null ? null : buyerNote.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.seller_note
     *
     * @return the value of orders.seller_note
     *
     * @mbggenerated
     */
    public String getSellerNote() {
        return sellerNote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.seller_note
     *
     * @param sellerNote the value for orders.seller_note
     *
     * @mbggenerated
     */
    public void setSellerNote(String sellerNote) {
        this.sellerNote = sellerNote == null ? null : sellerNote.trim();
    }
}