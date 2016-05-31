// JavaScript Document
$(function() {
			$("#caiwu_list").addClass("nav_hover");
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
		url : ctx + "/page/finance/queryFinance.s",
		cache : false,
		ifModified : true,
		data : {
			"js_date_start" : $("#js_date_start").val(),
			"js_date_end" : $("#js_date_end").val(),
			"js_status" : $("#js_status").val(),
			"smstime_updown" : $("#smstime_updown").val(),
			"page_offset" : offset * limit,
			"page_limit" : limit
		},
		success : function(data) {
			var json = JSON.parse(data);
			var flag = json.flag;// alert(data);
			if (flag == 1) {
				var tdata = json.data;
				var total = json.total;
				$("#res").html('');
				$("#ordertable tr:gt(0)").remove();

				if (tdata.length != 0) {
					for (var int = 0; int < tdata.length; int++) {
						var status_name = "";
						if (tdata[int].c_status == 2) {
							status_name = "结算中";
						} else if (tdata[int].c_status == 3) {
							status_name = "已结算";
						} else {
							status_name = "未知";
						}
						var newRow = "<tr><td class=\"td_content2\">"
								+ tdata[int].js_no
								+ "</td><td class=\"td_content2\">"
								+ tdata[int].clear_time
								+ "</td>"
								+ "<td class=\"td_content2\">"
								+ tdata[int].sum
								+ "</td><td class=\"td_content2\">"
								+ status_name
								+ "</td><td class=\"td_content2\">"
								+ tdata[int].accinfo
								+ "</td>"
								+ "<td class=\"td_content2\"><input class=\"btn_zp\" value=\"订单详情\" type=\"button\" onclick=\"openDetail("
								+ tdata[int].js_id + ")\"></td></tr>";
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

function openDetail(orderid) {
	window.open('./finance_detail.jsp?clear_id=' + orderid, '',
			'height=600,width=1200,scrollbars=yes,status =yes');
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

			$("#finance_query_btn").parent("dd").addClass('nav_hover');

			$('.search_button').click(function() {
						$("#page_offset").val(0);
						search();

					});

			$('.smstime_updown').click(function() {
						if ($("#smstime_updown").val() == '0') {
							$("#smstime_updown").val('1');
							$(".smstime_updown").removeClass("down")
									.addClass("up");
						} else {
							$("#smstime_updown").val('0');
							$(".smstime_updown").removeClass("up")
									.addClass("down");
						}
						search();
					});

			search();

		});