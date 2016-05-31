// JavaScript Document

$(function() {
			$('.tab_order ul li').click(function() {
				$(this).addClass('hit').siblings().removeClass('hit');
				$('.panes>div:eq(' + $(this).index() + ')').show().siblings()
						.hide();
			});
		});

$(document).ready(function() {
			$(".user_box ul li ").hover(function() {
						$(this).find("#em_nr").animate({
									opacity : "show"
								}, "slow");
					}, function() {
						$(this).find("#em_nr").animate({
									opacity : "hide"
								}, "fast");
					});

			$("#order_query_btn").addClass('nav_hover');

			$('.search_button').click(function() {
						alert(1);
						alert($.md5("123456"));

						var pwd = $("#pwd").val();
						var newpwd = $("#newpwd").val();
						var newpwd2 = $("#newpwd2").val();
						if (pwd == null || pwd == "") {
							alert("请输入原密码！");
						}
						if (newpwd == null || newpwd == "") {
							alert("请输入新密码！");
						}
						if (newpwd2 == null || newpwd2 == "") {
							alert("请重复新密码！");
						}
						if (newpwd == newpwd2) {
							alert("两次密码不一致！");
						}

						$.ajax({
									url : ctx + "/page/login/modify_password.s",
									cache : false,
									ifModified : true,
									data : {
										"pwd" : pwd,
										"newpwd" : newpwd,
										"newpwd2" : newpwd2
									},
									success : function(data) {
										var json = JSON.parse(data);
										var flag = json.flag;

										if (flag == "1") {
											alert("修改成功。");
										} else {
											alert(json.info);
										}

									}
								});
					});
		});