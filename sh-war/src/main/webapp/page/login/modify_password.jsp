<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单查询</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!--<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />-->
<link rel="alternate icon" type="image/png" href="../../images/logo_40.png">
<link href="./modify_password.css" rel="stylesheet" />
<link href="../../css/link.css" rel="stylesheet" />
<link href="<%=ctx%>/css/page.css" rel="stylesheet" />
<link href="<%=ctx%>/css/common.css" rel="stylesheet" />
</head>
<body>
 <div class="content">
 
    <jsp:include page="../addon/link.jsp"></jsp:include>

    <div class="box_right">
      <jsp:include page="../addon/head.jsp"></jsp:include>
      
        <div class="h"></div><div class="h"></div><div class="h"></div>
			<div class="cont_right">
				<div class="search_box">
					<div class="tck_content">
						<ul>
							<li class="row">
								<div class="search_box">
									输入原密码&ensp;&ensp;<input style="color: #323232;" class="search_kk"
										placeholder="请输入原密码" maxlength="30" autocomplete="off" id="pwd"
										type="password">
								</div>
							</li>
							<li class="row">
								<div class="search_box">
									输入新密码&ensp;&ensp;<input style="color: #323232;" class="search_kk"
										placeholder="请输入新密码" maxlength="30" autocomplete="off" id="newpwd"
										type="password">
								</div>
							</li>
							<li class="row">
								<div class="search_box">
									确认新密码&ensp;&ensp;<input style="color: #323232;" class="search_kk"
										placeholder="请再次输入新密码" maxlength="30" autocomplete="off" id="newpwd2"
										type="password">
								</div>
							</li>
							<li class="row"><input class="search_button" value="提  交" id="submit_button"
								type="button"></li>
							<li class="row">注：密码修改后（汽配超人）批发商城登录账号也将一起变化</li>
						</ul>
					</div>
				</div>
				<div class="tab_order">
					<div class="panes">
						<div class="pane"
							style="display: block; overflow: hidden; padding-bottom: 20px;">

						</div>
					</div>
				</div>
			</div>


		</div>
  </div>

<input type="hidden" id="page_limit" value=10>
<input type="hidden" id="page_offset" value=0>
<input type="hidden" id="page_size" value=0>
    
<script src="<%=ctx%>/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var ctx = '<%=ctx%>';
	var protocol =document.location.protocol;
	if(protocol=="https:"){
		if(!${useHttpsUrl}){
			window.location.href="http"+window.location.href.substring(5);
		}
	}
	else{
		if(${useHttpsUrl}){
			window.location.href="https"+window.location.href.substring(4);
		}
	}
</script>
<script type="text/javascript" src="../../common/md5.js"></script>
<script src="./modify_password.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
	
	