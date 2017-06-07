$(function() {

    //$("#header").load("header.html",function(){});
    //$("#footer").load("footer.html", function() {});

    $(".select-boxbtnAlarm .btnAlarm").click(function() {
        $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");

        var thisText = $(this).text();
        if(thisText == "自定义") {
            $(".select-boxWdate input").attr("disabled", false).removeClass("time-input-disable");
            $("#begin").focus();
        } else {
            $(".select-boxWdate input").attr("disabled", true).addClass("time-input-disable");
        }
    });

    //				$(".energy_consumption").click(function() {
    //					layer.open({
    //						type: 2,
    //						title: '能耗分析',
    //						shadeClose: true,
    //						shade: 0.8,
    //						area: ['98%', '95%'],
    //						content: 'energy_analysis_detail.html' //iframe的url
    //					});
    //				})

    //选择日期
    updateConfig();
    var startDate = null;
    var endDate = null;

    function updateConfig() {
        //					var options = {
        //						singleDatePicker: true,
        //						locale: {
        //
        //							customRangeLabel: '自定义',
        //							daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
        //							monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
        //								'七月', '八月', '九月', '十月', '十一月', '十二月'
        //							],
        //							firstDay: 1
        //						}
        //					};
        //
        //					$('#datepicker-config').daterangepicker(options, function(start, end, label) {
        //						console.info("start"+start.format('YYYY-MM-DD'));
        //						console.info("end"+end.format('YYYY-MM-DD'));
        //						$("#begin").val(start.format('YYYY-MM-DD'));
        //						$("#end").val(end.format('YYYY-MM-DD'));
        //
        //					});
        //
        $('#datepicker-config').click(function() {
            $("#selectdate").show();
            $('.calendar.left').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: 'yyyy-mm-dd'
            }).on('changeDate', function(ev) {
                startDate = ev.date;

            });
            $('.calendar.right').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            }).on('changeDate', function(ev) {
                endDate = ev.date;

            });

        });

    }

    //繁琐了
    //获取当前时间
    var nowdate = new Date();
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 1;
    var d = nowdate.getDate();
    var formatnowdate = y + '-' + m + '-' + d;

    //获取近一周
    var oneweekdate = new Date(nowdate - 7 * 24 * 3600 * 1000);
    var y = oneweekdate.getFullYear();
    var m = oneweekdate.getMonth() + 1;
    var d = oneweekdate.getDate();
    var formatoneweekdate = y + '-' + m + '-' + d;

    //获取近一月
    nowdate.setMonth(nowdate.getMonth() - 1);
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 1;
    var d = nowdate.getDate();
    var formatonemonth = y + '-' + m + '-' + d;

    //获取近两月
    nowdate.setMonth(nowdate.getMonth() - 2);
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 2;
    var d = nowdate.getDate();
    var formattwomonth = y + '-' + m + '-' + d;

    $("#nearlyaweek").click(function() {
        $(".btn-confirm").click();
        $("#begin").val(formatoneweekdate);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
    });

    $("#nearlyamonth").click(function() {
        $(".btn-confirm").click();
        $("#begin").val(formatonemonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
    });

    $("#nearlytwomonth").click(function() {
        $(".btn-confirm").click();
        $("#begin").val(formattwomonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
    });

    $(".applyBtn").click(function() {
        $("#begin").val(startDate.Format("yyyy-MM-dd"));
        $("#end").val(endDate.Format("yyyy-MM-dd"));
        if(endDate<startDate){
            $("#end").val("");
        }
        $("#selectdate").hide();
    });

    $(".cancelBtn").click(function() {
        $("#selectdate").hide();
    });

    $.ajax({
        url : _web+"/static/json/h-1.json",
        type : "GET",
        dataType: "json",
        error : function(request) {
            alert("Connection error");
        },
        success : function(data) {
            chart01Fun(data.data.pieceYardage.data, data.data.pieceYardage.yearDate, data.data.pieceYardage.other);

            chart02Fun(data.data.branchCost.data, data.data.branchCost.yearDate, data.data.branchCost.other);

            chart03Fun(data.data.carbonEmission.data, data.data.carbonEmission.yearDate);

            chart04Fun(data.data.cost.data, data.data.cost.yearDate, data.data.cost.other);

            chart05Fun();

            chart06Fun();

            chart07Fun();

            chart08Fun();

            chart09Fun();
        }
    });

});

/*website*/
var websiteheight;
websiteheight = $("#website").height() - 12;
$(".index_menuBox").height(websiteheight);

window.onresize = function() {
    chart01.resize();
    chart02.resize();
    chart03.resize();
    chart04.resize();
    myChartEnergy.resize();
    chart05.resize();
    myChartQualified.resize();
    myChartCarbon.resize();

    websiteheight = $("#website").height() - 12;
    $(".index_menuBox").height(websiteheight);
};

function typefun(these, code) {
    $(these).addClass("on").siblings().removeClass("on");
    $("#website").attr("src", "img/index/websitet_cs0" + code + ".png");

    if(code == 6) {
        $(".PeopleTabdiv").show();
        $(".otherTabdiv").hide();
        myChartQualified.resize();
        myChartCarbon.resize();
    } else {
        $(".PeopleTabdiv").hide();
        $(".otherTabdiv").show();
    }
};



/*单耗趋势-折线图*/
function chart01Fun(datalist, datelist, other){
    $("#chart01").empty();
    chart01 = echarts.init(document.getElementById('chart01'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '40',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick:{show:false},
            splitArea: {
                show: true
            },
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel : {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            },
            data: datelist

        },
        yAxis: {
            type: 'value',
            axisTick:{show:false},
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            }
        },
        color:['#3B96DD', '#c2ccd3'],
        series: []
    }
    $.each(datalist,function(index,value){
        var typeName = value.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";

        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:value.dataList
        }

        option.series.push(item);
    });

    //配置上限值 下限值  今年平均值
    var upperList = [];
    var lowerList = [];
    var averageList = [];
    var labelStyle= {
        normal: {
            show: true,
            position:'right',
            textStyle:{color:'#666666'},
            formatter:function(params){
                if(params.dataIndex == datelist.length - 1){
                    return params.data
                }else{
                    return ""
                }

            }
        }
    }
    $.each(datelist,function(index,value){
        upperList.push(parseFloat(other.upperLimit.data))
        lowerList.push(parseFloat(other.lowerLimit.data))
        averageList.push(parseFloat(other.average.data))
    })
    option.series.push({
        name:other.upperLimit.typeName,
        type:'line',
        symbolSize:1,
        lineStyle:{normal:{type:'dashed',color:'#e8afa6'}},
        label:labelStyle,
        data:upperList
    });
    option.series.push({
        name:other.lowerLimit.typeName,
        type:'line',
        symbolSize:1,
        lineStyle:{normal:{type:'dashed',color:'#9ad9d7'}},
        label:labelStyle,
        data:lowerList
    });
    option.series.push({
        name:other.average.typeName,
        type:'line',
        symbolSize:1,
        lineStyle:{normal:{type:'dashed',color:'#3B96DD'}},
        label:labelStyle,
        data:averageList
    });
    chart01.setOption(option);
}


/*分公司成本-柱状图*/

function chart02Fun(datalist, datelist, other){
    $("#branchcost-year").html(other.year);
    $("#chart02").empty();
    chart02 = echarts.init(document.getElementById('chart02'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '45',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            axisTick:{show:false},
            splitLine: {
                show: false
            },

            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel : {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            },
            data: datelist

        },
        yAxis: {
            type: 'value',
            axisTick:{show:false},
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitArea: {
                show: true
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            }
        },
        color:['#3B96DD'],
        series: []
    }
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var item = {
            name:typeName,
            type:'bar',
            barWidth: '20',
            markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
            },
            data:data.dataList
        }
        option.series.push(item);
    });
    chart02.setOption(option);
}

/*碳排放趋势-折线图*/
function chart03Fun(datalist,datelist){
    $("#chart03").empty();
    chart03 = echarts.init(document.getElementById('chart03'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '40',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick:{show:false},
            splitLine: {
                show: false
            },
            splitArea: {
                show: true
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel : {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            },
            data: datelist

        },
        yAxis: {
            type: 'value',
            axisTick:{show:false},
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },

            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            }
        },
        color:['#3B96DD', '#c2ccd3'],
        series: []
    }
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    chart03.setOption(option);
}

/*公司成本-折线图*/
function chart04Fun(datalist,datelist, other){
    $("#branchcost-year").html(other.year);
    $("#chart04").empty();
    chart04 = echarts.init(document.getElementById('chart04'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '40',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick:{show:false},
            splitLine: {
                show: false
            },
            splitArea: {
                show: true
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel : {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            },
            data: datelist

        },
        yAxis: {
            type: 'value',
            axisTick:{show:false},
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            }
        },
        color:['#3B96DD', '#c2ccd3'],
        series: []
    }
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    chart04.setOption(option);
}


/*成本明细-饼图*/
function chart05Fun(datalist, datelist, other){
    $("#chart05").empty();
    chart05 = echarts.init(document.getElementById('chart05'));
    var option = {
        title: {
            text: "897.2",
            subtext: '成本总量（万元）\n（1.6%↓）',  //↑↓
            x: 'center',
            y: 'center',
            itemGap: -5,
            textStyle : {
                color : '#8394aa',
                fontFamily : '微软雅黑',
                fontSize : 44,
                fontWeight : 'normal'
            },
            subtextStyle : {
                color : '#2eada8',
                fontFamily : '微软雅黑',
                fontSize : 12,
                fontWeight : 'normal'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)",
            show:true
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:[]
        },
        toolbox: {
            show : false,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : false,
        color:['#32bbb6', '#8394aa', '#b7c1cf', '#df5f4a', '#3b96db'],
        series : [
            {
                type:'pie',
                radius : ['0', '80%'],
                silent:true,
                itemStyle : {
                    normal : {
                        color:'#ffffff',
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    }
                },
                data:[
                    {value:1, name:'圈', selected:false,hoverAnimation:false}
                ]
            },
            {
                name:'成本明细',
                type:'pie',
                radius : ['60%', '80%'],

                // for funnel
                x: '60%',
                width: '35%',
                funnelAlign: 'left',
                itemStyle : {
                    normal : {
                        borderColor: '#fff',
                        borderWidth: '4',
                        label : {show:false}
                    }
                },

                data:[
                    {value:206.4, name:'人工费'},
                    {value:192.5, name:'管理费'},
                    {value:258.7, name:'其他费'},
                    {value:207.2, name:'能源费'},
                    {value:106.2, name:'设备费'}
                ]
            }
        ]
    };
    chart05.setOption(option);
}

function chart06Fun(){
    //占比-赋值即可
    var level_ = 0.75;
    //$("#level_num").text((level_ * 100) + '%');
    var wave = (function () {
        var ctx;
        var waveImage;
        var canvasWidth;
        var canvasHeight;
        var needAnimate = false;

        function init (callback) {
            var wave = document.getElementById('chart06');
            var canvas = document.createElement('canvas');
            if (!canvas.getContext) return;
            ctx = canvas.getContext('2d');
            canvasWidth = wave.offsetWidth;
            canvasHeight = wave.offsetHeight;
            canvas.setAttribute('width', canvasWidth);
            canvas.setAttribute('height', canvasHeight);
            wave.appendChild(canvas);
            waveImage = new Image();
            waveImage.onload = function () {
                waveImage.onload = null;
                callback();
            }
            waveImage.src = _web+'/static/img/index/wave.png';
        }

        function animate () {
            var waveX = 0;
            var waveY = 0;
            var waveX_min = -220;
            var waveY_max = canvasHeight * level_;
            var requestAnimationFrame =
                window.requestAnimationFrame ||
                window.mozRequestAnimationFrame ||
                window.webkitRequestAnimationFrame ||
                window.msRequestAnimationFrame ||
                function (callback) { window.setTimeout(callback, 1000 / 60); };
            function loop () {
                ctx.clearRect(0, 0, canvasWidth, canvasHeight);
                if (!needAnimate) return;
                if (waveY < waveY_max) waveY += 1.5;
                if (waveX < waveX_min) waveX = 0; else waveX -= 3;

                ctx.globalCompositeOperation = 'source-over';
                ctx.beginPath();
                ctx.arc(canvasWidth/2, canvasHeight/2, canvasHeight/2, 0, Math.PI*2, true);
                ctx.closePath();
                ctx.fill();

                ctx.globalCompositeOperation = 'source-in';
                ctx.drawImage(waveImage, waveX, canvasHeight - waveY);

                requestAnimationFrame(loop);
            }
            loop();
        }

        function start () {
            if (!ctx) return init(start);
            needAnimate = true;
            setTimeout(function () {
                if (needAnimate) animate();
            }, 500);
        }
        function stop () {
            needAnimate = false;
        }
        return {start: start, stop: stop};
    }());
    wave.start();
}

/*能耗明细*/
function chart07Fun(){
    var myChartEnergy = echarts.init(document.getElementById('EnergyChart'));
    var option1 = {
        tooltip : {
            formatter: "{a} <br/>{c} {b}%"
        },

        series : [
            {
                name: '能耗',
                type: 'gauge',
                z: 3,
                min: 0,
                max: 100,
                startAngle: 180,
                endAngle: 0,
                splitNumber: -1,
                radius: '100%',
                axisLine:{
                    show:true,
                    lineStyle:{
                        color:[[0.5, '#3b96db'],[1, '#df5f4a'] ],
                        width:10
                    }
                },
                itemStyle:{
                    normal:{
                        color:'#d44243'
                    }
                },

                detail:{
                    show:false
                },
                data:[{value: "100"}]
            }

        ]
    };

//需要处理进行算法处理~~~？！！！
    var colorvalue ;
    var calcvalue = 50;
    var kedu1 = 0.3; //蓝色的刻度
    colorvalue = [[kedu1, '#3b96db'],[0.75, '#32bbb6'],[1, '#df5f4a']];
    option1.series[0].axisLine.lineStyle.color=colorvalue;
    option1.series[0].data[0].value=calcvalue;

    myChartEnergy.setOption(option1);
}




/*居民 合格率趋势*/
function chart08Fun(){
    var myChartQualified = echarts.init(document.getElementById('QualifiedChart'));
    var data = [
        ['15-01', 4.374394],
        ['15-01', 3.374394],
        ['15-01', 4.774394],
        ['15-03', 3.213817],
        ['16-03', 3.952681],
        ['16-13', 3.129283]
    ];

// See https://github.com/ecomfe/echarts-stat
    var myRegression = ecStat.regression('linear', data);

    myRegression.points.sort(function(a, b) {
        return a[0] - b[0];
    });

    optionQualified = {

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            left: '15',
            top: '30',
            right: '40',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            axisTick:{show:false},
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel : {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            },
            data: ['15-01', '15-03', '16-03', '16-06', '16-13']

        },
        yAxis: {
            type: 'value',
            axisTick:{show:false},
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show:true,
                textStyle: {
                    color: '#666',
                    fontFamily: 'arial'
                }
            }
        },
        series: [{
            name: '室温',
            type: 'scatter',
            markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
            },
            itemStyle : {
                normal : {
                    color:'#7fb7e1'
                }
            },
            data: data
        }]
    };
    myChartQualified.setOption(optionQualified);

}

/*居民室温合格率--chartCarbon*/
function chart09Fun(){
    var myChartCarbon = echarts.init(document.getElementById('chartCarbon'));
    optionCarbon = {
        title: {
            text: "67.2",
            subtext: '室温合格率（%）\n（1.6%↓）',  //↑↓
            x: 'center',
            y: 'center',
            itemGap: -5,
            textStyle : {
                color : '#348bce',
                fontFamily : '微软雅黑',
                fontSize : 44,
                fontWeight : 'normal'
            },
            subtextStyle : {
                color : '#d4513b',
                fontFamily : '微软雅黑',
                fontSize : 12,
                fontWeight : 'normal'
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        series: [
            {
                type:'pie',
                radius : ['0', '82%'],
                silent:true,
                itemStyle : {
                    normal : {
                        color:'#ffffff',
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    }
                },
                data:[
                    {value:1, name:'背景', selected:false,hoverAnimation:false}
                ]
            },
            {
                name:'合格率',
                type:'pie',
                radius : ['60%', '80%'],
                itemStyle : {
                    normal : {
                        color:'#dce0e5',
                        label : {show:false}
                    }
                },
                data:[
                    {value:1, name:'圈', selected:false,hoverAnimation:false}
                ]
            },
            {
                name:'合格率',
                type:'pie',
                radius : ['60%', '80%'],
                funnelAlign: 'left',
                itemStyle : {
                    normal : {
                        label : {show:false}
                    }
                },
                data: [{
                    value: 206.4,
                    name: '合格率',
                    itemStyle: {
                        normal: {
                            color: '#3b96db'
                        }
                    }
                }, {
                    value: 800,
                    name: '占位',
                    hoverAnimation:false,
                    tooltip: {
                        show: false
                    },
                    itemStyle: {
                        normal : {
                            color: 'rgba(0,0,0,0)',
                            label: {show:false},
                            labelLine: {show:false}
                        },
                        emphasis : {
                            color: 'rgba(0,0,0,0)'
                        }
                    }
                }]
            }
        ]
    };
    myChartCarbon.setOption(optionCarbon);
}


function cutNh(){
    if($("#bg-left").hasClass("button-group-act")) return;
    $("#bg-right").removeClass("button-group-act");
    $("#bg-left").addClass("button-group-act");

    $("#qs-title").hide();
    $("#nh-title").show();

    $("#chart04").hide();
    $("#chart02").show();
    chart02.resize();

}

function cutQs(){
    if($("#bg-right").hasClass("button-group-act")) return;
    $("#bg-left").removeClass("button-group-act");
    $("#bg-right").addClass("button-group-act");

    $("#nh-title").hide();
    $("#qs-title").show();

    $("#chart02").hide();
    $("#chart04").show();
    chart04.resize();
}

function selectYear(changeYear){
    $.ajax({
        url : "json/h-2.json",
        data: parseInt($("#branchcost-year").html()) + changeYear,
        type : "GET",
        dataType: "json",
        error : function(request) {
            alert("Connection error");
        },
        success : function(data) {
            chart02Fun(data.data.branchCost.data, data.data.branchCost.yearDate, data.data.branchCost.other);

            chart04Fun(data.data.branchCost.data, data.data.branchCost.yearDate, data.data.cost.other);
        }
    });
}

/*window.onresize = function(){
 chart01.resize();
 chart02.resize();
 chart03.resize();
 chart04.resize();

 myChartEnergy.resize();
 chart05.resize();

 myChartQualified.resize();
 myChartCarbon.resize();
 }*/