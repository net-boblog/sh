// JavaScript Document

function downloadfile() {

	var url = "./download.s?clear_id=" + $("#clear_id").val();
	// alert(url);
	// window.open(url, "","height=1,width=1,scrollbars=yes,status =yes");

	$("#fm1").attr("action", url);

	$("#fm1").submit();

}

function search() {
	var clear_id = $("#clear_id").val();
	if (clear_id == null || clear_id == '') {
		alert("参数错误！");
		return;
	}

	$.ajax({
				url : ctx + "/page/finance/queryFinanceDetail.s",
				cache : false,
				ifModified : true,
				data : {
					"clear_id" : clear_id
				},
				success : function(data) {
					var json = JSON.parse(data);
					var flag = json.flag;
					if (flag == 1) {
						var tdata = json.data;
						$("#ordertable tr:gt(0)").remove();

						if (tdata.length != 0) {
							for (var int = 0; int < tdata.length; int++) {
								var newRow = "<tr><td class=\"td_content2\">"
										+ tdata[int].no
										+ "</td><td class=\"td_content2\">"
										+ tdata[int].content
										+ "</td><td class=\"td_content2\">"
										+ tdata[int].sum + "</td>"
										+ "<td class=\"td_content2\">"
										+ tdata[int].userinfo
										+ "</td><td class=\"td_content2\">"
										+ tdata[int].server_type + "</td>"
										+ "<td class=\"td_content2\">"
										+ tdata[int].price
										+ "</td><td class=\"td_content2\">"
										+ tdata[int].sms_code
										+ "</td><td class=\"td_content2\">"
										+ tdata[int].sms_date + "</td></tr>";
								$("#ordertable tr:last").after(newRow);
							}
						} else {
							alert('查询到的记录为0');
						}

					} else {
						alert(json.info);
					}

				}
			});
}

$(document).ready(function() {
			search();
		});