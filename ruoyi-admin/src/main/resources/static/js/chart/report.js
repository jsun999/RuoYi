/**
 * Resize function without multiple trigger
 *
 * Usage:
 * $(window).smartresize(function(){  
 *     // code here
 * });
 */
(function ($, sr) {
    // debouncing function from John Hann
    // http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
    var debounce = function (func, threshold, execAsap) {
        var timeout;

        return function debounced() {
            var obj = this, args = arguments;

            function delayed() {
                if (!execAsap)
                    func.apply(obj, args);
                timeout = null;
            }

            if (timeout)
                clearTimeout(timeout);
            else if (execAsap)
                func.apply(obj, args);

            timeout = setTimeout(delayed, threshold || 100);
        };
    };

    // smartresize 
    jQuery.fn[sr] = function (fn) {
        return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr);
    };

})(jQuery, 'smartresize');

// Panel toolbox
$(document).ready(function() {
    $('.collapse-link').on('click', function() {
        var $BOX_PANEL = $(this).closest('.x_panel'),
            $ICON = $(this).find('i'),
            $BOX_CONTENT = $BOX_PANEL.find('.x_content');

        // fix for some div with hardcoded fix class
        if ($BOX_PANEL.attr('style')) {
            $BOX_CONTENT.slideToggle(200, function(){
                $BOX_PANEL.removeAttr('style');
            });
        } else {
            $BOX_CONTENT.slideToggle(200);
            $BOX_PANEL.css('height', 'auto');
        }

        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });

    $('.close-link').click(function () {
        var $BOX_PANEL = $(this).closest('.x_panel');

        $BOX_PANEL.remove();
    });
});
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var echartBar1;
var echartBar2;
var echartPie;
function init_echarts() {

    if (typeof (echarts) === 'undefined') {
        return;
    }
    console.log('init_echarts');
    var theme = {
        color: [
            '#26B99A', '#34495E', '#BDC3C7', '#3498DB',
            '#9B59B6', '#8abb6f', '#759c6a', '#bfd3b7'
        ],

        title: {
            itemGap: 8,
            textStyle: {
                fontWeight: 'normal',
                color: '#408829'
            }
        },

        dataRange: {
            color: ['#1f610a', '#97b58d']
        },

        toolbox: {
            color: ['#408829', '#408829', '#408829', '#408829']
        },

        tooltip: {
            backgroundColor: 'rgba(0,0,0,0.5)',
            axisPointer: {
                type: 'line',
                lineStyle: {
                    color: '#408829',
                    type: 'dashed'
                },
                crossStyle: {
                    color: '#408829'
                },
                shadowStyle: {
                    color: 'rgba(200,200,200,0.3)'
                }
            }
        },

        dataZoom: {
            dataBackgroundColor: '#eee',
            fillerColor: 'rgba(64,136,41,0.2)',
            handleColor: '#408829'
        },
        grid: {
            borderWidth: 0
        },

        categoryAxis: {
            axisLine: {
                lineStyle: {
                    color: '#408829'
                }
            },
            splitLine: {
                lineStyle: {
                    color: ['#eee']
                }
            }
        },

        valueAxis: {
            axisLine: {
                lineStyle: {
                    color: '#408829'
                }
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(250,250,250,0.1)', 'rgba(200,200,200,0.1)']
                }
            },
            splitLine: {
                lineStyle: {
                    color: ['#eee']
                }
            }
        },
        timeline: {
            lineStyle: {
                color: '#408829'
            },
            controlStyle: {
                normal: {color: '#408829'},
                emphasis: {color: '#408829'}
            }
        },

        k: {
            itemStyle: {
                normal: {
                    color: '#68a54a',
                    color0: '#a9cba2',
                    lineStyle: {
                        width: 1,
                        color: '#408829',
                        color0: '#86b379'
                    }
                }
            }
        },
        map: {
            itemStyle: {
                normal: {
                    areaStyle: {
                        color: '#ddd'
                    },
                    label: {
                        textStyle: {
                            color: '#c12e34'
                        }
                    }
                },
                emphasis: {
                    areaStyle: {
                        color: '#99d2dd'
                    },
                    label: {
                        textStyle: {
                            color: '#c12e34'
                        }
                    }
                }
            }
        },
        force: {
            itemStyle: {
                normal: {
                    linkStyle: {
                        strokeColor: '#408829'
                    }
                }
            }
        },
        chord: {
            padding: 4,
            itemStyle: {
                normal: {
                    lineStyle: {
                        width: 1,
                        color: 'rgba(128, 128, 128, 0.5)'
                    },
                    chordStyle: {
                        lineStyle: {
                            width: 1,
                            color: 'rgba(128, 128, 128, 0.5)'
                        }
                    }
                },
                emphasis: {
                    lineStyle: {
                        width: 1,
                        color: 'rgba(128, 128, 128, 0.5)'
                    },
                    chordStyle: {
                        lineStyle: {
                            width: 1,
                            color: 'rgba(128, 128, 128, 0.5)'
                        }
                    }
                }
            }
        },
        gauge: {
            startAngle: 225,
            endAngle: -45,
            axisLine: {
                show: true,
                lineStyle: {
                    color: [[0.2, '#86b379'], [0.8, '#68a54a'], [1, '#408829']],
                    width: 8
                }
            },
            axisTick: {
                splitNumber: 10,
                length: 12,
                lineStyle: {
                    color: 'auto'
                }
            },
            axisLabel: {
                textStyle: {
                    color: 'auto'
                }
            },
            splitLine: {
                length: 18,
                lineStyle: {
                    color: 'auto'
                }
            },
            pointer: {
                length: '90%',
                color: 'auto'
            },
            title: {
                textStyle: {
                    color: '#333'
                }
            },
            detail: {
                textStyle: {
                    color: 'auto'
                }
            }
        },
        textStyle: {
            fontFamily: 'Arial, Verdana, sans-serif'
        }
    };
    if ($('#echart_pie').length) {

        echartPie = echarts.init(document.getElementById('echart_pie'), theme);

        echartPie.setOption({
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                x: 'center',
                y: 'bottom',
                data: []
            },

            toolbox: {
                show: true,
                feature: {
                    magicType: {
                        show: true,
                        type: ['pie', 'funnel'],
                        option: {
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    restore: {
                        show: true,
                        title: "Restore"
                    },
                    saveAsImage: {
                        show: true,
                        title: "Save Image"
                    }
                }
            },
            calculable: true,
            series: [{
                name: '客户金额',
                type: 'pie',
                radius: '55%',
                center: ['50%', '48%'],
                data: []
            }]
        });
        echartPie.showLoading();
        customerReportType(1);

        var dataStyle = {
            normal: {
                label: {
                    show: false
                },
                labelLine: {
                    show: false
                }
            }
        };

        var placeHolderStyle = {
            normal: {
                color: 'rgba(0,0,0,0)',
                label: {
                    show: false
                },
                labelLine: {
                    show: false
                }
            },
            emphasis: {
                color: 'rgba(0,0,0,0)'
            }
        };

    }
    if ($('#echart_bar1').length) {
        echartBar1 = echarts.init(document.getElementById('echart_bar1'), theme);
        echartBar1.setOption({
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                }
            },
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '项目总金额',
                    type: 'bar',
                    barWidth: '60%',
                    data: []
                }
            ]

        })
        echartBar1.showLoading();
        earningTimeType(1);
    }
    if ($('#echart_gauge').length) {
        echartGauge = echarts.init(document.getElementById('echart_gauge'));
        echartGauge.setOption({
            backgroundColor: "#ffffff",
            color: ["#37A2DA", "#32C5E9", "#67E0E3"],
            series: [{
                name: '业务指标',
                type: 'gauge',
                detail: {
                    formatter: '{value}%'
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        width: 10,
                        shadowBlur: 0,
                        color: [
                            [0.3, '#fd666d'],
                            [0.7, '#e6e632'],
                            [1, '#67e0e3']
                        ]
                    }
                },
                data: [{
                    value: 50,
                    name: '完成率',
                }]

            }]
        })
        echartGauge.showLoading();
        performanceCompletion(1);
    }

    if ($('#echart_bar2').length) {
        echartBar2 = echarts.init(document.getElementById('echart_bar2'), theme);
        echartBar2.setOption({
            color: ['#3398DB','#456c81'],
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['领用','报废']
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis: {
                type: 'category',
                data: []
            },
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '领用',
                    type: 'bar',
                    data: [],
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name: '报废',
                    type: 'bar',
                    data: [],
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]

        })
        echartBar2.showLoading();
        toolCostTimeType(1);
    }
}
function toolCostTimeType(type) {
    $.ajax({
        "type": "POST",
        "data":{type:type},
        "url": "/report/report/toolCostReport",
        "async": false,
        "success": function (data) {
            echartBar2.hideLoading();
            var xaxis = new Array();
            var yaxis1 = new Array();
            var yaxis2 = new Array();
            for(var i=0;i<data.length;i++){
                xaxis.push(data[i].xaxis);
                if(data[i].changeType==1){
                    yaxis1.push(data[i].yaxis);
                }else if(data[i].changeType==2){
                    yaxis2.push(data[i].yaxis);
                }
            }
            echartBar2.setOption({
                xAxis : {
                    data: Array.from(new Set(xaxis)),
                },
                series : [
                    {
                        type: 'bar',
                        data:yaxis1,
                    },{
                        type: 'bar',
                        data:yaxis2,
                    }
                ]
            })
        }
    });
}
function earningTimeType(type) {
    $.ajax({
        "type": "POST",
        "data":{type:type},
        "url": "/report/report/earningReport",
        "async": false,
        "success": function (data) {
            echartBar1.hideLoading();
            var xaxis = new Array();
            var yaxis = new Array();
            for(var i=0;i<data.length;i++){
                xaxis.push(data[i].xaxis);
                yaxis.push(data[i].yaxis);
            }
            echartBar1.setOption({
                xAxis : {
                    data: xaxis,
                },
                series : [
                    {
                        name:'项目总金额',
                        data:yaxis,
                    }
                ]
            })
        }
    });
}

function customerReportType(type) {
    $.ajax({
        "type": "POST",
        "data":{type:type},
        "url": "/report/report/customerReport",
        "async": false,
        "success": function (data) {
            echartPie.hideLoading();
            var xaxis = new Array();
            var yaxis = new Array();
            if(data.length<6){
                for(var i=0;i<data.length;i++){
                    xaxis.push(data[i].xaxis);
                    var ob = {value:data[i].yaxis,name:data[i].xaxis};
                    yaxis.push(ob);
                }
            }else{
                for(var i=0;i<5;i++){
                    xaxis.push(data[i].xaxis);
                    var ob = {value:data[i].yaxis,name:data[i].xaxis};
                    yaxis.push(ob);
                }
                var other =0;
                for (var i=5;i<data.length;i++){
                    other += data[i].yaxis
                }
                xaxis.push('其他');
                yaxis.push({value:other,name:'其他'});
            }
            echartPie.setOption({
                legend : {
                    data: xaxis,
                },
                series : [
                    {
                        name:'客户金额',
                        data:yaxis,
                    }
                ]
            })
        }
    });
}

function performanceCompletion(type) {
    $.ajax({
        "type": "POST",
        "data":{type:type},
        "url": "/report/report/performanceCompletion",
        "async": false,
        "success": function (data) {
            echartGauge.hideLoading();
            var yaxis = new Array();
            yaxis.push({value:100*(data[0].yaxis/parseInt(data[0].xaxis)),name:'目标业绩：'+data[0].xaxis});
            echartGauge.setOption({
                series : [
                    {
                        data:yaxis,
                    }
                ]
            })
        }
    });
}

$(document).ready(function () {
    init_echarts();
});


