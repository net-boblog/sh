// JavaScript Document
$(function() {
			$("#order_list").addClass("nav_hover");
		});
$(function() {
			$('.tab_order ul li').click(function() {
				$(this).addClass('hit').siblings().removeClass('hit');
				$('.panes>div:eq(' + $(this).index() + ')').show().siblings()
						.hide();
			});
		});

function search() {

	var offset = parseInt($("#page_offset").val());
	var limit = parseInt($("#page_limit").val());

	$.ajax({
				url : ctx + "/page/order/query.s",
				cache : false,
				ifModified : true,
				data : {
					"searchinfo" : $("#searchinfo").val(),
					"mendian" : $("#mendian").val(),
					"service_type" : $("#service_type").val(),
					"js_date_start" : $("#js_date_start").val(),
					"js_date_end" : $("#js_date_end").val(),
					"js_status" : $("#js_status").val(),
					"smstime_updown" : $("#smstime_updown").val(),
					"page_offset" : offset * limit,
					"page_limit" : limit
				},
				success : function(data) {
					// alert(data);
					var json = JSON.parse(data);
					var flag = json.flag;
					if (flag == 1) {
						var tdata = json.data;
						var total = json.total;
						$("#res").html('');
						$("#ordertable tr:gt(0)").remove();

						if (json.data.length != 0) {
							for (var int = 0; int < json.data.length; int++) {

								var orders_status = "";
								if (tdata[int].clear_status == 2) {
									orders_status += "结算中";
								} else if (tdata[int].clear_status == 3) {
									orders_status += "已结算";
								} else if (tdata[int].clear_status == -1
										&& tdata[int].audit_status == 0) {
									orders_status += "可结算";
								} else if (tdata[int].clear_status == -1
										&& tdata[int].audit_status == 2) {
									orders_status += "待确认";
								} else if (tdata[int].clear_status == -1
										&& tdata[int].audit_status == 1
										&& tdata[int].backFlag == 1) {
									orders_status += "异常单";
								} else if (tdata[int].clear_status == -1
										&& tdata[int].audit_status == 1
										&& tdata[int].backFlag != 1) {
									orders_status += "失效单";
								}
								var newRow = "<tr><td class=\"td_content1\">"
										+ tdata[int].server_id
										+ "</td><td class=\"td_content1\">"
										+ tdata[int].item_name
										+ "</td><td class=\"td_content1\">"
										+ tdata[int].sale_num
										+ "</td><td class=\"td_content1\">"
										+ tdata[int].userinfo + "</td>"
										+ "<td class=\"td_content1\">"
										+ tdata[int].server_name
										+ "</td><td class=\"td_content1\">"
										+ tdata[int].sms_code + "</td>"
										+ "<td class=\"td_content1\">"
										+ tdata[int].sms_check_date
										+ "</td><td class=\"td_content1\">"
										+ orders_status + "</td></tr>";
								$("#ordertable tr:last").after(newRow);
								addPageBtn(offset, limit, total);
							}
						} else {
							$(".page").html('');
							$("#res").html('暂无数据');
						}

					} else {
						alert(json.info);
					}
				}
			});
}

function prePage() {
	var page_offset = parseInt($("#page_offset").val());
	if (page_offset > 0) {
		$("#page_offset").val(page_offset - 1);
		search();
	} else {
		alert("已经是第一页");
	}
}

function nextPage() {
	var page_offset = parseInt($("#page_offset").val());
	var page_size = parseInt($("#page_size").val());
	if (page_offset + 1 < page_size) {
		$("#page_offset").val(page_offset + 1);
		search();
	} else {
		alert("已经是最后一页");
	}
}

function doPage(offset) {
	$("#page_offset").val(offset);
	search();
}

// 添加页数按钮
function addPageBtn(p_offset, p_limit, p_total) {
	var offset = parseInt(p_offset);
	var limit = parseInt(p_limit);
	var total = parseInt(p_total);
	// 总页数
	var pageSize = Math.ceil(total / limit);
	$("#page_size").val(pageSize);
	// alert(offset+"|"+limit+"|"+total+"|"+pageSize);
	if (pageSize > 0) {
		var addtxt = "";
		addtxt += "<a href=\"javascript:prePage();\">上一页</a>";
		if ((offset - 1) > 0) {
			var pageno = offset - 1;
			addtxt += "<a href=\"javascript:doPage(" + (offset - 2) + ")\">"
					+ pageno + "</a>";
		}
		if (offset > 0) {
			var pageno = offset;
			addtxt += "<a href=\"javascript:doPage(" + (offset - 1) + ")\">"
					+ pageno + "</a>";
		}
		if (0 < (offset + 1) <= pageSize) {
			var pageno = offset + 1;
			addtxt += "<span class=\"current\">" + pageno + "</span>";
		}
		if ((offset + 2) <= pageSize) {
			var pageno = offset + 2;
			addtxt += "<a href=\"javascript:doPage(" + (offset + 1) + ")\">"
					+ pageno + "</a>";
		}
		if ((offset + 3) <= pageSize) {
			var pageno = offset + 3;
			addtxt += "<a href=\"javascript:doPage(" + (offset + 2) + ")\">"
					+ pageno + "</a>";
		}
		addtxt += "<a href=\"javascript:nextPage();\">下一页</a>";
		$(".page").html(addtxt);
	}
}

function hideshowccc() {

	if ($("#ccc").is(":hidden")) {
		$("#ccc").show();
		$("#searchccc").text("精简搜索");
	} else {
		$("#ccc").hide();
		$("#searchccc").text("高级搜索");
		$("#js_date_start").val('');
		$("#js_date_end").val('');
		$("#service_type").get(0).selectedIndex = 0;
		$("#js_status").get(0).selectedIndex = 0;
	}
}

function downloadfile() {

	var url = "./download.s?searchinfo=" + $("#searchinfo").val() + "&mendian="
			+ $("#mendian").val() + "&service_type=" + $("#service_type").val()
			+ "&js_date_start=" + $("#js_date_start").val() + "&js_date_end="
			+ $("#js_date_end").val() + "&js_status=" + $("#js_status").val()
			+ "&smstime_updown=0";
	$("#fm1").attr("action", url);

	$("#fm1").submit();

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

	$("#order_query_btn").parent("dd").addClass('nav_hover');

	$('.search_button').click(function() {
				$("#page_offset").val(0);
				search();
			});

	$('.smstime_updown').click(function() {
				if ($("#smstime_updown").val() == '0') {
					$("#smstime_updown").val('1');
					$(".smstime_updown").removeClass("down").addClass("up");
				} else {
					$("#smstime_updown").val('0');
					$(".smstime_updown").removeClass("up").addClass("down");
				}
				search();
			});

	hideshowccc();

	$.ajax({
				url : ctx + "/page/order/queryServerType.s",
				cache : false,
				ifModified : true,
				data : {
					"page_limit" : 123
				},
				success : function(data) {
					// alert(data);
					var json = JSON.parse(data);
					var flag = json.flag;
					if (flag == 1) {
						var tdata = json.data;

						if (tdata.length != 0) {
							for (var int = 0; int < json.data.length; int++) {
								$("#service_type").append("<option value='"
										+ tdata[int].id + "'>"
										+ tdata[int].idname + "</option>");
							}
						} else {
							// alert('初始化服务类型错误！');
						}
						var storelist = json.store;
						if (storelist.length != 0) {
							for (var int = 0; int < storelist.length; int++) {
								$("#mendian").append("<option value=\""
										+ storelist[int].pkid + "\">"
										+ storelist[int].storeName
										+ "</option>");
							}
							$("#mendians").show();
						}

					} else {
						alert(json.info);
					}

				}
			});

	if ($("#searchinfo").val() == null) {
		$("#searchinfo").val('');
	}

	search();

});