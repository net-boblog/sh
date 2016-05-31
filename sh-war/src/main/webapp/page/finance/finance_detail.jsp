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
 
      
        <div class="h"></div>

      
   <div class="tab_order"> <a href="javascript:downloadfile()" class="download_a"> 下载订单数据</a><div style="clear:both;"></div>
          <div class="panes">
           <div class="pane" style="display:block; overflow:hidden; padding-bottom:20px;">
                <table class="tab_box" id="ordertable">
  <tr>
    <td class="td_title">订单号</td>
    <td class="td_title">订单内容</td>
    <td class="td_title">数量</td>
    <td class="td_title">客户信息</td>
    <td class="td_title">服务类型</td>
    <td class="td_title">结算费用</td>
    <td class="td_title">验证码</td>
    <td class="td_title">验证时间</td>
  </tr>
  <tr>
    <td class="td_content2">光大轮胎</td>
    <td class="td_content2">2</td>
    <td class="td_content2">谁谁谁 1452363534</td>
    <td class="td_content2">正常安装</td>
    <td class="td_content2">20元</td>
   <td class="td_content2">21434543</td>
   <td class="td_content2">2015/02/10  10:43</td>
  </tr>
</table>
 </div>
</div>
              </div>
    
              
             
  </div>

<input type="text" id="clear_id" value=<%=request.getParameter("clear_id") %>>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
 
 <form action="" method="post" id="fm1" target="fraSubmit">
</form>    
    
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script src="./finance_detail.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/My97DatePicker/WdatePicker.js"></script>
	
	