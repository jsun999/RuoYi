var series = [];
var data = [];


var option = {
    backgroundColor: "#fff",
    title: {
        text: "生产排程",
        left: 'center',
        padding: 20,
        position: 'center',
        textStyle: {
            fontSize: 17,
            fontWeight: "bolder",
            color: "#333"
        },
    },
    legend: {
        data: ["计划时间", "实际时间"],
        top: 50
    },
    grid: {
        containLabel: true,
        show: false,
        right: 130,
        left: 40,
        bottom: 40,
        top: 90
    },
    xAxis: {
        type: "time",
        maxInterval: 3600 * 1000 * 24,
        axisLabel: {
            "show": true,
            "interval": 0
        }
    },
    yAxis: {
        splitLine: {
            show: true
        },
        name: '工序',
        axisLabel: {
            show: true,
            interval: 0,
            formatter: function (value, index) {
                //console.log(value,index)
                var last = ""
                var max = 5;
                var len = value.length;
                var hang = Math.ceil(len / max);
                if (hang > 1) {
                    for (var i = 0; i < hang; i++) {
                        var start = i * max;
                        var end = start + max;
                        var temp = value.substring(start, end) + "\n";
                        last += temp; //凭借最终的字符串
                    }
                    return last;
                } else {
                    return value;
                }
            }
        },

        data: []
    },
    tooltip: {
        trigger: "axis",
        axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params) {
            //console.log(params[1])
            var res = "";
            var name = "";
            var start0 = "";
            var start = "";
            var end0 = "";
            var end = "";
            for (var i in params) {
                var k = i % 2;
                if (!k) { //偶数
                    start0 = new Date(params[i].data);
                    // console.log(start0)
                    start = start0.getFullYear() + "-" + (start0.getMonth() + 1) + "-" + start0.getDate();
                }
                if (k) { //奇数
                    name = params[i].seriesName;
                    end0 = new Date(params[i].data);
                    end = end0.getFullYear() + "-" + (end0.getMonth() + 1) + "-" + end0.getDate();
                    res += name + " : " + end + "~" + start + "</br>";
                }
            }
            return res;
        }
    },
    series: series
}

if ($('#echart_process_gantt').length) {
    var processCharts = echarts.init(document.getElementById('echart_process_gantt'));
    processCharts.setOption(option);
    processCharts.showLoading();
    getProcess($('#projectNumberSelect').val());
}

function ganttRefresh(){
    processCharts.showLoading();
    getProcess($('#projectNumberSelect').val());
}

function getProcess(projectNumber) {
    series = [];
    $.ajax({
        "type": "get",
        "data": {'projectNumber': projectNumber},
        "url": "/project/production/gantt/" + projectNumber,
        "async": false,
        "success": function (dataQuery) {
            processCharts.hideLoading();
            var xaxis = new Array();
            data = dataQuery.gantts;
            for (var i in data) {
                var dates = data[i].data;
                var date1 = new Array();
                for (var j in dates) {
                    date1[j] = new Date(dates[j]);
                }
                if (data[i].type == 1) {
                    series.push({
                        name: data[i].name,
                        type: "bar",
                        stack: data[i].stack,

                        label: {
                            normal: {
                                show: true,
                                position: "right",
                                formatter: function (params) {
                                    return params.seriesName
                                }
                            }
                        },
                        itemStyle: {
                            normal: {
                                //color: data[i].color,
                                borderColor: "#fff",
                                borderWidth: 2,
                            },

                        },
                        zlevel: -1,
                        data: date1
                    },)
                } else {
                    series.push({
                        name: data[i].name,
                        type: "bar",
                        stack: data[i].stack,
                        itemStyle: {
                            normal: {
                                color: '#fff',
                            }
                        },
                        zlevel: -1,
                        z: 3,
                        data: date1
                    })
                }
            }
            processCharts.setOption({
                yAxis: {
                    data: dataQuery.processType
                },
                series: series
            })
        }
    });
}