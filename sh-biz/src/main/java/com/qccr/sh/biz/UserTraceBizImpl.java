package com.qccr.sh.biz;

import java.util.Date;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qccr.sh.domain.Usertrace;
import com.qccr.sh.mapper.UsertraceMapper;
import com.qccr.sh.util.BT;

@Service
@Transactional
public class UserTraceBizImpl implements UserTraceBiz {

	@Autowired
	UsertraceMapper usertraceMapper;

	@Autowired
	private UserMerchantExt userMerchantExt;

	@Override
	public void recordTrace(String username, String tracecontent, String clientip) {
		//根据用户名查门店用户
		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(username);
		if(boResult==null || boResult.isFailed() || boResult.getData()==null || boResult.getData().getId()==0){
			throw new RuntimeException("用户不存在！");
		}
		UserMerchantBO user = boResult.getData();

		Usertrace record = new Usertrace();
		record.setUserid(user.getId());
		record.setUsername(username);
		record.setUsertype(user.getUsertype());
		record.setSysName(BT.getConfig("SysName"));
		record.setTracecontent(tracecontent);
		record.setClientip(clientip);
		record.setCreateDate(new Date());

		usertraceMapper.insert(record);
	}

}
