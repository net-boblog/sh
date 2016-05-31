<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/link.css?v=20160312" />
<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/common.css?v=20160312"/>
<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/tikuan.css?v=20160312"/>
<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/member.css?v=20160312"/>
<script type="text/javascript">
	 var ctx = '<%=ctx%>';
	 function quit(){
		/*  if(confirm("是否确定退出？"))
			 location.href = ctx + '/j_spring_security_logout'; */
		var length=document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
		$("#dialoglogout").show().css("top",length+300+"px");
		$("#halflogout").show().css("height",$(document).height());
	 }
	 function outbut(){
		 location.href = ctx + '/anon/login/logout.s';
	 }
	 function nothing(){
		 $("#dialoglogout").hide();
		 $("#halflogout").hide();
	 }
</script>

<div class="box_top">
        <div class="old_line"></div>
          <div class="top_right">
            <div class="user_box">
            <ul>

				<li class="use_name" id="open_status">
					<a>
						<span id="isOpen"></span>
						<img class="user_icon" src="../../images/zhanghao_icon.png">
					</a><i>|</i>
				</li>

              <li class="use_name"><a><span id="loginName"></span><img class="user_icon" src="../../images/zhanghao_icon.png"></a><i>|</i>
                <div id="em_nr" class="em_nr">
                <i class="em_out_sj"><img src="../../images/sanjiao.png"></i>
                <div class="em_out">
                 <a href="../account/allStoreInfo.s">账号信息</a><br>
                <a href="../login/modifyPasswordPage.s">修改密码</a><br>
                 <a href="javascript:outbut()">注销</a>
                </div>
              </div>
              </li>
              <li class="message"><a href="../message/page.s" style="padding-right:14px;"><img class="exit_icon" src="../../images/message_icon.png">消息</a><i>|</i><span id="unreadMessageNum"></span></li>
				<%--<li class="message"><a href="../opinion/index.s">意见反馈</a><i>|</i></li>--%>
				<li class="phone_code"><a><img class="exit_icon" src="../../images/phone_icon.png">手机版</a>
                 <div class="wei_ma"><img src="../../images/erweima.png"/></div>
              </li>
            </ul>
              
            </div>
          </div>
      
      </div>	
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/util.js?v=20160312"></script>
<script type="text/javascript" src="<%=ctx%>/js/popups.js?v=20160312"></script>
<script src="<%=ctx%>/js/head.js?v=20160527"></script>
<script type="text/javascript">
	$.ajax({
		url:ctx +'/page/message/unreadMessage.s',
		success:function(msg){
		//	var object= JSON.parse(msg);
			var object=msg;
			if(object!=null&&object.flag){
				if(object!=null&&object.flag){
					if(object.info!=null&&object.info!=0){
						$("#unreadMessageNum").show();
						$("#unreadMessageNum").text(object.info);
						$("#unredaMsgNum").text(object.info);
					}else{
						$("#unreadMessageNum").hide();
					}
					
				}
			}
		}
	});

    $.ajax({
        url:ctx +'/anon/login/getHeadTitle.s',
        success:function(data){
            if(data.success){
                $('#loginName').text(data.loginName);
            }
        }
    });

	
</script>
