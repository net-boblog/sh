$(function() {
	$("#username").focus(function(){
		$("#date_prompt").hide();
	});
	provinces(0);
	
	var $loginForm = $("#loginForm");
	
	var $username = $("#storename");
	var $password = $("#store_owner");
	//var $captcha = $("#captcha");
	//var $captchaImage = $("#captchaImage");
	//var $isRememberUsername = $("#isRememberUsername");

	$("#submitbtn").click( function() {
		var guhua=/^0\d{2,3}-?\d{7,8}$/;
		var tel=/^1\d{10}$/;
		$username.val($.trim($username.val()));
		if ($username.val() == "") {
			alert('加盟店名称为空！');
			return false;
		}
		else if ($password.val() == "") {
			alert('联系人为空！');
			return false;
		}
		else if ($("#store_tel").val() == "") {
			alert('联系电话为空！');
			
			return false;
		}
		else if(!guhua.test($("#store_tel").val())&&!tel.test($("#store_tel").val())){
			alert('电话格式有误，请输入正确的电话联系方式');
			return false;
		}
		else if ($("#province").val() == "") {
			alert('省份为空！');
			return false;
		}
		else if ($("#city").val() == "") {
			alert('城市为空！');
			return false;
		}
		else if ($("#areaId").val() == "") {
			alert('区域为空！');
			return false;
		}
		var itemid='';
		var serviceid='';
		$("input[name='citem']").each(function(){
			if($(this).is(':checked')){
				//itemid=itemid+'1';
				itemid += $(this).val()+","
			}else{
				itemid=itemid;
				
			}
			$("#itemid").val(itemid);
			
		})
		$("input[name='service']:checked").each(function(){
			if(serviceid==''){	
				serviceid=$(this).val();
			}else{
				serviceid=serviceid+','+$(this).val();
				
			}
			$("#serviceid").val(serviceid);
		})
		$loginForm.submit();
	});
	
	
	$("#sendcode").click(function(){
		var username =$.trim($("#username").val());
		if(username==""){
			$(".date_prompt").show(300).delay(3000).hide(300).html("请输入账号！");
			return false;
		}
		
		$.ajax({
			
			url : ctx + '/anon/apply/sendmsg.s',
			type: 'post',
			data: 'username='+username,
			asyn: false,
			success : function(data){
				var data = $.parseJSON(data);
				$("#sendmes").empty();
				$("#sendmes").append(data.msg);
			}
			
		});
	});
	
	$("#nextbutton").click(function(){
		if($.trim($("#username").val())==""){
			$(".date_prompt").show();
			return false;
		}else if($.trim($("#identifying_code").val())==""){
			$("#perrow_tishi").show(300).delay(3000).hide(300).find(".perrow_tishi").html("请输入验证码！");
			return false;
		}
		var identifying_code=$("#identifying_code").val();
		var username=$("#username").val();
		$.ajax({
			url : ctx + '/anon/apply/checkuser.s',
			type : 'get',
			data : 'identifying_code=' + identifying_code +'&username='+username ,
			asyn : false,
			success : function(data) {
				var data = $.parseJSON(data);
				if (data.flag == 'success') {
					$("#checkform").submit();
					return;
				}else {
					
					$("#perrow_tishi").show(300).delay(3000).hide(300).find(".perrow_tishi").html("验证码错误");
				}
				
			}
		});
	});
	
	$("#resetsure").click(function(){
		if($.trim($("#password").val())==""){
			alert("请输入密码！");
			return false;
		}else if($.trim($("#sure_password").val())==""){
			alert("请输入确认密码！");
			return false;
		}else if($.trim($("#password").val())!=$.trim($("#sure_password").val())){
			alert("两次密码输入不相同！");
			return false;
		}
		var temp=$("#password").val();
		$("#password").val(hex_md5(temp));
		$("#sure_password").val(hex_md5(temp));
		var password=$("#password").val();
		var username=$("#username").val();
		$.ajax({
			url : ctx + '/anon/apply/password_resetDo.s',
			type : 'post',
			data : 'password=' + password +'&username='+username ,
			asyn : false,
			success : function(data) {
				var data = $.parseJSON(data);
				if(data.flag=='success'){
					location.href="reset_success.jsp";
				}else{
					alert(data.flag);
				}
				
			}
		});
	})
	
});

/**
 * 加载所有的省份
 */
function provinces(serverId) {
	jQuery.ajax({
		url : ctx + '/anon/apply/provinces.s?serverId=0',
		type : 'get',
		asyn : false,
		success : function(data) {
			$("#province").children().remove();
			if (data == null || data.length == 0) {
				return;
			}
			var strOption = "<option value=''>选择省份</option>";
			for (var i = 0; i < data.length; i++) {
				strOption += '<option value="' + data[i].code + '">'
						+ data[i].desc + '</option>';
			}
			$("#province").append(strOption);
		}
	});
}
/**
 * 加载省份下的城市
 */
function citys(province) {
	
	if (province == '') {
		return;
	}
	$.ajax({
		url : ctx + '/anon/apply/city.s',
		type : 'post',
		data : 'province=' + province + '&serverId=0',
		success : function(data) {
			$("#city").children().remove();
			if (data == null || data.length == 0) {
				return;
			}
			var strOption = "<option value=''>选择城市</option>";
			for (var i = 0; i < data.length; i++) {
				strOption += '<option value="' + data[i].code + '">'
						+ data[i].desc + '</option>';
			}

			$("#city").append(strOption);
		}
	});
	
}
/**
 * 加载省份和城市下的区域
 */
function areas(city) {
	var province = $("#province").val();
	
	if (city == '') {
		return;
	}
	$.ajax({
		url : ctx + '/anon/apply/area.s',
		type : 'post',
		data : 'province=' + province + '&serverId=0' + '&city=' + city,
		success : function(data) {
			$("#areaId").children().remove();
			if (data == null || data.length == 0) {
				return;
			}
			var strOption = "<option value=''>选择区域</option>";
			for (var i = 0; i < data.length; i++) {
				strOption += '<option value="' + data[i].code + '">'
						+ data[i].desc + '</option>';
			}

			$("#areaId").append(strOption);
		}
	});
}
