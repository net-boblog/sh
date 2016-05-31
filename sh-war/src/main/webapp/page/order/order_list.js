/**
 * Created by dongxc on 2015/12/3.
 */
$(function() {
    var times ;
    $("#confirm_window_href").click(function(){
        var orderId = $("#confirm_orderId").val();
        $.ajax({
            url : "/page/order/orderConfirm.s",
            data : {
                "orderId" : orderId
            },
            success : function(json) {
                $("#Confirm_goods_div").hide();
                if (json.success) {
                    $("#Confirm_goods_success_div").show();
                    times = setInterval(function(){
                        window.location.href="/page/order/list.s";
                    },3000);
                } else {
                    $("#fail_msg").html(json.msg);
                    $("#update_fail").show();
                }
            },
            error : function() {
                $("#fail_msg").html("确认收货失败：服务器异常");
                $("#update_fail").show();
            }
        });
    });
    search_boxes();
});

function search_boxes(){
    $("#page_offset").val(0);
    search();
};

//查询数据
function search(){
    $.ajax({
        url:ctx +"/page/order/deliveryOrderList.s",
        dataType:"json",
        data : {
            "pageStart" : $("#page_offset").val(),
            "pageSize" : $("#page_limit").val(),
            "orderCode" : $("#orderCode").val(),
            "status":$("#status").val()
        },
        success : function(data){
            $("#no_data_div").hide();
            $("#xq_table tr:gt(0)").remove();
            if(data!=null && data.success && (data.success == "true" || data.success == true)){
                var list = data.data;
                var total = data.total;
                var offset = $("#page_offset").val();
                var limit = 20;
                addPageBtn(offset, limit, total);
                if (list != null && list.length > 0) {
                    var len = list.length;
                    for (var i = 0; i < len; i++) {
                        var newRow="";
                        newRow = "<tr><td colspan=\"5\" class=\"order_number_td p10\"><span class=\"gray\">订单号：</span><span class=\"lan\">"+
                                 "<a href=\""+ctx+"/page/order/order_detail.s?orderId="+list[i].id+"\">"+list[i].no+"</a></span>"+
                                 "<span class=\"gray\" style=\"padding-left: 28px;\">下单时间：</span>"+Util.timestampformat(list[i].createTime,"yyyy-MM-dd hh:mm:ss")+"</td></tr>";
                        $("#xq_table tr:last").after(newRow);

                        newRow = "";
                        var goodsList = list[i].goodsOrderBOList;
                        var subLen = goodsList.length;
                        for(var j=0;j<subLen;j++){
                            newRow += "<tr><td align=\"left\" class=\"dbor\" style=\"border-right: 0;\"><div class=\"luntai_pic\" >" +
                                      "<img src=\""+goodsList[j].img+"\" style=\"max-height: 81px;max-width: 81px;min-height: 81px;min-width: 81px\"/>" +
                                      "</div><div class=\"luntai_text\"><p>"+goodsList[j].goodsName+"</p></div></td>"+
                            "<td valign=\"top\" class=\"dbor\">"+goodsList[j].saleNum+"</td>";
                            if(j==0){
                                newRow += "<td  valign=\"top\" rowspan=\""+subLen+"\" class=\"dbor\"><b class=\"orange\">￥"+list[i].price+"</b></td>"+
                                "<td valign=\"top\" rowspan=\""+subLen+"\"  class=\"dbor\">";
                                if(list[i].orderStatus==1){
                                    newRow += "待收货";
                                }else if(list[i].orderStatus == 2){
                                    newRow += "待服务";
                                }else if(list[i].orderStatus == 3){
                                    newRow += "已完成";
                                }else if(list[i].orderStatus == 4){
                                    newRow += "改派到其他门店";
                                }
                                newRow += "</td><td  valign=\"top\" rowspan=\""+subLen+"\" class=\"dbor\">";
                                if(list[i].orderStatus==1){
                                    newRow += "<a href=\"javascript:confirm_btn("+list[i].id+");\" class=\"confirm_btn\"  >确认收货</a>";
                                }
                                newRow += "<a href=\""+ctx+"/page/order/order_detail.s?orderId="+list[i].id+"\" class=\"check_details_btn\">查看详情</a></td>";

                            }
                            newRow += "</tr>";
                        }
                        $("#xq_table tr:last").after(newRow);
                    }
                } else {
                    $("#no_data_div").show();
                }
            }else{
                $("#no_data_div").show();
            }
        }
    });
}