/*jquery.cookie start*/
jQuery.cookie = function(name, value, options) { 
	  if (typeof value != 'undefined') { 
				options = options || {}; 
				if (value === null) { 
						  value = ''; 
						  options = $.extend({}, options); 
						  options.expires = -1; 
				} 
				var expires = ''; 
				if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) { 
						  var date; 
						  if (typeof options.expires == 'number') { 
									date = new Date(); 
									date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000)); 	//这里改时间，单位毫秒，默认为1天。
						  } else { 
									date = options.expires; 
						  } 
						  expires = '; expires=' + date.toUTCString(); 
				} 
				var path = options.path ? '; path=' + (options.path) : ''; 
				var domain = options.domain ? '; domain=' + (options.domain) : ''; 
				var secure = options.secure ? '; secure' : ''; 
				document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join(''); 
	  } else { 
				var cookieValue = null; 
				if (document.cookie && document.cookie != '') { 
						  var cookies = document.cookie.split(';'); 
						  for (var i = 0; i < cookies.length; i++) { 
									var cookie = jQuery.trim(cookies[i]); 
									if (cookie.substring(0, name.length + 1) == (name + '=')) { 
											  cookieValue = decodeURIComponent(cookie.substring(name.length + 1)); 
											  break; 
									} 
						  } 
				} 
				return cookieValue; 
	  } 
}; 

//添加Cookie
function addCookie(name, value, options) {
  if (arguments.length > 1 && name != null) {
    if (options == null) {
      options = {};
    }
    if (value == null) {
      options.expires = -1;
    }
    if (typeof options.expires == "number") {
      var time = options.expires;
      var expires = options.expires = new Date();
      expires.setTime(expires.getTime() + time * 1000);
    }
    if (options.path == null) {
      options.path = "/";
    }
    if (options.domain == null) {
      options.domain = ".qccr.com";
    }
    document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires != null ? "; expires=" + options.expires.toUTCString() : "") + ("; path=/") + ("; domain="+options.domain+"") + (options.secure != null ? "; secure" : "");
  }
}
// 获取Cookie
function getCookie(name) {
  if (name != null) {
    var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
    return value ? decodeURIComponent(value[1]) : null;
  }
}
// 移除Cookie
function removeCookie(name, options) {
  addCookie(name, null, options);
}
/* jquery.cookie end */
/*
本代码由js代码网收集并编辑整理;
尊重他人劳动成果;
转载请保留js代码网链接 - http://www.jsdaima.com
*/