<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>代收货订单</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
	<link href="<%=ctx%>/css/link.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css" />
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css"/>
	<link href="<%=ctx%>/css/page.css" rel="stylesheet" />
	<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=ctx%>/js/basic.js"></script>
	<script type="text/javascript" src="<%=ctx%>/page/order/order.js"></script>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	%>
	<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
</head>
<body>
<div class="content">
	<jsp:include page="../addon/link.jsp"></jsp:include>
	<div class="box_right">
		<jsp:include page="../addon/head.jsp"></jsp:include>
		<div class="wrap_right">
			<div class="title_mune">订单管理 > 代收货订单</div>
			<div class="collecting_cargo radiu" style="padding-top:15px;">
				<table width="80%" border="0" class="coll_table">
					<tr>
						<td><label>订单号</label><input type="text" class="coll_input" value="${paramMap.orderCode }"
													 name="orderCode" id="orderCode" /></td>
						<td><label>订单状态</label><select class="coll_sele1"
													   name="status" id="status">
							<c:forEach items="${paramMap.statusMap}" var="item">
								<option value="${item.key}" <c:if test="${paramMap.orderStatus==item.key}">selected="selected"</c:if>>${item.value}</option>
							</c:forEach>
						</select></td>
						<td class="down_btn_tr"><a href="#" class="look_btn" id="search_href" style="margin-top:0;" onclick="search_boxes();">查&nbsp;找</a></td>
					</tr>
				</table>
			</div>
			<div class="shop_list radiu" style="padding-bottom:0;">
				<table width="100%" border="0" class="shop_table" id="xq_table">
					<thead>
					<th align="left" width="50%" class="p10">商品</th>
					<th>数量</th>
					<th>服务费</th>
					<th>订单状态</th>
					<th>操作</th>
					</thead>
					<%--<c:forEach items="${resultList}" var="result">
						<tr>
							<td colspan="5" class="order_number_td p10"><span
									class="gray">订单号：</span><span class="lan"><a href="/page/order/order_detail.s?orderId=${result.orderId }">${result.no}</a></span>
								<span class="gray" style="padding-left: 28px;">下单时间：</span>${result.create_time }</td>
						</tr>
						<% int count = 1 ;%>
						<c:forEach items="${result.goods}" var="goods">
							<tr>
								<td align="left" class="dbor" style="border-right: 0;">
									<div class="luntai_pic" >
										<img src="${goods.img }" style="max-height: 81px;max-width: 81px;min-height: 81px;min-width: 81px"/>
									</div>
									<div class="luntai_text">
										<p>${goods.goodsName }</p>
									</div>
								</td>
								<td valign="top" class="dbor">${goods.saleNum }</td>
								<%
									if(count ==1){%>
								<td  valign="top" rowspan="${result.goodsSize }"><b class="orange">￥${result.price}</b></td>
								<td valign="top" rowspan="${result.goodsSize }">
									<c:if test="${result.orderStatus== 1}">待收货</c:if>
									<c:if test="${result.orderStatus== 2}">待服务</c:if>
									<c:if test="${result.orderStatus== 3}">已完成</c:if>
								</td>
								<td  valign="top" rowspan="${result.goodsSize }">
									<c:if test="${result.orderStatus== 1}"><a href="javascript:confirm_btn(${result.orderId });" class="confirm_btn"  >确认收货</a> </c:if>
									<a href="/page/order/order_detail.s?orderId=${result.orderId }" class="check_details_btn">查看详情</a></td>

								<% }
								%>
							</tr>
							<% count = count+1 ;%>
						</c:forEach>
					</c:forEach>--%>
				</table>
				<div id="no_data_div" style="width:112px;height:158px; margin:0 auto;display: none;"><img src="<%=basePath%>/images/no_pingjia.jpg"></div>
				<div class="page clearfix"> </div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/popup.jsp"></jsp:include><input type="hidden" id="confirm_orderId">
</div>
<input type="hidden" id="page_limit" value=20>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script src="<%=basePath%>/page/order/order_list.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/js/page-control.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/util.js"></script>
</body>
