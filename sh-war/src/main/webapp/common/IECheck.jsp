<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
.noticeIE6{
	height:30px;
	width:100%;
	background-color:blue;
	color:#FFFFFF;
	font-size:14px;
	line-height:30px; 
	display:none;
}
.infoxx{
	text-align: left;
	float:left;
	margin-left: 200px;
}
.closexx{
	cursor:pointer;
}
</style>

<body>
	<div class="noticeIE6" id="noticeIE6"><span class="infoxx">您目前使用的浏览器版本过低，为了更好的浏览效果，建议使用IE7以上或firefox、chrome浏览器</span><span class="closexx" onclick="closeIE6();">关闭</span></div>
</body>
<script type="text/javascript">
window.onload = function(){
	if(getOs()=='MSIE6.0'){
		var noticeIE6 = document.getElementById('noticeIE6');
		noticeIE6.style.display='block';
	}
};
function getOs()  
{  
   if(navigator.userAgent.indexOf("MSIE")>0) { 
	    var trim_Version = navigator.appVersion.split(";")[1].replace(/[ ]/g,"");
        return trim_Version;  
   }  
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
        return "Firefox";  
   }  
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {  
        return "Safari";  
   }   
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){  
        return "Camino";  
   }  
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){  
        return "Gecko";  
   }  
     
}  
function closeIE6(){
	var noticeIE6 = document.getElementById('noticeIE6');
	noticeIE6.style.display='none';
}
</script>