$(function(){
    var $tab_nr = $('#zlgy_right #con_two');
    $(document).ajaxComplete(function (event, request) {
        var loginStatus = request
            .getResponseHeader("loginStatus");
        if (loginStatus == "accessDenied") {
            location.href = ctx + "/anon/login/login.jsp";
        }
    });
});
function toSShop() {
    $.ajax({
        url: ctx + "/page/jump/toSShop.s",
        cache: false,
        ifModified: true,
        async: false,
        success: function (data) {
            var flag = data.flag;// alert(data);
            if (flag) {
                var jumpUrl = data.data.url;
                if(jumpUrl!=null && jumpUrl!="" && jumpUrl.indexOf("haveStore=1")>=0){
                    $("#jumpFormToApply").attr("action",jumpUrl);
                    $("#jumpFormToApply").submit();
                }else{
                    $("#jumpForm").attr("action", jumpUrl);
                    $("#jumpForm").submit();
                }
            } else {
                alert(data.info);
            }
        }
    });
}
