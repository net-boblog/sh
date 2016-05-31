/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
//服务下拉数据
var selectData = {};
//二级服务重新加载
function serviceSelectOnchange(){
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
    } else {
        $.ajax({
            url : ctx + "/page/comment/findService.s",
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
                }
            }
        });
    }
};

function commit() {
    if($(".ad_dui_1").is(":visible")){
        $.myAlert({
            title:"温馨提示",
            content:"请阅读并同意活动协议",
            buttonCount : "1"
        },function(){

        });
        return;
    }

    if((parseInt($("#prod_price").html()) -
        parseInt($("#platform_allowance").html()) -
        parseInt($("#prom_money").val()))<=100){
        $.myAlert({
            title:"温馨提示",
            content:"销售价必须大于1元，请重新选择补贴价",
            buttonCount : "1"
        },function(){

        });
        return;
    }

    if(($("#sale_number").val()!='' && isNaN($("#sale_number").val())) || $("#sale_number").val()==""
        ||parseInt($("#sale_number").val()) < $("#saleMinNumber").val()){
        $.myAlert({
            title:"温馨提示",
            content:"请输入正确的服务数量",
            buttonCount : "1"
        },function(){

        });
        return;
    }


    $.ajax({
        url: ctx + "/page/activity/edit.s",
        ifModified: false,
        cache: false,
        data: {
            "sale_number": $("#sale_number").val(),
            "prom_money": $("#prom_money").val(),
            "act_id": $("#promotionId").val()
        },
        success: function (data) {
            if(data.success){
                $("#update_success p").text("提交成功")
                showSuccessMsg();
                setTimeout(baomingUrl,2200);
            }else{

                $.myAlert({
                    title:"温馨提示",
                    content:"提交失败",
                    buttonCount : "1"
                },function(){
                });
                return;
            }
        }
    });
}

function hideMsg(tag){
    $("#update_fail").hide();

    if(tag==1){
        window.location.href ="/page/activity/index.s";
    }
}

function cancel() {
    window.location.href ="/page/activity/index.s";
}

$(function(){
    // 查询商户提供的一级服务
    $.ajax({
        url : ctx + "/page/comment/findService.s",
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
            }
        }
    });
    //一级服务变更触发
    $("#firstCategoryId").change(serviceSelectOnchange);
});