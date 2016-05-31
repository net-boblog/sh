/**
 * Created by user on 2015/10/29.
 */
$(function() {
			$("#user_evaluation_list").addClass("nav_hover");
		});
var selectData = {};
var con = {
	"serviceParentId" : "",
	"serviceId" : "",
	"createTimeStart" : "",
	"createTimeEnd" : ""
};
$(document).ready(function() {
	// 左边菜单选中
	// $("#user_comments_btn").parent("dd").addClass('nav_hover');
	// 查询上到的名称与平均分
	$.ajax({
				url : basePath + "/page/comment/shopInfo.s",
				data : {},
				success : function(data) {
					$("#store_name_span").html("");
					$("#ruler").show();
					if (data.success
							&& (data.success == "true" || data.success == true)) {
						// 商户名称
						if (data.storeName != null && data.storeName != "") {
							$("#store_name_span").html(data.storeName);
						}
						// 商户平均分
						if (data.avgScore != null && data.avgScore != "") {
							$("#store_score_left").html(data.avgScore);
							$("#store_score_right").html(data.avgScore);
							var score = parseFloat(data.avgScore);
							var starCount = 0;
							// 平均分以星星展示
							$("#score_star_img").html("");
							var starHtml = "";
							for (var i = 0; i < 5 && score > 0; i++) {
								if (score >= 0.5) {
									starHtml += "<img src='" + basePath
											+ "/images/start1.png'/>";
								} else {
									starHtml += "<img src='" + basePath
											+ "/images/start2.png'/>";
								}
								score = score - 1;
								starCount++;
							}
							for (i = 0; i < (5 - starCount); i++) {
								starHtml += "<img src='" + basePath
										+ "/images/gary_statr.png'/>";
							}
							$("#score_star_img").html(starHtml);
							// 平均分以尺子展示
							try {
								score = parseFloat(data.avgScore);
								var range = (378 * (score / 5));
								range = parseInt(range + "");
								range = range - 14;
								$("#store_score_right").css("left",
										range + "px");
							} catch (ex) {
								$("#store_score_right").css("left", "329px");
							}
						}
					}
				},
				error : function() {

				}
			});
	// 查询商户提供的一级服务
	$.ajax({
				url : basePath + "/page/comment/findService.s",
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
	// 查询商户评价统计
	loadScoreStat();
	// 评价类型单选
	$(".user_evaluate > ul > li").click(function() {
				var rank = $(this).attr("rank");
				$("#rank_hidden").val(rank);
				searchRadioOnclick();
			});
	$(".user_evaluate").on("click", ".photos-thumb li", function() {
		var bigimg = $(this).parents(".photos").siblings(".imgxob")
				.find(".bigimg>img").attr("src");
		$(this).addClass("curren").siblings("li").removeClass("curren");
		var clisrc = $(this).find("img").attr("src");

		$(this).parents(".photos").siblings(".imgxob").find(".bigimg>img")
				.attr("src", clisrc);
		if (clisrc == bigimg) {
			$(this).parents(".photos").siblings(".imgxob").toggle();
		} else {
			$(this).parents(".photos").siblings(".imgxob").show();

		}
	});

	$(".user_evaluate").on("click", ".toleft,.toright", function() {
				$(this).parents(".imgxob").toggle();
			})

	// 左边
	$(".user_evaluate").on("click", ".prev", function() {
		$(this).parents(".imgxob").siblings(".photos").find("li.curren")
				.prev("li").addClass("curren").siblings("li")
				.removeClass("curren");
		var clisrc = $(this).parents(".imgxob").siblings(".photos")
				.find("li.curren").find("img").attr("src");
		var lilength = $(this).parents(".imgxob").siblings(".photos")
				.find("li.curren").prev("li").length;
		if (lilength > 0) {
			$(this).show();
		} else {
			$(this).hide();
		}
		$(this).closest("dt").find(".imgxob .bigimg>img").attr("src", clisrc);
	});

	// 右边
	$(".user_evaluate").on("click", ".next", function() {
		$(this).parents(".imgxob").siblings(".photos").find("li.curren")
				.next("li").addClass("curren").siblings("li")
				.removeClass("curren");
		var clisrc = $(this).parents(".imgxob").siblings(".photos")
				.find("li.curren").find("img").attr("src");
		var lilength = $(this).parents(".imgxob").siblings(".photos")
				.find("li.curren").next("li").length;
		if (lilength > 0) {
			$(this).show();
		} else {
			$(this).hide();
		}
		$(this).closest("dt").find(".imgxob .bigimg>img").attr("src", clisrc);
	});
	searchOnclick();
});
// 查询商户评价统计
function loadScoreStat() {
	$.ajax({
				url : basePath + "/page/comment/stat.s",
				data : {
					"serviceParentId" : $("#serviceParentId").val(),
					"serviceId" : $("#serviceId").val(),
					"createTimeStart" : $("#createTimeStart").val(),
					"createTimeEnd" : $("#createTimeEnd").val()
				},
				success : function(data) {
					$("#total_comment_choose_span").html("0");
					$("#image_comment_choose_span").html("0");
					$("#good_comment_choose_span").html("0");
					$("#medium_comment_choose_span").html("0");
					$("#bad_comment_choose_span").html("0");
					if (data.success
							&& (data.success == "true" || data.success == true)) {
						$("#total_comment_choose_span").html(data.totalCount);
						$("#image_comment_choose_span").html(data.imgCount);
						$("#good_comment_choose_span")
								.html(data.goodCommentCount);
						$("#medium_comment_choose_span")
								.html(data.mediumCommentCount);
						$("#bad_comment_choose_span")
								.html(data.badCommentCount);
					}
				},
				error : function() {

				}
			});
}

// 服务项目下拉选择事件
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
					url : basePath + "/page/comment/findService.s",
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

function searchOnclick() {
	$("#page_offset").val("0");
	con.serviceParentId = $("#serviceParentId").val();
	con.serviceId = $("#serviceId").val();
	con.createTimeStart = $("#createTimeStart").val();
	con.createTimeEnd = $("#createTimeEnd").val();
	loadScoreStat();
	search();
}
//重置查询条件
function reset(){
	$("#serviceParentId").val("");
	$("#serviceId").val("");
	$("#createTimeStart").val("");
	$("#createTimeEnd").val("");
	serviceSelectOnchange();
	searchOnclick();
}
// 单选点击重新搜索、不需要重新统计
function searchRadioOnclick() {
	$("#page_offset").val("0");
	search();
}

// 查询评价列表
function search() {
	$.ajax({
		url : basePath + "/page/comment/list.s",
		data : {
			"pageStart" : $("#page_offset").val(),
			"pageSize" : $("#pageSize").val(),
			"serviceParentId" : con.serviceParentId,
			"serviceId" : con.serviceId,
			"commentTypeId" : $("#rank_hidden").val(),
			"createTimeStart" : con.createTimeStart,
			"createTimeEnd" : con.createTimeEnd
		},
		ifModified : false,
		cache : false,
		success : function(data) {
			$(".reviewlist tbody").html("");
			$("#data_list_div").html("");
			$("#no_data_div").hide();
			if (data.success
					&& (data.success == "true" || data.success == true)) {
				var list = data.data;
				var total = data.total;
				var offset = $("#page_offset").val();
				var limit = 20;
				addPageBtn(offset, limit, total);
				if (list != null && list.length > 0) {
					var len = list.length;
					var imgs = "";
					var newDl = "";
					var imgArr = [];
					var background = false;
					var userName="";
					for (var i = 0; i < len; i++) {
						imgs = list[i].imgs;
						if (imgs == undefined || imgs == null) {
							imgs = "";
						}
						if (background) {
							newDl += "<dl class='clearfix' style='background: rgb(247, 249, 251);'>";
							background = false;
						} else {
							newDl += "<dl class='clearfix'>";
							background = true;
						}
						newDl += "<dt>" + list[i].comment; // 评论
						if (imgs != "") {
							newDl += "<div class='photos'><ul class='photos-thumb' myimg='"
									+ imgs + "'>";
							imgArr = imgs.split(",");
							for (var j = 0; j < imgArr.length; j++) {
								newDl += "<li class=''><img src='" + imgArr[j]
										+ "'><span></span>";
							}
							newDl += "</ul></div>";
							newDl += "<div class='imgxob' style='display: none;'><div class='bigimg'>"
									+ "<div class='prev' style='display: none;'><img src='http://static.qichechaoren.com/upload/2015/09/toleft.png'></div>"
									+ "<div class='next' style='display: none;'><img src='http://static.qichechaoren.com/upload/2015/09/toright.png'></div>"
									+ "<div class='toleft'></div><div class='toright'></div>"
									+ "<img src='"
									+ imgArr[0]
									+ "'></div></div>";
						}
						newDl += "</dt><dd>";
						userName = list[i].username;
						if(userName==undefined || userName == null || userName==""){
							userName = "匿名用户";
						}
						newDl += "<p>" + userName + "</p>"; // 评价用户
						newDl += "<p>" + Util.timestampformat(list[i].commentTime,"yyyy-MM-dd hh:mm:ss") + "</p>"; // 评价时间
						newDl += "</dt>";

                        var txtImg = 'huifu';
                        var txtColor = '#0080FF';
                        var txt = '回复';
                        var isEdit = "";
                        var isShow = 'none';
                        var btnsIsShow = 'block';
						var reply = "";
                        if(list[i].reply != null){
                            isShow = 'block';
                            isEdit = 'disabled';
                            btnsIsShow = 'none';
                            txtColor = '#ADADAD';
                            txt = '商家回复';
                            txtImg = 'huifu_0';
							reply = list[i].reply;
                        }
                        reply = "sdsdsd"
						/*商户回复暂时屏蔽*/
						var newDl_huifu = "";
						newDl_huifu +='<dt style="margin-top:20px;">'
							+'<input opt="user'+i+'" type="text" hidden value="'+list[i].userId+'">'
                            +'<span opt="replayBtn'+i+'" onclick="replayClick(this,'+i+');" style="color: '+txtColor+';cursor:pointer;">'
							+'<img style="width: 15px;height:15px;margin-right: 3px;" src="/images/'+txtImg+'.png"/><span style="vertical-align: middle;">'+txt+'</span>'
							+'</span>'
							+'</dt>'
							+'<div opt="replay'+i+'" style="display: '+isShow+';">'
							+'<dt>'
                            +'<div class="container">'
                            +'<input hidden opt="reply_val'+i+'" value="'+reply+'">'
							+'<textarea name="replay_content'+i+'" onkeyup="contentChange(this,'+i+')" '+isEdit+' class="reply_area" placeholder="您的回复会被公开展示，请注意措辞。最多500字">'+reply+'</textarea>'
							+'<s><i></i></s></div>'
							+'<label opt="replay_error'+i+'" class="error" style="display: none;width: 170px;">回复内容不得超过500字</label>'
                            +'<dt opt="btns'+i+'" style="margin-top: 10px;display: '+btnsIsShow+';">'
							+'<a href="javaScript:;" class="submit_btn" onclick="replay('+i+')">确&nbsp;定</a>'
							+'&nbsp;&nbsp;'
							+'<a href="javaScript:;" class="cancel_btn" onclick="cancel('+i+');">取消</a>'
							+'</dt>'
							+'<dt class="update_btn" opt="updateBtn'+i+'" style="display:'+isShow+';"><span onclick="upCss('+i+');" class="reply_up">修改</span></dt>'
							+'</dt>'
							+'</div>';
						newDl += "</dl>";
					}
					$("#data_list_div").html(newDl);
					// 左边滑入滑出
					$(".imgxob .bigimg .toleft").hover(function() {
						var lilength = $(this).parents(".imgxob")
								.siblings(".photos").find("li.curren")
								.prev("li").length;
						if (lilength > 0) {
							$(this).siblings(".prev").show();
						} else {
							$(this).siblings(".prev").hide();
						}

					}, function() {
						$(this).siblings(".prev").hide();
					});
					$(".imgxob .bigimg .prev").hover(function() {
						var lilength = $(this).parents(".imgxob")
								.siblings(".photos").find("li.curren")
								.prev("li").length;
						if (lilength > 0) {
							$(this).show();
						} else {
							$(this).hide();
						}

					}, function() {
						$(this).hide();
					});

					// 右边滑入滑出
					$(".imgxob .bigimg .toright").hover(function() {
						var lilength = $(this).parents(".imgxob")
								.siblings(".photos").find("li.curren")
								.next("li").length;
						if (lilength > 0) {
							$(this).siblings(".next").show();
						} else {
							$(this).siblings(".next").hide();
						}
					}, function() {
						$(this).siblings(".next").hide();
					});
					$(".imgxob .bigimg .next").hover(function() {
						var lilength = $(this).parents(".imgxob")
								.siblings(".photos").find("li.curren")
								.next("li").length;
						if (lilength > 0) {
							$(this).show();
						} else {
							$(this).hide();
						}
					}, function() {
						$(this).hide();
					});
				} else {
					$("#no_data_div").show();
				}
			}
		}
	});
}

/*确定*/
function replay(i){
    var replay_content = $('[name=replay_content'+i+']').val().trim();
    if(replay_content.length > 0 && replay_content.length <= 500){
        $.ajax({
            url:basePath + "/page/comment/replyComment.s",
            cache : false,
            type:'post',
            data:{
                reply:replay_content,
                userId:$('[opt=user'+i+']').val()
            },
            success: function (data) {
                if(data.success){
                    $('[name=replay_content'+i+']').attr("disabled",true);
                    $('[opt=replayBtn'+i+']').find('span').text("商家回复");
                    $('[opt=updateBtn'+i+']').show();
                    $('[opt=btns'+i+']').hide();
                    //window.location.reload();
                }
            }
        });
    }
    if(replay_content.length > 500){
        $('[opt=replay_error'+i+']').show();
        return false;
    }
}
/*更新*/
function upCss(i){
	$('[opt=updateBtn'+i+']').hide();
	$('[opt=btns'+i+']').show();
	$('[name=replay_content'+i+']').attr("disabled",false);
	$('[opt=replayBtn'+i+']').find('span').text("回复");
}
/*取消*/
function cancel(i){
    var replay_content = $('[name=replay_content'+i+']').val().trim();
    var replyVal = $('[opt=reply_val'+i+']').val();
    this.hasContent = function (reply) {
        $('[opt=replayBtn'+i+']').find('span').text("商家回复");
        $('[name=replay_content'+i+']').val(reply);
        $('[name=replay_content'+i+']').attr("disabled",true);
        $('[opt=updateBtn'+i+']').show();
        $('[opt=btns'+i+']').hide();
    }
    if (replyVal != null && replyVal.length > 0){
        this.hasContent(replyVal);
    }else{
        if(replay_content.length == 0){
            $('[opt=replay'+i+']').hide();
            $('[name=replay_content'+i+']').val("")
            $('[opt=replayBtn'+i+']').find('img').attr('src','/images/huifu.png');
            $('[opt=replayBtn'+i+']').css('color','#0080FF');
        }else{
            this.hasContent(null);
        }
    }
    $('[opt=replay_error'+i+']').hide();
}
/*单击回复按钮*/
function replayClick(e,i){
	$(e).find('img').attr('src','/images/huifu_0.png');
	$(e).css('color','#ADADAD');
	$('[opt=replay'+i+']').show();
}
/*内容变动事件*/
function contentChange(e,i){
    if($(e).val().trim().length > 0 && $(e).val().trim().length <= 500){
        $('[opt=replay_error'+i+']').hide();
    }
}