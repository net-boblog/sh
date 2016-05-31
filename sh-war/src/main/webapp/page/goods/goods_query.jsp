<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商品</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
    <link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
    <link href="/css/link.css" rel="stylesheet"/>
    <link href="/css/page.css" rel="stylesheet"/>
    <link href="/css/common.css" rel="stylesheet"/>
    <link href="/css/tikuan.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css?v=20160312"/>
    <script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="content">
    <jsp:include page="../addon/link.jsp"></jsp:include>
    <div class="box_right">
        <jsp:include page="../addon/head.jsp"></jsp:include>
        <div class="wrap_right">
            <%--<a href="#" class="create_goods_btn" id="create_href">新增服务</a>--%>
            <div class="title_mune">我的服务</div>
            <div class="collecting_cargo">
                <table class="tk_table" id="ordertable" width="100%" style="background:#fff;">
                    <thead>
                      <th>服务项目</th>
                      <th>结算价格(元)</th>
                      <th>已服务订单数</th>
                      <th>状态</th>
                      <%--<th>操作</th>--%>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>

<script type="text/javascript">
    var ctx = '<%=ctx%>';
</script>
<script type="text/javascript" src="<%=ctx%>/js/basic.js"></script>
<script src="<%=ctx%>/page/goods/goods_query.js?v=201605261" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=ctx%>/js/util.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
</body>
	
	