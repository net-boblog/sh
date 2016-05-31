<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>财务管理-业绩分析</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<link rel="alternate icon" type="image/png" href="/images/logo_40.png">
<link href="/css/link.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/css/common.css" />
<link rel="stylesheet" type="text/css" href="/css/tikuan.css" />
</head>
<body>
	<div class="content">
		<jsp:include page="../addon/link.jsp"></jsp:include>
		<div class="box_right">
			<jsp:include page="../addon/head.jsp"></jsp:include>
			<div class="wrap_right">
				<div class="title_mune">财务管理 > 业绩分析</div>
				<div class="ts_hint">
					<p>
						<b>· </b>数据都截止到昨天24点
					</p>
					<p>
						<b>· </b>此数据不包含奖励订单和异常订单
					</p>
				</div>

				<div class="key_zhibiao radiu">
					<h5 class="can_amount">
						关键指标</span>
					</h5>
					<table width="100%" border="1" class="key_table">
						<tr>
							<td>
								<table width="100%" border="1" class="k_table_small">
									<tr>
										<td colspan="2" class="top_td_day tdsize">昨日</td>
									</tr>
									<tr>
										<td class="order_num_td y_quota_order_num_td"
											style="border-right: #eee 1px solid; padding-left: 15%;"><p
												class="ordernum_text">
												服务订单数<a class="yichang_prompt y_quota_tips"
													style="display: none"><img
													src="../../images/wenhao_icon.png" /></a></span>
											<div class="yc_box" style="display: none">
												<img src="../../images/arrow_icon.png" />
												<p id="y_quota_tips"></p>
											</div>
											</p>
											<div>
												<i id="y_quota_nomalCount">--</i><span
													id="y_quota_nomalCount_rate" class="rate_top">--</span>
											</div></td>
										<td class="order_num_td y_quota_order_num_td"><p>获得收益（元）</p>
											<div>
												<i id="y_quota_profit">--</i><span id="y_quota_profit_rate"
													class="rate_top">--</span>
											</div></td>
									</tr>
								</table>

							</td>
							<td><table width="100%" border="1" class="k_table_small">
									<tr>
										<td colspan="2" class="top_td_day tdsize">近7天</td>
									</tr>
									<tr>
										<td class="order_num_td d7_quota_order_num_td"
											style="border-right: #eee 1px solid; padding-left: 15%;"><p
												class="ordernum_text">
												服务订单数<a class="yichang_prompt d7_quota_tips"
													style="display: none"><img
													src="../../images/wenhao_icon.png" /></a>
											<div class="yc_box" style="display: none">
												<img src="../../images/arrow_icon.png" />
												<p id="d7_quota_tips"></p>
											</div>
											</p>
											<div>
												<i id="d7_quota_nomalCount">--</i><span
													id="d7_quota_nomalCount_rate" class="rate_top">--</span>
											</div></td>
										<td class="order_num_td d7_quota_order_num_td"><p>获得收益（元）</p>
											<div>
												<i id="d7_quota_profit">--</i><span
													id="d7_quota_profit_rate" class="rate_top">--</span>
											</div></td>
									</tr>
								</table></td>

							<td>
								<table width="100%" border="1" class="k_table_small">
									<tr>
										<td colspan="2" class="top_td_day tdsize">近30天</td>
									</tr>
									<tr>
										<td class="order_num_td d30_quota_order_num_td"
											style="border-right: #eee 1px solid; padding-left: 15%;"><p
												class="ordernum_text">
												服务订单数<a class="yichang_prompt d30_quota_tips"
													style="display: none"><img
													src="../../images/wenhao_icon.png" /></a>
											<div class="yc_box" style="display: none">
												<img src="../../images/arrow_icon.png" />
												<p id="d30_quota_tips"></p>
											</div>
											</p>
											<div>
												<i id="d30_quota_nomalCount">--</i><span
													id="d30_quota_nomalCount_rate" class="rate_top">--</span>
											</div></td>
										<td class="order_num_td d30_quota_order_num_td"><p>获得收益（元）</p>
											<div>
												<i id="d30_quota_profit">--</i><span
													id="d30_quota_profit_rate" class="rate_down">--</span>
											</div></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>

				<div class="income_trend radiu">
					<h5 class="can_amount">
					收入趋势
						<div class="near_day">
							<a id="7daysData" class="dayon qi">近7天</a><a id="30daysData"
								class="sanshi">近30天</a>
						</div>
					</h5>
					<div id="container" />
				</div>
			</div>
		</div>
		<!--wrap_right-->
	</div>
	<script src="/common/jquery/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="/common/highcharts/highcharts.js" type="text/javascript"></script>
	<script src="/common/highcharts/themes/grid-light.js"
		type="text/javascript"></script>
	<script type="text/javascript">
        var data = eval(${performance_analysis});
        var ctx = '<%=ctx%>';
    </script>
	<script src="./performance_analysis.js" type="text/javascript"></script>
	<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/basic.js"></script>
</body>