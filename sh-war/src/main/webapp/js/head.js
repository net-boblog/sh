function storeRecover(){

	$.myAlert({
		title : "你确定要立刻营业吗？",
		content : "立刻营业后休业时间将清空！",
		buttonCount : "2"
	}, function() {
		//立刻营业
		$.ajax({
			url: "../../page/account/storeRecover.s",
			cache: false,
			success: function (data) {
				if (data.flag == 1) {
					$("#update_success p").text("立即营业成功");
					showSuccessMsg();
					//跳转刷新
					window.location.href=window.location.href=ctx + "/page/account/allStoreInfo.s";
				} else {
					alert(data.info);
				}
			}
		});
	});
}

$(function() {
		$(".phone_code").bind("click mouseover",function(){
			$(".wei_ma").show();
		});
		$(".phone_code").bind("mouseout",function(){
			$(".wei_ma").hide();
		});

		$.ajax({
			url:"../../page/account/isOpen.s",
			cache : false,
			success : function(data) {
			if(data.flag==1){

				$("#isOpen").html(data.isOpen);

				if(data.isOpen != "门店已下线"){
					var html="<div id=\"em_nr1\" class=\"em_nr1\" >"
						+ "<i class=\"em_out_sj\"><img src=\"../../images/sanjiao.png\"></i>"
						+ "<div class=\"em_out1\" style=\"text-align:left;\">";

					if(data.isOpen=="休业中"){
						html+="<img src=\"../../images/time.png\" />  <a style='color:#e62d46' href=\"javascript:void(0)\" onclick='storeRecover()'>立刻营业</a> <br><div style='  width:100% ;  border: 1px solid #F8F8FF;margin-top: 10px;margin-bottom: 7px' ></div>";
					}
					html+="<a href=\"../account/allStoreInfo.s?isModify=true\">休业日期设置</a>";
					if(data.storeInfoBO.outBizStartDate != null && data.storeInfoBO.outBizStartDate != "" && data.storeInfoBO.outBizEndDate != null && data.storeInfoBO.outBizEndDate != "" ){
						html+= "<label style='float: left;  margin-top: -5px'>("+data.storeInfoBO.outBizStartDate+" 至 "+data.storeInfoBO.outBizEndDate+") </label>";
					}else{
						html+="<div style='margin-top: -5px;margin-bottom: -15px;'>(未设置)</div>";
					}
					html+= "<div style='  width:100% ;  border: 1px solid #F8F8FF;margin-top: 23px;margin-bottom: 7px' ></div>"
						+ "<div style='width:100% ;height: 42px'> <a href=\"../account/allStoreInfo.s?isModify=true\">营业日期设置</a><br>"
						+ "<label style=' float:left;margin-top: -5px'>("+data.storeInfoBO.bizStartTime+" - "+data.storeInfoBO.bizEndTime+")</label>"
						+"</div></div></div>";

					$("#open_status").append(html);
				}
			}
			}
		});
	});