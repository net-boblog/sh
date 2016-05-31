<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>代收货订单</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
<link rel="alternate icon" type="image/png"
	href="../../images/logo_40.png">
<link href="../../css/link.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="../../css/common.css" />
<link rel="stylesheet" type="text/css" href="../../css/tikuan.css" />
   <link rel="stylesheet" type="text/css" href="/css/member.css"/>
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../js/basic.js"></script>
<script type="text/javascript" src="./order.js"></script>
<script type="text/javascript">
var ctx = '<%=ctx%>';
</script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>

</head>
<body>
	<div class="content">

		<jsp:include page="../addon/link.jsp"></jsp:include>
		<div class="box_right">
			<jsp:include page="../addon/head.jsp"></jsp:include>

			<div class="h"></div>
			<div class="wrap_right">
				<div class="title_mune">订单管理 > <a href="/page/order/list.s">代收货订单</a> > 订单详情</div>
				<div class="radiu order_status">
					当前订单状态：<span class="orange">${resultMap.statusName }</span>
				</div>

				<div class="radiu sta_down">
					<table width="100%" border="0" class="shop_table" id="shop_table">
						<thead class="sta_thead">
							<th align="left" class="p10" width="50%">商品</th>
							<th>数量</th>
							<th>价格</th>
							<th>服务费</th>
						</thead>
						<% int count = 1 ;%>
						<c:forEach items="${resultMap.goods }" var="goods">
						   	<tr>
							<td align="left" class="dbor" style="border-right: 0;">
								<div class="luntai_pic">
									<img src="${goods.img }" style="max-height: 81px;max-width: 81px;min-height: 81px;min-width: 81px"/>
								</div>
								<div class="luntai_text">
									<p>${goods.goodsName }</p>
								</div>
							</td>
							<td valign="top" class="dbor" style="border-right: 0;">${goods.saleNum }</td>
							<td valign="top" class="dbor"></td>
							<% if(count ==1){%>
							  <td align="center" rowspan="${resultMap.rowNum }" class="dbor" style="border-right: 0;"><b class="orange" >￥${resultMap.salePrice }</b></td>
							<% } %>
						</tr><% count = count+1 ;%>
						</c:forEach>
						<c:forEach items="${resultMap.server }" var="server">
						   	<tr>
							<td align="left" class="dbor" style="border-right: 0;">
								<div class="luntai_pic">
									<img src="${server.img }" style="max-height: 81px;max-width: 81px;min-height: 81px;min-width: 81px"/>
								</div>
								<div class="luntai_text">
									<p><span class="gray">${server.serverName}</span></p>
									<c:if test="${server.status ==1}">
									   <p><span class="red">待服务</span></p>
									</c:if>
									<c:if test="${server.status !=1}">
									   <p><span style="color: green;">已服务</span></p>
									</c:if>
								</div>
							</td>
							<td valign="top" class="dbor" style="border-right: 0;">${server.saleNum }</td>
							<td valign="top" class="dbor">${server.sprice }</td>
						</tr>
						</c:forEach>
					</table>
				</div>

				<div class="order_infor radiu">
					<h5 class="can_amount">
						<span>订单信息</span>
					</h5>
					<ul>
						<li><span>订单编号 :</span><b class="lan">${resultMap.order.no }</b></li>
						<li><span>配送方式 :</span>
						<c:if test="${resultMap.order.sendType==1}">配送到店</c:if>
						<c:if test="${resultMap.order.sendType==2}">送货上门</c:if>
						<c:if test="${resultMap.order.sendType==3}">无需配送</c:if>
						<li><span>下单时间 :</span><fmt:formatDate value="${resultMap.order.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></li>
					</ul>
				</div>

				<div class="order_infor radiu">
					<h5 class="can_amount">
						<span>收货人信息</span>
					</h5>
					<ul>
						<li><span>联系人 :</span>${resultMap.order.buyerName }</li>
						<li><span>联系电话 :</span>${resultMap.order.buyerPhone }</li>
						<li><span>用户留言 :</span>${resultMap.order.buyerNote }</li>
					</ul>
				</div>

				<div class="order_infor radiu">
					<h5 class="can_amount">
						<span>物流信息</span>
					</h5>
					<ul>
					   <c:if test="${resultMap.logistics.no!=null && resultMap.logistics.no!= '' }">
					      <li><span>物流公司 :</span>${resultMap.logistics.company }</li>
						  <li><span>物流单号 :</span>${resultMap.logistics.no }</li>
						  <c:if test="${resultMap.kuaiDi.data !=null}">
						  <% int detailCount = 1;%>
							  <c:forEach items="${resultMap.kuaiDi.data }" var="kuaidi" varStatus="status">
							     <li><span><% if(detailCount==1){%>物流信息<%} %></span>
							     <b <c:if test="${fn:length(resultMap.kuaiDi.data) ==(status.index+1)}">class="orange"</c:if> >${kuaidi.time } ${kuaidi.context }</b></li>
							     <% detailCount++;%>
							  </c:forEach>
						  </c:if>
					   </c:if>
					   <c:if test="${resultMap.logistics.no==null}"><b class="orange">暂无物流信息</b></c:if>
					</ul>
				</div>
				<c:if test="${resultMap.order.orderStatus==1 }">
				   <div class="infor_bottom">
					  <a href="javascript:confirm_btn(${resultMap.orderId });" class="queren_btn">确认收货</a>
				   </div>
				</c:if>
			</div>

		</div><jsp:include page="../common/popup.jsp"></jsp:include><input type="hidden" id="confirm_orderId">
</body>
<script type="text/javascript">
			$(function() {
				var times ;	
					$("#confirm_window_href").click(function(){
						var orderId = $("#confirm_orderId").val();
						$.ajax({
							url : "/page/order/orderConfirm.s",
							data : {
								"orderId" : orderId
							},
							success : function(json) {
								$("#Confirm_goods_div").hide();
								if (json.success) {
									$("#Confirm_goods_success_div").show();
									times = setInterval(function(){
										window.location.reload();
									},3000);
								} else {
									$("#fail_msg").html(json.msg);
									$("#update_fail").show();
								}

							},
							error : function() {
								$("#fail_msg").html("确认收货失败：服务器异常");
								$("#update_fail").show();
							}
						});
					});
			
			});
			
		</script>
