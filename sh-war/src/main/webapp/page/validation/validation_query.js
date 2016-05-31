// JavaScript Document
$(function() {
	      $("#service_list").addClass("nav_hover");
		});
$(function() {
			$('.tab_order ul li').click(function() {
				$(this).addClass('hit').siblings().removeClass('hit');
				$('.panes>div:eq(' + $(this).index() + ')').show().siblings()
						.hide();
			});

			showDiv($(".DialogDiv_0"));
			showDiv($(".DialogDiv_1"));
		});

function showDiv(obj) {

	center(obj);
	$(window).scroll(function() {
				center(obj);
			});
	$(window).resize(function() {
				center(obj);
			});
}

function center(obj) {
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $(obj).height();
	var popupWidth = $(obj).width();
	$(obj).css({
				"top" : (windowHeight - popupHeight) / 2
						+ $(document).scrollTop(),
				"left" : (windowWidth - popupWidth) / 2
			});
}

function show111() {
	$(".DialogDiv_1").show();
}

$(document).ready(function() {
	$(".search_box font").css("padding-left",
			$(".search_kk").offset().left - 200);
	$(".user_box ul li ").hover(function() {
				$(this).find("#em_nr").animate({
							opacity : "show"
						}, "slow");
				$(this).addClass("add_bg");
			}, function() {
				$(this).find("#em_nr").animate({
							opacity : "hide"
						}, "fast");
				$(this).removeClass("add_bg");
			});

	$("#validation_query_btn").parent("dd").addClass('nav_hover');

	$(".close").click(function() {
				$(".dialog_bj").hide();
				$(".DialogDiv_0").hide();
				$(".DialogDiv_1").hide();
			});

	$("#button_cancel").click(function() {
				$(".dialog_bj,.DialogDiv_0").css("display", "none");
			});

	$("#button_sure").click(function() {
		$("#sms_code").val($.trim($("#sms_code").val()));
		$.ajax({
					url : ctx + "/page/validation/verification.s",
					cache : false,
					ifModified : true,
					dataType:'json',
					data : {
						"sms_code" : $("#sms_code").val()
					},
					success : function(data) {
						// alert(data);
						var json = data;

						if (json.flag == 1) {
							$(".dialog_bj,.DialogDiv_0").css("display", "none");
							$(".DialogDiv_1").show();
						} else {
							alert(json.info);
							$(".dialog_bj,.DialogDiv_0").css("display", "none");
						}
					}
				});

	});

	$('.search_button').click(function() {
				if ($("#sms_code").val() == null || $("#sms_code").val() == '') {
					alert("请输入正确的服务验证码！！");
					return;
				}
				$("#sms_code").val($.trim($("#sms_code").val()));
				var regex = /^[^_][A-Za-z]*[a-z0-9_]*$/;
				var ret = regex.test($("#sms_code").val());
				if (ret == true) {
					// alert("输入正确");
				} else {
					alert("验证码不可以有特殊字符！");
					return;
				}

				$.ajax({
							url : ctx + "/page/validation/query_order.s",
							cache : false,
							ifModified : true,
							data : {
								"sms_code" : $("#sms_code").val()
							},
							success : function(data) {
								// alert(data);
								var json = JSON.parse(data);

								if (json.flag == '1') {
									var tdata = json.data;
									$("#content").html(tdata.content);
									$("#userinfo").html(tdata.userinfo);
									$("#server_type").html(tdata.server_type);
									$("#seller_note").html(tdata.seller_note);

									$(".dialog_bj").show();
									$(".DialogDiv_0").show();
								} else {
									alert(json.info);
								}

							}
						});

			});

	$("#button_continue").click(function() {
				$(".dialog_bj,.DialogDiv_1").css("display", "none");
			});

	$("#button_vieworder").click(function() {
		window.location.href = '/page/serverOrder/list.s?smsCode='
				+ $("#sms_code").val();
	});

//	$.ajax({
//				url : ctx + "/page/login/checksimple.s",
//				cache : false,
//				ifModified : true,
//				dataType:'json',
//				data : {
//					"sms_code" : "111"
//				},
//				success : function(data) {
//					// alert(data);
//					var json = data;
//
//					if (json.flag == '1') {
//						var tdata = json.data;
//
//					} else {
//						alert(json.info);
//						window.location.href = '../login/modify_password.jsp';
//					}
//
//				}
//			});

});
//addCookie("isClose", "qqq", {domain:".csqccr.com",expires:3600*24*30})加cookie
//removeCookie("isClose")删
//getCookie("isClose") 获取
$(function() {
	$height=$(window).height();
   $(".up_wrap").height($height);
		//	  if(!getCookie("isClose")){
		//		  $(".up_wrap").show();
		//		  $(".up_close").click(function(){
		//			  $(".up_wrap").hide();
		//		  }); 
		//		  addCookie("isClose", "qqq", {expires:300})//一天
		//	  }else{
		//		    $(".up_wrap").hide() ;
		//	  }
			 
			 if(window.location.href.split("#")[1]&&window.location.href.split("#")[1]=="openwin")
			 {
			   $(".up_wrap").show();		
			 }
			$(".up_close").click(function() {
						$(".up_wrap").hide();
						location.href = location.href.split("#")[0];
					});
		});
