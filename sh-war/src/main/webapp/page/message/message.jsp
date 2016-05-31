<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme() +"://" + 
  request.getServerName() + ":" +
          request.getServerPort() + 
          path +"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common.css"/>
<link href="<%=basePath %>css/link.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/tikuan.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/page.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/member.css"/>
<script type="text/javascript" src="<%=basePath %>common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/basic.js"/></script>
<script type="text/javascript" src="<%=basePath %>js/page-control.js"/></script>
<title>消息中心</title>
</head>
<body class="content">
  <jsp:include page="../addon/link.jsp"></jsp:include>
  <div class="box_right">
 <jsp:include page="../addon/head.jsp"></jsp:include>
 <div class="wrap_right">
  <div class="collecting_cargo radiu mt20">
   <h5 class="can_amount">消息中心</span></h5>
   <div class="message_num">共 <span id="countMessageNum">0</span> 条 | <span class="orange" id="unredaMsgNum">0</span> 条未读 </div>
   <table width="100%" border="0" class="tk_table message_table">
 	
</table>
 <div class="page" style="margin-top:3px;">  </div>
  </div>
</div>
<input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
<input type="hidden" id="smstime_updown" value=0>
<script type="text/javascript">
	var baseUrl='<%=basePath %>';
</script>

<script type="text/javascript" src="message.js?v=20160505"/></script>
</div>
</body>
</html>