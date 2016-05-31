//收入趋势加载
function loadActivityConsume(dayRange){
    $.ajax({
        url : ctx + "/page/myActivity/consumeHistory.s",
        ifModified : false,
        cache : false,
        data : "promotionId=" + $("#promotionId").val() + "&dayRange="+dayRange + "&productId="+$("#productId").val(),
        success : function(response) {
            if (response.success && (response.success == "true" || response.success == true)) {
                var info = response.info;
                var soldNumber = parseInt(info.soldNumber);
                var saleNumber = parseInt(info.saleNumber);
                var leftNumber = saleNumber - soldNumber;  //剩余多少份
                var dailyPromotionConsumeBOs = info.dailyPromotionConsumeBOs;
                var dayDate = new Array();
                var daysConsume = new Array();
                var dayTips = new Array();
                var dayDateTmp = new Array();
                var daysConsumeTmp = new Array();
                var dayTipsTmp = new Array();
                $("#soldNumberLabel").html(soldNumber+"单");
                $("#leftNumberLabel").html(leftNumber+"单");
                if(dailyPromotionConsumeBOs!=null &&dailyPromotionConsumeBOs.length>0){
                    $.each(dailyPromotionConsumeBOs, function(index, dailyPromotionConsume) {
                        var date_time = new Date(dailyPromotionConsume.recordDate);
                        var dateUTC = Date.UTC(date_time.getUTCFullYear(),date_time.getUTCMonth(),date_time.getUTCDate() + 1);
                        var consumeDate = Highcharts.dateFormat('%Y-%m-%d', dateUTC);
                        var tips = consumeDate+" 服务"+dailyPromotionConsume.dailySoldNumber+"单 收益"+(parseFloat(dailyPromotionConsume.dailyIncome)/100)+"元";
                        var dayData = parseFloat(dailyPromotionConsume.dailyIncome)/100;
                        dayDateTmp.push(consumeDate);
                        dayTipsTmp.push(tips);
                        daysConsumeTmp.push(dayData);
                    });
                }
                //遍历近N天，没有记录的往里塞0
                var dateObj = new Date();
                dateObj.setDate(dateObj.getDate()-dayRange+1);
                for(var i =0;i<dayRange;i++){
                    //var forDateUTC = Date.UTC(dateObj.getUTCFullYear(),dateObj.getUTCMonth(),dateObj.getUTCDate());
                    var forDateUTC = Date.UTC(dateObj.getFullYear(),
                        dateObj.getMonth(),
                        dateObj.getDate());

                    var forConsumeDate = Highcharts.dateFormat('%Y-%m-%d', forDateUTC);
                    var exist = false;
                    for(var idx=0;idx<dayDateTmp.length;idx++){
                        if(forConsumeDate==dayDateTmp[idx]){
                            exist=true;
                            dayDate.push(dayDateTmp[idx]);
                            dayTips.push(dayTipsTmp[idx]);
                            daysConsume.push(daysConsumeTmp[idx]);
                            break;
                        }
                    }
                    if(!exist){
                        dayDate.push(forConsumeDate);
                        dayTips.push(forConsumeDate+" 服务0单 收益0元");
                        daysConsume.push(0);
                    }
                    dateObj.setDate(dateObj.getDate()+1);
                }
                var tick_interval = 1;
                if(dayRange>7){
                    tick_interval = 3;
                }
                new Highcharts.Chart({
                    chart : {
                        renderTo : 'container',
                        type : 'spline'
                    },
                    credits : {
                        enabled : false
                    },
                    title : {
                        text : '',
                        x : -20
                    },
                    subtitle : {
                        text : '',
                        x : -20
                    },
                    xAxis : {
                        labels : {
                            align : 'center',
                            formatter : function() {
                                return dayDate[this.value];
                            }
                        },
                        crosshair : {
                            color : 'rgb(230,45,70)',
                            width : 2
                        },
                        tickPosition : 'inside',
                        startOnTick : false,
                        minPadding : 0,
                        showLastLabel : true,
                        tickInterval : tick_interval
                    },
                    yAxis : {
                        title : {
                            text : null
                        }
                    },
                    tooltip : {
                        headerFormat : '',
                        pointFormatter : function() {
                            return '<font style="color:#FFFFFF">'
                                + dayTips[this.x] + '</font>';
                        }
                    },
                    legend : {
                        enabled : false
                    },
                    series : [ {
                        data : daysConsume
                    } ]
                });
            } else {
                $.myAlert({
                    title : "查询收入趋势失败",
                    content : response.info,
                    buttonCount : "1"
                },function(){

                });
            }
        }
    });
}

$(function(){
    loadActivityConsume(7);
});

