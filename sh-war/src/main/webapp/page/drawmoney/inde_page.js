
$(function(){
	search_boxes();
	var abnormal_text=$("#disable_tk").attr("reason");
	$("#abnormal_title").html(abnormal_text);
	$("#iknow").click(function(){
		$(".touming_bg").hide();
		$("#tk_abnormal").hide();
	});
});

/*提款异常*/

function tk_disable(){
	$(".touming_bg").show();
	$("#tk_abnormal").show();
	
}
/*确认提交*/
function qr_tijiao(){
	var dataID;
	$.ajax({
		url:ctx +"/page/drawmoney/doDrawMoney.s",
		dataType:"json",
		success:function(data){
			    dataID=data.data;
				window.location.href=ctx+"/page/drawmoney/drawDetail.s?clearId="+ dataID;
			//}
			
		}
	
	});
	
	
}
function tk_show(){ /*弹窗出现*/
	$(".touming_bg").show();
	$("#sure_tikuan_pup").show();
}
function tk_hide(){ /*弹窗消失*/
	$(".touming_bg").hide();
	$("#sure_tikuan_pup").hide();
}
function search_boxes(){
	$("#page_offset").val(0);
	search();
};
function search(){
	$.ajax({
		url:ctx +"/page/drawmoney/drawRecord.s",
		dataType:"json",
		data : {
			"page" : parseInt($("#page_offset").val())+1,
			"status" : $("#status").val(),
			"beginTime" : $("#beginTime").val(),
			"endTime" : $("#endTime").val()
		},
		success : function(data){
			if(data.flag==0){
				window.location.href=ctx+"/page/common/error.jsp";
			}
			else{
				$("#data_table tr:gt(0)").remove();
				var tdata = data.data.dataList;
				var total = data.data.totalCount;
				var offset = $("#page_offset").val();
				var limit = data.data.pageSize;
				addPageBtn(offset, limit, total);
				var showClass=false;
				for (var i = 0; i < tdata.length; i++) {
					var newRow="";
					if(showClass==true){
						 newRow += "<tr class=\"even_bg\">";
						 showClass=false;
					}else{
						newRow += "<tr>";
						 showClass=true;
					}
					newRow += "<td valign=\"top\">"+tdata[i]["no"]+"</td>"+
	            "<td valign=\"top\">"+tdata[i]["createTimeStr"]+"</td>"+
	            "<td valign=\"top\">"+tdata[i]["clearTimeStr"]+"</td>"+
	            "<td valign=\"top\">"+Util.moneyScale(tdata[i]["sum"], 1)+"</td>"+
	            "<td valign=\"top\">"+tdata[i]["statusDesc"]+"</td>"+
	            "<td>"+tdata[i]["accountNo"]+"<p>"+tdata[i]["accountName"]+"</p></td>"+
	            "<td valign=\"top\"><a href=\"/page/drawmoney/drawDetail.s?clearId="+tdata[i]["id"]+"\">查看详情</a></td>"+
	          "</tr>";
					$("#data_table tr:last").after(newRow);

				}
			}
			
		}
	});
	
}
