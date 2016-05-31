<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>超人活动</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <link rel="alternate icon" type="image/png" href="<%=ctx%>/images/logo_40.png">
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css?v=20160312" />
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/page.css?v=20160312" />
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/activity.css?v=20160312">
  <link rel="stylesheet" type="text/css" href="<%=ctx%>/page/activity/css/short.css">
    <script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/js/basic.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js?v=20160312"></script>
    <script src="<%=ctx%>/js/link.js?v=20160312" type="text/javascript"></script>
    <script src="<%=ctx%>/page/addon/server.js?v=20160312" type="text/javascript"></script>
    <script type="text/javascript" src="<%=ctx%>/common/laydate/laydate.js?v=20160312"></script>
    <script type="text/javascript" src="<%=ctx%>/common/page-control.js?v=20160312"></script>
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
            <div class="title_mune">活动管理 > 超人活动</div>
            <div class="radiu" style="padding-bottom:0;">
                <div class="collecting_cargo" style="padding-top:10px;">
                    <form action="<%=ctx%>/page/activity/list.s" id="searchForm" method="post">
                        <input type="hidden" id="page_limit" name="page_limit" value="${paramMap.page_limit}">
                        <input type="hidden" id="page_offset" name="page_offset" value="${paramMap.page_offset}">
                        <input type="hidden" id="page_size" value="5">
                        <input type="hidden" id="total" value="${paramMap.total}">
                        <input type="hidden" id="act_id" name="act_id" value="0">
                        <input type="hidden" id="status" name="status" value="0">
                        <input type="hidden" id="order_type" name="order_type" value="${paramMap.order_type}">
                        <input type="hidden" id="firstService" name="firstService" value="${param.first_service}">
                        <input type="hidden" id="secondService" name="secondService" value="${param.second_service}">
                        <table width="100%" border="0" class="coll_table">
                            <tr>
                                <td><label>活动名称：</label>
                                    <input type="text" class="coll_input" id="act_name" name="act_name"
                                           value="${paramMap.act_name}"/>
                                </td>
                                <td><label>服务项目：</label>
                                    <select class="s_obj" id="first_service" name="first_service">
                                        <option value="0">全部</option>
                                    </select>
                                    <select class="s_obj" id="second_service" name="second_service">
                                        <option value="0">全部</option>
                                    </select>
                                </td>
                                <td><label>活动时间：</label>
                                    <input type="text" class="Wdate" id="date_start" name="date_start" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'date_end\')||\'%y-%M-%d\'}'})"
                                           value="${paramMap.date_start}" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" /> --
                                    <input type="text" class="Wdate" id="date_end" name="date_end" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'date_start\')}'})"
                                           value="${paramMap.date_end}" style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" />
                                </td>
                            </tr>
                            <tr>
                                <td><label>活动状态：</label>
                                    <select class="coll_sele1" id="act_state" name="act_state">
                                        <option value="">全部</option>
                                        <c:forEach items="${auditMap}" var="item">
                                            <option value="${item.value}"
                                                    <c:if test="${paramMap.act_state==item.value}">selected="selected"</c:if>>${item.name}</option>
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
            <div class="sp_list">
                <div class="fl">
                    <div class="platform_bt_wa">
                        <span class="button_con"><a href="javascript:searchOrder(1)" class="addStore super_hot_act"
                                                    id="hrefHotAct" target="_self" style="width:100px;">热门活动</a></span>
                    </div>
                </div>

                <div class="act_time">
                    <div class="platform_bt_time">
						    <span class="button_con">
						    	<a href="javascript:searchOrder(2)" class="addStore super_act_time" style="width:100px;" target="_self" id="hrefActTime">
                                    活动时间
                                    <img src="<%=ctx%>/images/u123_03.png" id="sortUp" class="act_time_123" />
                                    <img src="<%=ctx%>/images/u124_03.png" id="sortDown" class="act_time_124" />
                                </a>

						    </span>
                    </div>
                </div>
            </div>
            <div class="radiu">
                    <% int count = 0;%>
                    <c:forEach items="${resultMap}" var="result">
                            <div class="Wa_approval">
                                <div id="contentList1" class="contont_list_content">
                                    <div class="supera_content_block">
                                        <c:choose>
                                            <c:when test="${result.webEnrollPhoto == ''}">
                                                <img src="<%=ctx%>/images/u2.png" class="supera_block_img" />
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${paramMap.staticUrl}${result.webEnrollPhoto}" class="supera_block_img" />
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="fl">
                                            <div class="supera_block_title">
                                                <a class="supera_block_title" href="javascript:apply(${result.promotionId}, ${result.promotionStatus})">${result.name}</a></div>
                                      <div style="width:100%">
                                            <div class="supera_block_text">
                                                <span class="sp_service">服务项目：</span>
                                                <span>${result.secondCategoryName}</span>
                                            </div>
                                      </div>
                                            <div class="supera_block_text">
                                            <div class="active_time">
                                                <span class="sp_act_backtime">活动时间：</span>
                                                <span><fmt:formatDate value="${result.startTime}" pattern="M月d日"/>-
                                                    <fmt:formatDate value="${result.endTime}" pattern="M月d日"/></span>
                                            </div>
                                             <div class="active_area">
                                                <span class="sp_act_area">活动地区：</span>
                                                <span>${paramMap.province}  ${paramMap.city}</span>
                                            </div>
                                            </div>
                                           
                                            <div class="supera_block_text mrl_0"></div>
                                           
                                           <div class="baoming_stop" id="baoming_stop">
                                               <c:if test="${result.promotionStatus==1}">
                                                <div class="remainSeconds" id="remainSeconds<%=count%>" style="display:none;">
                                                        ${(result.endRegistrationTime.time - paramMap.nowTime.time)}</div>
                                                <span class="sp_time">离报名截止日期还剩：</span>
                                                <span class="remainTime" id="remainTime<%=count%>" style="color:#e62d46"></span>
                                               </c:if>
                                            </div>
                                        </div>
                                        <div class="sp_block_right fr">
                                            <div class="mart_40"></div>
                                            <span class="sp_font_14">已报名商户：</span>
                                            <span class="sp_font_14 red">${result.applyNumber}</span>
                                            <div class="add">
                                        <c:choose>
                                            <c:when test="${result.promotionStatus==1}">
                                                <div class="sp_bt_sign" opt="remainTime<%=count%>">
                                                    <span class="button_con">
                                                        <a href="javascript:apply(${result.promotionId}, ${result.promotionStatus})" class="addStore" target="_self">
                                                            我要报名</a>
                                                    </span>
                                                </div>
                                            </c:when>
                                            <c:when test="${result.promotionStatus==2}">
                                                <div class="sp_bt_stop">
                                                    <span class="button_con">
                                                        <a href="javascript:apply(${result.promotionId}, ${result.promotionStatus})" class="addStore" target="_self">
                                                        已报名</a></span>
                                                </div>
                                            </c:when>
                                            <c:when test="${result.promotionStatus==3}">
                                                <div class="sp_bt_stop">
                                                    <span class="button_con"><a href="javascript:apply(${result.promotionId}, ${result.promotionStatus})" class="addStore" target="_self">
                                                        报名截止</a></span>
                                                </div>
                                            </c:when>
                                            <c:when test="${result.promotionStatus==4}">
                                                <div class="sp_bt_stop">
                                                    <span class="button_con"><a href="javascript:apply(${result.promotionId}, ${result.promotionStatus})" class="addStore" target="_self">
                                                        已开始</a></span>
                                                </div>
                                            </c:when>
                                            <c:when test="${result.promotionStatus==5}">
                                                <div class="sp_bt_stop">
                                                    <span class="button_con"><a href="javascript:apply(${result.promotionId}, ${result.promotionStatus})" class="addStore" target="_self">
                                                        已结束</a></span>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="sp_bt_sign" opt="remainTime<%=count%>">
                                                    <span class="button_con">
                                                        <a href="javascript:apply(${result.promotionId}, ${result.promotionStatus})" class="addStore" target="_self">
                                                            我要报名</a>
                                                    </span>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <% count = count + 1;%>
                    </c:forEach>
                <c:if test="${paramMap.total=='0'}" >
                    <div id="no_data_div" style="width:112px;height:158px; margin:0 auto;">
                        <img src="<%=ctx%>/images/no_pingjia.jpg">
                    </div>
                </c:if>

                <div class="page"></div>
            </div>
        </div>
        <jsp:include page="../common/popup.jsp"></jsp:include>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=ctx%>/common/page-control.js?v=2016031201"></script>
<script type="text/javascript" src="<%=ctx%>/page/activity/act-list.js?v=03152016031104"></script>

<script type="text/javascript">
        <% int idx = 0;%>
        <c:forEach items="${resultMap}" var="result">
        <c:if test="${result.promotionStatus==1}">

        var SysSecond<%=idx%> = parseInt($("#remainSeconds<%=idx%>").html())/1000;
        var InterValObj<%=idx%> = window.setInterval(SetRemainTime<%=idx%>, 1000);
        function SetRemainTime<%=idx%>() {
            if (SysSecond<%=idx%> >= 1) {
                SysSecond<%=idx%> = SysSecond<%=idx%> - 1;
                var second = Math.floor(SysSecond<%=idx%> % 60);
                var minite = Math.floor((SysSecond<%=idx%> / 60) % 60);
                var hour = Math.floor((SysSecond<%=idx%> / 3600) % 24);
                var day = Math.floor((SysSecond<%=idx%> / 3600) / 24);
                $("#remainTime<%=idx%>").html(day + "天" + hour + "小时" + minite + "分" + second + "秒");
            } else {
                $('[opt="remainTime<%=idx%>"]').css("background","#9eabb6");
                $('[opt="remainTime<%=idx%>"]').find("a").text("报名截止");
                window.clearInterval(InterValObj<%=idx%>);
            }
        }
        </c:if>
        <% idx = idx + 1;%>
        </c:forEach>

</script>
</html>