package com.qccr.sh.external.orderCenter.bo;

import java.io.Serializable;

/**
 * Created by dongxc on 2015/12/1.
 */
public class GoodsOrderBO implements Serializable {

    private static final long serialVersionUID = 2182140612452857564L;

    /** 订单商品关系ID */
    private Long id;

    /** 订单ID */
    private Long orderId;

    /** 商品ID */
    private Integer goodsId;

    /** 市场价 */
    private Double marketCost;

    /** 销售价 */
    private Double saleCost;

    /** 销售数量 */
    private Integer saleNum;

    /** 状态 */
    private Integer status;

    /** 状态名称 */
    private String statusName;

    /** 退换货数量 */
    private Integer refundNum;

    /** 退款金额 */
    private Double refundSum;

    /** 商品名称 */
    private String goodsName;

    /** 商品图片URL */
    private String img;

    /**第三方订单信息 存放传过来的 MerchCode 理论上是商品编码，实际数据库中可能没有*/
    private String extDesc;

    /**第三方订单信息 存放传过来的 MerchId有*/
    private String merchId;

    /**该字段并非数据库字段，定义变量用于优惠券判断*/
    /**前台要求返回，标识商品类型  10：轮胎，102：机油*/
    private Integer categoryId;

    /**套餐ID*/
    private Integer packageId;

    /**售后ID*/
    private Long afterSaleId;

    /**套餐展示信息*/
    private String packageInfo;

    /**该字段并非数据库字段，定义产品关联服务*/
    private Integer serverId;

    private Long giftOgid;

    /** 保养ID */
    private Integer maintainId;

    /** 营销活动id */
    private Integer promotionId;

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(Double marketCost) {
        this.marketCost = marketCost;
    }

    public Double getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(Double saleCost) {
        this.saleCost = saleCost;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
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

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Double getRefundSum() {
        return refundSum;
    }

    public void setRefundSum(Double refundSum) {
        this.refundSum = refundSum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExtDesc() {
        return extDesc;
    }

    public void setExtDesc(String extDesc) {
        this.extDesc = extDesc;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Long getGiftOgid() {
        return giftOgid;
    }

    public void setGiftOgid(Long giftOgid) {
        this.giftOgid = giftOgid;
    }

    public Integer getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(Integer maintainId) {
        this.maintainId = maintainId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
}
