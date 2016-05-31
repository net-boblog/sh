package com.qccr.sh.external.crm.bo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dongxc on 2015/12/2.
 */
public class ProductBO implements Serializable {
    private static final long serialVersionUID = -3244887226873887225L;

    private Integer id;
    /** 服务项目ID */
    private String productId;
    /** 服务项目 */
    private String productName;
    /** 项目价格 */
    private Double clearAmt;
    /** 店铺名称 */
    private String storeName;
    /**门店ID*/
    private String storeId;
    /**一级分类*/
    private String  firstCategory;
    /**二级分类*/
    private String  secondCategory;
    /**折扣*/
    private String  discount;
    /**类目编码*/
    private String   categoryCode;
    /**服务类型[纯服务:1、服务含材料:2]*/
    private String   itemType;
    /**审核不通过理由*/
    private String productStatusMessage;
    /**存二级属性和服务价格属性*/
    private List<ItemAttrBo> itemAttrList;
    /**市场价单位元*/
    private Double  marketAmt;

    /**签约用户*/
    private String            belongUser;
    /**创建用户*/
    private String            createUser;
    /**审核状态*/
    private String            productStatus;
    /**更新用户*/
    private String            updateUser;
    /** 是否可编辑 */
    private boolean           editable = false;
    /**是否可以删除*/
    private boolean           delete = false;
    /**已服务订单数*/
    private String countOrder;
    private boolean isMyProduct =false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getClearAmt() {
        return clearAmt;
    }

    public void setClearAmt(Double clearAmt) {
        this.clearAmt = clearAmt;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getProductStatusMessage() {
        return productStatusMessage;
    }

    public void setProductStatusMessage(String productStatusMessage) {
        this.productStatusMessage = productStatusMessage;
    }

    /**市场价单位元*/
    public Double getMarketAmt() {
        return marketAmt;
    }

    public void setMarketAmt(Double marketAmt) {
        this.marketAmt = marketAmt;
    }

    public List<ItemAttrBo> getItemAttrList() {
        return itemAttrList;
    }

    public void setItemAttrList(List<ItemAttrBo> itemAttrList) {
        this.itemAttrList = itemAttrList;
    }

    public String getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(String countOrder) {
        this.countOrder = countOrder;
    }

    public boolean isMyProduct() {
        return isMyProduct;
    }

    public void setMyProduct(boolean myProduct) {
        isMyProduct = myProduct;
    }
}
