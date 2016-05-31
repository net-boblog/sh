package com.qccr.sh.controller;


import javax.servlet.http.HttpServletRequest;
import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class BaseModule {
	@Autowired
    protected HttpServletRequest   request;

	@Autowired
	private UserMerchantExt userMerchantExt;

	@Value("#{properties['static_url1']?: 'http://static.qichechaoren.com/upload/'}")
	protected String staticUrl1; //图片访问地址1

	@Value("#{properties['static_url2']?: 'http://static.qichechaoren.com/'}")
	protected  String staticUrl2; //图片访问地址2

	@Value("#{properties['useHttpsUrl']?: 'false'}")
	protected String useHttpsUrl; //https 请求开关

	public UserMerchantBO getLoginUser(){
		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(request.getRemoteUser());
		if(boResult.isFailed() || boResult.getData()==null){
			throw new RuntimeException( "用户查询错误！"+
					(boResult.getStatusText()!=null ? boResult.getStatusText():"") );
		}
		return boResult.getData();
	}

}
