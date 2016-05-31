/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.qccr.sh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * Interceptor - 用户权限
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String username =null;
		Subject subject = SecurityUtils.getSubject();
		PrincipalCollection collection = subject.getPrincipals();
		if (null != collection && !collection.isEmpty()) {
			username=(String) collection.iterator().next();
		}
		if(username==null){
			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
				response.addHeader("loginStatus", "accessDenied");
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return false;
			} else {
		    	response.sendRedirect(request.getContextPath()+"/anon/login/login.jsp");
				return false;
			}
		}
		return true;

	}

	
}