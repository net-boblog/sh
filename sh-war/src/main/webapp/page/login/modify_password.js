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
				$(this).addClass("add_bg");
			}, function() {
				$(this).find("#em_nr").animate({
							opacity : "hide"
						}, "fast");
				$(this).removeClass("add_bg");
			});

	function validate_pwd(ph) {
		var pattern = new RegExp(/^\d{6}$/);
		return pattern.test(ph);
	}
	$('.search_button').click(function() {

		var pwd = $("#pwd").val();
		var newpwd = $("#newpwd").val();
		var newpwd2 = $("#newpwd2").val();
		if (pwd == null || pwd == "") {
			alert("请输入原密码！");
			$("#pwd").val("");
			return;
		}
		if (newpwd == null || newpwd == "") {
			alert("请输入新密码！");
			return;
		}
		if(newpwd.length<6){
			alert("新密码不得少于6位！");
			return;
		}
		if (newpwd2 == null || newpwd2 == "") {
			alert("请重复新密码！");
			return;
		}
		if (newpwd != newpwd2) {
			alert("两次密码不一致！");
			return;
		}
		if (pwd == newpwd) {
			alert("新密码和原密码不能一致！");
			return;
		}
		$("#submit_button").attr("disabled", "disabled");
		$.ajax({
					url : ctx + "/page/login/modify_password.s",
					cache : false,
					ifModified : true,
					data : {
						"pwd" : hex_md5(pwd),
						"newpwd" : hex_md5(newpwd)
					},
					success : function(data) {
						var json = JSON.parse(data);
						var flag = json.flag;

						if (flag == "1") {
							alert("修改成功。");
							window.location = ctx
									+ "/page/validation/validation_query.jsp";
						} else {
							$("#submit_button").removeAttr("disabled");
							alert(json.info);
						}
					}
				});
	});
});