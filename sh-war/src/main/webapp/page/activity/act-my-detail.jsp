<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>超人活动详情</title>
	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
	<link rel="alternate icon" type="image/png"
		  href="../../images/logo_40.png">
	<link href="../../css/link.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="../../css/common.css?v=20160312" />
	<link rel="stylesheet" type="text/css" href="../../css/tikuan.css?v=20160312" />
	<link rel="stylesheet" type="text/css" href="/css/member.css?v=20160312"/>
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/activity.css?v=20160312">
	<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../../js/basic.js?v=20160312"></script>
	<link rel="stylesheet" type="text/css" href="./css/short.css">
	<script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
	<script type="text/javascript">
		var ctx = '<%=ctx%>';
	</script>
	<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>

</head>
<body>
<div class="content">

	<jsp:include page="../addon/link.jsp"></jsp:include>
	<div class="box_right">
		<jsp:include page="../addon/head.jsp"></jsp:include>
		<div class="wrap_right">
			<div class="title_mune">活动管理 > 活动详情</div>
			<div class="radiu" style="padding-bottom:0;">
				<div class="collecting_cargo">
					<form action="#" method="post" name="storeListForm" id="storeListForm">
						<input type="hidden" id="act_id" name="act_id" value="${paramMap.act_id}">
						<input type="hidden" id="day_range" name="day_range" value="${paramMap.day_range}">
						<div class="seach">
							<div class="seach_list">
								<div class="ad_top_content">
									<img src="${paramMap.staticUrl}${detail.webEnrollPhoto}" class="ad_block_img" />
                                   <div class="seach_right">
									<div class="fl ad_wid_500">
										<div class="ad_block_title">${detail.name}</div>
										<div style="width:100%">
										<div class="supera_block_text oright">
											<span class="sp_service">服务项目：</span>
											<span>${detail.secondCategoryName}</span>
										</div>
										<div class="supera_block_text">
											<span class="ad_cycle">限购周期：</span>
											<span class="ad_cycle_introduce">${fn:split(detail.cycle, ":")[0]}天/次</span>
										</div>
										</div>
										<div class="supera_block_text oright">
											<span class="sp_act_backtime">活动时间：</span>
											<span><fmt:formatDate value="${detail.startTime}" pattern="M月d日"/>-
                                                    <fmt:formatDate value="${detail.endTime}" pattern="M月d日"/></span>
										</div>
										<div class="supera_block_text">
											<span class="sp_act_area">活动地区：</span>
											<span>${paramMap.province_name}  ${paramMap.city_name}</span>
										</div>
										<div class="supera_block_text mrl_0"></div>
									</div>
									<div class="ad_wid_450 fl details">
										<p>
											<span>签约价格：
											<fmt:formatNumber value="${prodPrice/100}" pattern="0.00"/> 元
									       </span>
										
											<span style="padding-left:80px;">平台补贴：
											<fmt:formatNumber value="${detail.platformAllowance/100}" pattern="0.00"/> 元</span>
										</p>
										<p>
											<span>门店补贴：
												<fmt:formatNumber value="${detail.merchantAllowance/100}" pattern="0.00"/>
												元
											</span>
											<span>(
													结算价格：<fmt:formatNumber value="${detail.promotionClearAmt/100}" pattern="0.00"/> 元
													销售价：<fmt:formatNumber value="${detail.promotionAmt/100}" pattern="0.00"/> 元 )</span>
											
										</p>
										<div style="width:100%">
										<div class="ad_block_text mrl_37 mart_10">
											<span>服务数量：</span>
											<div>
												${detail.saleNumber}件
											</div>
										</div>
										</div>
										<div class="h"></div>
									</div>

									   <div class="clear"></div>
									   <div class="ad_sign">
										   <c:choose>
											   <c:when test="${detail.promotionStatus==1}">
												   <div class="sp_bt_stop">
													   <span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">审核中</a></span>
												   </div>
											   </c:when>
											   <c:when test="${detail.promotionStatus==2}">
												   <div class="sp_bt_stop">
													   <span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">等待开始</a></span>
												   </div>
											   </c:when>
											   <c:when test="${detail.promotionStatus==3}">
												   <div class="sp_bt_stop">
													   <span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">已开始</a></span>
												   </div>
											   </c:when>
											   <c:when test="${detail.promotionStatus==4}">
												   <div class="sp_bt_stop">
													   <span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">已结束</a></span>
												   </div>
											   </c:when>
											   <c:when test="${detail.promotionStatus==5}">
												   <div class="sp_bt_stop">
													   <span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">审核失败</a></span>
												   </div>
											   </c:when>
											   <c:when test="${detail.promotionStatus==6}">
												   <div class="sp_bt_stop">
													   <span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">已退出</a></span>
												   </div>
											   </c:when>
										   </c:choose>
									   </div>
								</div>


								</div>
							</div>
						</div>
								
						<input type="hidden" id="userId" name="belongUser">
					</form>
				</div>
			</div>

			<c:if test="${paramMap.hasStat=='1'}">
		<div class="income_trend radiu">
			<h5 class="can_amount">
				收入趋势
				<div class="near_day">
					<a id="7daysData" class="dayon qi" href="javascript:refresh(${detail.promotionId}, 7)">近7天</a><a id="30daysData"
																 class="sanshi" href="javascript:refresh(${detail.promotionId}, 30)">近30天</a>
				</div>
			</h5>
			<p class="sold">已售出 <span class="red">${stat.soldNumber}单</span>剩余：<span class="red">${stat.saleNumber-stat.soldNumber}单</span></p>
			<div id="container" />
		</div>
			</c:if>
    </div>
	</div>
</body>

<script type="text/javascript">
	var data = eval(${analysis});
</script>
<script src="./act-analysis.js?v=20160315" type="text/javascript"></script>
<script src="/common/highcharts/highcharts.js" type="text/javascript"></script>
<script src="/common/highcharts/themes/grid-light.js"
		type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		if('${paramMap.day_range}'=='7'){
			$("#7daysData").attr("class", "dayon qi");
			$("#30daysData").attr("class", "sanshi");
		}else{
			$("#7daysData").attr("class", "sanshi");
			$("#30daysData").attr("class", "dayon qi");
		}

		function apploy(actId){
			$.ajax({
				url : "/page/activity/apply.s",
				data : {
					"sale_number" : $("#sale_number").val(),
					"prom_money": $("#prom_money").val(),
					"act_id": actId
				},
				success : function(json) {
					$("#fail_msg").html(json.info);
					$("#failConfirm").attr("href", "javascript:refresh(${detail.promotionId})");
					$("#update_fail").show();
				},
				error : function() {
					$("#fail_msg").html("报名失败：服务器异常");
					$("#update_fail").show();
				}
			});
		}
	});

	function refresh(actId, dayRange){
		$("#storeListForm").attr("action", "<%=ctx%>/page/activity/my-detail.s");
		$("#storeListForm").attr("target", "");
		$("#act_id").val(actId);
		$("#day_range").val(dayRange);
		$("#storeListForm").submit();
	}

</script>
