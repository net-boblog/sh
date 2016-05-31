<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>我发布的活动列表</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
  <link rel="alternate icon" type="image/png" href="<%=ctx%>/images/logo_40.png">
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css?v=20160312" />
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css?v=20160312"/>
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css?v=20160312"/>
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css?v=20160312"/>
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/page.css?v=20160312"/>
  <script type="text/javascript" src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="<%=ctx%>/js/basic.js?v=20160312"></script>
  <script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="<%=ctx%>/js/link.js?v=20160312"></script>
  <script type="text/javascript" src="<%=ctx%>/common/laydate/laydate.js"></script>
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
    <div class="title_mune">活动管理 &gt; 我发布的活动</div>
      <div class="radiu">
        <ul class="applyActive clearfix">
	        <li><a href="javascript:void(0);" onclick="jumpToActivity();">我报名的活动</a></li>
	        <li class="on"><a href="javascript:void(0);">我发布的活动</a></li>
        </ul>
      </div>
      <div class="radiu wt20" style="padding-bottom:0;">
        <div class="collecting_cargo" style="padding-top:10px;">
          <form action="<%=ctx%>/page/serverOrder/list.s" id="searchForm">
            <input type="hidden" id="page_limit" name="page_limit" value="20">
            <input type="hidden" id="page_offset" name="page_offset" value="0">
            <input type="hidden" id="page_size" name="page_size" value="20">
            <table width="100%" border="0" class="coll_table">
              <tr>
                <td><label>活动名称：</label>
                  <input type="text" class="coll_input" id="name" name="name"
                         value=""/>
                </td>
                <td><label>服务项目：</label>
                  <select id="firstCategoryId" name="firstCategoryId" class="s_obj">
                    <option value="">全部</option>
                  </select>
                  <select id="secondCategoryId" name="secondCategoryId" class="s_obj">
                    <option value="">全部</option>
                  </select>
                </td>
                <td><label>活动时间：</label>
                  <input type="text" class="Wdate" id="promotionTimeFrom" name="promotionTimeFrom" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'promotionTimeTo\')}'})"
                         value="" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" /> --
                  <input type="text" class="Wdate" id="promotionTimeTo" name="promotionTimeTo" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'promotionTimeFrom\')}'})"
                         value="" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" />
                </td>
              </tr>
              <tr>
                <td><label>活动状态：</label>
                  <select class="coll_sele1" id="buildPromotionStatusConstants" name="buildPromotionStatusConstants">
                    <option value="-1">全部</option>
                    <c:forEach items="${statusSelect}" var="item">
                      <option value="${item.value}">${item.name}</option>
                    </c:forEach>
                  </select>
                </td>
                <td></td>
                <td></td>
              </tr>
              <tr class="down_btn_tr">
                <td colspan="3">
                  <a href="#" class="find_btn" id="search_href">查&nbsp;找</a>
                  <a href="#" class="reset_btn" id="reset_href">重&nbsp;置</a>
                </td>
                
              </tr>
            </table>
          </form>
        </div>
      </div>
      <a href="#" class="create_active_btn" id="create_href">创建活动</a>
      <div class="radiu wt20">
        <table width="100%" border="1" class="tk_table" id="activity_table">
          <thead>
          <th>活动名称</th>
          <th>起始时间</th>
          <th>结束时间</th>
          <th>活动状态</th>
          <th>商户补贴/元</th>
          <th>数量</th>
          <th>售价/元</th>
          <th>结算价/元</th>
          <th>服务项目</th>
          <th>操作</th>
          </thead>
        </table>
        <div id="no_data_div" style="width:112px;height:158px; margin:0 auto;display:none;">
          <img src="<%=ctx%>/images/no_pingjia.jpg">
        </div>
        <div class="page"></div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="<%=ctx%>/common/page-control.js"></script>
<script type="text/javascript" src="<%=ctx%>/page/myActivity/my_activity_list.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/util.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/popups.js"></script>
</body>
</html>
	