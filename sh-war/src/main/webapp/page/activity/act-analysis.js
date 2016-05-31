function initQuotas(quotas) {
    $.each(quotas, function(index, quota) {
        $("#" + quota.name + "_nomalCount").html(quota.nomalCount);
        $("#" + quota.name + "_profit").html(quota.profit.toFixed(2));

        var nomalCount_rate = $("#" + quota.name + "_nomalCount_rate");
        var profit_rate = $("#" + quota.name + "_profit_rate");

        nomalCount_rate.html(quota.cpropor);
        profit_rate.html(quota.spropor);

        setRate(quota.cflag, nomalCount_rate);
        setRate(quota.sflag, profit_rate);

        if (quota.abnormalCount > 0) {
            $("." + quota.name + "_tips").each(function(index, element) {
                $(element).show();
            });

            $("." + quota.name + "_order_num_td").mouseover(function() {
                $(this).find(".yc_box").show();
            });

            $("." + quota.name + "_order_num_td").mouseout(function() {
                $(this).find(".yc_box").hide();
            });

            $("#" + quota.name + "_tips").html(
                    "注：（" + quota.nomalCount + "笔=" + quota.serviceCount
                            + "笔 - " + quota.abnormalCount + "笔异订单）");
        }
    });
}

function setRate(flag, element) {
    switch (flag) {
    case 1:
        element.attr("class", "rate_top");
        break;
    case 0:
        element.attr("class", "rate_top");
        break;
    case -1:
        element.attr("class", "rate_down");
        break;
    default:
        break;
    }
}

$(document)
        .ready(
                function() {
                    //var y_quota = data.y_quota;
                    //var d7_quota = data.d7_quota;
                    //var d30_quota = data.d30_quota;
                    //y_quota.name = "y_quota";
                    //d7_quota.name = "d7_quota";
                    //d30_quota.name = "d30_quota";

                    //initQuotas([ d7_quota, d30_quota ]);
                    if(data==null)
                        return;

                    var storeAchievementAnalysisList = data.storeAchievementAnalysisList;
                    var _7daysData = new Array();
                    var _7daysCategories = new Array();
                    var _7daysTips = new Array();
                    var _30daysData = new Array();
                    var _30daysCategories = new Array();
                    var _30daysTips = new Array();
                    var dayRange = $("#day_range").val();

                    var _tips = new Array();
                    $.each(storeAchievementAnalysisList, function(index,
                            storeAchievementAnalysis) {
                        var date_time = new Date(
                                storeAchievementAnalysis.date_time);
                        /*var dateUTC = Date.UTC(date_time.getUTCFullYear(),
                                date_time.getUTCMonth(),
                                date_time.getUTCDate());*/
                        var dateUTC = Date.UTC(date_time.getFullYear(),
                            date_time.getMonth(),
                            date_time.getDate());

                        var category = Highcharts.dateFormat('%Y-%m-%d',
                                dateUTC);
                        var profit = new Number(storeAchievementAnalysis.profit).toFixed(2);
                        var tips = category + "，服务"
                                + storeAchievementAnalysis.nomalCount + "单，收益"
                                + profit + "元";
                        //var dayData = storeAchievementAnalysis.nomalCount;
                        var dayData = parseInt(profit);
                        if (index < 7) {
                            _7daysCategories.push(category);
                            _7daysTips.push(tips);
                            _7daysData.push(dayData);
                        }

                        _30daysCategories.push(category);
                        _30daysTips.push(tips);
                        _30daysData.push(dayData);
                    });

                    $("#7daysData").click(function() {
                        get7DayChart();
                    });

                    $("#30daysData").click(function() {
                        get30DayChart();
                    });

                    function get30DayChart() {
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
                                        return _30daysCategories[this.value];
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
                                tickInterval : 3
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
                                            + _30daysTips[this.x] + '</font>';
                                }
                            },
                            legend : {
                                enabled : false
                            },
                            series : [ {
                                data : _30daysData
                            } ]
                        });
                    }

                    function get7DayChart() {
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
                                        return _7daysCategories[this.value];
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
                                tickInterval : 1
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
                                            + _7daysTips[this.x] + '</font>';
                                }
                            },
                            legend : {
                                enabled : false
                            },
                            series : [ {
                                data : _7daysData
                            } ]
                        });
                    }

                    if(dayRange==7)
                        get7DayChart();
                    else
                        get30DayChart();
                });