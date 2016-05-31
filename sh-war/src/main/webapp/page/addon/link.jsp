<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
  <script type="text/javascript">
  var ctx = '<%=ctx%>';
  var url=window.location.href;
  if(url.lastIndexOf("https")>=0&&url.lastIndexOf("modifyPasswordPage.s")<0){
	  url=url.replace("https","http");
	  window.location.href=url;
  }
</script>
<div class="nav">
	<div class="nav_top">
		<div class="web_logo">
			<a><img src="../../images/logo.png"></a>
		</div>
	</div>
	<div class="nav_bottom">
		<ul class="nav_left">
		  <li class="sub1" id="service_list">
		  <a class="left_sublist1" id="validation_query_btn" href="../validation/validation_query.jsp" target="_self">服务验证</a></li>
		  <li class="sub1" id="order_list"><a class="left_sublist2"  target="_self" href="/page/serverOrder/list.s">订单管理
		    <span style="padding-left:5px;"class="subarrow_pic"></span>
		   </a>
		    <ul class="sublist_left">
		      <li id="yz_sub_nav"><a href="/page/serverOrder/list.s">已验证订单</a></li>
		      <li id="ds_sub_nav"><a href="/page/order/list.s">代收货订单</a></li>
		    </ul>
		  </li>
		  <li class="sub1" id="activity_list"><a class="left_sublist6"  target="_self" href="/page/activity/list.s">活动管理
		    <span style="padding-left:5px;"class="subarrow_pic"></span>
		   </a>
		    <ul class="sublist_left">
				<li id="sys_act_sub_nav"><a href="/page/activity/list.s">超人活动</a></li>
				<li id="activity_sub_nav"><a href="/page/activity/index.s">我的活动</a></li>
		    </ul>
		  </li>
		  <li class="sub1" id="caiwu_list"><a class="left_sublist3" id="finance_query_btn" href="/page/drawmoney/index.s" target="_self">财务管理
		     <span style="padding-left:5px;" class="subarrow_pic"></span>
		  </a>
		      <ul class="sublist_left">
		      <li id="tk_sub_nav"><a href="/page/drawmoney/index.s">我要提款</a></li>
		      <li id="md_sub_nav"><a href="/page/storeReward/index.s">门店奖励</a></li>
		      <li id="yj_sub_nav"><a href="/page/finance/performance_analysis.s">业绩分析</a></li>
		    </ul>
		  </li>
		  <li class="sub1" id="my_service_list"><a  class="left_sublist4" id="goods_query_btn" href="../goods/goods_query.jsp" target="_self">我的服务</a></li>
		  <li class="sub1" id="user_evaluation_list"><a  class="left_sublist5" id="user_comments_btn" href="../comment/user_comment.jsp" target="_self">用户评价</a></li>
		</ul>
       <div  style="margin-top:20px;">
			<a onclick="toSShop()" style="cursor:pointer"><img src="../../images/Car_parts.gif"/></a>
		</div>
		<form id="jumpForm" action = "" method="get" target="_blank">
		</form>
		<form id="jumpFormToApply" action = "" method="get" target="_blank">
			<input type="hidden" name="haveStore" value="1"/>
		</form>

	</div>

</div>
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>