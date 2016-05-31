// JavaScript Document
$(document).ready(function() {

	$(".wrap  ul li").click(function(){
		var _this = this;
		if($(_this).attr("class")=="click"){
			 $(_this).removeClass('click');
			 $(_this).find(".bgimg").remove();
		}else{
			$(_this).addClass('click');
			$(_this).append("<span class='bgimg'></span>");
		}
	});

	if($('#telephone').val() !=null  && $('#telephone').val() !=''){
		$('#telephone').attr("readonly",true);
	}

	if($('#tel').val() !=null && $('#tel').val() !=''){
		$('#tel').attr("readonly",true);
	}

	//清除固话格式不正确的数据显示为空
	var reg=/(\d{3,4}-\d{7,8})/;
	if (!(reg.test($.trim($('#tel').val())))) {
		$('#tel').val("");
		$("#zonetel").html("")
	}

	 // 校验手机号不正确显示空
	if (!(/^1\d{10}$/.test($.trim($('#telephone').val())))) {
		$('#telephone').val("");
		$('#phone').val("");
	}

	//默认显示时间
	if($("#bizStartTime").val()==null || $("#bizStartTime").val()==""){
		$("#start_bizHour").find("option[value=8]").attr("selected",true);
	}

	//默认显示时间
	if($("#bizEndTime").val()==null || $("#bizEndTime").val()==""){
		$("#end_bizHour").find("option[value=20]").attr("selected",true);ba
	}

	// 门店信息点击编辑
	$('#store_modify').click(function() {
				$('#oldStoreInfo').hide();
				$("#store_modify").hide();
				$('#msg1').hide();
				$('#msg2').hide();
				$('#msg4').hide();
				$('#msg5').hide();
				$('#newStoreInfo').show();
				$('#store_button').show();
			});

	if($("#isModify").val()=="true"){
		$('#oldStoreInfo').hide();
		$("#store_modify").hide();
		$('#msg1').hide();
		$('#msg2').hide();
		$('#msg4').hide();
		$('#msg5').hide();
		$('#newStoreInfo').show();
		$('#store_button').show();
	}else{
		$('#oldStoreInfo').show();
		$("#store_modify").show();
		$('#msg1').show();
		$('#msg2').show();
		$('#msg4').show();
		$('#msg5').show();
		$('#newStoreInfo').hide();
		$('#store_button').hide();
	}

	// 门店修改点击取消
	$('#store_cancel').click(function() {
				cancelStore();
			});

	// 修改门店信息
	$('#store_save').click(function() {
		if ($('#telephone').val() == '' && $('#tel').val() == '') {
			$('#msg4').show();
			$('#msg5').show();
			return false;
		} else {
			$('#msg4').hide();
			$('#msg5').hide();
		}
		if ($('#telephone').val() != '') {// 校验手机号
			if (!(/^1\d{10}$/.test($.trim($('#telephone').val())))) {
				$('#msg1').show();
				return false;
			} else {
				$('#msg1').hide();
			}
		}
		if ($('#tel').val() != '' && !$('#tel').attr("readonly")) {// 校验座机格式
			var reg=/(\d{3,4}-\d{7,8})/;
			if (!(reg.test($.trim($('#tel').val())))) {
				$('#msg2').show();
				return false;
			} else {
				$('#msg2').hide();
			}
		}

		var bizStartTime = $("#start_bizHour").val() + ":"
				+ $("#start_bizMin").val();
		var bizEndTime = $("#end_bizHour").val() + ":" + $("#end_bizMin").val();

		var bizStart=parseInt($("#start_bizHour").val())*60+parseInt($("#start_bizMin").val());
		var bizEnd=parseInt($("#end_bizHour").val())*60+parseInt($("#end_bizMin").val());
		if(bizStart>bizEnd){
			alert("请正确填写营业时间!");
			return false;
		}
		var phone = $("#telephone").val();
		var zonetel = $("#tel").val();
		var chk_value =[];

		$(".click").each(function(){
			chk_value.push($(this).attr("value"));
		});
		if(chk_value.length==0){
			alert("营业周期必填");
			return false;
		}
		$.ajax({
			url : ctx + "/page/account/updateStore.s",
			ifModified : false,
			cache : false,
			data : {
				"storeId" : $("#storeId").val(),
				"bizStartTime" : bizStartTime,
				"bizEndTime" : bizEndTime,
				"phone" : phone,
				"zonetel" : zonetel,
				"outBizStartDate" : $("#outBizStartDate").val(),
				"outBizEndDate" : $("#outBizEndDate").val(),
				"closedCycle" : chk_value.toString()
			},
			success : function(data) {
				var flag = data.flag;
				if (flag == '1') {
					$("#update_success p").text("修改成功");
					showSuccessMsg();
					window.location.href=ctx + "/page/account/allStoreInfo.s";
					//$.ajax({
					//			url : ctx
					//					+ "/page/account/refreshAccountInfo.s",
					//			ifModified : false,
					//			cache : false,
					//			data : {
					//				"storeId" : $("#storeId").val()
					//			},
					//			success : function(data) {
					//				var flag = data.flag;
					//				if (flag == '1') {
					//					cancelStore();
					//					$('#name').text(data.data.storeName);
					//					$('#bizTime')
					//							.text(data.data.bizStartTime
					//									+ "-"
					//									+ data.data.bizEndTime);
					//					if (data.data.bizStartTime == null
					//							|| data.data.bizStartTime == "") {
					//						$('#start_bizHour').val('8');
					//						$('#start_bizMin').val('00');
					//						$('#end_bizHour').val('20');
					//						$('#end_bizMin').val('00');
					//					} else {
					//						$('#start_bizHour')
					//								.val(data.data.bizStartTime
					//										.split(":")[0]);
					//						$('#start_bizMin')
					//								.val(data.data.bizStartTime
					//										.split(":")[1]);
					//						$('#end_bizHour')
					//								.val(data.data.bizEndTime
					//										.split(":")[0]);
					//						$('#end_bizMin')
					//								.val(data.data.bizEndTime
					//										.split(":")[1]);
					//					}
					//					$('#address').text(data.data.address);
					//					$('#phone').text(data.data.telephone);
					//					$('#telephone')
					//							.val(data.data.telephone);
					//					$('#zonetel').text(data.data.tel);
					//					$('#tel').val(data.data.tel);
					//					$('#changeCount').val(data.data.changeCount);
					//				} else {
					//					showFailMsg("刷新页面失败", data.info);
					//				}
					//			}
					//		});
				} else {
					showFailMsg("修改失败", data.info);
				}
			}
		});
	});

	// 点击更换银行卡
	$('#change_bankcard').click(function() {
		if (parseInt($('#changeCount').val()) > 0) {
			showFailMsg("温馨提示", "请联系客服修改银行卡！");
			return false;
		}
		$("#op_bank option:gt(0)").remove();
		$("#payeeprovince option:gt(0)").remove();
		$.ajax({
					url : ctx + "/page/account/getBankProList.s",
					ifModified : false,
					cache : false,
					data : {

		}			,
					success : function(data) {
						var flag = data.flag;
						if (flag == '1') {
							if (data == null || data.bank.length == 0) {

							} else {
								var strOption = "";
								for (var i = 0; i < data.bank.length; i++) {
									strOption += '<option value="'
											+ data.bank[i].code + '">'
											+ data.bank[i].desc + '</option>';
								}
								$("#op_bank").append(strOption);
							}

							if (data.pro.length != 0) {
								var province = "";
								for (var j = 0; j < data.pro.length; j++) {
									province += '<option value="'
											+ data.pro[j].code + '">'
											+ data.pro[j].desc + '</option>';
								}
								$("#payeeprovince").append(province);
							}
							$('#old_info').hide();
							$('#account_info').show();
							$('#account_button').show();
						} else {
							showFailMsg("加载信息失败", data.info);
						}
					}

				});

	});

	// 选择其他银行，显示银行名称输入框
	$('#op_bank').change(function() {
				$('#otherbank').val('');
				$('#remind1').hide();
				$('#remind2').hide();
				if ($('#op_bank').val() == 44) {
					$('#otherbank').show();
				} else {
					$('#otherbank').hide();
				}
			});

	$('#payeeprovince').change(function() {
		$("#fail_title").val('');
		$("#fail_msg").val('');
		$("#payeecity option:gt(0)").remove();
		if ($('#payeeprovince').val() == '') {
			return false;
		}
		$.ajax({
					url : ctx + "/page/account/getCityList.s",
					ifModified : false,
					cache : false,
					data : {
						"code" : $('#payeeprovince').val()
						,
					},
					success : function(data) {
						var flag = data.flag;
						if (flag == '1') {

							if (data == null || data.city.length == 0) {

							} else {
								var strOption = "";
								for (var i = 0; i < data.city.length; i++) {
									strOption += '<option value="'
											+ data.city[i].code + '">'
											+ data.city[i].desc + '</option>';
								}
								$("#payeecity").append(strOption);
							}
						} else {
							showFailMsg("加载城市失败", data.info);
						}
					}

				});

	});

	// 取消账号信息变更
	$('#cancel_account').click(function() {
				cancelBank();
			});
	// 修改收款账号信息
	$('#modify_account').click(function() {
		$("#fail_msg").val('');
		$('#remind1').hide();
		$('#remind2').hide();
		$('#remind3').hide();
		$('#remind4').hide();
		$('#remind5').hide();
		$('#remind6').hide();
		if ($('#op_bank').val() == '') {
			$('#remind1').show();
			return false;
		}
		if ($('#op_bank').val() != '' && $('#op_bank').val() == '44') {
			if ($('#otherbank').val() == '') {
				$('#remind2').show();
				return false;
			}
		}
		if ($('#payeeprovince').val() == '' || $('#payeecity').val() == '') {
			$('#remind3').show();
			return false;
		}
		if ($('#branchbank').val() == '') {
			$('#remind4').show();
			return false;
		}
		if ($('#account_no') == '') {
			$('#remind5').show();
			return false;
		}
		if (!(/^[0-9]*$/.test($.trim($('#account_no').val())))) {
			$('#remind6').show();
			return false;
		}
		$.ajax({
			url : ctx + "/page/account/updateAccount.s",
			ifModified : false,
			cache : false,
			data : {
				"storeId" : $('#storeId').val(),
				"bankcode" : $('#op_bank').val(),
				"bankname" : $('#otherbank').val(),
				"provinceCode" : $('#payeeprovince').val(),
				"cityCode" : $('#payeecity').val(),
				"bankBranch" : $('#branchbank').val(),
				"payAccount" : $('#account_no').val()
			},
			success : function(data) {
				var flag = data.flag;
				if (flag == '1') {
					$("#update_success p").text("修改成功");
					showSuccessMsg();
					$.ajax({
								url : ctx
										+ "/page/account/refreshAccountInfo.s",
								ifModified : false,
								cache : false,
								data : {
									"storeId" : $("#storeId").val()
									,
								},
								success : function(data) {
									var flag = data.flag;
									if (flag == '1') {
										cancelBank();
										if (data.data.payWay == 1) {
											$("#bank_logo").prop('src',"../../images/banklogo"+data.data.bankCode+".png");
											$('#oldbankname')
													.text(data.data.bankName);
										} else if (data.data.payWay == 2) {
											$("#bank_logo").prop('src',
													"../../images/zfb.png");
											$('#oldbankname').text("支付宝账号");
										} else if (data.data.payWay == 3) {
											$("#bank_logo").prop('src',
													"../../images/wx.png");
											$('#oldbankname').text("微信账号");
										}

										$('#oldbankno')
												.text(data.data.payAccount);
										$('#changeCount').val(data.data.changeCount);
									} else {
										showFailMsg("刷新页面失败", data.info);
									}
								}
							});
				} else {
					showFailMsg("修改失败", data.info);
				}
			}
		});

	});
});

function cancelStore() {
	$('#oldStoreInfo').show();
	$("#store_modify").show();
	$('#newStoreInfo').hide();
	$('#store_button').hide();
}

function cancelBank() {
	$('#old_info').show();
	$('#account_info').hide();
	$('#otherbank').hide();
	$('#account_button').hide();
	$('#op_bank').val('');
	$('#otherbank').val('');
	$('#payeeprovince').val('');
	$('#payeecity').val('');
	$('#branchbank').val('');
	$('#account_no').val('');
}
