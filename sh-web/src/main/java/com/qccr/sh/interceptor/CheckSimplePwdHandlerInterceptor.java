package com.qccr.sh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.memberCenter.UserMerchantExt;
import com.qccr.sh.external.memberCenter.bo.UserMerchantBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

public class CheckSimplePwdHandlerInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserMerchantExt userMerchantExt;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //根据用户名查门店用户
		Result<UserMerchantBO> boResult = userMerchantExt.queryByUsername(request.getRemoteUser());
		if(boResult==null || boResult.isFailed() || boResult.getData()==null || boResult.getData().getId()==0){
			throw new RuntimeException("用户不存在！");
		}
		UserMerchantBO user = boResult.getData();

		String uri = request.getRequestURI();

		if (uri.indexOf("modify_password.s") < 0 && "e10adc3949ba59abbe56e057f20f883e".equals(user.getPassword())) {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", "-1");
			jsonObject.put("info", "您的密码过于简单，必须修改密码！");

			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonObject.toString());

			// response.sendRedirect(request.getContextPath()+"/page/login/modify_password.jsp?info=您的密码过于简单，请修改");
			return false;
		}

		return true;// 继续流程
	}

}
