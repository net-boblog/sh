// JavaScript Document

$(function() {
	$('.tab_order ul li').click(function() {
		$(this).addClass('hit').siblings().removeClass('hit');
		$('.panes>div:eq(' + $(this).index() + ')').show().siblings().hide();
	});

	$("#my_service_list").addClass("nav_hover");

});

function search() {
	$.ajax({
				url : ctx + "/page/goods/query.s",
				cache : false,
				ifModified : true,
				dataType:'json',
				data : {
					"FFF" : 0
				},
				success : function(data) {
					var json = data;
					var flag = json.flag;
					var audit=json.audit;
					if (flag == '1') {
						var tdata = json.data;

						$("#ordertable tr:gt(0)").remove();
						for (var int = 0; int < json.data.length; int++) {
							var bianji="";
							var shanchu="";
							var newRow = "<tr><td>"
									+ tdata[int].productName
									+ "</td><td>";

							//if(tdata[int].editable){
							//	bianji="<a href='javascript:void(0);' onclick='updateGoods("+tdata[int].productId+")'>编辑</a>  &nbsp;&nbsp;&nbsp;&nbsp;";
							//}

							if(tdata[int].myProduct){
								newRow+=tdata[int].discount+"折";
							}else{
								newRow+=tdata[int].clearAmt;
							}

							//if(tdata[int].delete){
							//	shanchu="<a href='javascript:void(0);' onclick='deleteGoods("+tdata[int].productId+")'> 删除 </a>";
							//}

							newRow+= "</td><td>"
								+ tdata[int].countOrder
								+"</td>";

							if(tdata[int].productStatus==5 || tdata[int].productStatus==7){
								var errStr="<img src=\"../../images/yichang_icon.png\" class=\"shenheError_img\"/>"
										+"<div class=\"tishi_text_box\" style=\"display: none;\">"+tdata[int].productStatusMessage+"</div>";
								newRow+= "<td class='small_pup'>"
									+"<span class=\"orange\">"+audit[tdata[int].productStatus]+"</span>"
									+errStr+"</td><td></tr>";
							}else {
								newRow+= "<td>"
									+audit[tdata[int].productStatus]
									+"</td></tr>";
							}

							//newRow+=bianji+shanchu+"</td></tr>";

							$("#ordertable tr:last").after(newRow);
						}
					} else {
						alert(json.info);
					}
					$(".tk_table tr:even").css("background","#f7f9fb");

					$(".shenheError_img").mouseover(function () {
						$(this).next().show();
					});

					$(".shenheError_img").mouseout(function () {
						$(this).next().hide();
					});
				}
			});
}

//创建服务
function createGoods(){
	window.location.href = ctx + "/page/goods/createPage.s";
}
//删除服务
function  deleteGoods(productId){
	$.myAlert({
		title : "你确定要删除吗？",
		content : "",
		buttonCount : "2"
	}, function() {
		$.ajax({
			url : ctx + "/page/goods/deleteGood.s",
			data :{
				"productId":productId
			},
			ifModified : false,
			cache : false,
			success : function(data) {
				if (data.success && (data.success == "true" || data.success == true)) {
					$("#update_success p").text("删除成功！");
					showSuccessMsg();
					setTimeout(window.location.href=ctx+"goods_query.jsp",2200);
				} else {
					$.myAlert({
						title : "删除失败",
						content : data.message,
						buttonCount : "1"
					},function(){
					});
				}
			}
		});
	});
}
//修改服务
function  updateGoods(productId){
	window.location.href="/page/goods/updatePage.s?productId="+productId;
}

$(document).ready(function() {
			$(".user_box ul li ").hover(function() {
						$(this).find("#em_nr").animate({
									opacity : "show"
								}, "slow");
						$(this).addClass("add_bg");
					}, function() {
						$(this).find("#em_nr").animate({
									opacity : "hide"
								}, "fast");
						$(this).removeClass("add_bg");
					});

			$("#goods_query_btn").parent("dd").addClass('nav_hover');
			//创建服务按钮
			$("#create_href").click(createGoods);
			search();
		});