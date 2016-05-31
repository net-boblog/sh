/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户自建活动详情BO
 * @author dongxuechai
 * @date 2016年3月01日 上午11:48
 */
public class MerchantPromotionDetailBO implements Serializable {
    private static final long serialVersionUID = 4188580704315262912L;

    /** 促销活动业务主键ID */
    private Long                promotionId;
    /** 活动名称 */
    private String              name;
    /** 活动类型（1.商品；2.门店服务；3.保养优惠套餐；4.保养项目活动） */
    private Integer             type;
    /** 活动描述 */
    private String              desc;
    /** 活动运营状态（1.上线；2.下线）*/
    private Integer             status;
    /** 服务一级分类id */
    private String              firstCategoryId;
    /** 服务一级分类名称 */
    private String              firstCategoryName;
    /** 服务二级分类id */
    private String              secondCategoryId;
    /** 服务二级分类名称 */
    private String              secondCategoryName;
    /** 活动开始日期 */
    private Date                startTime;
    /** 活动结束日期 */
    private Date                endTime;
    /** 促销频次，n:m（N天M次） */
    private String              cycle;
    /** 活动发起人（1.平台；2.商户） */
    private Integer             sponsor;
    /** 创建时间 */
    private Date                createTime;
    /** 创建人 */
    private String              createPerson;
    /** 修改时间 */
    private Date                updateTime;
    /** 修改人 */
    private String              updatePerson;
    /** 是否支持优惠券（1.是；2.否） */
    private Integer             useCoupon;
    /** * 门店服务ID */
    private Integer              productId;
    /** 商家活动参加状态，参考EnrollPromotionStatus常量 */
    private Integer             enrollPromotionStatus;

    //具体活动信息

    /** 主键id */
    private Integer           id;
    /** 门店id */
    private Integer           storeId;
    /** 门店名称 */
    private String            storeName;
    /** 门店所属省份 */
    private Integer           provinceId;
    /** 门店所属城市 */
    private Integer           cityId;
    /** 运营状态(0待上线,1上线；2下线) */
    private Integer           operateStatus;
    /** 销售总数 */
    private Integer           saleNumber;
    /** 库存 */
    private Integer           stock;
    /** 售出数量 */
    private Integer           soldNumber;
    /** 促销价格（分为单位） */
    private Integer           promotionAmt;
    /** 促销结算价（分为单位） */
    private Integer           promotionClearAmt;
    /** 商户补贴（分为单位） */
    private Integer           merchantAllowance;
    /** 审批状态（1.待审批；2.审批通过；3.审批失败 参考：ApproveConstants） */
    private Integer           approveStatus;
    /** 报名时间 */
    private Date              enrollTime;


    /** 活动状态名称 */
    private String              promotionStatusName;
    /** 是否可编辑 */
    private boolean             editable = false;
    /** 是否可下线*/
    private boolean             allowOffline = false;
    /** 促销频次 N天*/
    private Integer             cycleDayes;
    /** 促销频次 M次*/
    private Integer             cycleTimes;
    /** 产品ID*/

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(String firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public String getSecondCategoryId() {
        return secondCategoryId;
    }

    public void setSecondCategoryId(String secondCategoryId) {
        this.secondCategoryId = secondCategoryId;
    }

    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getSponsor() {
        return sponsor;
    }

    public void setSponsor(Integer sponsor) {
        this.sponsor = sponsor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Integer getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(Integer useCoupon) {
        this.useCoupon = useCoupon;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getEnrollPromotionStatus() {
        return enrollPromotionStatus;
    }

    public void setEnrollPromotionStatus(Integer enrollPromotionStatus) {
        this.enrollPromotionStatus = enrollPromotionStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Integer getPromotionAmt() {
        return promotionAmt;
    }

    public void setPromotionAmt(Integer promotionAmt) {
        this.promotionAmt = promotionAmt;
    }

    public Integer getPromotionClearAmt() {
        return promotionClearAmt;
    }

    public void setPromotionClearAmt(Integer promotionClearAmt) {
        this.promotionClearAmt = promotionClearAmt;
    }

    public Integer getMerchantAllowance() {
        return merchantAllowance;
    }

    public void setMerchantAllowance(Integer merchantAllowance) {
        this.merchantAllowance = merchantAllowance;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Date getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(Date enrollTime) {
        this.enrollTime = enrollTime;
    }

    public String getPromotionStatusName() {
        return promotionStatusName;
    }

    public void setPromotionStatusName(String promotionStatusName) {
        this.promotionStatusName = promotionStatusName;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isAllowOffline() {
        return allowOffline;
    }

    public void setAllowOffline(boolean allowOffline) {
        this.allowOffline = allowOffline;
    }

    public Integer getCycleDayes() {
        return cycleDayes;
    }

    public void setCycleDayes(Integer cycleDayes) {
        this.cycleDayes = cycleDayes;
    }

    public Integer getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(Integer cycleTimes) {
        this.cycleTimes = cycleTimes;
    }
}
