<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../../css/login.css"/>
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script src="<%=ctx%>/common/jquery/jquery.cookie.js" type="text/javascript"></script>

<title>验证账号</title>
<meta charset="UTF-8">
</head>

<body class="setting_bg">
<jsp:include page="../../page/addon/login_head.jsp"></jsp:include>

<div class="big_wrap yj">
<div class="yz_step"><img src="../../images/step1.png"/></div>
<div class="yz_box">

<form id="checkform" action="password_reset.s" method="post">
 <table width="100%" border="0" class="amend_table">
 <tr  style="display:none;padding-top:0;" id="perrow_tishi">
 <td>&nbsp;</td>
 <td><div  class="perrow_tishi" ><img src="../../images/yichang_icon.png">用户名密码不匹配！</div></td></tr>
  <tr>
    <td valign="top" class="gray"><span>账号</span></td>
    <td><input id="username" name="username" type="text" class="apply_inpu" placeholder="请输入账号"/> <span style="display:none" class="date_prompt" id="date_prompt"><img src="../../images/yichang_icon.png"/>请输入账号 </span></td>
  </tr>
  <tr>
    <td valign="top" class="gray"><span>验证码</span></td>
    <td valign="top"><input id="identifying_code" name="identifying_code" type="text" class="apply_inpu" placeholder="请输入验证码"/><a id="sendcode" href="#" class="find_btn find_yzm_ban">发送验证码</a>
    <p class="yzm_text"><span id="sendmes"></span></p>
    </td> 
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><a href="#" id="nextbutton" class="reset_btn next_step_btn">下一步</a></td>
  </tr>
</table>
</form>
</div>
</div>
</body>


<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script src="./apply_join.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/md5.js"></script>

</html>
