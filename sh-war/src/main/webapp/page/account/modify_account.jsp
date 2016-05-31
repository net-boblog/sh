<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>账号信息</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
<link href="../../css/link.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="../../css/common.css?v=20160520"/>
<link rel="stylesheet" type="text/css" href="../../css/tikuan.css?v=20160520"/>
<link rel="stylesheet" type="text/css" href="../../css/member.css?v=20160520"/>
<style type="text/css" >
	.wrap{
		margin: -25px 1px 1px 280px ;
		height:22px;
		width: 900px;
	}
	.wrap dl dt{
		float: left;
	}
	.wrap dl dd{
		list-style-type: none;
	}
	.wrap dl dd ul li{
		float: left;
		margin-left: 10px;
		position: relative;
		list-style-type: none;
		padding:0;
	}
	.wrap dl dd ul li:hover{
		background: #F6F6F6;
	}
	.wrap dl dd a{
		padding: 0 20px;
		line-height: 22px;
		border: 1px solid #ccc;
		display: block;
		border-radius: 3px;
		text-decoration: none;
		color: #00BCD4;
	}
	.businessHours select{
		height: 24px;
	}
	 .click{
		border: 1px solid red;
		border-radius: 3px;
	}
	.bgimg{
		background-image: url(../../images/duigou.png);
		background-repeat: no-repeat;
		position: absolute;
		top:0;
		right: 0;
		margin-top: -6px;
		margin-right: -6px;
		height: 24px;
		width: 24px;
	}
</style>
</head>
<body>
 <div class="content">
    <jsp:include page="../addon/link.jsp"></jsp:include>
    <div class="box_right">
      <jsp:include page="../addon/head.jsp"></jsp:include>
			<div class="wrap_right">
				<div class="title_mune">账号信息</div>
				<div class="store_information radiu">
					<h5 class="can_amount">门店信息</span><a id="store_modify" class="bianji_btn">编 辑</a></h5>
					<input type="hidden" id="isModify" value="${isModify}">
					     <ul class="store_infor_text">
						     <li><b>门店名称 :</b><span id="name">${storeInfo.storeName}</span></li>
						     <li><b>门店地址 :</b><span id="address">${storeInfo.address}</span></li>
						 </ul>
						 <ul id="oldStoreInfo" class="store_infor_text">    
						     <li><b>营业时间 :</b><span id="bizTime">${storeInfo.bizStartTime}-${storeInfo.bizEndTime}
								 <c:forEach items="${closedCycleEnums}" var="result">
									 ${result.name}
								 </c:forEach>
							 </span></li>
							 <li><b>休业时间:</b><c:if test="${storeInfo.outBizStartDate != null && storeInfo.outBizStartDate != ''}"><span  id="start">${storeInfo.outBizStartDate} 至 </span> <span  id="end" >${storeInfo.outBizEndDate}</span></c:if><c:if test="${storeInfo.outBizStartDate == '' || storeInfo.outBizStartDate == null}">   未设置</c:if></li>
						     <li><b>手机号码 :</b><span id="phone">${storeInfo.telephone}</span></li>
						     <li><b>固话 :</b><span id="zonetel">${storeInfo.tel}</span></li>
						 </ul>
						 <ul id="newStoreInfo" class="store_edit" style="display:none">
							 <input type="hidden" id="bizStartTime" value="${storeInfo.bizStartTime}" />
							 <input type="hidden" id="bizEndTime" value="${storeInfo.bizEndTime}" />
						     <li class="businessHours" ><b>营业时间 :</b><select id="start_bizHour"  >
										<option value='0' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='00' || fn:substringBefore(storeInfo.bizStartTime, ':')==0} ">selected="selected"</c:if>>0</option>
										<option value='1' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='01' || fn:substringBefore(storeInfo.bizStartTime, ':')==1}">selected="selected"</c:if>>1</option>
										<option value='2' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='02' || fn:substringBefore(storeInfo.bizStartTime, ':')==2}">selected="selected"</c:if>>2</option>
										<option value='3' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='03' || fn:substringBefore(storeInfo.bizStartTime, ':')==3}">selected="selected"</c:if>>3</option>
										<option value='4' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='04' || fn:substringBefore(storeInfo.bizStartTime, ':')==4}">selected="selected"</c:if>>4</option>
										<option value='5' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='05' || fn:substringBefore(storeInfo.bizStartTime, ':')==5}">selected="selected"</c:if>>5</option>
										<option value='6' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='06' || fn:substringBefore(storeInfo.bizStartTime, ':')==6}">selected="selected"</c:if>>6</option>
										<option value='7' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='07' || fn:substringBefore(storeInfo.bizStartTime, ':')==7}">selected="selected"</c:if>>7</option>
										<option value='8' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='08' || fn:substringBefore(storeInfo.bizStartTime, ':')==8}">selected="selected"</c:if>>8</option>
										<option value='9' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')=='09' || fn:substringBefore(storeInfo.bizStartTime, ':')==9}">selected="selected"</c:if>>9</option>
										<option value='10' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==10}">selected="selected"</c:if>>10</option>
										<option value='11' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==11}">selected="selected"</c:if>>11</option>
										<option value='12' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==12}">selected="selected"</c:if>>12</option>
										<option value='13' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==13}">selected="selected"</c:if>>13</option>
										<option value='14' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==14}">selected="selected"</c:if>>14</option>
										<option value='15' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==15}">selected="selected"</c:if>>15</option>
										<option value='16' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==16}">selected="selected"</c:if>>16</option>
										<option value='17' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==17}">selected="selected"</c:if>>17</option>
										<option value='18' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==18}">selected="selected"</c:if>>18</option>
										<option value='19' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==19}">selected="selected"</c:if>>19</option>
										<option value='20' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==20}">selected="selected"</c:if>>20</option>
										<option value='21' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==21}">selected="selected"</c:if>>21</option>
										<option value='22' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==22}">selected="selected"</c:if>>22</option>
										<option value='23' <c:if test="${fn:substringBefore(storeInfo.bizStartTime, ':')==23}">selected="selected"</c:if>>23</option>
									</select>
									:<select id="start_bizMin">
										<option value='00' selected="selected" <c:if test="${fn:substringAfter(storeInfo.bizStartTime, ':')==00}">selected="selected"</c:if>>00</option>
										<option value='15' <c:if test="${fn:substringAfter(storeInfo.bizStartTime, ':')==15}">selected="selected"</c:if>>15</option>
										<option value='30' <c:if test="${fn:substringAfter(storeInfo.bizStartTime, ':')==30}">selected="selected"</c:if>>30</option>
										<option value='45' <c:if test="${fn:substringAfter(storeInfo.bizStartTime, ':')==45}">selected="selected"</c:if>>45</option>
									</select>
									-
									<select id="end_bizHour">
										<option value='0' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='00' || fn:substringBefore(storeInfo.bizStartTime, ':')==0}">selected="selected"</c:if>>0</option>
										<option value='1' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='01' || fn:substringBefore(storeInfo.bizStartTime, ':')==1}">selected="selected"</c:if>>1</option>
										<option value='2' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='02' || fn:substringBefore(storeInfo.bizStartTime, ':')==2}">selected="selected"</c:if>>2</option>
										<option value='3' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='03' || fn:substringBefore(storeInfo.bizStartTime, ':')==3}">selected="selected"</c:if>>3</option>
										<option value='4' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='04' || fn:substringBefore(storeInfo.bizStartTime, ':')==4}">selected="selected"</c:if>>4</option>
										<option value='5' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='05' || fn:substringBefore(storeInfo.bizStartTime, ':')==5}">selected="selected"</c:if>>5</option>
										<option value='6' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='06' || fn:substringBefore(storeInfo.bizStartTime, ':')==6}">selected="selected"</c:if>>6</option>
										<option value='7' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='07' || fn:substringBefore(storeInfo.bizStartTime, ':')==7}">selected="selected"</c:if>>7</option>
										<option value='8' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='08' || fn:substringBefore(storeInfo.bizStartTime, ':')==8}">selected="selected"</c:if>>8</option>
										<option value='9' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')=='09' || fn:substringBefore(storeInfo.bizStartTime, ':')==9}">selected="selected"</c:if>>9</option>
										<option value='10' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==10}">selected="selected"</c:if>>10</option>
										<option value='11' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==11}">selected="selected"</c:if>>11</option>
										<option value='12' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==12}">selected="selected"</c:if>>12</option>
										<option value='13' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==13}">selected="selected"</c:if>>13</option>
										<option value='14' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==14}">selected="selected"</c:if>>14</option>
										<option value='15' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==15}">selected="selected"</c:if>>15</option>
										<option value='16' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==16}">selected="selected"</c:if>>16</option>
										<option value='17' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==17}">selected="selected"</c:if>>17</option>
										<option value='18' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==18}">selected="selected"</c:if>>18</option>
										<option value='19' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==19}">selected="selected"</c:if>>19</option>
										<option value='20' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==20}">selected="selected"</c:if>>20</option>
										<option value='21' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==21}">selected="selected"</c:if>>21</option>
										<option value='22' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==22}">selected="selected"</c:if>>22</option>
										<option value='23' <c:if test="${fn:substringBefore(storeInfo.bizEndTime, ':')==23}">selected="selected"</c:if>>23</option>
									</select>
									:<select id="end_bizMin">
										<option value='00' selected="selected" <c:if test="${fn:substringAfter(storeInfo.bizEndTime, ':')==00}">selected="selected"</c:if>>00</option>
										<option value='15' <c:if test="${fn:substringAfter(storeInfo.bizEndTime, ':')==15}">selected="selected"</c:if>>15</option>
										<option value='30' <c:if test="${fn:substringAfter(storeInfo.bizEndTime, ':')==30}">selected="selected"</c:if>>30</option>
										<option value='45' <c:if test="${fn:substringAfter(storeInfo.bizEndTime, ':')==45}">selected="selected"</c:if>>45</option>
									</select>


								 <div class="wrap"  >
									 <dl>
										 <dd id="anniu">
											 <ul>

												 <c:forEach items="${closedCycleEnumsAll}" var="resultAll">
													 <c:forEach items="${closedCycleEnums}" var="result" >
														 <c:if test="${resultAll.code==result.code}">
															 <li class="click" value="${result.code}"><a href="#"  ><span>${result.name}</span></a><span class='bgimg'></span></li>
															 <%--<input type="checkbox" id="${result.code}" name="closedCycle" checked value="${result.code}" />${result.name}--%>
															 <c:set var="isDoing" value="1"/>
														 </c:if>
													 </c:forEach>
													 <c:if test="${isDoing != 1}">
														 <li value="${resultAll.code}"><a href="#" class="" ><span>${resultAll.name}</span></a></li>
														 <%--<input type="checkbox" id="${resultAll.code}" name="closedCycle" value="${resultAll.code}" />${resultAll.name}--%>
													 </c:if>
													 <c:set var="isDoing" value="2"/>
												 </c:forEach>
											 </ul>
										 </dd>
									 </dl>
								 </div>
								 		<%--<input type="checkbox" id="closedCycle7" name="closedCycle" checked value="SUNDAY" />周日--%>
								 		<%--<input type="checkbox" id="closedCycle1" name="closedCycle" checked value="MONDAY" />周一--%>
								 		<%--<input type="checkbox" id="closedCycle2" name="closedCycle" checked value="TUESDAY" />周二--%>
								 		<%--<input type="checkbox" id="closedCycle3" name="closedCycle" checked value="WEDNESDAY" />周三--%>
								 		<%--<input type="checkbox" id="closedCycle4" name="closedCycle" checked value="THURSDAY" />周四--%>
								 		<%--<input type="checkbox" id="closedCycle5" name="closedCycle" checked value="FRIDAY" />周五--%>
								 		<%--<input type="checkbox" id="closedCycle6" name="closedCycle" checked value="SATURDAY" />周六--%>
							  </li>
							 <br>

							 	<li><b>休业时间:</b><input type="text" class="Wdate" id="outBizStartDate" value="${storeInfo.outBizStartDate}" name="outBizStartDate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate:'${outBizStartDate}'})"
													value=" " style="width:129px; height:30px; margin-left:-3px; border:#eee 1px solid;" /> --
								 <input type="text" class="Wdate" id="outBizEndDate" name="outBizEndDate" value="${storeInfo.outBizEndDate}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'outBizStartDate\')}'})"
										value=" " style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" />

							 	</li>

							     <li><b>手机号码 :</b><input  class="editor_inpu" id='telephone' placeholder='手机号' value='${storeInfo.telephone }'/>
											<span id="msg1" class="date_prompt"><img src="../../images/yichang_icon.png"/>手机号格式有误</span>
											<span id="msg4"  class="date_prompt"><img src="../../images/yichang_icon.png"/>手机号及固话必填一项</span></li>
							     <li><b>固话 :</b><input id='tel'  class="editor_inpu" placeholder='区号-电话号码' value='${storeInfo.tel}'/>
											<span id="msg2"  class="date_prompt"><img src="../../images/yichang_icon.png"/>固话格式有误</span>
											<span id="msg5"  class="date_prompt"><img src="../../images/yichang_icon.png"/>手机号及固话必填一项</span></li>
						 </ul>
						<div class="down_btn_tr" id="store_button" style="display:none">
							<a href="#" class="find_btn" id="store_save">保&nbsp;存</a><a href="#" class="reset_btn" id="store_cancel">取&nbsp;消</a>
						</div>
					</div>
					
					
					<div class="draw_money_infor mt20 radiu" style="padding-bottom:0;">
					<h5 class="can_amount">收款信息</span><a class="bianji_btn" id="change_bankcard">更换银行卡</a></h5>
					<div class="car_box" id="old_info">
			     	 <h2>
			     	 	<c:choose>
			     	 	<c:when test="${storeInfo.storeSafetyBo.payWay==1 }">
			     	 	<img id="bank_logo" src="../../images/banklogo${storeInfo.storeSafetyBo.bankCode }.png"/><span id="oldbankname">${storeInfo.storeSafetyBo.bankName }</span>
			     	 	</c:when>
			     	 	<c:when test="${storeInfo.storeSafetyBo.payWay==2 }">
			     	 	<img id="bank_logo" src="../../images/zfb.png"/><span id="oldbankname">支付宝账号</span>
			     	 	</c:when>
			     	 	<c:when test="${storeInfo.storeSafetyBo.payWay==3 }">
			     	 	<img id="bank_logo" src="../../images/wx.png"/><span id="oldbankname">微信账号</span>
			     	 	</c:when>
			     	 	</c:choose>
			     	 </h2>
			      		<p id="oldbankno">
			      				${storeInfo.storeSafetyBo.payAccount }
						</p>
			      		<div class="h"></div>
   					</div>
						<ul class="shoukuan_editor" id="account_info" style="display:none">
					        <li><b>持卡人：</b><span id="payee">${storeInfo.storeSafetyBo.receiver }</span><span>&nbsp;&nbsp;（改持卡人请联系业务经理修改）</span></li>
					        <li>
					        	<b><span class="orange">*</span>开户银行 :</b><select class="check_bank" id="op_bank"><option value="">请选择银行</option></select><input id="otherbank" type="请填写具体的银行" class="tianxie_add" style="margin-left:10px;display:none;"/>
					        	<span id="remind1" class="date_prompt"><img src="../../images/yichang_icon.png"/>银行必选</span>
								<span id="remind2" class="date_prompt"><img src="../../images/yichang_icon.png"/>选择其他银行时银行全名必填</span>
					        </li>
					        <li>
					        	<b><span class="orange">*</span>开户支行 :</b><select id="payeeprovince" class="area_sel as1"><option value="">请选择省份</option></select>
					        	<select id="payeecity" class="area_sel as2"><option value="">请选择城市</option></select>
					        	<span id="remind3" class="date_prompt"><img src="../../images/yichang_icon.png"/>省市必填</span>
					        </li>
					        <li><b style="color:#fff;">没有内容</b><input id="branchbank" type="请填写具体的开户支行" class="tianxie_add"/><a href="javascript:void():" class="tixing_popup"><img src="../../images/wenhao_icon.png"/><span id="remind4" class="date_prompt"><img src="../../images/yichang_icon.png"/>具体开户支行必填</span>
					        <div class="tishi_text_box"><img src="../../images/top_xsj_icon.png"/>开户行支行是指银行卡开户行的分支机构名称，可以通过拨打银行电话查询。</div></a>
					       	
					       </li>
					        <li>
					        	<b><span class="orange">*</span>账号：</b><input id="account_no" class="tianxie_add"/>
					        	<span id="remind5" class="date_prompt"><img src="../../images/yichang_icon.png"/>账号必填</span>
					        	<span id="remind6" class="date_prompt"><img src="../../images/yichang_icon.png"/>账号格式错误</span>
					       </li>
					    </ul>
						<div id="account_button" class="down_btn_tr" style="padding:20px 90px;display:none">
						<a href="#" class="find_btn" id="modify_account">保&nbsp;存</a>
						<a href="#" class="reset_btn" id="cancel_account">取&nbsp;消</a></div>
					    <p class="xiugai_rule_ts"><span class="orange">修改规则：</span>为保证账户安全，只能修改一次银行卡。</p>
					    </div>
						
						
			</div>
		<input type="hidden" id="changeCount" value="${storeInfo.changeCount}">
		<%--<input type="hidden" id="changeCount" value="1">--%>
		<input type="hidden" id="storeId" value="${storeInfo.storeId}">
	</div>
	<jsp:include page="../common/popup.jsp"></jsp:include>
  </body>
</html>

<script type="text/javascript">
	var ctx = '<%=ctx%>';
</script>
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script src="./modify_account.js?v=201605272" type="text/javascript"></script>
<script type="text/javascript" src="<%=ctx%>/js/basic.js?v=20160520"></script>
<script src="<%=ctx%>/js/popups.js?v=20160520" type="text/javascript"></script>
<script type="text/javascript" src="<%=ctx%>/common/datePicker/WdatePicker.js"></script>
	