/**
 * 
 */
$(function(){
	$("#unreadMessageNum").hide();//影藏消息未读数量
	//unreadMessage();
	search();
	
	
});

/**
 * 未读信息
 **/
function unreadMessage(){
	$.ajax({
		url:baseUrl+'page/message/unreadMessage.s',
		success:function(msg){
		//	var object= JSON.parse(msg);
			var object=msg;
			if(object!=null&&object.flag){
				if(object.info!=null&&object.info!=0){
					$("#unreadMessageNum").show();
					$("#unreadMessageNum").text(object.info);
					$("#unredaMsgNum").text(object.info);
				}else{
					$("#unredaMsgNum").text(object.info);
					$("#unreadMessageNum").hide();
				}
				
			}
		}
	});
}

//消息总数
function countMessage(){
	var num=0;
	$.ajax({
		url:baseUrl+'page/message/countMessage.s',
		async:false,
		success:function(msg){
//			var object= JSON.parse(msg);
			var object=msg;
			if(object!=null&&object.flag){
				$("#countMessageNum").text(object.info);
				num=object.info;
			}
		}
	});
	return num;
}

//标示为已读
function market(messageId,flag){
	if(flag){
		return;
	}
	var src_str=baseUrl+'images/yuan1.png';
	$.ajax({
		url:baseUrl+'page/message/market.s',
		data:'messageId='+messageId,
		async:false,
		success:function(msg){
//			var object= JSON.parse(msg);
			var object=msg;
			if(object!=null&&object.flag){
				$(".changeReadImg_"+messageId).attr("src",src_str);
				unreadMessage();
			}
		}
		
	});
}

/**
 * 分页查询数据
 * 
 **/

function search(){
	var count=countMessage();
	if(count<=0){
		var str_htm='<div class="error_pic"><img src="'+baseUrl+'images/error.jpg"/></div>';
		$(".message_table").html(str_htm);
		return ;
	}
	var offset = parseInt($("#page_offset").val());
	if (isNaN(offset)) {
		offset = 0;
	}
	var limit = parseInt($("#page_limit").val());
	$.ajax({
		url:baseUrl+'page/message/messageList.s',
		data : {
			"pageStart" : offset * limit,
			"pageSize" : limit
		},
		success:function(msg){
//			var object= JSON.parse(msg);
			var object=msg;
			var str_htm=' <thead>'
					    +'<th width="20"></th>'
					    +'<th width="30%">类别</th>'
					    +'<th width="30%">标题</th>'
					    +' <th>日期 </th>'
					   	+'</thead>';
			if(object!=null&&object.listMessage!=null&&object.listMessage.length>0){
				for(var i=0;i<object.listMessage.length;i++){
					if(object.listMessage[i].type==10){
						if(i%2==0){
							str_htm+=' <tr class="biaoge_col system_tr" onclick="market('+object.listMessage[i].id+','+object.listMessage[i].isRead+')">';
						}else{
							str_htm+=' <tr class="system_tr" onclick="market('+object.listMessage[i].id+','+object.listMessage[i].isRead+')">';
						}
						
					}else{
						if(i%2==0){
							str_htm+=' <tr class="biaoge_col" onclick="market('+object.listMessage[i].id+','+object.listMessage[i].isRead+')">';
						}else{
							str_htm+=' <tr onclick="market('+object.listMessage[i].id+','+object.listMessage[i].isRead+')">';
						}
						
					}

					if(object.listMessage[i].isRead){
						str_htm+='<td><img src="'+baseUrl+'images/yuan1.png" /></td>';
					}else{
						str_htm+='<td><img src="'+baseUrl+'images/yuan2.png" class="changeReadImg_'+object.listMessage[i].id+'"/></td>';
					}

					var htm_str = '<p>'+object.listMessage[i].content+'</p>';
					if(object.listMessage[i].type==0){
						str_htm+='<td>系统消息</td>';
					}else if(object.listMessage[i].type==10){
						str_htm+='<td>异常订单</td>';
					}else if(object.listMessage[i].type==11){
						str_htm+='<td>恢复异常</td>';
					}else if(object.listMessage[i].type==12){
						str_htm+='<td>新增订单</td>';
					}else if(object.listMessage[i].type==20){
						str_htm+='<td>拆单提醒</td>';
					}/*else if(object.listMessage[i].type==101){
						str_htm+='<td>活动申请</td>';
                        htm_str = '<form action="/page/activity/index.s" method="post"><p>'+object.listMessage[i].content
                            +',你可发起'
                            +'<input class="applyagain" type="submit" value="再次申请"></p>'
                            +'</form>';
					}*/else{
						str_htm+='<td>系统消息</td>';
					}
					str_htm+=' <td>'+object.listMessage[i].title+'</td>'
					    +'<td>'+getLocalTime(object.listMessage[i].createTime)+'</td>'
					  +'</tr>';
					 str_htm+='<tr  class="an_li"><td colspan="4"><div class="an_down">'
					     +'<img src="'+baseUrl+'images/arrow_icon.png"/>'
					     + '<div class="an_box_text">'
					     +' <h5>'+object.listMessage[i].title+'</h5>'
						 + htm_str
						 +'</div>'
						 +'</div>'
						 +'</td>'
						 +' </tr>';
				}
				
			}
			$(".message_table").html(str_htm);
			addPageBtn(offset, limit, count);
		}
		
	});
}

/**
 * 时间戳变成日期
 **/

function getLocalTime(dt){
	//return new Date(parseInt(dt)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ").replace(/\//g, "-").replace(/上午|下午|晚上/g, "&nbsp;&nbsp;&nbsp;");    
	if(dt==null){
		return "";
	}
	var date=new Date(parseInt(dt));
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var date_m=date.getDate();
	var hour=date.getHours();
	var minutes=date.getMinutes();
	var seconds=date.getSeconds();
	var date_str=year+"-";
	if(month<10){
		date_str+="0"+month+"-";
	}else{
		date_str+=month+"-";
	}
	if(date_m<10){
		date_str+="0"+date_m;
	}else{
		date_str+=date_m;
	}
	date_str+="&nbsp;&nbsp;&nbsp;";
	if(hour<10){
		date_str+="0"+hour+":";
	}else{
		date_str+=hour+":";
	}
	if(minutes<10){
		date_str+="0"+minutes+":";
	}else{
		date_str+=minutes+":";
	}
	if(seconds<10){
		date_str+="0"+seconds;
	}else{
		date_str+=seconds;
	}
	return date_str;
}