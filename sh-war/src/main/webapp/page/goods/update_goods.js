//服务下拉数据
var selectData = {};

// 修改
function updateGood(){
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
        title : "你确定要修改吗？",
        content : "",
        buttonCount : "2"
    }, function() {
        $.ajax({
            url : ctx + "/page/goods/updateGood.s",
            data :{
                "productId":$("#productId").val(),
                "marketAmt":$("#marketAmt").val(),
                "discount":$("#discount").val()
            },
            ifModified : false,
            cache : false,
            success : function(data) {
                if (data.success && (data.success == "true" || data.success == true)) {
                    $("#update_success p").text("修改成功！");
                    showSuccessMsg();
                    setTimeout(window.location.href=ctx+"goods_query.jsp",2200);
                } else {
                    $.myAlert({
                        title : "修改失败",
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
    // 提交按钮
    $("#search_href").click(updateGood);
    //验证
    goodsValidate.init();
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