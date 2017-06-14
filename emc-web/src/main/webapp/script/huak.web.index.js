$(function() {

    //$("#header").load("header.html",function(){});
    //$("#footer").load("footer.html", function() {});

    var myChartEnergy;
    var myChartQualified;
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
    $("#website").attr("src", _web + "/static/img/index/websitet_cs0" + code + ".png");

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
function chart05Fun(){
    $.ajax({
        url:_web +'/component/costDetail',
        type:'post',
        async:true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:$("#searchTools").serialize(),
        dataType:"json",
        success:function(result) {
            debugger;
            var energy = result.object.ccs;
            var device = result.object.device;
            var labor  = result.object.labor;
            var manage = result.object.manage ;
            var other = result.object.other ;
            var total = result.object.total_sum;
            var tb_flag = result.object.flag_total;
            var total_tb = result.object.tb_total;
            var tbl ="";
            if(tb_flag == 'true'){
                tbl ="("+total_tb+"↑)";
            }
            if(tb_flag == 'false'){
                tbl= "("+total_tb+"↓)";
            }
            if(tb_flag == 'null'){
                tbl = "("+total_tb+"→)";
            }
            /*能源费*/
            $("#energy_cost").html(energy);
            var tb_flag = result.object.flag_ccs;
            var total_tb = result.object.tb_css;
            if(tb_flag == 'true'){
                $("#energy_tb").html("("+total_tb+"↑)");
            }
            if(tb_flag == 'false'){
                $("#energy_tb").html("("+total_tb+"↓)");
            }
            if(tb_flag == 'null'){
                $("#energy_tb").html("("+total_tb+"→)");
            }
            /*设备费*/
            $("#device_cost").html(device);
            var tb_flag = result.object.flag_device;
            var total_tb = result.object.tb_device;
            if(tb_flag == 'true'){
                $("#device_tb").html("("+total_tb+"↑)");
            }
            if(tb_flag == 'false'){
                $("#device_tb").html("("+total_tb+"↓)");
            }
            if(tb_flag == 'null'){
                $("#device_tb").html("("+total_tb+"→)");
            }
            /*人工费*/
            $("#labor_cost").html(labor);
            var tb_flag = result.object.flag_labor;
            var total_tb = result.object.tb_labor;
            if(tb_flag == 'true'){
                $("#labor_tb").html("("+total_tb+"↑)");
            }
            if(tb_flag == 'false'){
                $("#labor_tb").html("("+total_tb+"↓)");
            }
            if(tb_flag == 'null'){
                $("#labor_tb").html("("+total_tb+"→)");
            }
            /*管理费*/
            $("#manage_cost").html(manage);
            var tb_flag = result.object.flag_manage;
            var total_tb = result.object.tb_manage;
            if(tb_flag == 'true'){
                $("#manage_tb").html("("+total_tb+"↑)");
            }
            if(tb_flag == 'false'){
                $("#manage_tb").html("("+total_tb+"↓)");
            }
            if(tb_flag == 'null'){
                $("#manage_tb").html("("+total_tb+"→)");
            }
            /*其他费*/
            $("#other_cost").html(other);
            var tb_flag = result.object.flag_other;
            var total_tb = result.object.tb_other;
            if(tb_flag == 'true'){
                $("#other_tb").html("("+total_tb+"↑)");
            }
            if(tb_flag == 'false'){
                $("#other_tb").html("("+total_tb+"↓)");
            }
            if(tb_flag == 'null'){
                $("#other_tb").html("("+total_tb+"→)");
            }
            initChart05(energy,device,manage,labor,other,total,tbl);
        }
    });

}

function initChart05(energy,device,manage,labor,other,total,tbl){
    $("#chart05").empty();
    chart05 = echarts.init(document.getElementById('chart05'));
    var option = {
        title: {
            text: total,
            subtext: '成本总量（万元）\n'+tbl,  //↑↓
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
                    {value:labor, name:'人工费'},
                    {value:manage, name:'管理费'},
                    {value:other, name:'其他费'},
                    {value:energy, name:'能源费'},
                    {value:device, name:'设备费'}
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
    $.ajax({
        url:_web +'/component/energyDetail',
        type:'post',
        async:true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:$("#searchTools").serialize(),
        dataType:"json",
        success:function(result) {
            if (result.flag) {
                /*仪表盘*/
                var kedu1 =0; //蓝色的刻度
                var pcd = 0; //偏差度
                var pcdz = 0; //偏差值
                var currentPlan = 0;
                var bm_total = 0;
                if(result.object.isInCurrentSeason == true){
                    var currentDays = result.object.currentDays;
                    var planDays = result.object.planDays;
                    currentPlan =result.object.currentPlan;
                    bm_total =result.object.bm_total;
                    kedu1 =(currentDays/planDays)*0.75;
                    pcd = (bm_total - (currentDays/planDays)*currentPlan)/currentPlan*100;
                    pcd =  toDecimal(pcd);
                    pcdz = bm_total - (currentDays/planDays)*currentPlan;
                    pcdz =  toDecimal(pcdz);
                }else{
                    kedu1 = 0.75;
                }
                initChart(kedu1,currentPlan,bm_total);

                $("#pc_plan_percent").html("偏差度("+pcd+"%)");
                $("#pc_plan").html(pcdz);
                /*总标煤展示*/
                $("#bm_total").html(result.object.bm_total);
                var tb_flag = result.object.total_flag;
                var total_tb = result.object.total_tb;
                if(tb_flag == true){
                    $("#total_tb").html("("+total_tb+"↑)");
                }
                if(tb_flag == false){
                    $("#total_tb").html("("+total_tb+"↓)");
                }
                if(tb_flag == null){
                    $("#total_tb").html("("+total_tb+"→)");
                }
                /*水*/
                $("#whater").html(result.object.whater+"T");
                var tb_flag = result.object.whater_flag;
                var total_tb = result.object.whater_tb;
                if(tb_flag == true){
                    $("#whater_tb").html("("+total_tb+"↑)");
                }
                if(tb_flag == false){
                    $("#whater_tb").html("("+total_tb+"↓)");
                }
                if(tb_flag == null){
                    $("#whater_tb").html("("+total_tb+"→)");
                }
                /*电*/
                $("#electric").html(result.object.electric+"Kw/h");
                var tb_flag = result.object.electric_flag;
                var total_tb = result.object.electric_tb;
                if(tb_flag == true){
                    $("#electric_tb").html("("+total_tb+"↑)");
                }
                if(tb_flag == false){
                    $("#electric_tb").html("("+total_tb+"↓)");
                }
                if(tb_flag == null){
                    $("#electric_tb").html("("+total_tb+"→)");
                }
                /*气*/
                $("#gas").html(result.object.gas+"M3");
                var tb_flag = result.object.gas_flag;
                var total_tb = result.object.gas_tb;
                if(tb_flag == true){
                    $("#gas_tb").html("("+total_tb+"↑)");
                }
                if(tb_flag == false){
                    $("#gas_tb").html("("+total_tb+"↓)");
                }
                if(tb_flag == null){
                    $("#gas_tb").html("("+total_tb+"→)");
                }
                /*热*/
                $("#heat").html(result.object.heat+"GJ");
                var tb_flag = result.object.heat_flag;
                var total_tb = result.object.heat_tb;
                if(tb_flag == true){
                    $("#heat_tb").html("("+total_tb+"↑)");
                }
                if(tb_flag == false){
                    $("#heat_tb").html("("+total_tb+"↓)");
                }
                if(tb_flag == null){
                    $("#heat_tb").html("("+total_tb+"→)");
                }
                /*煤*/
                $("#coal").html(result.object.coal+"T");
                var tb_flag = result.object.coal_flag;
                var total_tb = result.object.coal_tb;
                if(tb_flag == true){
                    $("#coal_tb").html("("+total_tb+"↑)");
                }
                if(tb_flag == false){
                    $("#coal_tb").html("("+total_tb+"↓)");
                }
                if(tb_flag == null){
                    $("#coal_tb").html("("+total_tb+"→)");
                }
            } else {
                alert("系统错误！");
            }
        },
        error:function(e){
            alert("访问失败");
        }
    });

}

function initChart(kedu1,mx,bm_total){
    myChartEnergy = echarts.init(document.getElementById('EnergyChart'));
    var option1 = {
        tooltip : {
            formatter: "{a} <br/>{c} {b}"
        },
        series : [
            {
                name: '能耗',
                type: 'gauge',
                z: 3,
                min: 0,
                max: mx,
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
                data:[{value: "50"}]
            }

        ]
    }
   var  colorvalue = [[kedu1, '#3b96db'],[0.75, '#32bbb6'],[1, '#df5f4a']];
    option1.series[0].axisLine.lineStyle.color=colorvalue;
    option1.series[0].data[0].value= bm_total;
    myChartEnergy.setOption(option1);
}

function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*100)/100;
    return f;
}



/*居民 合格率趋势*/
function chart08Fun(){
    myChartQualified = echarts.init(document.getElementById('QualifiedChart'));
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