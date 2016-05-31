<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme() +"://" + 
  request.getServerName() + ":" +
          request.getServerPort() + 
          path +"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../../css/login.css"/>
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){

	var protocol =document.location.protocol;
	if(protocol=="https:"){
		if(!${useHttpsUrl}){
			window.location.href="http"+window.location.href.substring(5);
		}
	}
	else{
		if(${useHttpsUrl}){
			window.location.href="https"+window.location.href.substring(4);
		}
	}

	if($(".perrow_tishi").text()==""){
		$(".perrow_tishi").hide();
	}else{
		$(".perrow_tishi").show();
	}
	
})
</script>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script type="text/javascript" src="../../common/md5.js"></script>
<script src="./login.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<title>登录</title>
<meta charset="UTF-8">
</head>

<body>
<jsp:include page="../../page/addon/login_head.jsp"></jsp:include>
<form id="loginForm" action="login.s" method="post">
	<div class="login_box">
	  <div class="login_main">
	    <div class="denglc">
	    <h5>商家登录</h5>
	    <input type="hidden" id="enPassword" name="enPassword" />
	    <div style="display:none" class="perrow_tishi"><img src="../../images/yichang_icon.png"/>${message}</div>
	    <div class="zhanghao"><input  type="text" class="yj" id="username" name="username"/></div>
	    <div class="password"><input type="password" class="yj" id="password" name="password"/></div>
	    <input type="submit" class="log_btn yj" value="登录" >
	    <div class="log_bottom"><a href="../apply/apply_join.s" class="no_account">没有账号？申请加盟</a><a href="../apply/check_username.jsp" class="forget_password">忘记密码？</a></div>
	    </div>
	  </div>
	</div>
</form>
</body>
</html>
