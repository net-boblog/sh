<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>已验证订单</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
    <link href="../../css/link.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/tikuan.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/member.css"/>
    <link href="<%=ctx%>/css/page.css" rel="stylesheet"/>
    <script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../../js/basic.js"></script>
    <script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var ctx = '<%=ctx%>';
    </script>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    %>
    <script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
    <script src="../addon/server.js" type="text/javascript"></script>
    <script type="text/javascript" src="../../common/laydate/laydate.js"></script>
    <script type="text/javascript" src="../../common/page-control.js"></script>
</head>
<body>
<div class="content">
    <jsp:include page="../addon/link.jsp"></jsp:include>
    <div class="box_right">
        <jsp:include page="../addon/head.jsp"></jsp:include>
        <div class="wrap_right">
            <div class="title_mune">订单管理 > 已验证订单</div>
            <div class="radiu" style="padding-bottom:0;">
                <div class="collecting_cargo" style="padding-top:10px;">
                    <form action="<%=basePath%>/page/serverOrder/list.s" id="searchForm">
                        <input type="hidden" id="page_limit" name="page_limit" value="${paramMap.page_limit}">
                        <input type="hidden" id="page_offset" name="page_offset" value="${paramMap.page_offset}">
                        <input type="hidden" id="page_size" value="0">
                        <input type="hidden" id="smstime_updown" name="smstime_updown"
                               value="${paramMap.smstime_updown}">
                        <table width="100%" border="0" class="coll_table">
                            <tr>
                                <td><label>验证码：</label>
                                    <input type="text" class="coll_input" id="smsCode" name="smsCode"
                                           value="${paramMap.smsCode}"/>
                                </td>
                                <td><label>订单号：</label>
                                    <input type="text" class="coll_input" id="orderCode" name="orderCode"
                                           value="${paramMap.orderCode}"/>
                                </td>
                                <td><label>服务项目：</label>
                                    <select class="s_obj" id="service_type_one" name="service_type_one">
                                        <option value="0">全部服务</option>
                                    </select>
                                    <select class="s_obj" id="service_type_two" name="service_type_two">
                                        <option value="0">全部</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><label>验证时间：</label>
                                    <input type="text" class="Wdate" id="date_start" name="date_start" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'date_end\')||\'%y-%M-%d\'}'})"
                                           value="${paramMap.date_start}" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" /> --
                                    <input type="text" class="Wdate" id="date_end" name="date_end" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'date_start\')}',maxDate:'%y-%M-%d'})"
                                           value="${paramMap.date_end}" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" />
                                </td>
                                <td><label>订单状态：</label>
                                    <select class="coll_sele1" id="audit_status" name="audit_status">
                                        <c:forEach items="${auditMap}" var="item">
                                            <option value="${item.key}"
                                                    <c:if test="${paramMap.audit_status==item.key}">selected="selected"</c:if>>${item.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td></td>
                            </tr>
                            <tr class="down_btn_tr">
                                <td colspan="2">
                                    <a href="#" class="find_btn" id="search_href">查&nbsp;找</a>
                                    <a href="#" class="reset_btn" id="reset_href">重&nbsp;置</a>
                                </td>
                                <td align="right" style="padding-right: 10px;">
                                    <input id="data_out" type="button" value="导出数据" class="daochu"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="h"></div>
            <div class="radiu">
                <table width="100%" border="1" class="tk_table">
                    <thead>
                    <th>订单号</th>
                    <th>服务项目</th>
                    <th>数量</th>
                    <th class="condition">金额</th>
                    <th>验证码</th>
                    <th class="condition">验证时间<span><a href="#" id="smstime_updown_href">
                        <c:if test="${paramMap.smstime_updown == 0 }">
                            <img src="../../images/down_icon.png"/>
                        </c:if>
                        <c:if test="${paramMap.smstime_updown != 0 }">
                            <img src="../../images/top_icon.png"/>
                        </c:if>
                    </a></span></th>
                    <th>订单状态</th>
                    </thead>
                    <% int count = 0;%>
                    <c:forEach items="${resultMap}" var="result">
                        <tr>
                            <td>${result.orderNo}</td>
                            <td>${result.serverName}</td>
                            <td>${result.saleNum}</td>
                            <td class="orange small_pup">${result.saleSprice}
                                <c:if test="${result.promotionSource !=null}">
                                    <c:choose>
                                        <c:when test="${result.promotionSource==1}">
                                            <img src="../../images/cu_icon.png" class="promotion_img"/>
                                        </c:when>
                                        <c:when test="${result.promotionSource==2}">
                                            <img src="../../images/shang_icon.png" class="promotion_img"/>
                                        </c:when>
                                        <c:when test="${result.promotionSource==3}">
                                            <img src="../../images/meituan_icon.png" class="promotion_img"/>
                                        </c:when>
                                    </c:choose>
                                    <div class="tishi_text_box" style="display: none;">
                                        参加活动：${result.promotionName}
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                ${result.smsCode}
                            </td>
                            <td><fmt:formatDate value="${result.smsTime}" pattern="yyyy-MM-dd"/></td>
                            <c:choose>
                                <c:when test="${result.auditStatus == 0}">
                                    <td class="gray">审核通过</td>
                                </c:when>
                                <c:when test="${result.auditStatus == 1}">
                                    <td class="small_pup">
                                        <span class="orange">异常</span>
                                        <img src="../../images/yichang_icon.png" class="yicang_img"/>
                                        <div class="tishi_text_box" style="display: none;">
                                            <c:if test="${paramMap.belongUserName!=null && paramMap.belongUserName!='' }">
                                                <img src="../../images/top_xsj_icon.png"/>您可以拨打业务经理
                                                <span class="orange">${paramMap.belongUserName }</span>的电话，
                                                <span class="orange">${paramMap.belongUserPhone }</span>对异常订单进行申诉
                                            </c:if>
                                            <c:if test="${paramMap.belongUserName==null || paramMap.belongUserName=='' }">
                                                获取业务经理联系人信息失败
                                            </c:if>
                                        </div>
                                    </td>
                                </c:when>
                                <c:when test="${result.auditStatus == 2}">
                                    <td class="gray">待审核</td>
                                </c:when>
                            </c:choose>
                        </tr>
                        <% count = count + 1;%>
                    </c:forEach>
                </table>
                <% if (count == 0) {
                %>
                <div id="no_data_div" style="width:112px;height:158px; margin:0 auto;">
                    <img src="<%=basePath%>/images/no_pingjia.jpg">
                </div>
                <% }%>
                <div class="page"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        new Server("service_type_one", "service_type_two").init(${paramMap.service_type_one}, ${paramMap.service_type_two});
        addPageBtn('${paramMap.page_offset}', '${paramMap.page_limit}', '${paramMap.total}');
        $('#search_href').click(function () {
            $("#page_offset").val(0);
            search();
        });
        $("#reset_href").click(function () {
            window.location.href = "/page/serverOrder/list.s";
        });

        $(".tk_table").find(".yicang_img").mouseover(function () {
            $(this).next().show();
        });
        $(".tk_table").find(".yicang_img").mouseout(function () {
            $(this).next().hide();
        });

        $(".tk_table").find(".promotion_img").mouseover(function () {
            $(this).next().show();
        });
        $(".tk_table").find(".promotion_img").mouseout(function () {
            $(this).next().hide();
        });

        $('#smstime_updown_href').click(function () {
            $("#page_offset").val(0);
            if ($("#smstime_updown").val() == '0') {
                $("#smstime_updown").val('1');
            } else {
                $("#smstime_updown").val('0');
            }
            search();
        });
        //无数据时导出按钮置为不可用
        <% if (count == 0) { %>
        $("#data_out").attr("disabled",true);
        $("#data_out").css({"background":"url(../../images/icon2-1.png) 5px center no-repeat",
            "border":"#ccc 1px solid",
            "color":"#ccc"
        });
        <% }else{%>
        $("#data_out").attr("disabled",false);
        $("#data_out").css({"background":"url(../../images/icon2.png) 5px center no-repeat",
            "border":"#98999b 1px solid",
            "color":"#4c4d51"
        });
        <% } %>
        //导出数据
        $("#data_out").click(function(){
            $("#searchForm").attr("action", '<%=basePath%>/page/serverOrder/downLoad.s');
            $("#searchForm").attr("target", "_self");
            $("#searchForm").submit();
        });
    });
    function search() {
        $("#searchForm").attr("action", "<%=basePath%>/page/serverOrder/list.s");
        $("#searchForm").attr("target", "");
        $("#searchForm").submit();
    }
</script>
	