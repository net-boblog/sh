package com.qccr.sh.biz;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserBizImpl implements UserBiz {

	@Autowired
	private UserMerchantExt userMerchantExt;

	@Override
	public void modifyPassword(String username, String oldPassword, String newPassword) {
		//根据用户名查门店用户
		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(username);
		if(boResult==null || boResult.isFailed() || boResult.getData()==null || boResult.getData().getId()==0){
			throw new RuntimeException("用户不存在！");
		}
        UserMerchantBO userMerchantBO = boResult.getData();
		Result<Integer> result = userMerchantExt.modifyPwdById(userMerchantBO.getId(),oldPassword,newPassword);
		if(result==null){
			throw new RuntimeException("调用修改密码接口失败！");
		}else if(result.isFailed()){
			throw new RuntimeException(result.getStatusText());
		}
	}

	@Override
	public Integer getStoreId(String userName) {
		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(userName);
		if(boResult==null || boResult.isFailed() || boResult.getData()==null){
			return null;
		}

		UserMerchantBO userMerchantBO = boResult.getData();
		if(userMerchantBO.getStoreId()==0){
			return null;
		}

        return userMerchantBO.getStoreId();
	}


}
