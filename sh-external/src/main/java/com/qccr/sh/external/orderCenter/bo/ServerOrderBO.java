package com.qccr.sh.external.orderCenter.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by dongxc on 2015/12/1.
 */
public class ServerOrderBO implements Serializable {
    private static final long serialVersionUID = -1739311767611986477L;

    /** 服务订单ID */
    private Long id;

    /** 订单ID */
    private Long orderId;

    /** 订单编号 */
    private String orderNo;

    /** 买家姓名 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 平台对商户结算价 */
    private Double sprice;

    private Double saleSprice;

    /** 核销码 */
    private String smsCode;

    /** 1初始创建 3.已核销 5.结算中 9已结算 11.已申请退款 21.退款中 24.已退款 */
    private Integer status;

    /** * 状态名称：1初始创建 3.已核销 5.结算中 9已结算 11.已申请退款 21.退款中 24.已退款 */
    private String statusName;

    /** 核销时间 */
    private Timestamp smsTime;

    /** 用户ID */
    private Integer userId;

    /** 服务名称 */
    private String serverName;

    /**修改愿意字段，添加数据库字段*/
    private Integer serverId;

    /** 订单审核状态 0 正常订单 1 异常订单 2 待审核订单 */
    private Integer auditStatus;

    /** 最后审核时间 */
    private Timestamp lastAuditTime;

    /** 服务数量  */
    private Integer saleNum;

    /** 营销活动id */
    private Integer promotionId;

    /** 对应的商品订单ID */
    private Long goodsOrderId;

    /** 门店ID */
    private Integer storeId;

    /** 门店服务ID */
    private Integer serverStoreId;

    /** 预约时间 */
    private Timestamp appointTime;

    /** 预约状态 */
    private Integer appointStatus;

    /** 预约时间 */
    private String appointMemo;

    /** 原价 */
    private Double origCost;

    /** 实付 */
    private Double realCost;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 退款金额 */
    private Double refundSum;

    private String kfNote;

    /**套餐ID*/
    private Integer packageId;

    /**售后ID*/
    private Long afterSaleId;

    /**套餐展示信息*/
    private String packageInfo;
    /***
     * 保养ID
     */
    private Integer maintainId;

    /** 异常备注 */
    private String memo;

    /** 是否是过期异常订单，非数据库字段 */
    private Boolean overdueAbnormal;

    /** 活动名称 */
    private String promotionName;

    /** 图片 */
    private String img;

    /**
     * 订单的活动来源(1:促=平台活动能够,2:商=商户自建活动,21:美团) 参考PromotionSourceEnum常量
     */
    private Integer promotionSource;


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

    public Double getSprice() {
        return sprice;
    }

    public void setSprice(Double sprice) {
        this.sprice = sprice;
    }

    public Double getSaleSprice() {
        return saleSprice;
    }

    public void setSaleSprice(Double saleSprice) {
        this.saleSprice = saleSprice;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Timestamp getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Timestamp smsTime) {
        this.smsTime = smsTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Timestamp getLastAuditTime() {
        return lastAuditTime;
    }

    public void setLastAuditTime(Timestamp lastAuditTime) {
        this.lastAuditTime = lastAuditTime;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Long getGoodsOrderId() {
        return goodsOrderId;
    }

    public void setGoodsOrderId(Long goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getServerStoreId() {
        return serverStoreId;
    }

    public void setServerStoreId(Integer serverStoreId) {
        this.serverStoreId = serverStoreId;
    }

    public Timestamp getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Timestamp appointTime) {
        this.appointTime = appointTime;
    }

    public Integer getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(Integer appointStatus) {
        this.appointStatus = appointStatus;
    }

    public String getAppointMemo() {
        return appointMemo;
    }

    public void setAppointMemo(String appointMemo) {
        this.appointMemo = appointMemo;
    }

    public Double getOrigCost() {
        return origCost;
    }

    public void setOrigCost(Double origCost) {
        this.origCost = origCost;
    }

    public Double getRealCost() {
        return realCost;
    }

    public void setRealCost(Double realCost) {
        this.realCost = realCost;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Double getRefundSum() {
        return refundSum;
    }

    public void setRefundSum(Double refundSum) {
        this.refundSum = refundSum;
    }

    public String getKfNote() {
        return kfNote;
    }

    public void setKfNote(String kfNote) {
        this.kfNote = kfNote;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Long getAfterSaleId() {
        return afterSaleId;
    }

    public void setAfterSaleId(Long afterSaleId) {
        this.afterSaleId = afterSaleId;
    }

    public String getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
    }

    public Integer getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(Integer maintainId) {
        this.maintainId = maintainId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Boolean isOverdueAbnormal() {
        return overdueAbnormal;
    }

    public void setOverdueAbnormal(Boolean overdueAbnormal) {
        this.overdueAbnormal = overdueAbnormal;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPromotionSource() {
        return promotionSource;
    }

    public void setPromotionSource(Integer promotionSource) {
        this.promotionSource = promotionSource;
    }
}
