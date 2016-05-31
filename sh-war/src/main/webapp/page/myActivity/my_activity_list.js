/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
//服务下拉数据
var selectData = {};
//查询条件
var con = {
    "firstCategoryId" : "",
    "secondCategoryId" : "",
    "promotionTimeFrom" : "",
    "promotionTimeTo" : "",
    "name":"",
    "buildPromotionStatusConstants":""
};
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
//点击搜索按钮
function searchOnclick(){
    $("#page_offset").val("0");
    con.firstCategoryId = $("#firstCategoryId").val();
    con.secondCategoryId = $("#secondCategoryId").val();
    con.promotionTimeFrom = $("#promotionTimeFrom").val();
    con.promotionTimeTo = $("#promotionTimeTo").val();
    con.buildPromotionStatusConstants = $("#buildPromotionStatusConstants").val();
    con.name = $("#name").val();
    search();
};
//加载列表
function search(){
    $.ajax({
        url  : ctx + "/page/myActivity/list.s",
        data : {
            "pageOffset"        : $("#page_offset").val(),
            "pageSize"          : $("#page_limit").val(),
            "name"              : con.name,
            "firstCategoryId"   : con.firstCategoryId,
            "secondCategoryId"  : con.secondCategoryId,
            "promotionTimeFrom" : con.promotionTimeFrom,
            "promotionTimeTo"   : con.promotionTimeTo,
            "buildPromotionStatusConstants" : con.buildPromotionStatusConstants
        },
        ifModified : false,
        cache : false,
        success : function(data) {
            $("#activity_table tr:gt(0)").remove();
            $("#no_data_div").hide();
            if (data.success
                && (data.success == "true" || data.success == true)) {
                var list = data.data;
                var total = data.total;
                var offset = $("#page_offset").val();
                var limit =  $("#page_limit").val();
                addPageBtn(offset, limit, total);
                if (list != null && list.length > 0) {
                    var len = list.length;
                    for(var i=0;i<len;i++){
                        var newRow = "<tr><td><a href=\"javascript:void(0);\" onclick=\"openDetail("+ list[i].promotionId + ")\">"+list[i].name+"</a>"
                            + "</td><td class=\"td_content2\">"
                            + Util.timestampformat(list[i].startTime,"yyyy-MM-dd")
                            + "</td>"
                            + "<td class=\"td_content2\">"
                            + Util.timestampformat(list[i].endTime,"yyyy-MM-dd")
                            + "</td><td class=\"td_content2\">"
                            + list[i].promotionStatusName
                            + "</td><td class=\"td_content2\">"
                            + (parseFloat(list[i].merchantAllowance)/100)
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + list[i].saleNumber
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + (parseFloat(list[i].promotionAmt)/100)
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + (parseFloat(list[i].promotionClearAmt)/100)
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + list[i].secondCategoryName
                            + "</td><td class=\"td_content2\">";
                        if(list[i].editable){
                            newRow += "<a href=\"javascript:void(0);\"  onclick=\"editActivity("+ list[i].promotionId + ")\">编辑</a>";
                            newRow += " &nbsp;&nbsp;";
                        }
                        if(list[i].allowOffline){
                            newRow += "<a href=\"javascript:void(0);\"  onclick=\"offlineActivity("+ list[i].promotionId + ",'"+list[i].name+"')\">下线</a>";
                        }
                        newRow+= "</td></tr>";
                        $("#activity_table tr:last").after(newRow);
                        SetTableRowColor();
                    }
                } else {
                    $(".page").html('');
                    $("#no_data_div").show();
                }
            }else{
                $.myAlert({
                    title : "加载活动列表失败",
                    content : data.info,
                    buttonCount : "1"
                },function(){});
            }
        }
    });
}
//查看详情
function openDetail(promotionId){
    window.open(ctx + "/page/myActivity/viewPage.s?promotionId="+promotionId,"_blank");
}
//编辑活动
function editActivity(promotionId){
    window.location.href = ctx + "/page/myActivity/editPage.s?promotionId="+promotionId;
}
//创建活动
function createActivity(){
    window.location.href = ctx + "/page/myActivity/createPage.s";
}
//下线活动
function offlineActivity(promotionId,name){
    $.myAlert({
        title : "您确认要下线吗？",
        content : "下线后将无法再恢复此次活动！",
        buttonCount : "2"
    }, function() {
        $.ajax({
            url : ctx + "/page/myActivity/offlineActivity.s",
            ifModified : false,
            cache : false,
            data : "promotionId=" + promotionId,
            success : function(response) {
                if (response.success && (response.success == "true" || response.success == true)) {
                    $.myAlert({
                        title : "下线成功",
                        content : "活动"+name+"已经成功下线了!",
                        buttonCount : "1"
                    },search);
                } else {
                    $.myAlert({
                        title : "下线失败",
                        content : response.info,
                        buttonCount : "1"
                    },function(){});
                }
            }
        });
    });
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
    //重置按钮
    $("#reset_href").click(function(){
        $("#firstCategoryId").val("");
        $("#secondCategoryId").val("");
        $("#promotionTimeFrom").val("");
        $("#promotionTimeTo").val("");
        $("#name").val("");
        $("#buildPromotionStatusConstants").val("-1");
        serviceSelectOnchange();
        searchOnclick();
    });
    //搜索按钮
    $("#search_href").click(searchOnclick);
    //创建活动按钮
    $("#create_href").click(createActivity);
    search();
});
//跳转到超人活动页面
function jumpToActivity(){
   window.location.href = ctx + "/page/activity/index.s";
}

