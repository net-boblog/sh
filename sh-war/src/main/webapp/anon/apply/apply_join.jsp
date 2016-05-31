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
<script type="text/javascript">
$(function(){
    var url=window.location.href;
    if(url.lastIndexOf("https")>=0){
        url=url.replace("https","http");
        window.location.href=url;
    }

	if($("#message").text()==""){
		$("#message").hide();
	}else{
		
		alert($("#message").text());
		location.href="../login/login.jsp";
	}
	
})
</script>
<title>门店加盟申请</title>
<meta charset="UTF-8">
</head>

<body class="setting_bg">
<jsp:include page="../../page/addon/login_head.jsp"></jsp:include>
<form id="loginForm" action="apply_joinDo.s" method="post">
<input type="hidden" name="token" value="${token}" />
<div class="big_wrap yj">
<h5 class="can_amount"><span>门店加盟申请</span></h5>

    <span style="display:none" class="orange" id="message">${message}</span>

<table width="60%" border="0" class="apply_table">
	
  <tr>
    <td class="gray"><span class="orange">* </span>加盟门店名称：</td>
    <td colspan="3"><input id="storename" name="storename" type="text" class="apply_inpu"/></td>
    </tr>
  <tr>
    <td class="gray"><span class="orange">* </span>联系人：</td>
    <td colspan="3"><input id="store_owner" name="store_owner" type="text" class="apply_inpu"/></td>
    </tr>
  <tr>
     <td class="gray"><span class="orange">* </span>联系电话 ：</td>
    <td colspan="3"><input id="store_tel" name="store_tel" type="text" class="apply_inpu"/></td>
    </tr>
  <tr>
     <td class="gray"><span class="orange">* </span>所在区域 ：</td>
    <td colspan="3">
     <select class="p_area1 pa1" name="province" id="province"  onchange="citys(this.value)"><option value=''>选择省份</option></select>
     <select class="p_area1 pa2" name="city" id="city" onchange="areas(this.value)"><option value=''>选择城市</option></select>
     <select class="p_area1 pa3" name="areaId" id="areaId" onchange="stores(this.value)"><option value=''>选择区域</option></select>
     
    </td>
    </tr>
  <tr>
     <td class="gray"><span class="orange"> </span>具体地址 ：</td>
    <td colspan="3"><input type="text" name="store_addr_detail" type="text" class="apply_inpu"/></td>
    </tr>
  <tr>
    <td class="gray"><span class="orange"> </span>门店面积 ：</td>
    <td colspan="3"><input name="store_square" type="text" class="apply_inpu"/></td>
  </tr>
  <tr>
   <td class="gray"><span class="orange"> </span>门店员工数 ：</td>
    <td colspan="3"><input  name="store_staff_num" type="text" class="apply_inpu"/></td>
  </tr>
    <tr>
    <td class="gray"><span class="orange"> </span>营业年限 ：</td>
    <td colspan="3"><input name="store_age" type="text" class="apply_inpu"/></td>
  </tr>
  <tr>
   <td class="gray"><span class="orange"> </span>主营品牌 ：</td>
    <td colspan="3"><input name="store_main_brand" type="text" class="apply_inpu"/></td>
  </tr>
  <tr style=" height:45px; line-height:45px;" class="jiameng_input">
   <td class="gray"><span class="orange"> </span>希望合作的项目：</td>
    <td><input name="citem" type="checkbox" value="CAR_SPA" />汽车美容 </td>
    <td><input name="citem" type="checkbox" value="CAR_MAINTAIN" />保养服务</td>
    <td><input name="citem" type="checkbox" value="CAR_TYRE" />轮胎服务</td>
  </tr>
  <tr>
    <td valign="top" class="gray" style="padding-top:12px;"><span class="orange"> </span>自身服务内容 ：</td>
    <td>
    <ul class="jiameng_input">
      <li><input name="service" type="checkbox" value="SMALL_MAINTAIN" />小保养</li>
      <li><input name="service" type="checkbox" value="BIG_MAINTAIN" />大保养</li>
      <li><input name="service" type="checkbox" value="ENGINE_CLEAN" />发动机清洗</li>
      <li><input name="service" type="checkbox" value="CONDITIONER_FILTER" />空调滤清器</li>
      <li><input name="service" type="checkbox" value="WIPER_BLADE" />雨刮片</li>
      <li><input name="service" type="checkbox" value="BRAKE_OIL" />刹车油</li>
      <li><input name="service" type="checkbox" value="CAREFUL_WASH" />精细洗车</li>
      <li><input name="service" type="checkbox" value="TIRE_ROTATION" />轮胎换位</li>
    </ul>
    </td>
    <td> 
    <ul>
      <li><input name="service" type="checkbox" value="OIL_FILTER" />机油滤清器</li>
      <li><input name="service" type="checkbox" value="INDOOR_CLEANING" />室内清洗</li>
      <li><input name="service" type="checkbox" value="CONDITIONER_PIPE_CLEAN" />空调管路清洗</li>
      <li><input name="service" type="checkbox" value="CONDITIONER_REFRIGERANT" />空调制冷剂</li>
      <li><input name="service" type="checkbox" value="BRAKE_PADS" />刹车片</li>
      <%--<li><input name="service" type="checkbox" value="14" />大保养</li>--%>
      <li><input name="service" type="checkbox" value="WAX" />打蜡</li>
      <li><input name="service" type="checkbox" value="WHEEL_ALIGNMENT" />四轮定位</li>
    </ul>
   </td>
    <td>
    <ul>
      <li><input name="service" type="checkbox" value="ENGINE_OIL" />机油</li>
      <li><input name="service" type="checkbox" value="CONDITIONER_CLEAN" />空调清洗</li>
      <li><input name="service" type="checkbox" value="THROTTLE_CLEAN" />节气门清洗</li>
      <li><input name="service" type="checkbox" value="PM25_FILTER" />PM2.5滤芯</li>
      <li><input name="service" type="checkbox" value="BRAKE_DISC" />刹车盘</li>
      <li><input name="service" type="checkbox" value="ORDINARY_WASH" />普通洗车</li>
      <li><input name="service" type="checkbox" value="TIRE_INSTALLATE" />轮胎安装</li>
    </ul>
    </td>
  </tr>

</table>
 <div class="down_btn_tr"><a href="#" id="submitbtn"  class="find_btn">提&nbsp;交</a><a href="../login/login.jsp" class="reset_btn">取&nbsp;消</a></div>

	<input type="hidden" value="" name="itemid" id="itemid"/>
	<input type="hidden" value=""  name ="serviceid" id="serviceid"/>
</div>
<div class="dhig"></div>
</form>
</body>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script type="text/javascript" src="./apply_join.js"></script>
<script type="text/javascript" src="../../common/md5.js"></script>
</html>
