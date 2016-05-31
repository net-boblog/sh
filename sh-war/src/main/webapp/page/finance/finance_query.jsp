<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>财务管理</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
<link href="../../css/link.css" rel="stylesheet" />
<link href="/css/common.css" rel="stylesheet" />
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/basic.js"></script>
</head>
<body>
 <div class="content">
 
    <jsp:include page="../addon/link.jsp"></jsp:include>

    <div class="box_right">
      <jsp:include page="../addon/head.jsp"></jsp:include>
      
        <div class="h"></div>
        <div class="cont_right">
        <div class="search_box">
        <div class="tck_content">
        <ul>
         <li class="row">
       结算时间&ensp;&ensp;<input type="text" id="js_date_start" value="" placeholder="请输入日期" class="laydate-icon" onclick="laydate()">
        
       &ensp;到&ensp; <input type="text" id="js_date_end" value="" placeholder="请输入日期" class="laydate-icon" onclick="laydate()">
        
        &ensp;&ensp;&ensp;结算状态 
        		<select class="sel_con2" id="js_status" >
        						<option value="0"  selected="selected">全部状态</option>
								<option value="2">结算中</option>
								<option value="3">已结算</option>
							</select> <input style="height:29px;" class="search_button" value="搜  索" type="button">
        </li>
          <li class="row">

        </li>

        </ul>
        </div></div>
              <div class="tab_order">
          <div class="panes">
           <div class="pane" style="display:block; overflow:hidden; padding-bottom:20px;">
                <table class="tab_box" id="ordertable">
  <tr>
    <td class="td_title">结算编号</td>
    <td class="td_title"><p style="display:inline">结算时间&ensp;&ensp;</p><input type="button" class="smstime_updown down"/></td>
    <td class="td_title">结算金额(元)</td>
    <td class="td_title">结算状态</td>
    <td class="td_title">收款账号</td>
    <td class="td_title">详情</td>
  </tr>
  
</table>
<div align="center"> &ensp;&ensp;<span id="res" style="color: red;text-align: center;font-size: 18px" ></span></div>
 <div class="page"> </div>
 </div>
</div>
              </div>
              </div>
              
             
    </div>
  </div>

<input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
<input type="hidden" id="smstime_updown" value=0>
    
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script src="./finance_query.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/laydate/laydate.js"></script>
	
	