<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//session.invalidate();

	//System.out.println(application.getInitParameter("serverName") + request.getContextPath());
	response.sendRedirect(application.getInitParameter("casServerLogoutUrl") + "?service=" + application.getInitParameter("serverName")
			+ request.getContextPath());
%>
	