function SetTableRowColor(){
	$(".tk_table tr:even").css("background","#f7f9fb");
}
$(function(){
	$(".tk_table tr:even").css("background","#f7f9fb");
	$(".year_nav li,.applyActive li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		});
    
	$(".near_day a").bind("click",function(){
		$(this).addClass("dayon").siblings().removeClass("dayon");
		});
		
	$("body").on("click",".message_table tr",function(){
		$("tr.an_li").hide();
		$(this).next("tr.an_li").eq(0).slideToggle();
		});
	
	$(".sub1").click(function(){
		$(this).addClass("nav_hover").siblings().removeClass("nav_hover");
		$(".nav_hover").find(".sublist_left").show();
		if($(this).find(".sublist_left").show()){
			$(this).find(".subarrow_pic").removeClass("subarrow_pic").addClass("subarrow_pic1");	
			$(this).siblings().find(".sublist_left").hide();
			$(this).siblings().find(".subarrow_pic1").removeClass("subarrow_pic1").addClass("subarrow_pic");
		}
		
	});
	
	$(".sublist_left li").click(function(){
		$(this).addClass("son").siblings().removeClass("son");
	});
	var urlname=window.location.href;
	
	/*已验证订单*/
	if(urlname.indexOf("page/serverOrder/list.s")>=0){
		$("#order_list").addClass("aon");
		$("#order_list").find(".left_sublist2").css({
			"color":"#fff",
			"background":"url(../../images/nav-icon.png) no-repeat scroll 0 0",
			"background-position":"20px -49px"
		});
		$("#yz_sub_nav").addClass("son");
		$("#order_list").find(".subarrow_pic").addClass("subarrow_pic1");	
	};
	
	/*待收货订单*/
	if(urlname.indexOf("/order/list.s")>=0 || urlname.indexOf("/order/order_detail.s")>=0){
		$("#order_list").addClass("aon");
		$("#order_list").find(".left_sublist2").css({
			"color":"#fff",
			"background":"url(../../images/nav-icon.png) no-repeat scroll 0 0",
			"background-position":"20px -49px"
		});
		$("#ds_sub_nav").addClass("son");
		$("#order_list").find(".subarrow_pic").addClass("subarrow_pic1");	
	}
	
	/*我要提款*/
	if(urlname.indexOf("drawmoney/index.s")>=0 || urlname.indexOf("/drawmoney/canDrawDetail.s")>=0 || urlname.indexOf("drawDetail.s?clearId")>=0){
		$("#caiwu_list").addClass("aon");
		$("#caiwu_list").find(".left_sublist3").css({
			"color":"#fff",
			"background":"url(../../images/nav-icon.png) no-repeat scroll 0 0",
			"background-position":"20px -89px"
		});
		$("#tk_sub_nav").addClass("son");
		$("#caiwu_list").find(".subarrow_pic").addClass("subarrow_pic1");	
	}
	/*门店奖励*/
	if(urlname.indexOf("storeReward/index.s")>=0){
		$("#caiwu_list").addClass("aon");
		$("#md_sub_nav").addClass("son");
		$("#caiwu_list").find(".subarrow_pic").addClass("subarrow_pic1");	
		$("#caiwu_list").find(".left_sublist3").css({
			"color":"#fff",
			"background":"url(../../images/nav-icon.png) no-repeat scroll 0 0",
			"background-position":"20px -89px"
		});
	}
	
	/*业绩分析*/
	 if (urlname.indexOf("finance/performance_analysis.s") >= 0) {
         $("#caiwu_list").addClass("aon");
         $("#yj_sub_nav").addClass("son");
         $("#caiwu_list").find(".subarrow_pic")
                 .addClass("subarrow_pic1");
         $("#caiwu_list")
                 .find(".left_sublist3")
                 .css(
                         {
                             "color" : "#fff",
                             "background" : "url(../../images/nav-icon.png) no-repeat scroll 0 0",
                             "background-position" : "20px -89px"
                         });
     }

	/*活动管理->超人活动*/
	 if(urlname.indexOf("/page/activity/list.s") >= 0 || urlname.indexOf("page/activity/detail.s")>=0){
		 $("#activity_list").addClass("aon");
		 $("#sys_act_sub_nav").addClass("son");

		 $("#activity_list").find(".subarrow_pic").addClass("subarrow_pic1");
		 $("#activity_list").find(".left_sublist6")
			 .css(
			 {
				 "color" : "#fff",
				 "background" : "url(../../images/nav-icon.png) no-repeat scroll 0 0",
				 "background-position" : "20px -207px"
			 });
	 }

	/*活动管理->我的活动*/
	 if(urlname.indexOf("/page/myActivity/index.s") >= 0 || urlname.indexOf("/page/activity/index.s") >= 0
	     || urlname.indexOf("/page/myActivity/createPage.s")>=0 || urlname.indexOf("/page/myActivity/editPage.s")>=0
	     || urlname.indexOf("/page/myActivity/viewPage.s")>=0 || urlname.indexOf("/page/activity/to-edit.s")>=0
		 || urlname.indexOf("page/activity/my-detail.s")>=0){
		 $("#activity_list").addClass("aon");
		 $("#activity_sub_nav").addClass("son");

		 $("#activity_list").find(".subarrow_pic").addClass("subarrow_pic1");
		 $("#activity_list").find(".left_sublist6")
			 .css(
			 {
				 "color" : "#fff",
				 "background" : "url(../../images/nav-icon.png) no-repeat scroll 0 0",
				 "background-position" : "20px -207px"
			 });
	 }

	if(urlname.indexOf("/page/goods/createPage.s") >= 0 || urlname.indexOf("/page/goods/updatePage.s") >= 0){
		$("#my_service_list").addClass("nav_hover");
	}

});

