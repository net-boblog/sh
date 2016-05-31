<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单管理</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
<link href="./order_query.css" rel="stylesheet" />
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
         <input id="searchinfo" class="search_kk" placeholder="输入验证码/买家姓名/手机号" maxlength="40" autocomplete="off" type="text" value=<%=request.getParameter("sms_code")==null?"":request.getParameter("sms_code") %>>
            <input class="search_button" value="搜  索" type="button">  &ensp;&ensp; <a href="javascript:hideshowccc()" id="searchccc" style="color: #323232;">精简搜索</a>
         </li>
        
         <li class="row" id="ccc"><span id="mendians" style="display: none;">
  	门店&ensp;&ensp;
        		<select class="sel_con2" id="mendian" >
        						<option value="0" selected="selected">全部门店</option>
							</select>   </span>    
         
        服务类型&ensp;&ensp;
        		<select class="sel_con2" id="service_type">
        						<option value="0" selected="selected">全部类型</option>
							</select>
       &ensp;&ensp;&ensp;&ensp;验证时间&ensp;&ensp;<input type="text" id="js_date_start" placeholder="请输入日期" class="laydate-icon" onclick="laydate()">
        
       &ensp; 到&ensp; <input type="text" id="js_date_end" placeholder="请输入日期" class="laydate-icon" onclick="laydate()">
        
        &ensp;&ensp;&ensp;结算状态 
        		<select class="sel_con2" id="js_status" >
        						<option value="-1"  selected="selected">全部状态</option>
								<option value="0">待确认</option>
								<option value="1">可结算</option>
								<option value="2">结算中</option>
								<option value="3">已结算</option>
								<option value="4">异常单</option>
							</select> 
							<input style="height:29px;" class="search_button" value="搜  索" type="button">
        </li>

        </ul>
        </div></div>
       
              <div class="tab_order"> <a class="download_a" href="javascript:downloadfile()"> 下载订单数据</a><div style="clear:both;"></div>
          <div class="panes">
           <div class="pane" style="display:block; overflow:hidden; padding-bottom:20px;">
                <table class="tab_box" id="ordertable">
  <tr>
    <td class="td_title">订单号</td>
    <td class="td_title">订单内容</td>
    <td class="td_title">数量</td>
    <td class="td_title">客户信息</td>
    <td class="td_title">服务类型</td>
    <td class="td_title">验证码</td>
    <td class="td_title"><p style="display:inline">验证时间&ensp;&ensp;</p><input type="button" class="smstime_updown down"/></td>
    <td class="td_title">结算状态</td>
  </tr>
</table>
 <div align="center"> &ensp;&ensp;<span id="res" style="color: red;text-align: center;font-size: 18px" ></span></div>
 <div class="page">  </div>
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
    
<form action="" method="post" id="fm1" target="fraSubmit">
</form>       
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script src="./order_query.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/laydate/laydate.js"></script>
	
	