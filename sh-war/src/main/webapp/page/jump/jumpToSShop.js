// JavaScript Document
$(document).ready(function() {
	$.ajax({
				url : ctx + "/page/jump/toSShop.s",
				cache : false,
				ifModified : true,
				async : false,
				data : {
					"redirectUrl" : url
					,
				},
				success : function(data) {
					var flag = data.flag;// alert(data);
					if (flag) {
						$("#jumpForm").attr("action", data.data.url);
						$("#jumpForm").find("input[name='username']")
								.val(data.data.username);
						$("#jumpForm").find("input[name='mark']")
								.val(data.data.mark);
						$("#jumpForm").find("input[name='nickname']")
								.val(data.data.nickname);
						$("#jumpForm").find("input[name='redirectUrl']")
								.val(data.data.redirectUrl);
						$("#jumpForm").submit();
					} else {
						alert(json.info);
					}
				}
			});
});