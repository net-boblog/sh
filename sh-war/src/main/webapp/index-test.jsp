<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>特维轮核销系统测试页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<link href="<%=ctx%>/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h3 class="page-header">您已启动核销系统</h3>
		
		<a href="./page/order/order_query.jsp">主页！！！！！！</a><br/>
		
		<br>
		<!-- 
		<a href="http://192.168.2.126:8083/cas/logout?service=http://192.168.2.99:8080/hexiao/index.jsp">退出</a><br/>
		
		<a href="common/logout.jsp">安全退出</a><br/>
 		-->
		<a href="./test/t2.s">测试连接</a><br>
		
		<a href="./testpage/index.html">模板页面</a><br>

		
	  
	  <a href="./goods/add.s">增加我的商品</a><br>
	  <a href="./page/goods/goods_query.jsp">我的商品</a><br>
	  <a href="./page/validation/validation_query.jsp">服务验证</a><br>
	  

	   <a href="./page/order/order_query.jsp">查询订单</a><br>

	  
	  <a href="./page/finance/finance_query.jsp">财务管理</a><br>
	  
	</div>

	<script src="<%=ctx%>/common/jquery/jquery-1.11.1.js"></script>
	<script src="<%=ctx%>/common/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var ctx = '<%=ctx%>';
	</script>
	<script type="text/javascript">
/*	function onBridgeReady(){
	     document.addEventListener('WeixinJSBridgeReady', function onBridgeReady()  {
	 WeixinJSBridge.call('hideOptionMenu');
	 });
	}

	if (typeof WeixinJSBridge == "undefined"){
	    if( document.addEventListener ){
	        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	    }else if (document.attachEvent){
	        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	    }
	}else{
	    onBridgeReady();
	}
*/	
	
	
	
	</script>
	
	
	
	