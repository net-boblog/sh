$(function(){
	search_boxes();
	$.ajax({
		url : ctx + "/page/comment/findService.s",
		ifModified : false,
		cache : false,
		success : function(data) {
			$("#serviceParentId option:gt(0)").remove();
			if (data.success
					&& (data.success == "true" || data.success == true)) {
				if (data.list == null || data.list.length == 0) {
					return;
				}
				var list = data.list;
				var strOption = "";
				for (var i = 0; i < list.length; i++) {
					strOption += '<option value="' + list[i].categoryId
							+ '">' + list[i].categoryDesc + '</option>';
				}
				$("#serviceParentId").append(strOption);
			}
		}
	});
});
//服务项目下拉选择事件
var selectData = {};
function serviceSelectOnchange() {
	var serviceParentId = $("#serviceParentId").val();
	$("#serviceId option:gt(0)").remove();
	if (serviceParentId == null || serviceParentId == "") {
		return;
	}
	if (selectData != null && selectData[serviceParentId] != null) {
		$("#serviceId option:gt(0)").remove();
		var list = selectData[serviceParentId];
		var strOption = "";
		for (var i = 0; i < list.length; i++) {
			strOption += '<option value="' + list[i].categoryId + '">'
					+ list[i].categoryDesc + '</option>';
		}
		$("#serviceId").append(strOption);
	} else {
		$.ajax({
					url : ctx + "/page/comment/findService.s",
					data : {
						"serviceParentId" : serviceParentId
					},
					ifModified : false,
					cache : false,
					success : function(data) {
						$("#serviceId option:gt(0)").remove();
						if (data.success
								&& (data.success == "true" || data.success == true)) {
							if (data.list == null || data.list.length == 0) {
								return;
							}
							var list = data.list;
							var strOption = "";
							for (var i = 0; i < list.length; i++) {
								strOption += '<option value="'
										+ list[i].categoryId + '">'
										+ list[i].categoryDesc + '</option>';
							}
							$("#serviceId").append(strOption);
							selectData[$("#serviceParentId").val()] = list;
						}
					}
				});
	}
}
function search_boxes(){
	$("#page_offset").val(0);
	search();
};

function downloadOrders(ths){

    //不可点击&&置灰
    disableDownLoadButton();

    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target","_self");
    form.attr("method","post");
    form.attr("action",ctx + "/page/drawmoney/downLoad.s");

    var jsonData = {
        "oneCategory" : $("#serviceParentId").val(),
        "twoCategory" : $("#serviceId").val(),
        "clearId":$("#clearId").val()
    }

    var inputs = '';
    jQuery.each(jsonData, function(name, value){
        inputs+='<input type="hidden" name="'+ name +'" value="'+ value +'" />';
    });
    form.append(inputs);
    //表单提交
    form.appendTo("body").submit().remove();

    //恢复
    enableDownLoadButton();
}

function search(){
	$.ajax({
		url:ctx +"/page/drawmoney/serviceList.s",
		dataType:"json",
		data : {
			"page" : parseInt($("#page_offset").val())+1,
			"oneCategory" : $("#serviceParentId").val(),
			"twoCategory" : $("#serviceId").val(),
			"clearId":$("#clearId").val()
		},
		success : function(data){
			if(data.flag==0){
				window.location.href=ctx +"/page/common/error.jsp";
			}
			else{
				$("#xq_table tr:gt(0)").remove();
				$("#nodata").hide();
				var tdata = data.data.data.serviceList;
				var total = data.data.totalCount;
				var offset = $("#page_offset").val();
				var limit = data.data.pageSize;
				$("#much_order").html(total); /*共多少个订单*/
				addPageBtn(offset, limit, total);
				var showClass=false;
				if(tdata.length==0 || tdata.length==null){
					$("#nodata").show();
                    disableDownLoadButton();
				}else{
                    enableDownLoadButton();
                }
				for (var i = 0; i < tdata.length; i++) {
					var newRow="";
					if(showClass==true){
						 newRow += "<tr class=\"even_bg\">";
						 showClass=false;
					}else{
						newRow += "<tr>";
						 showClass=true;
					}
					newRow +="<td>"+tdata[i]["orderNo"]+"</td>"+
		              "<td>"+tdata[i]["serviceName"]+"</td>"+
		              "<td>"+tdata[i]["count"]+"</td>"+
		              "<td class=\"orange\">"+Util.moneyScale(tdata[i]["money"], 1)+"</td>"+
		              "<td>"+tdata[i]["smsCode"]+"</td>"+
		              "<td>"+tdata[i]["smsTimeStr"]+"</td>"+
		            "</tr>";
					$("#xq_table tr:last").after(newRow);
					 
				}
			}
		
		}
	});
}

function disableDownLoadButton(){
    $("#data_out").attr("disabled", true)
        .css({"background":"url(../../images/icon2-1.png) 5px center no-repeat",
            "border":"#ccc 1px solid",
            "color":"#ccc"
        });
}

function enableDownLoadButton(){
    $("#data_out").attr("disabled", false)
        .css({"background":"url(../../images/icon2.png) 5px center no-repeat",
            "border":"#98999b 1px solid",
            "color":"#4c4d51"
        });
}