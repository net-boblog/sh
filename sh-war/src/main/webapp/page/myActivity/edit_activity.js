$(function(){
	allmanage.init();
});
var allmanage={
		init:function(){
			 allmanage.protocol(); 
		},

	protocol:function(){ //勾选活动协议
		$(".readtext").click(function(){
			  var checkPic=$("#agreeCheck").attr("src");
			  if(checkPic=="/images/dui1_03.png"){
				  $("#agreeCheck").attr("src","../../images/readicon.png");
			  }else{
				  $("#agreeCheck").attr("src","../../images/dui1_03.png");
			  }
			});
		
	}
}	

//服务下拉数据
var selectData = {};
//二级服务重新加载
function serviceSelectOnchange(defaultValue){
    var firstCategoryId = $("#firstCategoryId").val();
    $("#secondCategoryId option:gt(0)").remove();
    if (firstCategoryId == null || firstCategoryId == "") {
        return;
    }
    if (selectData != null && selectData[firstCategoryId] != null) {
        $("#secondCategoryId option:gt(0)").remove();
        var list = selectData[firstCategoryId];
        var strOption = "";
        for (var i = 0; i < list.length; i++) {
            strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
        }
        $("#secondCategoryId").append(strOption);
        if(defaultValue!=undefined&&defaultValue!=null&&defaultValue!=""){
            $("#secondCategoryId").val(defaultValue);
        }
    } else {
        $.ajax({
            url : ctx + "/page/myActivity/findService.s",
            data : {"serviceParentId" : firstCategoryId },
            ifModified : false,
            cache : false,
            success : function(data) {
                $("#secondCategoryId option:gt(0)").remove();
                if (data.success && (data.success == "true" || data.success == true)) {
                    if (data.list == null || data.list.length == 0) {
                        return;
                    }
                    var list = data.list;
                    var strOption = "";
                    for (var i = 0; i < list.length; i++) {
                        strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                    }
                    $("#secondCategoryId").append(strOption);
                    selectData[$("#firstCategoryId").val()] = list;
                    if(defaultValue!=undefined&&defaultValue!=null&&defaultValue!=""){
                        $("#secondCategoryId").val(defaultValue);
                    }
                }
            }
        });
    }
};
//保存
function saveActivity(){
    if($(".ad_dui_2").css("display")!="inline"){
        $.myAlert({
            title : "请阅读并同意活动协议",
            content : "",
            buttonCount : "1"
        }, function() {});
        return;
    }
    //数据校验
    if(!validate.formValidate().form()) {
      return false;
    }
    $.myAlert({
        title : "你确定要提交吗？",
        content : "",
        buttonCount : "2"
    }, function() {
        $.ajax({
            url : ctx + "/page/myActivity/updateActivity.s",
            data :{
                "promotionId":$("#promotionId").val(),
                "name":$("#name").val(),
                "startTimeHide":$("#startTimeHide").val(),
                "startTime":$("#startTime").val(),
                "endTime":$("#endTime").val(),
                "firstCategoryId":$("#firstCategoryId").val(),
                "firstCategoryName":$("#firstCategoryId").find("option:selected").text(),
                "secondCategoryId":$("#secondCategoryId").val(),
                "secondCategoryName":$("#secondCategoryId").find("option:selected").text(),
                "saleNumber":$("#saleNumber").val(),
                "cycleDays":$("#cycleDays").val(),
                "cycleTimes":$("#cycleTimes").val(),
                "merchantAllowance":Number($("#merchantAllowance").val()).toFixed(2)
            },
            ifModified : false,
            cache : false,
            success : function(data) {
                if (data.success && (data.success == "true" || data.success == true)) {
                    $("#update_success p").text("提交成功！")
                        showSuccessMsg();
                    setTimeout(linkurl,2200);

                } else {
                    $.myAlert({
                        title : "提交失败!",
                        content : data.info,
                        buttonCount : "1"
                    },function(){

                    });
                }
            }
        });
    });
}

//数据校验
$(function(){
    // 查询商户提供的一级服务
    $.ajax({
        url : ctx + "/page/myActivity/findService.s",
        ifModified : false,
        cache : false,
        success : function(data) {
            $("#firstCategoryId option:gt(0)").remove();
            if (data.success && (data.success == "true" || data.success == true)) {
                if (data.list == null || data.list.length == 0) {
                    return;
                }
                var list = data.list;
                var strOption = "";
                for (var i = 0; i < list.length; i++) {
                    strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                }
                $("#firstCategoryId").append(strOption);
                var firstCategory = $("#firstCategoryIdHide").val();
                var secondCategory = $("#secondCategoryIdHide").val();
                if(firstCategory!=""){
                    $("#firstCategoryId").val(firstCategory);
                    serviceSelectOnchange(secondCategory);
                }
            }
        }
    });
    //提交按钮
    $("#search_href").click(saveActivity);

});

