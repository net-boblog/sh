package com.qccr.sh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CheckSimplePwdFilter implements Filter  {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/page/login/modify_password.jsp");
		//dispatcher.forward(request, response); 
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
