$(document).keypress(function(e) {
	// 回车键事件
	
	if (e.which == 13) {
		if(e.target=="[object HTMLInputElement]"){
			if(parseInt($("#spage").val())<1||parseInt($("#spage").val())>parseInt($("#page_size").val())){
				alert("页码超出范围！");
				return;
			}

			if ($("#spage").val() == null || $("#spage").val() == '') {
				$("#page_offset").val(0);
			} else {
				$("#page_offset").val($("#spage").val()-1);
			}
			search();
		}
	}
}); 

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
	$(function(){
		$("#page_offset").val(offset);
		search();	
	});
	
}

// 添加页数按钮
function addPageBtn(p_offset, p_limit, p_total) {
	$(".page").html('');
	var offset = parseInt(p_offset);
	var limit = parseInt(p_limit);
	var total = parseInt(p_total);
	
	// 总页数
	var pageSize = Math.ceil(total / limit);
	$("#page_size").val(pageSize);
	// alert(offset+"|"+limit+"|"+total+"|"+pageSize);
	if (pageSize > 0) {
		var addtxt = "";
		addtxt += "<a id=\"first_page\" href=\"javascript:doPage(0)\">首页</a><a id=\"pre_page\" href=\"javascript:prePage();\">上一页</a>";
		if ((offset - 1) > 0) {
			var pageno = offset - 1;
			addtxt+="<a href=\"javascript:doPage("+(offset - 2)+")\">"+pageno+"</a>";
		}
		if (offset > 0) {
			var pageno = offset;
			addtxt+="<a href=\"javascript:doPage("+(offset - 1)+")\">"+pageno+"</a>";
		}
		if (0 < (offset + 1) <= pageSize) {
			var pageno = offset + 1;
			addtxt+="<span class=\"current\">"+pageno+"</span>";
		}
		if ((offset + 2) <= pageSize) {
			var pageno = offset + 2;
			addtxt+="<a href=\"javascript:doPage("+(offset + 1)+")\">"+pageno+"</a>";
		}		
		if ((offset + 3) <= pageSize) {
			var pageno = offset + 3;
			addtxt+="<a href=\"javascript:doPage("+(offset + 2)+")\">"+pageno+"</a>";
		}
		addtxt += "<a id=\"next_page\" href=\"javascript:nextPage();\">下一页</a><a id=\"last_page\" href=\"javascript:doPage("+(pageSize-1)+")\">尾页</a><span style=\"font-size:15px;padding-left:5px;padding-top:20px\"><input type='text' id='spage' style='width:27px;height:26px;margin-top:-2px;margin-left:6px;text-align:center;' value='"+(offset+1)+"' > /"+pageSize+"页</span>";
		$(".page").html(addtxt);
		if(offset==0){
			$("#first_page").removeAttr('href').css("background-color","#ccc").hover(function(){
				$("#first_page").css("color","#000");
			});
			$("#pre_page").removeAttr('href').css("background","#ccc").hover(function(){
				$("#pre_page").css("color","#000");
			});

		}
		 if((offset==0) &&(pageSize==1)){
				$("#next_page").removeAttr('href').css("background-color","#ccc").hover(function(){
					$("#next_page").css("color","#000");
				});
				$("#last_page").removeAttr('href').css("background-color","#ccc").hover(function(){
					$("#last_page").css("color","#000");
				});
		 }
		else if((offset+1)==pageSize){
			$("#last_page").removeAttr('href');
			$("#last_page").hover(function(){
				$("#last_page").css("color","#000");
			});
			$("#last_page").css("background-color","#ccc");
			$("#next_page").removeAttr('href');
			$("#next_page").hover(function(){
				$("#next_page").css("color","#000");
			});
			$("#next_page").css("background-color","#ccc");
		}
	}
}