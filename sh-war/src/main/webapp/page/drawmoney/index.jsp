<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() +"://" +
          request.getServerName() + ":" +
          request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
  <title>我要提款</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
  <!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
  <link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
  <link href="/css/link.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="/css/common.css"/>
  <link rel="stylesheet" type="text/css" href="/css/tikuan.css"/>
   <link rel="stylesheet" type="text/css" href="/css/member.css"/>
  <link href="<%=basePath%>/css/page.css" rel="stylesheet" />
  <script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
  <script type="text/javascript">
  var ctx = '<%=ctx%>';
</script>
  <script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="<%=ctx%>/page/drawmoney/inde_page.js"></script>
</head>
<body>
<div class="content">

  <jsp:include page="../addon/link.jsp"></jsp:include>

  <div class="box_right">
    <jsp:include page="../addon/head.jsp"></jsp:include>

    <div class="wrap_right">
      <div class="title_mune">财务管理 > 我要提款</div>
      <div class="tikuan_money clearfix">
        <div class="tikuan_left radiu">
          <h5 class="can_amount">可提款金额<span>（元）</span></h5>
          <div class="hao_money">
            <h1>${orderCanDraw}</h1>
            <a href="/page/drawmoney/canDrawDetail.s" class="look_mingxi">查看明细</a>
            <a href="#" reason="${reason}"  id="disable_tk" 
            <c:choose>
		   <c:when test="${isCanDraw}">  
		       class="can_tk_btn"  onclick="tk_show();"       
		   </c:when>
		   <c:otherwise> 
		       class="can_tk_btn"  onclick="tk_disable();"       
		   </c:otherwise>
		</c:choose>
            >我要提款</a>
          </div>
          <div class="tikuan_rule"><span>提款规则 :</span>${rule}</div>
        </div>
        <div class="tikuan_right radiu">
          <h5 class="can_amount">暂不可提款金额<span>（元）</span></h5>
          <div class="wait_money">
            <div class="wm_left">
              <p>待审核金额</p>
              <h2>${waitAudit}</h2>
            </div>
            <a href="/page/serverOrder/list.s?audit_status=2">查看详情</a>
          </div>

          <div class="wait_money">
            <div class="wm_left">
              <p>异常金额</p>
              <h2>${exception}</h2>
            </div>
            <a href="/page/serverOrder/list.s?audit_status=1">查看详情</a>
          </div>
        </div>
      </div>

      <div class="tk_records radiu">
        <div class="can_amount">提款记录</div>
        <div class="tk_time">
        <table><tr>
        <td><span>提款时间
          <input type = "hidden"  name="rank_hidden" id="rank_hidden" value="0" />
          <input type = "hidden"  name="page_offset" id="page_offset" value="0" />
          <!--  <input type = "hidden"  name="pageSize" id="pageSize" value="15" />-->
          <input type = "hidden"  name="page_size" id="page_size" value="15" />
          <input type="text" class="Wdate" id="beginTime" name="beginTime" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})"
           style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" /> --
          <input type="text" class="Wdate" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})"
          style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" /></td>
        <td width="300px"><span style="padding-left:70px;">状态</span>
          <select class="tk_selec" id ="status">
            <option value="-1">全部</option>
            <option value="2">结算中</option>
            <option value="3">已结算</option>
          </select></td>
        <td><a href="#" class="look_btn" id="look_btn" onclick="search_boxes();">查&nbsp;找</a></td>
        </tr></table>
        
         
       
        </div>
        <table width="100%" border="1" class="tk_table tkunder" id="data_table">
          <thead>
          <th>结算编号</th>
          <th>提款时间</th>
          <th>结算时间</th>
          <th>结算金额</th>
          <th>状态 </th>
          <th>收款账号</th>
          <th>操作</th>
          </thead>
        </table>
        <div class="page clearfix"> </div>
      </div>
    </div>
  </div>
<jsp:include page="../common/popup.jsp"></jsp:include>
</div>

<!--  <input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
<input type="hidden" id="smstime_updown" value=0>-->

<form action="" method="post" id="fm1" target="fraSubmit">
</form>
 <script type="text/javascript" src="/js/basic.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/page-control.js"></script>

