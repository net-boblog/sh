<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增服务</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <link rel="alternate icon" type="image/png" href="<%=ctx%>/images/logo_40.png">
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css?v=20160312" />
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/activity.css?v=20160312">
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/validate.css?v=20160312"/>
    <script type="text/javascript" src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/js/basic.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/js/link.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/common/laydate/laydate.js?v=20160312"></script>
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
            <div class="title_mune">我的服务 &gt; 修改服务</div>
            <form id="signupForm">
                <div class="radiu" style="padding-bottom: 0">
                    <table class="edit_active">
                        <tr>
                            <td class="edit_firsttd"><span class="red">*</span>服务名称</td>
                            <td><span class="edit_after">${productRo.productName}</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="edit_firsttd"><span class="red">*</span>服务项目</td>
                            <td>
				<span class="edit_after">
                      <select id="firstCategoryId" name="firstCategoryId" class="seviceobj">
                          <option >${categoryName}</option>
                      </select>-
                      <select id="itemAttrValueId1" name="itemAttrValueId1" class="seviceobj">
                          <option >${chandi}</option>

                      </select>
				</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="edit_firsttd"><span class="red">*</span>门店价格</td>
                            <td><span><input type="text" class="shortinput" id="marketAmt" name="marketAmt" value="${marketAmt}"> 元
                                <select id="itemAttrValueId2" name="itemAttrValueId2" class="seviceobj1">
                                    <option value="3">/${danwei}</option>
                                </select></span>
                                <p class="edit_text">门店设置大致的喷漆价格，可以根据米或者按面来算</p></td>
                        </tr>
                        <tr>
                            <td class="edit_firsttd"><span class="red">*</span>服务折扣</td>
                            <td><span><input type="text" class="shortinput" id="discount" name="discount" value="${productRo.discount}"> 折</span><p class="edit_text">车主在完成服务后可享受一定折扣的优惠 最低0.1折,最高9.9折</p></td>
                        </tr>
                    </table>
                </div>
                <input type="hidden" id="productId" value="${productRo.productId}">
            </form>
            <div class="radiu wt20 rprotocol">
                <img src="<%=ctx%>/images/dui1_03.png" class="ad_dui_1" onclick="img2Show();">
                <img src="<%=ctx%>/images/dui2_03.jpg" class="ad_dui_2" onclick="img1Show();">
                我已阅读并同意
                <a href="#" class="good_agreement">《汽车超人服务协议》</a>
            </div>
            <div class="down_btn_tr btn">
                <a href="#" class="find_btn" id="search_href">修&nbsp;改</a>
                <a href="/page/goods/goods_query.jsp" class="reset_btn" id="cancel_href">取&nbsp;消</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../goods/agreement.jsp"></jsp:include>
<script type="text/javascript" src="<%=ctx%>/js/util.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/jqueryUI/jquery.validate.js"></script>
<script type="text/javascript" src="<%=ctx%>/page/goods/update_goods.js?v=20160525"></script>
<script>
    function img2Show(){
        $(".ad_dui_1").hide();
        $(".ad_dui_2").show();
    }
    function img1Show(){
        $(".ad_dui_2").hide();
        $(".ad_dui_1").show();
    }
</script>
</body>
</html>
