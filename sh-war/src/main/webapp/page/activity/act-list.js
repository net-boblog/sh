/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
//服务下拉数据
var selectData = {};

//二级服务重新加载
function serviceSelectOnchange(){
    var firstCategoryId = $("#first_service").val();
    $("#second_service option:gt(0)").remove();
    if (firstCategoryId == null || firstCategoryId == "") {
        return;
    }
    if (selectData != null && selectData[firstCategoryId] != null) {
        $("#second_service option:gt(0)").remove();
        var secondService = $("#secondService").val();
        var list = selectData[firstCategoryId];
        var strOption = "";
        for (var i = 0; i < list.length; i++) {
            if(secondService==list[i].categoryId)
                strOption += '<option selected value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
            else
                strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
        }
        $("#second_service").append(strOption);
    } else {
        $.ajax({
            url : ctx + "/page/comment/findService.s",
            data : {"serviceParentId" : firstCategoryId },
            ifModified : false,
            cache : false,
            success : function(data) {
                $("#second_service option:gt(0)").remove();
                var secondService = $("#secondService").val();

                if (data.success && (data.success == "true" || data.success == true)) {
                    if (data.list == null || data.list.length == 0) {
                        return;
                    }
                    var list = data.list;
                    var strOption = "";
                    for (var i = 0; i < list.length; i++) {
                        if(secondService==list[i].categoryId)
                            strOption += '<option selected value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                        else
                            strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                    }
                    $("#second_service").append(strOption);
                    selectData[firstCategoryId] = list;
                }
            }
        });
    }
};

//查询
function search() {
    $("#searchForm").attr("action", "/page/activity/list.s");
    $("#searchForm").attr("target", "");
    $("#searchForm").submit();
}

function searchOrder(orderType) {
    var order_type =1;

    $("#searchForm").attr("action", "/page/activity/list.s");
    $("#searchForm").attr("target", "");
    if(orderType==2){
        if($("#order_type").val()=="2"){
            order_type = 3;
        }else{
            order_type = 2;
        }
    }

    $("#order_type").val(order_type);
    $("#searchForm").submit();
}

//报名
function apply(actId, status){
    var jsonParam = {
        "act_id" : actId
    };
    var action = "/page/activity/detail.s";
    Util.tempFormSubmit(jsonParam, action, "get");
}

$(function(){

    if($("#order_type").val()=="1"){
        $("#hrefHotAct").css("color", "#f00");
        $("#hrefActTime").css("color", "#666");
        $("#sortUp").attr("src", "/images/u124_032.png");
        $("#sortDown").attr("src", "/images/u124_03.png");
    }else{
        $("#hrefHotAct").css("color", "#666");
        $("#hrefActTime").css("color", "#f00");

        if($("#order_type").val()=="2"){
            $("#sortUp").attr("src", "/images/u123_03.png");
            $("#sortDown").attr("src", "/images/u124_03.png");
        }
        if($("#order_type").val()=="3"){
            $("#sortUp").attr("src", "/images/u124_032.png");
            $("#sortDown").attr("src", "/images/u123_034.png");
        }
    }
    if($("#order_type").val()==''){
        $("#hrefHotAct").css("color", "#666");
        $("#hrefActTime").css("color", "#666");
        $("#sortUp").attr("src", "/images/u124_032.png");
        $("#sortDown").attr("src", "/images/u124_03.png");
    }

    addPageBtn($('#page_offset').val(), $('#page_limit').val(), $('#total').val());

    $('#search_href').click(function () {
        $("#page_offset").val(0);
        search();
    });

    $("#reset_href").click(function () {
        window.location.href = "/page/activity/list.s";
    });

    // 查询商户提供的一级服务
    $.ajax({
        url : ctx + "/page/comment/findService.s",
        ifModified : false,
        cache : false,
        success : function(data) {
            $("#first_service option:gt(0)").remove();
            var firstService = $("#firstService").val();

            if (data.success && (data.success == "true" || data.success == true)) {
                if (data.list == null || data.list.length == 0) {
                    return;
                }
                var list = data.list;
                var strOption = "";
                for (var i = 0; i < list.length; i++) {
                    if(list[i].categoryId==firstService)
                        strOption += '<option selected value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                    else
                        strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                }
                $("#first_service").append(strOption);

                serviceSelectOnchange();
            }
        }
    });
    //一级服务变更触发
    $("#first_service").change(serviceSelectOnchange);
});

