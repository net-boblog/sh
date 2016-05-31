<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>服务验证</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
<link href="/css/validation_query.css" rel="stylesheet" />
<link href="/css/link.css" rel="stylesheet" />
 <link href="/css/common.css" rel="stylesheet" />
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/basic.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
</head>
<body>
 <div class="content">
 
    <jsp:include page="../addon/link.jsp"></jsp:include>

    <div class="box_right">
      <jsp:include page="../addon/head.jsp"></jsp:include>
      
        <div class="h"></div>
        <div class="h"></div>
        <div class="h"></div>
        <div class="h"></div>
        <div class="cont_right">
 
          <div class="search_box">
            <input class="search_kk" id="sms_code" placeholder="请输入正确的服务验证码"  autocomplete="off" type="text">
            <input class="search_button" value="验  证" type="button">
             <p>遇到问题？联系客服：400-699-0000</p>
          </div>
          
              </div>
              
             
    </div>
  </div>

<input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>

<div class="dialog_bj"></div>
<div class="DialogDiv_0">
  <div class="tck_title">
    <div class="tck_bt">
      <h2>确认验证订单信息</h2>
    </div>
      <div class="close"></div>
  </div>
    <div class="tck_content">
      <ul>

         <li class="row"> <label class="lab_bt">订单内容：</label><p id="content"></p></li>
         <li class="row"> <label class="lab_bt">客户信息：</label><p id="userinfo"></p></li>
         <li class="row"> <label class="lab_bt">服务类型：</label><p id="server_type"></p></li>
         <li class="row"> <label class="lab_bt">客服备注：</label><p id="seller_note"></p></li>
          
      </ul>
        <div class="h"></div>
        <div class="btn_box">
   <input id="button_cancel" class="button_cancel" value="取 消" type="button">&ensp;&ensp;&ensp;&ensp;
   <input id="button_sure" class="button_sure" value="验  证" type="button">
    </div>
    </div>
</div>

<div class="DialogDiv_1">
  <div class="tck_title">
    <div class="tck_bt">
      <h2>验证成功</h2>
    </div>
      <div class="close"></div>
  </div>
    <div class="tck_content">
      <ul>

         
          
      </ul>
        <div class="h"></div>
        <div class="btn_box">
   <input id="button_vieworder" class="button_sure" value="查看订单" type="button">&ensp;&ensp;&ensp;&ensp;
   <input id="button_continue" class="button_cancel" value="继续验证" type="button">
    </div>
    </div>
</div>

<div class="up_wrap" style="display:none;">
  <div class="up_bg"></div>
  <div class="up_pic">
  <div class="up_close"><img src="../../images/close-btn.png"/></div>
<a onclick="toSShop()" class="look-btn"><img src="../../images/look-btn.png"/></a></div>
</div> 

<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
 <script src="./validation_query.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>

	