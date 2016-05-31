package com.qccr.sh.external.crm.bo;

public class StoreInfoBO {
	//门店Id
	private String storeId;
	//门店名称
	private String storeName;
	//门店地址
	private String address;
	//开始营业时间 
	private String bizStartTime;
	//营业结束时间
	private String bizEndTime;
	//手机号码
	private String telephone;
	//座机
	private String tel;
	//收借人
	private String receiver;
	//支付方式
	private String payWay;
    //银行编码
    private String bankCode;
	//银行支行信息
	private String bankBranch;
	//省码
	private String provinceCode;
	//市码
	private String cityCode;
	//支付账号
	private String payAccount;
	//银行名称
	private String bankName;
	//银行卡修改次数
	private int  changeCount;
	/** 门头图片 */
	private String titlePhotoPath;
	/**暂停营业开始日期*/
	private String            outBizStartDate;
	/**暂停营业截至日期*/
	private String            outBizEndDate;
	/**营业周期值**/
	private String            closedCycle;

	private StoreSafetyBo storeSafetyBo;

	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBizStartTime() {
		return bizStartTime;
	}
	public void setBizStartTime(String bizStartTime) {
		this.bizStartTime = bizStartTime;
	}
	public String getBizEndTime() {
		return bizEndTime;
	}
	public void setBizEndTime(String bizEndTime) {
		this.bizEndTime = bizEndTime;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getChangeCount() {
		return changeCount;
	}
	public void setChangeCount(int changeCount) {
		this.changeCount = changeCount;
	}

	public String getTitlePhotoPath() {
		return titlePhotoPath;
	}

	public void setTitlePhotoPath(String titlePhotoPath) {
		this.titlePhotoPath = titlePhotoPath;
	}

	public String getOutBizStartDate() {
		return outBizStartDate;
	}

	public void setOutBizStartDate(String outBizStartDate) {
		this.outBizStartDate = outBizStartDate;
	}

	public String getOutBizEndDate() {
		return outBizEndDate;
	}

	public void setOutBizEndDate(String outBizEndDate) {
		this.outBizEndDate = outBizEndDate;
	}

	public String getClosedCycle() {
		return closedCycle;
	}

	public void setClosedCycle(String closedCycle) {
		this.closedCycle = closedCycle;
	}

	public StoreSafetyBo getStoreSafetyBo() {
		return storeSafetyBo;
	}

	public void setStoreSafetyBo(StoreSafetyBo storeSafetyBo) {
		this.storeSafetyBo = storeSafetyBo;
	}
}
