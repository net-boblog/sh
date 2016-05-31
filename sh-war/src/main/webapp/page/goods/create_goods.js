//服务下拉数据
var selectData = {};
// 二级服务重新加载
//function serviceSelectOnchange(){
//    var firstCategoryId = $("#firstCategoryId").val();
//    $("#secondCategoryId option:gt(0)").remove();
//    if (firstCategoryId == null || firstCategoryId == "") {
//        return;
//    }
//    if (selectData != null && selectData[firstCategoryId] != null) {
//        $("#secondCategoryId option:gt(0)").remove();
//        var list = selectData[firstCategoryId];
//        var strOption = "";
//        for (var i = 0; i < list.length; i++) {
//            strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
//        }
//        $("#secondCategoryId").append(strOption);
//    } else {
//        $.ajax({
//            url : ctx + "/page/myActivity/findService.s",
//            data : {"serviceParentId" : firstCategoryId },
//            ifModified : false,
//            cache : false,
//            success : function(data) {
//                $("#secondCategoryId option:gt(0)").remove();
//                if (data.success && (data.success == "true" || data.success == true)) {
//                    if (data.list == null || data.list.length == 0) {
//                        return;
//                    }
//                    var list = data.list;
//                    var strOption = "";
//                    for (var i = 0; i < list.length; i++) {
//                        strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
//                    }
//                    $("#secondCategoryId").append(strOption);
//                    selectData[$("#firstCategoryId").val()] = list;
//                }
//            }
//        });
//    }
//};

// 保存
function saveGoods(){
    // 数据校验
	  if(!goodsValidate.goodsFormValidate().form()) {
	      return false;
	    }
	   if($(".ad_dui_1").css("display")=="inline"){
		   $.myAlert({
			   title : "请阅读并同意服务协议",
	            content : "",
	            buttonCount : "1" 
		   },function(){});
		   return
	   }
    $.myAlert({
        title : "你确定要新增吗？",
        content : "",
        buttonCount : "2"
    }, function() {
        $.ajax({
            url : ctx + "/page/goods/saveGoods.s",
            data :{
                "itemAttrValueId1":$("#itemAttrValueId1").val(),
                "marketAmt":$("#marketAmt").val(),
                "discount":$("#discount").val(),
                "itemAttrValueId2":$("#itemAttrValueId2").val()
            },
            ifModified : false,
            cache : false,
            success : function(data) {
                if (data.success && (data.success == "true" || data.success == true)) {
                    $("#update_success p").text("新增成功！");
                    showSuccessMsg();
                    setTimeout(window.location.href=ctx+"goods_query.jsp",2200);
                } else {
                    $.myAlert({
                        title : "新增失败",
                        content : data.message,
                        buttonCount : "1"
                    },function(){
                    });
                }
            }
        });
    });
}
$(function(){
    // 查询商户提供的一级服务
    //$.ajax({
    //    url : ctx + "/page/goods/findService.s",
    //    ifModified : false,
    //    cache : false,
    //    success : function(data) {
    //        $("#firstCategoryId option:gt(0)").remove();
    //        if (data.success && (data.success == "true" || data.success == true)) {
    //            if (data.list == null || data.list.length == 0) {
    //                return;
    //            }
    //            var list = data.list;
    //            var strOption = "";
    //            for (var i = 0; i < list.length; i++) {
    //                strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
    //            }
    //            $("#firstCategoryId").append(strOption);
    //        }
    //    }
    //});

    //验证
    goodsValidate.init();

    // 提交按钮
    $("#search_href").click(saveGoods);

});

var goodsValidate={
    init:function(){
        goodsValidate.goodsFormValidate();
    },
    goodsFormValidate:function(){


        //开始验证
        return $("#signupForm").validate({
            rules : {
                marketAmt:{
                    required:true,
                    min:0,
                    decimal2:2
                },
                discount:{
                    required:true,
                    min:0.1,
                    max:9.9,
                    decimal1:1
                }
            },
            messages : {
                marketAmt:{
                    required :"门店价格不能为空",
                    min:"门店价格必须大于0",
                    decimal2:"小数位数不能超过2位"
                },
                discount:{
                    required :"服务折扣不能为空",
                    min:"请输入正确折扣0.1到9.9之间",
                    max:"请输入正确折扣0.1到9.9之间",
                    decimal1:"小数位数不能超过1位"
                }
            },
            errorPlacement: function (error, element) {  //修改错误提示位置
                error.appendTo(element.parent("span"));
            },
        });
    }
}

// 验证值小数位数
jQuery.validator.addMethod("decimal1", function(value, element) {
    var  decimal=/^-?\d+(\.\d{1,1})?$/;
    return this.optional(element) || (decimal.test(value));
}, $.validator.format("小数位数不能超过1位!"));

// 验证值小数位数
jQuery.validator.addMethod("decimal2", function(value, element) {
    var  decimal=/^-?\d+(\.\d{1,2})?$/;
    return this.optional(element) || (decimal.test(value));
}, $.validator.format("小数位数不能超过2位!"));

