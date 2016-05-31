$(function(){
    $("#prom_money").change(promMoneySelectOnchange);
})

function apply(actId){

    if(($("#sale_number").val()!='' && isNaN($("#sale_number").val())) || $("#sale_number").val()==""
        ||parseInt($("#sale_number").val()) < parseInt($("#serviceVal").html())){
        $.myAlert({
            title:"温馨提示",
            content:"请输入正确的服务数量",
            buttonCount : "1"
        },function(){

        });
        return;
    }

    if( (parseInt(parseFloat($("#promAmt").html())*100))<=100){
        $.myAlert({
            title:"温馨提示",
            content:"销售价必须大于1元，请重新选择补贴价",
            buttonCount : "1"
        },function(){

        });
        return;
    }

    if($(".ad_dui_2").css("display")!="inline"){
        $.myAlert({
            title:"温馨提示",
            content:"请阅读并同意活动协议",
            buttonCount : "1"
        },function(){

        });
        return;
    }

    $.ajax({
        url : "/page/activity/apply.s",
        data : {
            "sale_number" : $("#sale_number").val(),
            "prom_money": $("#prom_money").val(),
            "act_id": actId,
            "clear_money":$("#promClearAmt").html(),
            "sale_money":$("#promAmt").html()
        },
        success : function(json) {
            if(json.success){
                $("#update_success p").text(json.info)
                showSuccessMsg();
                setTimeout(baomingUrl,2200);
            }else{
                var contentHint;

                if(json.info==null || json.info==''){
                    contentHint ='报名失败';
                }else
                    contentHint = json.info;

                $.myAlert({
                    title:"温馨提示",
                    content:contentHint,
                    buttonCount : "1"
                },function(){
                });
                return;
            }


        },
        error : function() {
            $.myAlert({
                title:"温馨提示",
                content:"报名失败！",
                buttonCount : "1"
            },function(){

            });
            return;
        }
    });
}


function refresh(actId) {
    $("#applyForm").attr("action", "<%=ctx%>page/activity/my-detail.s");
    $("#applyForm").attr("target", "");
    $("#act_id").val(actId);
    $("#applyForm").submit();
}

function hideMsg(tag){
    $("#update_fail").hide();
    if(tag==1){
        window.location.href="/page/activity/index.s";
    }
}

//商家补贴变更
function promMoneySelectOnchange(){
    var actProdPrice = $("#qianyue").val(); //签约价
    var platformPrice = $("#platformPrice").val(); //平台补贴价

    //结算价
    var promClearAmtFen = actProdPrice-parseInt($("#prom_money").val());
    if(promClearAmtFen<0){
        promClearAmtFen = 0;
    }
    var promClearAmt = (promClearAmtFen/100.0).toFixed(2);

    $("#promClearAmt").html( promClearAmt );

    //销售价
    var promMoneyFen = (actProdPrice-parseInt($("#prom_money").val()) - parseInt(platformPrice) );
    if(promMoneyFen<0){
        promMoneyFen = 0;
    }
    var promAmt=( promMoneyFen/100.0 ).toFixed(2);

    $("#promAmt").html( promAmt );
};
/**
 * Created by user on 2016/3/12.
 */
