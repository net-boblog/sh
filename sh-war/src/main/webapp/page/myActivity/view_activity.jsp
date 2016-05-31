<%@ page import="com.qccr.sh.external.marketcenter.bo.StoreActivityStatusEnum" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>我发布的活动详情</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
  <link rel="alternate icon" type="image/png" href="<%=ctx%>/images/logo_40.png">
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css?v=20160312" />
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css?v=20160312"/>
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css?v=20160312"/>
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/activity.css?v=20160312">
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/page/activity/css/short.css">
  <script type="text/javascript" src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="<%=ctx%>/js/basic.js?v=20160312"></script>
  <script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js?v=20160312"></script>
  <script type="text/javascript" src="<%=ctx%>/js/link.js?v=20160312"></script>
  <script type="text/javascript" src="<%=ctx%>/common/laydate/laydate.js?v=20160312"></script>
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
        <div class="title_mune">
          <span class="act_management">活动管理 &gt;</span>
          <span class="act_management">我的活动 &gt;</span>
          <span class="finplace act_management">${activity.name} </span>
        </div>
        <form action="#" method="post" name="storeListForm" id="storeListForm">
          <input type="hidden" id="promotionId" name="promotionId" value="${activity.promotionId}">
          <input type="hidden" id="productId" name="productId" value="${activity.productId}">
          <div class="radiu">
            <div class="seach_list">
              <div class="ad_top_content">
                <c:if test="${storePic!=null && storePic!=''}">
                  <img src="${storePic}" class="ad_block_img" />
                </c:if>
                <c:if test="${storePic==null || storePic==''}">
                  <img src="<%=ctx%>/page/activity/img/u47.png" class="ad_block_img" />
                </c:if>
                <div class="seach_right">
                <div class="fl ad_wid_500">
                  <div class="ad_block_title">${activity.name}</div>
                  <div style="width:100%">
                  <div class="supera_block_text  oright">
                    <span class="sp_service">服务项目：</span>
                    <span>${activity.secondCategoryName}</span>
                  </div>
                  <div class="supera_block_text">
                    <span class="ad_cycle">限购周期：</span>
                    <span class="ad_cycle_introduce">${activity.cycleDayes}天/次</span>
                  </div>
                  </div>
                  <div class="supera_block_text oright">
                    <span class="sp_act_backtime">活动时间：</span>
                    <span><fmt:formatDate value="${activity.startTime}" pattern="MM"/>月<fmt:formatDate value="${activity.startTime}" pattern="dd"/>日-<fmt:formatDate value="${activity.endTime}" pattern="MM"/>月<fmt:formatDate value="${activity.endTime}" pattern="dd"/>日</span>
                  </div>
                  <div class="supera_block_text">
                    <span class="sp_act_area">活动地区：</span>
                    <span>${provinceName}  ${cityName}</span>
                  </div>
                  <div class="supera_block_text mrl_0"></div>
                </div>

                <div class="ad_wid_450 details">
                  <p>
                    <span>签约价格：</span>
                    <span>${contractPrice}元</span>
                  </p>
                  <div class="supera_block_text mrl_80" style="display:none;">
                    <span>平台补贴：</span>
                    <span>5 元</span>
                  </div>
                  <p>
                    <span>商家补贴：   ${activity.merchantAllowance/100}元</span>
                    <span class="mrl_15">(结算价格：${activity.promotionClearAmt/100} 元    销售价：${activity.promotionAmt/100} 元 )</span>
                  </p>
                  <p>服务数量：${activity.saleNumber}</p>
                  <div class="h"></div>
                </div>

                <div class="clear"></div>
                <div class="ad_sign">
                  <div class="sp_bt_stop">
                    <span class="button_con"><a shref="javascript:void(0)" class="addStore" target="_blank">${activity.promotionStatusName}</a></span>
                  </div>
                </div>
              </div>
              </div>
            </div>
          </div>
          <input type="hidden" id="userId" name="belongUser">
        </form>

        <c:set var="enrollPromotionStatus" value="${activity.enrollPromotionStatus}" scope="request" />
        <%
          String promotionStatusStr = request.getAttribute("enrollPromotionStatus").toString();
          boolean show = true;
          if(promotionStatusStr!=null&&!promotionStatusStr.equals("")){
            int enrollPromotionStatus = new Integer(promotionStatusStr).intValue();
            if(enrollPromotionStatus==StoreActivityStatusEnum.WAIT_START.getValue()){
               show = false;
            }
          }
          if(show){
        %>
        <div class="income_trend radiu">
        <%
          }else{
        %>
        <div class="income_trend radiu" style="display: none;">
        <%
        }
        %>
        <h5 class="can_amount">
          收入趋势
          <div class="near_day">
            <a id="7daysData" class="dayon qi" href="javascript:loadActivityConsume(7);">近7天</a><a id="30daysData" class="sanshi" href="javascript:loadActivityConsume(30);">近30天</a>
          </div>
          <h2 style="padding-left: 20px;padding-top: 10px;">已售出 <label id="soldNumberLabel" style="color:red;">0单</label> 剩余：<label id="leftNumberLabel" style="color: red;">0单</label></h2><br/>
        </h5>
        <div id="container" />
      </div>
      <input type="hidden" class="localurl" value="/resources">
      <input type="hidden" class="url" value="">
    </div>
  </div>
</div>
<script type="text/javascript" src="<%=ctx%>/common/highcharts/highcharts.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/common/highcharts/themes/grid-light.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/page/myActivity/view_activity.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/util.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
</body>
</html>
