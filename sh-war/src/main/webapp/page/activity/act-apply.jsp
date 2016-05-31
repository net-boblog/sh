<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
 	<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
	<link  rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css"/>
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css" />
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/activity.css?v=2016031201">
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css"/>
	<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/bootstrap.css"/>
	<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=ctx%>/js/basic.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/short.css">

	<script type="text/javascript">
		var ctx = '<%=ctx%>';
	</script>
	<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>

</head>
<style>
	.active_xq{
		float: left;}
</style>
<body>
<div class="content">

	<jsp:include page="../addon/link.jsp"></jsp:include>
	<div class="box_right">
		<jsp:include page="../addon/head.jsp"></jsp:include>
		<div class="wrap_right">
			<div class="title_mune">活动管理 > 活动详情</div>
			<div class="radiu" style="padding-bottom:0;">
				<div class="collecting_cargo"">
					<form action="#" method="post" name="storeListForm" id="storeListForm">
						<input type="hidden" id="pageNum" name="pageNum" value="1">
						<div class="seach">
							<div class="seach_list">
								<div class="ad_top_content">
									<c:choose>
										<c:when test="${result.webEnrollPhoto == ''}">
											<img src="img/u47.png" class="ad_block_img" />
										</c:when>
										<c:otherwise>
											<img src="${paramMap.staticUrl}${platPromDetail.webEnrollPhoto}" class="ad_block_img" />
										</c:otherwise>
									</c:choose>
									<div id="serviceVal" style="display:none;">${platPromDetail.saleMinNumber}</div>   <%--存放一个值，传到js里--%>
									<input type="hidden" value="${paramMap.actProdPrice}" id="qianyue"/> <%--签约值--%>
									<input type="hidden" value="${platPromDetail.platformAllowance}" id="platformPrice"/> <%--平台补贴价--%>
									<div class="seach_right">
									<div class="fl ad_wid_500">
										<div class="ad_block_title">${servPromDetail.name}</div>
										<div style="width:100%">
										<div class="supera_block_text oright" style="width:250px;">
											<span class="sp_service">服务项目：</span>
											<span>${servPromDetail.secondCategoryName}</span>
										</div>
										<div class="supera_block_text">
											<span class="ad_cycle">限购周期：</span>
										${fn:split(servPromDetail.cycle, ":")[0]}天/次
											<span class=" tips"  data-toggle="tooltip" data-placement="bottom" title="车主在一定周期内只能享受一次优惠">
												<img src="<%=ctx%>/images/u8_03.png"/></span>
										</div>
									</div>
										
										<div class="supera_block_text oright" style="width:250px;">
											<span class="sp_act_backtime" >活动时间：</span>
											<span><fmt:formatDate value="${servPromDetail.startTime}" pattern="M月d日"/>-
                                                    <fmt:formatDate value="${servPromDetail.endTime}" pattern="M月d日"/></span>
										</div>
										<div class="supera_block_text">
											<span class="sp_act_area">活动地区：</span>
											<span>${paramMap.province}  ${paramMap.city}</span>
										</div>
										<div class="supera_block_text mrl_0"></div>
									</div>

									<div class="ad_wid_450">
									<div style="width:100%">
										<div class="supera_block_text mrl_37">
											<span>签约价格：</span>
											<span><fmt:formatNumber value="${paramMap.actProdPrice/100}" pattern="0.00"/> 元</span>
										</div>
										<div class="supera_block_text mrl_80">
											<span>平台补贴：</span>
											<span><fmt:formatNumber value="${platPromDetail.platformAllowance/100}" pattern="0.00"/> 元</span>
										</div>
										</div>
										<c:if test="${paramMap.hasApply=='1' || (paramMap.hasApply=='0' && paramMap.hasEnd=='0') || paramMap.reapply=='1'}" >
											<div class="supera_block_text ad_subsidy">
												<span style="float:left;">门店补贴：</span>
												<div style="float:left;">
													<c:choose>
														<c:when test="${(paramMap.hasApply=='0' && paramMap.hasEnd=='0') || paramMap.reapply=='1'}">
															<select class="ad_service_input" id="prom_money" name="prom_money">
															<c:forEach items="${platPromDetail.merchantAllowanceGrade}" var="item">
																<c:choose>
																	<c:when test="${item == paramMap.merchantPrice}">
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
															</select>
														</c:when>
														<c:when test="${paramMap.hasApply=='1' && paramMap.reapply=='0'}">
															<fmt:formatNumber value='${paramMap.merchantPrice/100}' pattern='0.00'/>
														</c:when>
													</c:choose>
												&nbsp;&nbsp;
												元
												<span class="tips" data-toggle="tooltip" data-placement="bottom" title="商家参加该活动所补贴的金额">
													<img src="<%=ctx%>/images/u8_03.png"/></span>
												<span class="mrl_15">(
													结算价格：<span id="promClearAmt">
													<fmt:formatNumber value="${paramMap.promotionClearAmt/100}" pattern="0.00"/></span> 元
													销售价：<span id="promAmt"><fmt:formatNumber value="${paramMap.promotionAmt/100}" pattern="0.00"/></span> 元 )</span>
											</div>
										</div>
										<div class="clear"></div>
										<div style="width:100%">
										<div class="ad_block_text mrl_37 mart_10">
											<span style="float: left;">服务数量：</span>
											<div style="float: left;">
												<c:choose>
													<c:when test="${(paramMap.hasApply=='0' && paramMap.hasEnd=='0') || paramMap.reapply=='1'}">
														<input type="text" class="ad_service_input2" id="sale_number" value="${platPromDetail.saleMinNumber}" />
													</c:when>
													<c:when test="${paramMap.hasApply=='1' && paramMap.reapply=='0'}">
														${platPromDetail.saleMinNumber}
													</c:when>
												</c:choose>
												&nbsp;&nbsp;
												件
												<span class="tips" data-toggle="tooltip" data-placement="bottom" title="商家参加该活动所提供的服务件数" <%--onmouseover="allMethod.tips('商家参加该活动所提供的服务件数',200,this);" onmouseout="allMethod.tipsOut();"--%>><img src="<%=ctx%>/images/u8_03.png"/></span>
												<span class="mrl_15">(${platPromDetail.saleMinNumber}件起 )</span>
											</div>
										</div>
										</div>
										</c:if>
										<div class="reason">
											<c:if test="${paramMap.approveMsg!=null}">
												<p>失败原因：${paramMap.approveMsg}</p>
											</c:if>
										</div>
									</div>

										<c:if test="${paramMap.reapply=='1' || (servPromDetail.enrollPromotionStatus==1 &&
											paramMap.hasProduct=='1') }">
										<div class="remainSeconds" id="remainSeconds" style="display:none;">
											${(platPromDetail.endRegistrationTime.time - paramMap.nowTime.time)}</div>
										<p style="padding-left:30px; padding-top:15px;"><span class="sp_time">离报名截止日期还剩：</span>
										<span class="remainTime" id="remainTime" style="color:#e62d46"></span></p>
										</c:if>
<div style="width: 100%;">
										<c:choose>
											<c:when test="${servPromDetail.enrollPromotionStatus==1}">
												<c:choose>
													<c:when test="${paramMap.hasProduct=='0'}">
														<div class="sp_bt_stop active_xq">
															<span class="button_con">
														<a href="javascript:void(0)" class="addStore" target="_self">您未签约该服务</a>
																</span>
															</div>
													</c:when>
													<c:otherwise>
														<div class="sp_bt_sign active_xq">
														<span class="button_con">
														<c:choose>
															<c:when test="${paramMap.reapply=='1'}">
																<a href="javascript:apply(${platPromDetail.promotionId})" class="addStore" target="_self">再次报名</a>
															</c:when>
															<c:otherwise>
																<a href="javascript:apply(${platPromDetail.promotionId})" class="addStore" target="_self">我要报名</a>
															</c:otherwise>
														</c:choose>
															</span></div>
													</c:otherwise>
												</c:choose>

											</c:when>
											<c:when test="${servPromDetail.enrollPromotionStatus==2}">

													<c:choose>
														<c:when test="${paramMap.reapply=='1'}">
															<div class="sp_bt_sign active_xq">
																<span class="button_con"><a href="javascript:apply(${platPromDetail.promotionId})" class="addStore" target="_self">再次报名</a>
																</span>
															</div>
														</c:when>
														<c:otherwise>
															<div class="sp_bt_stop active_xq">
															<span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">已报名</a></span>
															</div>
														</c:otherwise>
													</c:choose>

											</c:when>
											<c:when test="${servPromDetail.enrollPromotionStatus==3}">
												<div class="sp_bt_stop active_xq">
													<span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">报名截止</a></span>
												</div>
											</c:when>
											<c:when test="${servPromDetail.enrollPromotionStatus==4}">
												<div class="sp_bt_stop active_xq">
													<span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">已开始</a></span>
												</div>
											</c:when>
											<c:when test="${servPromDetail.enrollPromotionStatus==5}">
												<div class="sp_bt_stop active_xq">
													<span class="button_con"><a href="javascript:void(0)" class="addStore" target="_self">已结束</a></span>
												</div>
											</c:when>
											<c:otherwise>
												<div class="sp_bt_sign active_xq">
													<c:choose>
														<c:when test="${paramMap.reapply=='1'}">
															<span class="button_con"><a href="javascript:apply(${platPromDetail.promotionId})" class="addStore" target="_self">再次报名</a></span>
														</c:when>
														<c:otherwise>
															<span class="button_con"><a href="javascript:apply(${platPromDetail.promotionId})" class="addStore" target="_self">我要报名</a></span>
														</c:otherwise>
													</c:choose>

												</div>
											</c:otherwise>
										</c:choose>
										<c:if test="${(servPromDetail.enrollPromotionStatus==1 || paramMap.reapply=='1') && paramMap.hasProduct=='1'}">
						    	<span class="ad_read_agree" style="float:left;padding-top:15px;">
						    		<img src="<%=ctx%>/images/dui1_03.png" class="ad_dui_1">
						    		<img src="<%=ctx%>/images/dui2_03.jpg" class="ad_dui_2">
						    		我已阅读并同意
						    		<a href="#" class="act_agreement">《汽车超人活动协议》</a>
						    	</span>
										</c:if>
</div>
									</div>

								</div>

							</div>

						</div>
						<input type="hidden" id="userId" name="belongUser">
					</form>
				</div>
			</div>

		<c:if test="${servPromDetail.desc!=null && servPromDetail.desc!=''}">
			<div class="h"></div>
			<div class="radiu">
				<div class="ad_introduce_text">活动介绍</div>

				<div class="ad_introduce_content">
					${servPromDetail.desc}
				</div>

			</div>
		</c:if>
			<jsp:include page="./agreement.jsp"></jsp:include>
				</div>
			</div>

			<jsp:include page="../common/popup.jsp"></jsp:include>
		</div>
	</div>
<script type="text/javascript" src="<%=ctx%>/js/popups.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/jqueryUI/jquery.validate.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/jqueryUI/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=ctx%>/page/activity/act-apply.js?v=2016031502"></script>

<script type="text/javascript">
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	<c:if test="${paramMap.reapply=='1' || (servPromDetail.enrollPromotionStatus==1 &&
		paramMap.hasProduct=='1') }">

	var SysSecond = parseInt($("#remainSeconds").html())/1000;
	var InterValObj = window.setInterval(SetRemainTime, 1000);

	function SetRemainTime() {
		if (SysSecond > 0) {
			SysSecond = SysSecond - 1;
			var second = Math.floor(SysSecond % 60);
			var minite = Math.floor((SysSecond / 60) % 60);
			var hour = Math.floor((SysSecond / 3600) % 24);
			var day = Math.floor((SysSecond / 3600) / 24);
			$("#remainTime").html(day + "天" + hour + "小时" + minite + "分" + second + "秒");
		} else {
			window.clearInterval(InterValObj);
		}
	}
	</c:if>

</script>

</body>