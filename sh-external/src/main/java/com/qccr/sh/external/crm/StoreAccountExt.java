package com.qccr.sh.external.crm;

import java.util.List;

import com.qccr.sh.external.crm.bo.StoreInfoBO;
import com.qccr.sh.response.Response;
import com.toowell.crm.facade.store.entity.BankRo;
import com.toowell.crm.facade.store.entity.CodeDescRo;

public interface StoreAccountExt {
	
	/**
	 * 根据门店ID查询门店收款信息
	 * @author jianghn
	 * @param storeId
	 * @return
	 */
	public Response<StoreInfoBO> getStoreInfo(Integer storeId);
	
	/**
	 * 修改门店信息(控制点:不包含收款信息)
	 * @author jianghn
	 * @param storeInfoBO
	 */
	public Response<Integer> updateStoreInfo(StoreInfoBO storeInfoBO);
	
	/**
	 * 修改收款账号信息
	 * @author jianghn
	 * @param bankAccountBO
	 * @return
	 */
	public Response<Integer> updateBankAccount(StoreInfoBO storeInfoBO);
	
	/**
	 * 获取银行列表
	 * @author jianghn
	 * @return
	 */
	public Response<List<BankRo>> getBankList();
	
	/**
	 * 获取省或市列表
	 * code=null 获取省
	 * @param code
	 * @return
	 */
	public Response<List<CodeDescRo>> getProvinceorCity(String code);

}
