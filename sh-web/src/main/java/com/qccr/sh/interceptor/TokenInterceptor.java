package com.qccr.sh.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qccr.sh.common.Token;

/**
 * <p>
 * 防重复提交拦截器
 * </p>
 * @author magg
 * @date 2015-10-30
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
	/*
	 * 
	 * 第三个参数代表的是当前请求对应的处理类的对应的方法（Contorller）
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation =method.getAnnotation(Token.class);
			if(annotation!=null){
				boolean add =  annotation.add();
				// 如果注解是add=true 说明是信息提交页面，需要生成一个唯一的id
				if(add){
					request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
				}
				// 如果注解是addDo=true 是提交页面，第一次提交成功，清除session中token
				 boolean addDo = annotation.addDo();
	                if (addDo) {
	                    if (isRepeatSubmit(request)) {
	                        return false;
	                    }
	                    request.getSession(false).removeAttribute("token");
	                }
			}
			
		}
		
		
		return true;
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
