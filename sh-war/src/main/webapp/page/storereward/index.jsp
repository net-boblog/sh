<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>门店奖励</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
  <!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
  <link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
  <link href="../../css/link.css" rel="stylesheet" />
    <link href="<%=ctx%>/css/page.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="/css/common.css"/>
  <link rel="stylesheet" type="text/css" href="/css/tikuan.css"/>
  <script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="/js/basic.js"></script>
</head>
<body>
<div class="content">

  <jsp:include page="../addon/link.jsp"></jsp:include>

  <div class="box_right">
    <jsp:include page="../addon/head.jsp"></jsp:include>

    <div class="wrap_right">
      <div class="title_mune">财务管理 > 门店奖励</div>
      <div class="reward_all radiu">
        <h5 class="can_amount">奖励总额<span>（元）</span></h5>
        <div class="all_money">
          <h1 class="orange">${rewardSums}</h1>
        </div>
      </div>

        <!--下面大div-->
    <div class="tk_records radiu">
        <table width="100%" border="1" class="tk_table t_center" id="xq_table">
          <thead>
          <th>奖励编号</th>
          <th>奖励金额<span class="gray">（元）</span></th>
          <th>时间</th>
          <th>类型</th>
          </thead>
        </table>
        <div id="nodata" style="display:none;">暂无数据</div>
        <div class="page clearfix"> </div>
      </div>
    </div>

  </div>

</div>

<input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
<input type="hidden" id="smstime_updown" value=0>

<form action="" method="post" id="fm1" target="fraSubmit">
</form>
<script type="text/javascript">
  var ctx = '<%=ctx%>';
</script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/laydate/laydate.js"></script>
<script type="text/javascript" src="/js/page-control.js"></script>
<script>
    search();
    function search(){
        $.ajax({
            url:ctx +"/page/storeReward/getHisReward.s",
            dataType:"json",
            data : {
                "page" : parseInt($("#page_offset").val())+1
            },
            success : function(data){
                if(data.flag==0){
                    window.location.href=ctx +"/page/common/error.jsp";
                }
                else{
                    $("#xq_table tr:gt(0)").remove();
                    var tdata = data.data.dataList;
                    var total = data.data.totalCount;
                    var offset = $("#page_offset").val();
                    var limit = data.data.pageSize;
                    addPageBtn(offset, limit, total);
                    if(total==0 || total==null){
                        $("#nodata").show();
                    }
                    var showClass=false;
                    if(tdata!=null){
                    	   for (var i = 0; i < tdata.length; i++) {
                               var newRow="";
                               if(showClass==true){
                                   newRow += "<tr class=\"even_bg\">";
                                   showClass=false;
                               }else{
                                   newRow += "<tr>";
                                   showClass=true;
                               }
                               newRow += "<td>"+tdata[i]["no"]+"</td>"+
                                       "<td class=\"orange\">"+tdata[i]["sum"]+"</td>"+
                                       "<td>"+tdata[i]["createTimeStr"]+"</td>"+
                                       "<td>"+tdata[i]["typeStr"]+"</td>"+
                                       "</tr>";
                               $("#xq_table tr:last").after(newRow);

                           }
                    }
                 
                }

            }
        });
    }
</script>

