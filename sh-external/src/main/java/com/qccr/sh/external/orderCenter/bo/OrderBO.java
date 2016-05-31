package com.qccr.sh.external.orderCenter.bo;

import com.qccr.ordercenter.facade.entity.order.ExtraOrderInfoRo;
import com.qccr.ordercenter.facade.entity.order.FixedOrderRo;
import com.qccr.ordercenter.facade.entity.order.LogisticsRo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dongxc on 2015/12/1.
 */
public class OrderBO implements Serializable {
    private static final long serialVersionUID = 5839428285076371607L;

    /** 订单ID */
    private Long id;

    /** 订单号 */
    private String no;

    /** 用户ID */
    private Long userId;

    /** 地址ID */
    private Long addressId;

    /** 发票ID */
    private Long receiptId;

    /** 优惠券ID */
    private Long userCouponId;

    private FixedOrderRo fixedOrder;
    /** 优惠金额 */
    private Double discount;

    /**来源	1、Android; 2、IOS; 3、Web; 4、第三方; 5、淘宝; 6、京东; 7、客服系统; 9、微信; 15、门店销售; 16、KA事业部 */
    private Integer source;

    /** 订单状态 1新增，4支付成功， 2确认收货 ,15退款成功,16支付成功不可退款类型*/
    //	原先定义中没用到的几个	3未支付，5发货，6退货		新增从15开始，原先貌似还有11的，不知道什么意义 */
    private Integer status;

    private String statusName;

    /** 订单类型 1.购买商品送服务 2.仅商品 3.仅服务 4.商品-(非赠送）服务混合 ' */
    private Integer type;

    /** 支付方式，1支付宝，2网银，3门店支付，4货到付款，5、第三方电商平台（同步订单）6.微信支付 7.财付通  60.免费 */
    private Integer payType;

    /** 流水号 */
    private String billNo;

    /** 买家账号 */
    private String payAccount;

    /** 配送方式 */
    private Integer sendType;

    /** 支付时间 */
    private Timestamp payTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 创建时间 */
    private Timestamp createTime;

    /** 原價 */
    private Double originalCost;

    /** 真实付款 */
    private Double realCost;

    /** 用户积分 */
    private Integer usePoint;

    /** 退货标记 */
    private Integer refundMark;

    /**收货人姓名 */
    private String buyerName;

    /**收货人手机号*/
    private String buyerPhone;

    /**0-默认(app&web) 2-秒杀  3-第三方 4-批发 */
    private Integer channel;

    /** 买家留言 */
    private String buyerNote;

    /** 卖家留言 */
    private String sellerNote;

    /** 门店ID dubbo数据调用 不做存储 */
    private Integer storeId;

    /** 客服端用到的所有短信验证码 */
    private String smsCode;

    private String thirdId;

    /** 经度 */
    private Double lon;

    /** 纬度 */
    private Double lat;

    private String imei;

    /** 车型id */
    private Integer userCarId;

    /** 是否前100名免单 */
    private Boolean before100;

    /** 电话（座机） */
    private String telephone;

    /** 扩展信息 */
    private ExtraOrderInfoRo extraOrderInfoRo;

    private LogisticsRo logisticsRo;

    /** 订单里的服务费用 */
    private Double price;

    /** 页码展示的订单状态 */
    private int orderStatus;

    private boolean changeStoreFlag;

    private String changeStoreDesc;

    /** 服务订单列表 */
    private List<ServerOrderBO> serverOrderBOList;

    /** 商品订单列表 */
    private List<GoodsOrderBO> goodsOrderBOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Long getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Long userCouponId) {
        this.userCouponId = userCouponId;
    }

    public FixedOrderRo getFixedOrder() {
        return fixedOrder;
    }

    public void setFixedOrder(FixedOrderRo fixedOrder) {
        this.fixedOrder = fixedOrder;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Double originalCost) {
        this.originalCost = originalCost;
    }

    public Double getRealCost() {
        return realCost;
    }

    public void setRealCost(Double realCost) {
        this.realCost = realCost;
    }

    public Integer getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(Integer usePoint) {
        this.usePoint = usePoint;
    }

    public Integer getRefundMark() {
        return refundMark;
    }

    public void setRefundMark(Integer refundMark) {
        this.refundMark = refundMark;
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

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getBuyerNote() {
        return buyerNote;
    }

    public void setBuyerNote(String buyerNote) {
        this.buyerNote = buyerNote;
    }

    public String getSellerNote() {
        return sellerNote;
    }

    public void setSellerNote(String sellerNote) {
        this.sellerNote = sellerNote;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(Integer userCarId) {
        this.userCarId = userCarId;
    }

    public Boolean isBefore100() {
        return before100;
    }

    public void setBefore100(Boolean before100) {
        this.before100 = before100;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public ExtraOrderInfoRo getExtraOrderInfoRo() {
        return extraOrderInfoRo;
    }

    public void setExtraOrderInfoRo(ExtraOrderInfoRo extraOrderInfoRo) {
        this.extraOrderInfoRo = extraOrderInfoRo;
    }

    public LogisticsRo getLogisticsRo() {
        return logisticsRo;
    }

    public void setLogisticsRo(LogisticsRo logisticsRo) {
        this.logisticsRo = logisticsRo;
    }

    public List<GoodsOrderBO> getGoodsOrderBOList() {
        return goodsOrderBOList;
    }

    public void setGoodsOrderBOList(List<GoodsOrderBO> goodsOrderBOList) {
        this.goodsOrderBOList = goodsOrderBOList;
    }

    public List<ServerOrderBO> getServerOrderBOList() {
        return serverOrderBOList;
    }

    public void setServerOrderBOList(List<ServerOrderBO> serverOrderBOList) {
        this.serverOrderBOList = serverOrderBOList;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isChangeStoreFlag() {
        return changeStoreFlag;
    }

    public void setChangeStoreFlag(boolean changeStoreFlag) {
        this.changeStoreFlag = changeStoreFlag;
    }

    public String getChangeStoreDesc() {
        return changeStoreDesc;
    }

    public void setChangeStoreDesc(String changeStoreDesc) {
        this.changeStoreDesc = changeStoreDesc;
    }
}
