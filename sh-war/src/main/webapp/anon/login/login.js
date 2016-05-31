$(function() {

	var $loginForm = $("#loginForm");
	var $enPassword = $("#enPassword");
	var $username = $("#username");
	var $password = $("#password");
	//var $captcha = $("#captcha");
	//var $captchaImage = $("#captchaImage");
	//var $isRememberUsername = $("#isRememberUsername");
	
	
	$loginForm.submit( function() {
		$username.val($.trim($username.val()));
		if ($username.val() == "") {
			alert('用户名为空！');
			return false;
		}
		if ($password.val() == "") {
			alert('密码为空！');
			return false;
		}
		$password.val(hex_md5($password.val()));
	});
});
