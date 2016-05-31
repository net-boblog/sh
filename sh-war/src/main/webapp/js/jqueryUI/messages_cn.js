$(function(){
	validate.init();
});
var validate={
	init:function(){
		validate.formValidate();
	},
	formValidate:function(){
	    //开始验证
	    return $("#signupForm").validate({
	        rules : {
	            name : {
	                required : true
	            },
	            endTime:{
	            	required:true,
	            	time:true,
					compareTime:true
	            },
	            secondCategoryId:{
	            	required : true,

	            },
	            saleNumber:{
	            	required : true,
	            	salenum:true,
	            	min:0
	            },
	            cycleDays:{
	            	required : true,
	            	salenum:true,
	            	min:1
	            },
	            merchantAllowance:{
	            	required:true,
	            	min:0.001
	            }
	        },
	        messages : {
	            name : {
	                required : '活动名称不能为空'
	            },
	            endTime:{
	            	required:"开始时间和结束时间不能为空",
	            	time:"开始时间和结束时间都不能为空!"
	            		},
	           secondCategoryId:{
	               required:"服务项目必须要选择"
	                },
	           cycleDays:{
	        	   required:"限购周期必填",
	        	   salenum:"限购周期必须是整数",
	        		   min:"限购周期必须是大于0的整数"
	        		   },
	           merchantAllowance:{ //商户补贴金额
	        	   required:"商户补贴金额必须为大于0的数值",
	        	   gt:"商户补贴金额必须大于0"
	        	   
	        	   },
	           saleNumber:{ //销售数量
	        	   required:"销售数量不能为空",
	        	   salenum:"必须是整数",
	        	   min:"销售数量必须大于0"
	           },
	           merchantAllowance:{
	        	   required:"商户补贴金额必填",
	        	   min:"商户补贴金额必须大于0"
	           }
	        },
	        errorPlacement: function (error, element) {  //修改错误提示位置
	                error.appendTo(element.parent("span"));
	        },
	    });
	}
}

jQuery.extend(jQuery.validator.messages, {
  required: "必选字段",
  remote: "请修正该字段",
  email: "请输入正确格式的电子邮件",
  url: "请输入合法的网址",
  date: "请输入合法的日期",
  dateISO: "请输入合法的日期 (ISO).",
  number: "请输入合法的数字",
  digits: "只能输入整数",
  creditcard: "请输入合法的信用卡号",
  equalTo: "请再次输入相同的值",
  accept: "请输入拥有合法后缀名的字符串",
  maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
  minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
  rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
  max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
  min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});



jQuery.validator.addMethod("salenum",function(value,element){  //大于0并且是整数
	 var returnVal = false;
	if($(element).val().indexOf(".")<1){
		returnVal = true;
	};
	return returnVal;
},"销售数量不能为空且必须为整数");

jQuery.validator.addMethod("gt",function(value, element){//大于指定数
   var returnVal = false;
   var gt = $(element).data("gt");
   if(value > gt && value != ""){
       returnVal = true;
   }
   return returnVal;
},"不能小于0 或空");

jQuery.validator.addMethod("time",function(value,elemet){//验证时间不能为空
	var hasTime=false;
	if($("#startTime").val()!=""&&$(elemet).val()!=""){
		hasTime=true;
	}
	return hasTime;
},"开始时间和结束时间必填");

jQuery.validator.addMethod("compareTime",function(value,elemet){
	var ctime=true;
	var a=$("#startTime").val();
	var b=$(elemet).val();
	var a1= a.split("-");
	var b1= b.split("-");
	if(a1>b1){
		ctime=false;
	}
	return ctime;
},"开始时间不能大于结束时间");