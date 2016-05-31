$(function(){
	allMethod.init();
	$(".tishibox").hide();
});
var allMethod={
		init:function(){
			allMethod.tips();
			allMethod.failConfirm();
		},
		tips:function(content,tipwidth,obj){
			$(".tishibox").remove();
			var tips='<div class="tishibox"><p>'+content+'</p><span class="tipstop"></span></div>';
			$(obj).append(tips);
			$(obj).find(".tishibox").width(tipwidth);
			$(obj).find(".tishibox").show();
		},
		tipsOut:function(){
			$(".tishibox").hide();
		},
		failConfirm : function() {
		$("#failConfirm").click(function() {
			$("#fail_title").val('');
			$("#fail_msg").val('');
			$("#update_fail").hide();
		});

		$("#successClose").click(function() {
			$("#update_success").hide();
		});
		$(".quxiao_close,.cancel_window").click(function(){
				$(".Confirm_goods").hide();
		});
		}
}


function showFailMsg(title, msg) {
	$("#fail_title").text(title);
	$("#fail_msg").text(msg);
	$("#update_fail").show();
}

function showSuccessMsg() {
	$("#update_success").show().delay(2000).hide(300);
}
function linkurl(){
	window.location.href=ctx + "/page/myActivity/index.s";
}
function baomingUrl(){
	window.location.href=ctx + "/page/activity/index.s";
}
//弹窗插件
(function($){
	$.extend({
		myAlert : function(options,sure){
			$(".Confirm_goods").remove();
		  var defaults = $.extend({
			title: "您确认要取消吗",
			content: "下线后将无法在恢复此活动！",
            buttonCount: "2",
            firstButton: "确认",
            secondButton: "取消",
            titleClass: "Confirm_goods"
		  },options);
		  var options=$.extend(defaults,options);
		  //构建弹出框
		  var promptbox='<div class="Confirm_goods">';
			  if(options.needClose!=false){
				  promptbox +='<span class="quxiao_close">x</span>'; 
			  }
			  promptbox +='<div class="sure_pup">';
			  promptbox +='<p class="pup_text1">'+ options.title +'</p>';
			  promptbox +=' <p class="pup_text2">'+ options.content +'</p>';
			  promptbox +='</div>';
			  if(options.buttonCount == 2){
				  promptbox +=' <div class="pup_btn"><a href="javascript:void(0);" class="confirm_window" id="sure_btn">'+ options.firstButton +'</a>';
				  promptbox +='<a href="javascript:void(0);" class="cancel_window">'+ options.secondButton +'</a></div>'; 
			  }else if(options.buttonCount == 1){
				  promptbox +='<div class="sure_btn_down"><a id="sure_btn" href="javascript:void();" class="confirm_btn1 yj" style="margin-left:30%">'+ options.firstButton +'</a></div>';
			  }else if(options.buttonCount == 0){
			  }
			  promptbox +='</div>'; 
			  $("body").append( promptbox);
			  $(".Confirm_goods").show();  
			  $(".quxiao_close,.cancel_window").click(function(){
				 $(".Confirm_goods").hide(); 
			  });
			  $("#sure_btn").click(function() {
				  $(".Confirm_goods").hide();
				  sure();
				  return false;
			  });
		}
	});
})(jQuery);

$(function() {
    $(".ad_dui_1").click(function(){
			$(this).hide();
			$(".ad_dui_2").show()
    })
	$(".ad_dui_2").click(function(){
		$(this).hide();
		$(".ad_dui_1").show();
	})

    
//  弹出协议盒子居中
    var offwidth=$(window).width();
    var offheight=$(window).height();
    var tcwidth=$(".ad_act_agreement").width();
    var tcheight=$(".ad_act_agreement").height();
    $(".ad_act_agreement").css({"position":"absolute","left":offwidth/2-tcwidth/2+"px","top":offheight/2-tcheight/2+"px"});
    $(".hui").css("height",offheight+"px");
    
    var inheight=$(".ad_ac_introduce").height();
    $(".ad_ac_introduce").each(function(){
    	var conheight=$(".ad_introduce_content").height();
    	if(conheight.length>3){
    		alert
    	}
    })
    
    
    $(".act_agreement").click(function() {
		iDialog.show("idlg", $('.ad_act_agreement').html(), {
			width: 770,
			height: 566,
			quickClose: true,
			oktext:"已阅读并同意此协议",
			title: "汽车超人活动协议",
			ok: function() {
				$(".imask").hide();
				$(".icontent").hide();
				if($(".ad_read_agree>img").has("dui1_03.png")){
					$(".ad_dui_2").show();
					$(".ad_dui_1").hide();
				}
			},
			cancel: function() {
				iDialog.close("idlg");
			}
		});
	});

	$(".good_agreement").click(function() {
		iDialog.show("idlg", $('.ad_act_agreement').html(), {
			width: 770,
			height: 566,
			quickClose: true,
			oktext:"已阅读并同意此协议",
			title: "汽车超人商户服务合作协议",
			ok: function() {
				$(".imask").hide();
				$(".icontent").hide();
				if($(".ad_read_agree>img").has("dui1_03.png")){
					$(".ad_dui_2").show();
					$(".ad_dui_1").hide();
				}
			},
			cancel: function() {
				iDialog.close("idlg");
			}
		});
	});

});

var showDialog = function(content, callback) {
	iDialog.show("idlg", content, {
		ok: callback,
		cancel: function() {
			iDialog.close("idlg");
		},
		canceltext: "关闭"
	});
}
var showMsg = function(msg, type) {
	iDialog.show("imsg", msg, {
		lock: false,
		auto: true,
		zindex: 999,
		defaulttmpl: false,
		type: type
	});
}

var iDialog = {
	_id: null,
	options: {},
	default: {
		width: 320,
		height: 180,
		lock: true,
		quickClose: false,
		auto: false,
		time: 20000,
		zindex: 98,
		closebtn: false,
		defaulttmpl: true,
		title: "提示",
		oktext: "确定",
		ok: null,
		canceltext: "取消",
		cancel: null,
		type: null
	},
	show: function(id, content, options, callback) {
		iDialog._id = !id ? "idlg" : id;
		if (iDialog.default != null && typeof(iDialog.default) != "undefined") {
			options = options == null ? {} : options;
			for (var key in iDialog.default) {
				if (options[key] === undefined || options[key] === null) {
					options[key] = iDialog.default[key];
				}
			}
			iDialog.options[iDialog._id] = options;
		}
		if (iDialog.options[iDialog._id].defaulttmpl) {
			content = '<div class="confirmmsg" style="margin: auto;">' +
				'<div class="ftitle" style="font-size: 16px; font-weight: bold; text-align: center; padding: 18px 0; border-bottom:solid 1px #ccc; ">' + iDialog.options[iDialog._id].title + '</div>' +
				'<div class="fcontent" style="color: #666666; padding: 10px 10px 20px 20px; text-align: left;">' + content + '</div>' +
				'<div class="ffoot" style="margin:-5px auto; padding: 10px 0; text-align: center;  color: #FFFFFF; font-size: 14px; border-top:solid 1px #ccc;">' +
				'<div class="fok" style="display:inline-block; color:#fff;  padding: 6px 20px; cursor: pointer; background-color:#E05658; margin:0 20px; border-radius:5px;">' + iDialog.options[iDialog._id].oktext + '</div>' +
				'<div class="fcancel" style="display:inline-block; color:#fff; padding: 6px 20px; cursor: pointer; background-color:#999; margin:0 20px; border-radius:5px;">' + iDialog.options[iDialog._id].canceltext + '</div></div></div>';
		}
		var tmplHtml = '<div id=' + iDialog._id + '><div class="imask" style="display: none;position: absolute;top: 0px;left: 0px;background-color: #333;z-index:' + iDialog.options[iDialog._id].zindex + ';opacity: 0.6;-moz-opacity: 0.3;filter: alpha(opacity=30);"></div>' +
			'<div class="ipopup" style="display: none;position: fixed;border: 1px solid rgba(178,178,178,.3);border-radius: 6px;background: #fff;z-index:' + (iDialog.options[iDialog._id].zindex + 1) + ';max-width:90%;">' +
			'<div class="iclose" style="position:absolute;top:-10px;right:-10px;width:22px;height:22px;border-radius: 16px;color:#f00;cursor:pointer;background: url(/images/close_icon.png) no-repeat center center; background-size: contain; background-origin: content-box;"></div>' +
			'<div class="icontent"></div></div></div>';
		var ele = $(tmplHtml);
		if ($('#' + iDialog._id).length <= 0) {
			$("body").append(ele);
		}

		var _dlg = $('#' + iDialog._id);
		var _dlg_id = _dlg.attr("id");
		_dlg.find('.icontent').html(content);
		_dlg.find('.fok').on({
			click: function() {
				if (!iDialog.options[_dlg_id].ok) {

				} else {
					iDialog.options[_dlg_id].ok();
				}
				return false;
			}
		});
		_dlg.find('.fcancel').on({
			click: function() {
				if (!iDialog.options[_dlg_id].cancel) {
					iDialog.close(_dlg_id);
				} else {
					iDialog.options[_dlg_id].cancel();
				}
				return false;
			}
		});
		_dlg.find('.iclose').on({
			click: function() {
				if (_dlg) {
					iDialog.close(_dlg_id);
				}
				return false;
			}
		});

		if (!iDialog.options[iDialog._id].closebtn) {
			_dlg.find('.iclose').hide();
		}
		if (iDialog.options[iDialog._id].lock) {
			iDialog.showMask();
		}

		iDialog.showContent();

		if (iDialog.options[iDialog._id].auto) {
			setTimeout(function() {
				if (_dlg) {
					iDialog.close(_dlg_id);
				}
			}, iDialog.options[iDialog._id].time);
		}

		if (callback != null && typeof(callback) != "undefined" && typeof(callback) == "function") {
			callback();
		}
		
	},
	showMask: function() {
		var documentHeight = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
		var documentWidth = Math.max(document.documentElement.scrollWidth, document.body.scrollWidth);
		$('#' + iDialog._id + ' .imask').css({
			"height": documentHeight + "px",
			"width": documentWidth + "px"
		}).show();
		if (iDialog.options[iDialog._id].quickClose) {
			$('#' + iDialog._id + ' .imask').on("click", function() {
				var dlg = $(this).parent();
				iDialog.close(dlg.attr("id"));
			});
		}
	},
	showContent: function() {
		var ipopupobj = $('#' + iDialog._id + ' .ipopup');
		ipopupobj.show();
		iDialog.resize();
		iDialog.center(ipopupobj);
		ipopupobj.removeClass('animateOut').addClass('animateIn').removeClass('animated').addClass('animated');
	},
	resize: function() {
		var content = $('#' + iDialog._id + ' .icontent');
		var width = iDialog.options[iDialog._id].width;
		var height = iDialog.options[iDialog._id].height;
		content.removeAttr("style");
		if (content.children().length == 0) {
			content.css({
				"padding": "16px"
			});
			if (!!iDialog.options[iDialog._id].type) {
				$('#' + iDialog._id + ' .ipopup').removeClass(function(index, css) {
					return (css.match(/\bmsg-\S+/g) || []).join('');
				})
				$('#' + iDialog._id + ' .ipopup').addClass("msg-" + iDialog.options[iDialog._id].type);
			}
			return false;
		}
		content.css({
			"height": height + "px",
			"width": width + "px"
		});
		var fcontentHeight=height-58-58;
		$(".fcontent").css("height",fcontentHeight+"px")
	},
	center: function(obj) {
		var top = ($(window).height() - $(obj).height()) / 2;
		var left = ($(window).width() - $(obj).width()) / 2;
		$(obj).css({
			"top": top + "px",
			"left": left + "px"
		});
	},
	close: function(id) {
		if ($('#' + id + ' .ipopup').css('animation-name') == 'none') {
			$('#' + id + ' .ipopup').hide();
		} else {
			setTimeout(function() {
				$('#' + id + ' .ipopup').hide();
			}, 500);
		}
		$('#' + id + ' .ipopup').removeClass('animateIn').addClass("animateOut").removeClass('animated').addClass('animated');
		$('#' + id + ' .imask').hide();
	}
}