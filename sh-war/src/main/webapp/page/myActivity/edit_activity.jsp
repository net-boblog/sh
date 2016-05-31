<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑我发布的活动</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <link rel="alternate icon" type="image/png" href="<%=ctx%>/images/logo_40.png">
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css?v=20160312" />
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/validate.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/activity.css?v=20160312">
    <script type="text/javascript" src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/js/basic.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/js/link.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/common/laydate/laydate.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
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
            <div class="title_mune">活动管理 &gt; 编辑我发布的活动</div>
            <form id="signupForm">
            <div class="radiu" style="padding-bottom: 0">
                <input type="hidden" id="promotionId" name="promotionId" value="${activity.promotionId}"/>
                <input type="hidden" id="firstCategoryIdHide" name="firstCategoryIdHide" value="${activity.firstCategoryId}"/>
                <input type="hidden" id="secondCategoryIdHide" name="secondCategoryIdHide" value="${activity.secondCategoryId}"/>
                <input type="hidden" id="startTimeHide" name="startTimeHide" value="<fmt:formatDate value="${activity.startTime}" pattern="yyyy-MM-dd"/>"/>
                <input type="hidden" id="cycleTimes" name="cycleTimes" value="${activity.cycleTimes}"/>
                <table class="edit_active">
                    <tr>
                        <td class="edit_firsttd"><span class="red">*</span>活动促销</td>
                        <td><span class="edit_after"><input type="text" id="name" name="name" value="${activity.name}" class="activecuxiao" required></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="edit_firsttd"><span class="red">*</span>服务项目</td>
                        <td>
					        <span class="edit_after">
                              <select id="firstCategoryId" name="firstCategoryId" onchange="serviceSelectOnchange()" class="seviceobj">
                                  <option value="">请选择</option>
                              </select>-
                              <select id="secondCategoryId" name="secondCategoryId" class="seviceobj" required>
                                  <option value="">请选择</option>
                              </select>
					        </span>
                        </td>
                    </tr>
                    <tr>
                        <td class="edit_firsttd"><span class="red">*</span>活动时间</td>
                        <td><span class="edit_after">
                            <input type="text" class="Wdate" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'%y-%M-{%d+1}'})"
                                   value="<fmt:formatDate value="${activity.startTime}" pattern="yyyy-MM-dd"/>" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;"/> --
                            <input type="text" class="Wdate" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})"
                                   value="<fmt:formatDate value="${activity.endTime}" pattern="yyyy-MM-dd"/>" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" required/>
                            </span>
					        <p class="edit_text">指活动生效的时间段。设置日期须从当前的第二天开始</p>
                        </td>
                    </tr>
                    <tr>
                        <td class="edit_firsttd"><span class="red">*</span>销售数量</td>
                        <td><span><input type="text" class="shortinput" id="saleNumber" name="saleNumber" value="${activity.saleNumber}" required> 件</span><p class="edit_text">商家发起该活动所提供的服务数量</p></td>
                    </tr>
                    <tr>
                        <td class="edit_firsttd"><span class="red">*</span>限购周期</td>
                        <td><span><input type="text" class="shortinput" id="cycleDays" name="cycleDays" value="${activity.cycleDayes}" required data-gt="0"/> 天/次</span><p class="edit_text">车主在一定周期内只能享受一次优惠，例7天/次，表示7天内该用户只能享受此活动1次</p></td>
                    </tr>
                    <tr>
                        <td class="edit_firsttd"><span class="red">*</span>商户补贴金额</td>
                        <td><span><input type="text" class="shortinput" id="merchantAllowance" name="merchantAllowance" value="${activity.merchantAllowance/100}" required> 元</span><p class="edit_text">商户提供的优惠额度，设置的金额数字＞0</p></td>
                    </tr>
                </table>
            </div>
            </form>
            <div class="radiu wt20 rprotocol">
                <img src="/images/dui1_03.png" class="ad_dui_1" style="display: none;">
                <img src="/images/dui2_03.jpg" class="ad_dui_2" style="display: inline;">
                我已阅读并同意
                <a href="#" class="act_agreement">《汽车超人活动协议》</a>
            </div>
            <div class="down_btn_tr btn">
                <a href="#" class="find_btn" id="search_href">提&nbsp;交</a>
                <a href="/page/myActivity/index.s" class="reset_btn" id="cancel_href">取&nbsp;消</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/popup.jsp"></jsp:include>
<jsp:include page="../activity/agreement.jsp"></jsp:include>
<script type="text/javascript" src="<%=ctx%>/page/myActivity/edit_activity.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/util.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/jqueryUI/jquery.validate.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/jqueryUI/messages_cn.js"></script>
</body>
</html>
