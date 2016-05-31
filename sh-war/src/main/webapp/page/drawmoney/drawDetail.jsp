<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>我要提款</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
  <!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
  <link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
  <link href="../../css/link.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="/css/common.css"/>
  <link rel="stylesheet" type="text/css" href="/css/tikuan.css"/>
  <link href="<%=ctx%>/css/page.css" rel="stylesheet" />
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="content">

  <jsp:include page="../addon/link.jsp"></jsp:include>

  <div class="box_right">
    <jsp:include page="../addon/head.jsp"></jsp:include>

    <div class="wrap_right">
      <div class="title_mune">财务管理 > 我要提款</div>
      <div
              <c:choose>
                <c:when test="${isBalance}">
                  class="jiesuan_cg radiu"
                </c:when>
                <c:otherwise>
                  class="jiesuan_process radiu"
                </c:otherwise>
              </c:choose>
      >
        <p class="start_date">${createTime}</p>
        <p class="end_date">${clearTime}</p>
      </div>
      
	   <div class="radiu">
	      <div class="jiesuan_money_box">
	        <span class="js_amount">${drawMoney}</span>
	        <span class="dd_amount">${orderDrawMoney}</span>
	        <span class="jl_amount">${rewardMoney}</span>
	      </div>
     </div>
      <div class="tk_records radiu">
        <div class="service_object">
        <table width="100%">
        <tr>
         <td width="400px">服务项目
          <select  class="s_obj" id="serviceParentId" onchange="serviceSelectOnchange();">
            <option value="-1" name="oneCat">全部</option>
          </select>
          <select  class="s_obj"  id="serviceId">
            <option name="twoCat" value="-1">全部</option>
          </select></td>
         <td><a href="#" class="look_btn" id="look_btn" onclick="search_boxes()">查&nbsp;找</a></td>
         <td style="text-align:right;"><div class="all_order">共<span id="much_order"></span>个订单</div></td>
            <td align="right" style="padding-right: 10px;">
                <input id="data_out" type="button" value="导出数据" class="daochu" onclick="downloadOrders(this)"/>
            </td>
        </tr>
        </table>
        </div>
        <table width="100%" border="1" class="tk_table" id="xq_table">
          <thead>
          <th>订单号</th>
          <th>服务项目</th>
          <th>数量</th>
          <th>金额</th>
          <th>验证码</th>
          <th>验证时间</th>
          </thead>
        </table>
         <div id="nodata" style="display:none;">暂无数据</div>
          <div class="page clearfix"> </div>
      </div><!--tk_records-->
    </div><!--wrap_right-->

  </div>

</div>

<input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
<input type="hidden" id="smstime_updown" value=0>
<input type="hidden" id="clearId" value="${clearId}">

<form action="" method="post" id="fm1" target="fraSubmit">
</form>

<script type="text/javascript">
  var ctx = '<%=ctx%>';
  $(function(){
	  $("#xq_table tr:even").css("background","#f7f9fb");
  });
</script>
<script type="text/javascript" src="/js/basic.js"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=ctx%>/page/drawmoney/drawDetail.js"></script>
<script type="text/javascript" src="/js/page-control.js"></script>

