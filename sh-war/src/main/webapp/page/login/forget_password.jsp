<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单查询</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<link rel="icon" href="/eins/image/favicon.ico" type="image/x-icon" />
<link href="./forget_password.css" rel="stylesheet" />
<link href="../../css/link.css" rel="stylesheet" />
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
									<input style="color: #ABA9A9;" class="search_kk"
										placeholder="请输入原密码" maxlength="30" autocomplete="off" id="pwd"
										type="text">
								</div>
							</li>
							<li class="row">
								<div class="search_box">
									<input style="color: #ABA9A9;" class="search_kk"
										placeholder="请输入新密码" maxlength="30" autocomplete="off" id="newpwd"
										type="text">
								</div>
							</li>
							<li class="row">
								<div class="search_box">
									<input style="color: #ABA9A9;" class="search_kk"
										placeholder="请再次输入新密码" maxlength="30" autocomplete="off" id="newpwd2"
										type="text">
								</div>
							</li>
							<li class="row"><input class="search_button" value="提交" id="submit_button"
								type="button"></li>
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
</script>
<script src="./forget_password.js" type="text/javascript"></script>
<script src="<%=ctx%>/js/link.js" type="text/javascript"></script>
<script type="text/javascript" src="../../common/My97DatePicker/WdatePicker.js"></script>
	
	