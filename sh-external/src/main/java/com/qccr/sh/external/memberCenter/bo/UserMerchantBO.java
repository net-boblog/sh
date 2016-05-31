package com.qccr.sh.external.memberCenter.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by dongxc on 2015/12/29.
 */
public class UserMerchantBO implements Serializable {

    private static final long serialVersionUID = -2821896105581463604L;

    private int       id;
    private String    username;
    private String    password;
    private String    fullname;
    private String    nick;
    private int       gender = -1;
    private String    phone;
    private String    telephone;
    private String    email;
    private String    address;
    private String    usertype;
    private int       province;
    private int       city;
    private int       county;
    private String    license;
    private String    certificateno;
    private String    licensePic;
    private String    certificatePic;
    private String    merchantName;
    private String    storeName;
    private int       storeId;
    private int       merchantId;
    private int       managerId;
    private boolean   isWholeseler;
    private boolean   isEmployee;
    private boolean   isLock;
    private boolean   isDelete;
    private int       registerType;
    private String    wholeselerActivatePerson;
    private String    storeActivatePerson;
    private Timestamp wholeselerActivateTime;
    private Timestamp storeActivateTime;
    private String    createOperator;
    private Timestamp createTime;
    private String    updateOperator;
    private Timestamp updateTime;
    private Timestamp deleteTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }

    public String getLicensePic() {
        return licensePic;
    }

    public void setLicensePic(String licensePic) {
        this.licensePic = licensePic;
    }

    public String getCertificatePic() {
        return certificatePic;
    }

    public void setCertificatePic(String certificatePic) {
        this.certificatePic = certificatePic;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public boolean isWholeseler() {
        return this.isWholeseler;
    }

    public void setWholeseler(boolean isWholeseler) {
        this.isWholeseler = isWholeseler;
    }

    public boolean isEmployee() {
        return this.isEmployee;
    }

    public void setEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public boolean isLock() {
        return this.isLock;
    }

    public void setLock(boolean isLock) {
        this.isLock = isLock;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getRegisterType() {
        return registerType;
    }

    public void setRegisterType(int registerType) {
        this.registerType = registerType;
    }

    public String getWholeselerActivatePerson() {
        return wholeselerActivatePerson;
    }

    public void setWholeselerActivatePerson(String wholeselerActivatePerson) {
        this.wholeselerActivatePerson = wholeselerActivatePerson;
    }

    public String getStoreActivatePerson() {
        return storeActivatePerson;
    }

    public void setStoreActivatePerson(String storeActivatePerson) {
        this.storeActivatePerson = storeActivatePerson;
    }

    public Timestamp getWholeselerActivateTime() {
        return wholeselerActivateTime;
    }

    public void setWholeselerActivateTime(Timestamp wholeselerActivateTime) {
        this.wholeselerActivateTime = wholeselerActivateTime;
    }

    public Timestamp getStoreActivateTime() {
        return storeActivateTime;
    }

    public void setStoreActivateTime(Timestamp storeActivateTime) {
        this.storeActivateTime = storeActivateTime;
    }

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }
}
