package com.qccr.sh.biz;

import java.util.List;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.crm.ProductExt;
import com.qccr.sh.external.crm.bo.ProductBO;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import com.qccr.sh.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyGoodsBizImpl implements MyGoodsBiz {

	@Autowired
	private ProductExt productExt;

	@Autowired
	private UserMerchantExt userMerchantExt;

	@Override
	public List<ProductBO> queryGoodsByUsername(String username) {
		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(username);
        if(boResult==null || boResult.isFailed() || boResult.getData()==null){
			throw new RuntimeException("查询不到该账户！");
		}
        UserMerchantBO userMerchantBO = boResult.getData();
		if(userMerchantBO.getStoreId()==0){
			throw new RuntimeException("查询不到该账户关联的门店！");
		}
		Response<List<ProductBO>> listResponse = productExt.getProductsByStoreId(userMerchantBO.getStoreId());
		if(listResponse!=null&&listResponse.isSuccess()&&listResponse.getData()!=null){

			return listResponse.getData();
		}else{
			String errMsg = "查询接口无数据";
			if(listResponse!=null && listResponse.getMessage()!=null && !listResponse.getMessage().equals("")){
				 errMsg = listResponse.getMessage();
			}
			throw new RuntimeException(errMsg);
		}
	}

}
