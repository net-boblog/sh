<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>重置密码</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../../css/login.css"/>
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script src="<%=ctx%>/common/jquery/jquery.cookie.js" type="text/javascript"></script>
</head>

<body class="setting_bg">
<div class="h_top_wrap"><div class="header"><div class="logo"><img src="../../images/login_logo.png"/><p></p></div><div class="h_number"><img src="../../images/phone_num.png"/></div></div></div>
<div class="big_wrap yj">
<div class="yz_step"><img src="../../images/step2.png"/></div>
<div class="yz_box">
<form id="checkform" action="password_reset.s" method="post">
 <table width="100%" border="0" class="amend_table">
  <tr>
    <td valign="top" class="gray"><span>输入新密码</span></td>
    <td><input id="password" name="password" type="password" class="apply_inpu" placeholder="请输入新密码"/></td>
  </tr>
  <tr>
    <td valign="top" class="gray"><span>确认密码</span></td>
    <td valign="top"><input id="sure_password" name="sure_password" type="password" class="apply_inpu" placeholder="请再次输入密码"/>
    </td> 
  </tr>
  <tr>
		<td align="right"><input type="hidden" id="username" name="username" value="${username}"/></td>
	</tr>
  <tr>
    <td>&nbsp;</td>
    <td><a id="resetsure" href="#" class="reset_btn next_step_btn">下一步</a></td><!--不可用状态class用reset_btn   可用状态classf将reset_btn替换成ind_btn-->
  </tr>
</table>
</form> 
</div>
</body>

<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script src="./apply_join.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/md5.js"></script>
</html>
