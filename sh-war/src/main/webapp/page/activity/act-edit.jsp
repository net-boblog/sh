<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<title>编辑我报名的活动</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<link rel="alternate icon" type="image/png" href="<%=ctx%>/images/logo_40.png">
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css?v=20160312" />
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css?v=20160312"/>
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css?v=20160312"/>
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css?v=20160312"/>
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/activity.css?v=20160312"/>
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/page/activity/css/short.css">
	<script type="text/javascript">
		var ctx = '<%=ctx%>';
	</script>
</head>
<body>
<div class="content">
	<jsp:include page="../addon/link.jsp"></jsp:include>
	<div class="box_right">
		<jsp:include page="../addon/head.jsp"></jsp:include>
		<div class="wrap_right">
			<div class="title_mune">活动管理 &gt; 编辑活动</div>
			<div class="radiu" style="padding-bottom: 0">
				<input type="hidden" id="promotionId" name="promotionId" value="${detail.promotionId}"/>
				<input type="hidden" id="firstCategoryIdHide" name="firstCategoryIdHide" value="${detail.firstCategoryId}"/>
				<input type="hidden" id="secondCategoryIdHide" name="secondCategoryIdHide" value="${detail.secondCategoryId}"/>
				<input type="hidden" id="saleMinNumber" name="saleMinNumber" value="${detail.saleMinNumber}"/>
				<input type="hidden" id="cycleTimes" name="cycleTimes" value="${detail.cycle}"/>
				<table class="edit_active">
					<tr>
						<td class="edit_firsttd"><span class="red">*</span>活动名称</td>
						<td><span class="edit_after">${detail.name}</span>
						</td>
					</tr>
					<tr>
						<td class="edit_firsttd"><span class="red">*</span>服务项目</td>
						<td>
					        <span class="edit_after">${detail.secondCategoryName}</span>
						</td>
					</tr>
					<tr>
						<td class="edit_firsttd"><span class="red">*</span>活动时间</td>
						<td><span class="edit_after">
                            <fmt:formatDate value="${detail.startTime}" pattern="M月d日"/>-
							<fmt:formatDate value="${detail.endTime}" pattern="M月d日"/></span>
						</td>
					</tr>
					<tr>
						<td class="edit_firsttd"><span class="red">*</span>限购周期</td>
						<td>${fn:split(detail.cycle, ":")[0]}天/次<p class="edit_text">车主在一定周期内只能享受一次优惠，例7天/次，表示7天内该用户只能享受此活动1次</p></td>
					</tr>
					<tr>
						<td class="edit_firsttd"><span class="red">*</span>签约价</td>
						<div id="prod_price" style="display:none;">${prodPrice}</div>
						<td><fmt:formatNumber value="${prodPrice/100}" pattern="0.00"/>元</td>
					</tr>
					<tr>
						<td class="edit_firsttd"><span class="red">*</span>平台补贴</td>
						<div id="platform_allowance" style="display:none;">${detail.platformAllowance}</div>
						<td><fmt:formatNumber value="${detail.platformAllowance/100}" pattern="0.00"/>元</td>
					</tr>
					<tr>
						<td class="edit_firsttd"><span class="red">*</span>销售数量</td>
						<td>
							<input type="text" class="shortinput" id="sale_number" name="sale_number"
								   value="${detail.saleNumber}"> 件（${detail.saleMinNumber}件起）<p class="edit_text">商家报名该活动所提供的服务数量</p></td>
					</tr>

					<tr>
						<td class="edit_firsttd"><span class="red">*</span>商户补贴金额</td>
						<td>
							<select id="prom_money" name="prom_money">
								<c:forEach items="${detail.merchantAllowanceGrade}" var="item">
									<c:choose>
										<c:when test="${item == detail.merchantAllowance}">
											<option value="${item}" selected>
												<fmt:formatNumber value='${item/100}' pattern='0.00'/>
											</option>
										</c:when>
										<c:otherwise>
											<option value="${item}">
												<fmt:formatNumber value='${item/100}' pattern='0.00'/>
											</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select> 元<p class="edit_text">商户提供的优惠额度，设置的金额数字＞0</p></td>
					</tr>
				</table>
			</div>

				<div class="radiu wt20 rprotocol">
					<img style="display: none;" src="<%=ctx%>/images/dui1_03.png" class="ad_dui_1">
					<img style="display: inline;" src="<%=ctx%>/images/dui2_03.jpg" class="ad_dui_2">
					我已阅读并同意
			        <a href="#" class="act_agreement">《汽车超人活动协议》</a>
	            </div>
			<div class="down_btn_tr btn">
				<a href="javascript:commit()" class="find_btn" id="commit">提&nbsp;交</a>
				<a href="javascript:cancel()" class="reset_btn" id="reset_href">取&nbsp;消</a>
			</div>

			<jsp:include page="./agreement.jsp"></jsp:include>

		</div>

	</div>
	<jsp:include page="../common/popup.jsp"></jsp:include>
</div>
<script type="text/javascript" src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/basic.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/link.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/common/laydate/laydate.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/page/activity/act-edit.js?v=20160315"></script>
<script type="text/javascript" src="<%=ctx%>/js/util.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
</body>
</html>
