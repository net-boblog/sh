$(function(){
	search_boxes();
	// 查询商户提供的一级服务
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
function search(){
	$.ajax({
		url:ctx +"/page/drawmoney/canDrawService.s",
		dataType:"json",
		data : {
			"page" : parseInt($("#page_offset").val())+1,
			"oneCategory" : $("#serviceParentId").val(),
			"twoCategory" : $("#serviceId").val(),
		},
		success : function(data){
			if(data.flag==0){
				window.location.href=ctx +"/page/common/error.jsp";
			}
			else{
				$("#xq_table tr:gt(0)").remove();
				var tdata = data.data.dataList;
				var total = data.data.totalCount;
				var offset = $("#page_offset").val();
				var limit = data.data.pageSize;
				addPageBtn(offset, limit, total);
				$("#total_order").html(total);
				var showClass=false;
                if(tdata.length==0 || tdata.length==null){
                    $("#nodata").show();
				}else{
                    $("#nodata").hide();
                    for (var i = 0; i < tdata.length; i++) {
                        var newRow="";
                        if(showClass==true){
                            newRow += "<tr class=\"even_bg\">";
                            showClass=false;
                        }else{
                            newRow += "<tr>";
                            showClass=true;
                        }
                        newRow += "<td>"+tdata[i]["orderNo"]+"</td>"+
                            "<td>"+tdata[i]["serviceName"]+"</td>"+
                            "<td>"+tdata[i]["count"]+"</td>"+
                            "<td class=\"orange\">"+tdata[i]["money"]+"</td>"+
                            "<td>"+tdata[i]["smsCode"]+"</td>"+
                            "<td>"+tdata[i]["smsTimeStr"]+"</td>"+
                            "</tr>";
                        $("#xq_table tr:last").after(newRow);

                    }
                }

			}
			
		}
	});
}