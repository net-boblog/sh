<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
</head>
<body>
	<form id="jumpForm" action = "" method="post" target="_self">
		<input type="hidden" name="username" id="username" value=""/>
		<input type="hidden" name="from" id="from" value="sh" />
		<input type="hidden" name="mark" id="mark" value="" />
		<input type="hidden" name="nickname" id="nickname" value="" />
		<input type="hidden" name="redirectUrl" id="redirectUrl" value="" />
	</form>
</body>
    
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
	var url = '<%=request.getParameter("redirectUrl")%>'
</script>
<script src="./jumpToSShop.js" type="text/javascript"></script>

	
	