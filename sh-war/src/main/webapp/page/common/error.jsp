<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>错误提示</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
    <link href="<%=ctx%>/css/link.css" rel="stylesheet" />
    <link href="<%=ctx%>/css/common.css" rel="stylesheet" />
    <link href="<%=ctx%>/css/tikuan.css" rel="stylesheet" />
</head>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<body style="background:#f0f3f7;">
<div class="content">
   <jsp:include page="../addon/link.jsp"></jsp:include>
   <div class="box_right">
     <jsp:include page="../addon/head.jsp"></jsp:include>
      <div class="wrap_right">
      <div class="show_error">
     </div>
      </div>
   </div>
</div>
    
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script type="text/javascript" src="/js/basic.js"></script>
<script src="./modify_account.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
	
	