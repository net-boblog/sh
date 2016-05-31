/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
//服务下拉数据
var selectData = {};
//查询条件
var con = {
    "first_service" : "",
    "second_service" : "",
    "date_start" : "",
    "date_end" : "",
    "act_name":"",
    "act_state":""
};
//二级服务重新加载
function serviceSelectOnchange(){
    var firstCategoryId = $("#first_service").val();
    $("#second_service option:gt(0)").remove();
    if (firstCategoryId == null || firstCategoryId == "") {
        return;
    }
    if (selectData != null && selectData[firstCategoryId] != null) {
        $("#second_service option:gt(0)").remove();
        var list = selectData[firstCategoryId];
        var strOption = "";
        for (var i = 0; i < list.length; i++) {
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
                if (data.success && (data.success == "true" || data.success == true)) {
                    if (data.list == null || data.list.length == 0) {
                        return;
                    }
                    var list = data.list;
                    var strOption = "";
                    for (var i = 0; i < list.length; i++) {
                        strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                    }
                    $("#second_service").append(strOption);
                    selectData[$("#first_service").val()] = list;
                }
            }
        });
    }
};
//点击搜索按钮
function searchOnclick(){
    $("#page_offset").val("0");
    con.first_service = $("#first_service").val();
    con.second_service = $("#second_service").val();
    con.date_start = $("#date_start").val();
    con.date_end = $("#date_end").val();
    con.act_state = $("#act_state").val();
    con.act_name = $("#act_name").val();
    search();
};
//加载列表
function search(){
    $.ajax({
        url  : ctx + "/page/activity/my-list.s",
        data : {
            "page_offset"        : $("#page_offset").val(),
            "page_limit"          : $("#page_limit").val(),
            "act_name"              : con.act_name,
            "first_service"   : con.first_service,
            "second_service"  : con.second_service,
            "date_start" : con.date_start,
            "date_end"   : con.date_end,
            "act_state" : con.act_state
        },
        ifModified : false,
        cache : false,
        success : function(data) {
            $("#activity_table tr:gt(0)").remove();
            var total = data.total;
            if(total==0)
                $("#no_data_div").show();
            else
                $("#no_data_div").hide();

            if (data.success
                && (data.success == "true" || data.success == true)) {
                var list = data.data;

                var offset = $("#page_offset").val();
                var limit =  $("#page_limit").val();
                addPageBtn(offset, limit, total);

                if (list != null && list.length > 0) {
                    var len = list.length;
                    for(var i=0;i<len;i++){
                        var newRow = "<tr><td class=\"td_content2\"><a href=\"javascript:void(0);\" onclick=\"openDetail("+ list[i].promotionId + ")\">"+list[i].name+"</a>"
                            + "</td><td class=\"td_content2\">"
                            + Util.timestampformat(list[i].startTime,"yyyy-MM-dd")
                            + "</td>"
                            + "<td class=\"td_content2\">"
                            + Util.timestampformat(list[i].endTime,"yyyy-MM-dd")

                        if(list[i].enrollPromotionStatus==5 && list[i].approveRemark!=null && list[i].approveRemark!='')
                            newRow += "<td class=\"small_pup\">"
                                + "<span class=\"orange\">" + list[i].statusName + "</span>"
                                + "<img src=\"../../images/yichang_icon.png\" class=\"yicang_img\">"
                                + "<div class=\"tishi_text_box\" style=\"display: none;\">"
                                + "<img src=\"../../images/top_xsj_icon.png\">" + list[i].approveRemark + "</div>";
                        else
                            newRow += "</td><td class=\"td_content2\">"
                                + list[i].statusName;

                            newRow += "</td><td class=\"td_content2\">"
                            + (parseInt(list[i].merchantAllowance)/100.0).toFixed(2)
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + list[i].saleNumber
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + (parseInt(list[i].promotionAmt)/100.0).toFixed(2)
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + (parseInt(list[i].promotionClearAmt)/100.0).toFixed(2)
                            + "</td>"
                            + "</td><td class=\"td_content2\">"
                            + list[i].secondCategoryName
                            + "</td><td class=\"td_content2\">";
                        if(list[i].editable){
                            if(list[i].enrollPromotionStatus==5 || list[i].enrollPromotionStatus==6)
                                newRow += "<a href=\"javascript:void(0);\" onclick=\"reaplyActivity("+ list[i].promotionId + ")\" style=\"margin-right:10px;\">再次报名</a>";
                            else
                                newRow += "<a href=\"javascript:void(0);\" onclick=\"editActivity("+ list[i].promotionId + ")\" style=\"margin-right:10px;\">编辑</a>";
                        }
                        if(list[i].allowOffline){
                            newRow += "<a href=\"javascript:void(0);\" onclick=\"offlineActivity("+ list[i].promotionId + ",'"+list[i].name+"')\">退出活动</a>";
                        }
                        newRow+= "</td></tr>";
                        $("#activity_table tr:last").after(newRow);
                    }

                    $(".tk_table").find(".yicang_img").mouseover(function () {
                        $(this).next().show();
                    });
                    $(".tk_table").find(".yicang_img").mouseout(function () {
                        $(this).next().hide();
                    });
                } else {
                    $(".page").html('');
                    $("#no_data_div").show();
                }
            }else{
                //alert(data.info);
            }
        }
    });
}
//查看详情
function openDetail(promotionId){
    window.open(ctx + "/page/activity/my-detail.s?act_id="+promotionId+"&day_range=7","_self");
}

//编辑活动
function editActivity(promotionId){
    window.open(ctx + "/page/activity/to-edit.s?act_id="+promotionId+"&day_range=7","_self");
}

function reaplyActivity(promotionId){
    window.open(ctx + "/page/activity/detail.s?act_id="+promotionId+"&reapply=1", "_self");
}

//下线活动
function offlineActivity(promotionId,name){
    $(".pup_text1").show();
    $(".pup_text1").html("你确定要把"+name+"退出？");
    $(".pup_text2").html("");
    $("#confirm_window_href").attr("href", "javascript:cancel(" + promotionId + ")");
    $(".cancel_window").attr("href", "javascript:hideConfirm()");
    $(".cancel_window").attr("onclick", "");
    $("#Confirm_goods_div").show();
}

function hideConfirm(){
    $(".pup_text1").hide();
    $("#Confirm_goods_div").hide();
}

function hideMsg(tag){
    $("#update_fail").hide();
    if(tag==1)
        window.open(ctx + "/page/activity/index.s", "_self");
}

function cancel(promotionId){
    $.ajax({
        url : ctx + "/page/activity/cancel.s",
        ifModified : false,
        cache : false,
        data : "act_id=" + promotionId,
        success : function(json) {
            hideConfirm();
            if (json.success) {
                $("#fail_msg").html(json.info);
                $("#failConfirm").attr("href", "javascript:hideMsg(1)");
                $("#update_fail").show();

            } else {
                $("#fail_msg").html(json.info);
                $("#failConfirm").attr("href", "javascript:hideMsg(0)");
                $("#update_fail").show();
            }
        },
        error:function(){
            hideConfirm();
            $("#fail_msg").html("退出失败");
            $("#failConfirm").attr("href", "javascript:hideMsg(0)");
            $("#update_fail").show();
        }
    });
}

$(function(){
    // 查询商户提供的一级服务
    $.ajax({
        url : ctx + "/page/comment/findService.s",
        ifModified : false,
        cache : false,
        success : function(data) {
            $("#first_service option:gt(0)").remove();
            if (data.success && (data.success == "true" || data.success == true)) {
                if (data.list == null || data.list.length == 0) {
                    return;
                }
                var list = data.list;
                var strOption = "";
                for (var i = 0; i < list.length; i++) {
                    strOption += '<option value="' + list[i].categoryId + '">' + list[i].categoryDesc + '</option>';
                }
                $("#first_service").append(strOption);
            }
        }
    });
    //一级服务变更触发
    $("#first_service").change(serviceSelectOnchange);
    //重置按钮
    $("#reset_href").click(function(){
        $("#first_service").val("");
        $("#second_service").val("");
        $("#date_start").val("");
        $("#date_end").val("");
        $("#act_name").val("");
        $("#act_state").val("");
        serviceSelectOnchange();
        searchOnclick();
    });
    //搜索按钮
    $("#search_href").click(searchOnclick);
    search();

});

