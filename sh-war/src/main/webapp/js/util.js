/**************************************************************
 工具类Util
 **************************************************************/
var Util = {};

/**************************************************************
 格式化timestamp对象
 **************************************************************/
Util.timestampformat = function timestampformat(timestamp,format){
    if(timestamp==null||format==null){
        return "";
    }else{
        return (new Date(timestamp)).format(format);
    }
};

/**
 * 金額精度处理
 * 如果金额有两位则保留，否则保留1位小数（scale=1）
 * @param number 金额
 * @param scale 小数精度
 * @returns {*}
 */
Util.moneyScale = function(number, scale){
    var maxScale = 2;//最大保留两位小数
    if(isNaN(number)){
        return number;
    }
    if(!scale || isNaN(scale)){
        scale = 1;
    }
    if(scale > maxScale){
        scale = maxScale;
    }
    var num = Number(number);
    var numStr = num.toString();
    if(numStr.lastIndexOf(".") == -1){
        return num.toFixed(scale);
    }else{
        var decimalLength = numStr.substring(numStr.lastIndexOf(".")).length;
        if(decimalLength >= maxScale){
            return num.toFixed(maxScale);
        }else if(decimalLength > scale){
            //有几位小数就保留几位
            return num.toFixed(decimalLength);
        }else{
            return num.toFixed(scale);
        }
    }
};

/**
 * 动态创建&提交表单
 * @param jsonParam json数据
 * @param action url地址
 * @param method get or post
 * @param target
 */
Util.tempFormSubmit = function(jsonParam, action, method, target){
    target = target||"_self";
    method = method|| "get";
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target",target);
    form.attr("method",method);
    form.attr("action",ctx + action);

    var inputs = '';
    jQuery.each(jsonParam, function(name, value){
        inputs+='<input type="hidden" name="'+ name +'" value="'+ value +'" />';
    });
    form.append(inputs);
    //表单提交+表单删除
    form.appendTo("body").submit().remove();
}

Date.prototype.format = function(format) {
    var o = {"M+": this.getMonth() + 1,
        // month
        "d+": this.getDate(),
        // day
        "h+": this.getHours(),
        // hour
        "m+": this.getMinutes(),
        // minute
        "s+": this.getSeconds(),
        // second
        "q+": Math.floor((this.getMonth() + 3) / 3),
        // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};