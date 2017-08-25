/*function loadDataFun() {
 loadFun();
 loadassessment();
 }

 function loadassessment(){
 var data = $("#searchTools").serialize()+"&type="+$("#type").val();
 $.ajax({
 url: _web + '/third/energy/assessment',
 type: 'post',
 async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
 data:data,
 dataType: "json",
 success: function (result) {
 console.info(result);
 initassessment(result);
 }
 });

 }

 *//*三级页面-能耗分析-站的各种能源类型排名*//*
 function initassessment(result){
 if(result.flag){
 echartsSelf({
 id: 'linechart_as',
 echartsConfig: {
 xAxisBoundaryGap: true,
 dataZoom: true,
 dataZoomstartValue: 1,
 dataZoomendValue: 5,
 xData: result.object.heatnames,
 series: [{
 type: 'bar',
 dataList:  result.object.heatnum

 }]
 }
 });

 echartsSelf({
 id: 'piechart_as',
 echartsConfig: {
 xAxisBoundaryGap: true,
 dataZoom: true,
 dataZoomstartValue: 1,
 dataZoomendValue: 5,
 xData: result.object.stationnames,
 series: [{
 type: 'bar',
 dataList: result.object.stationnums

 }]
 }
 });
 }
 }

 *//*三级页面-能耗分析-源、网、站、线、户的各种能源类型能耗*//*
 function loadFun(){
 var data = $("#searchTools").serialize()+"&type="+$("#type").val();
 $.ajax({
 url: _web + '/third/energy/energyDetail',
 type: 'post',
 async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
 data:data,
 dataType: "json",
 success: function (result) {
 initchart(result);
 }
 });
 }

 function initchart(result){

 if(result.flag){
 var datelist = result.object.date;
 $.each(result.object.data,function(index,value){
 var options = {
 id: value.type,
 echartsConfig: {
 xData: datelist,
 series: [{
 type: 'line',
 dataList: value.currentYear,
 typeLine: 'solid'
 },
 {
 type: 'line',
 dataList:value.lastyear,
 typeLine: 'dashed'
 }
 ]
 }
 };
 echartsSelf(options);
 })
 }


 };*/
$(function() {
    loadDHdetail();

    loadOrgFeedDH();
    loadOrgNetDH();
    loadOrgStationDH();
    loadOrgLineDH();
    loadOrgRoomDH();


    loadFeedDH();
    loadStationDH();
    loadTable();
});

//加载水单耗明细
function loadDHdetail(){

    var  type=$("#thirdType").val();
    var  unittype="0";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/"+type+"/"+unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            $(".groupTotal").text(data.ZDH);
            if(data.TB>0){
                var TB = data.TB+"<span class='arrow'>↑</span>";
                $(".groupchangeRate").html(TB);
            }else if(data.TB==0){
                var TB = data.TB+"<span class='arrow'>→</span>";
                $(".groupchangeRate").html(TB)
            }else if(data.TB<0){
                var TB = data.TB+"<span class='arrow'>↓</span>";
                $(".groupchangeRate").html(TB)
            }

            echartsSelf({
                id: "groupEnergyChart",
                echartsConfig: {
                    xData: data.xdatas,
                    series: [{
                        type: 'line',
                        name:data.datas[0].typeName,
                        dataList: data.datas[0].dataList,
                        typeLine: 'solid'
                    },
                        {
                            type: 'line',
                            name:data.datas[1].typeName,
                            dataList: data.datas["1"].dataList,
                            typeLine: 'dashed'
                        }
                    ]
                }
            });
        }
    });
}
//加载热源的单耗排名
function loadFeedDH() {

    $.ajax({
        url: _web + "/third/analysis/water/feed-dh",
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            feedDh(data);
        }
    });
}
/*三级页面-各站点能源类型用量明细*/
function loadTable(){
    createtable();
    var data = $("#searchTools").serialize()+"&type="+$("#thirdType").val();
    $.ajax({
        url: _web + '/third/analysis/table-list',
        type: 'post',
        async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            debugger;
            thirdTable(result.object);
        }
    });

}
//加载换热站的单耗排名
function loadStationDH() {

    $.ajax({
        url: _web + "/third/analysis/water/station-dh",
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            stationDh(data);
        }
    });
}
//1 -- 热源水单耗
function loadOrgFeedDH() {
    var type = $("#thirdType").val();
    var unittype = "1";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getFeedEc(data,type);

        }
    });
}
//2 -- 管网水单耗
function loadOrgNetDH() {
    var type = $("#thirdType").val();
    var unittype = "2";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getNetEc(data,type);

        }
    });
}
//3 --加载换热站站单耗
function loadOrgStationDH() {
    var type = $("#thirdType").val();
    var unittype = "3";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getStationEc(data,type);

        }
    });
}

//4 -- 管线水单耗
function loadOrgLineDH() {
    var type = $("#thirdType").val();
    var unittype = "4";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getLine(data,type);

        }
    });
}
//5 -- 民户水单耗
function loadOrgRoomDH() {
    var type = $("#thirdType").val();
    var unittype = "5";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getRoomEc(data,type);

        }
    });
}
function getStationEc(data,type){
        $("#chart3").empty();
        chart3  =echarts.init(document.getElementById('chart3'));
        $(".stationTotal").html(data.ZDH);
        if(type=="1"){
            $(".stationDw").html("T/m²");
        }else if(type=="2"){
            $(".stationDw").html("kW·h/m²");
        }else if(type=="3"){
            $(".stationDw").html("m³/m²");
        }else if(type=="4"){
            $(".stationDw").html("GJ/m²");
        }else if(type=="5"){
            $(".stationDw").html("T/m²");
        }
        if(data.TB>0){
            var TB = data.TB+"<span class='arrow'>↑</span>";
            $(".stationTQ").html(TB);
        }else if(data.TB==0){
            var TB = data.TB+"<span class='arrow'>→</span>";
            $(".stationTQ").html(TB)
        }else if(data.TB<0){
            var TB = data.TB+"<span class='arrow'>↓</span>";
            $(".stationTQ").html(TB)
        }
        echartsSelf({
            id: "chart3",
            echartsConfig: {
                xData: data.xdatas,
                series: [{
                    type: 'line',
                    name:data.datas[0].typeName,
                    dataList: data.datas[0].dataList,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        name:data.datas[1].typeName,
                        dataList: data.datas[1].dataList,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
function getFeedEc(data,type){
        $("#chart1").empty();
        chart1  =echarts.init(document.getElementById('chart1'));
            $(".feedTotal").html(data.ZDH);
        if(type=="1"){
            $(".feedDw").html("T/m²");
        }else if(type=="2"){
            $(".feedDw").html("kW·h/m²");
        }else if(type=="3"){
            $(".feedDw").html("m³/m²");
        }else if(type=="4"){
            $(".feedDw").html("GJ/m²");
        }else if(type=="5"){
            $(".feedDw").html("T/m²");
        }
        if(data.TB>0){
            var TB = data.TB+"<span class='arrow'>↑</span>";
            $(".feedTQ").html(TB);
        }else if(data.TB==0){
            var TB = data.TB+"<span class='arrow'>→</span>";
            $(".feedTQ").html(TB)
        }else if(data.TB<0){
            var TB = data.TB+"<span class='arrow'>↓</span>";
            $(".feedTQ").html(TB)
        }
        echartsSelf({
            id: "chart1",
            echartsConfig: {
                xData: data.xdatas,
                series: [{
                    type: 'line',
                    name:data.datas[0].typeName,
                    dataList: data.datas[0].dataList,
                    typeLine: 'solid'
                },
                    {
                        type: 'line',
                        name:data.datas[1].typeName,
                        dataList: data.datas[1].dataList,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
    function getNetEc(data,type){
        $("#chart2").empty();
        chart2  =echarts.init(document.getElementById('chart2'));
        $(".netTotal").html(data.ZDH)
        if(type=="1"){
            $(".netDw").html("T/m²");
        }else if(type=="2"){
            $(".netDw").html("kW·h/m²");
        }else if(type=="3"){
            $(".netDw").html("m³/m²");
        }else if(type=="4"){
            $(".netDw").html("GJ/m²");
        }else if(type=="5"){
            $(".netDw").html("T/m²");
        }
        if(data.TB>0){
            var TB = data.TB+"<span class='arrow'>↑</span>";
            $(".netTQ").html(TB);
        }else if(data.TB==0){
            var TB = data.TB+"<span class='arrow'>→</span>";
            $(".netTQ").html(TB)
        }else if(data.TB<0){
            var TB = data.TB+"<span class='arrow'>↓</span>";
            $(".netTQ").html(TB)
        }
        echartsSelf({
            id: "chart2",
            echartsConfig: {
                xData: data.xdatas,
                series: [{
                    type: 'line',
                    name:data.datas[0].typeName,
                    dataList: data.datas[0].dataList,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        name:data.datas[1].typeName,
                        dataList: data.datas[1].dataList,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
    function getLine(data,type){
        $("#chart4").empty();
        chart4  =echarts.init(document.getElementById('chart4'));
        $(".lineTotal").html(data.ZDH);
            if(type=="1"){
                $(".lineDw").html("T/m²");
            }else if(type=="2"){
                $(".lineDw").html("kW·h/m²");
            }else if(type=="3"){
                $(".lineDw").html("m³/m²");
            }else if(type=="4"){
                $(".lineDw").html("GJ/m²");
            }else if(type=="5") {
                $(".lineDw").html("T/m²");
            }
        if(data.TB>0){
        var TB = data.TB+"<span class='arrow'>↑</span>";
        $(".lineTQ").html(TB);
        }else if(data.TB==0){
            var TB = data.TB+"<span class='arrow'>→</span>";
            $(".lineTQ").html(TB)
        }else if(data.TB<0){
            var TB = data.TB+"<span class='arrow'>↓</span>";
            $(".lineTQ").html(TB)
        }
        echartsSelf({
            id: "chart4",
            echartsConfig: {
                xData: data.xdatas,
                series: [{
                    type: 'line',
                    name:data.datas[0].typeName,
                    dataList: data.datas[0].dataList,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        name:data.datas[1].typeName,
                        dataList: data.datas[1].dataList,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
    function getRoomEc(data,type){
        $("#chart5").empty();
        chart5  =echarts.init(document.getElementById('chart5'));
        $(".roomTotal").html(data.ZDH);
        if(type=="1"){
            $(".roomDw").html("T/m²");
        }else if(type=="2"){
            $(".roomDw").html("kW·h/m²");
        }else if(type=="3"){
            $(".roomDw").html("m³/m²");
        }else if(type=="4"){
            $(".roomDw").html("GJ/m²");
        }else if(type=="5") {
            $(".roomDw").html("T/m²");
        }
        if(data.TB>0){
            var TB = data.TB+"<span class='arrow'>↑</span>";
            $(".roomTQ").html(TB);
        }else if(data.TB==0){
            var TB = data.TB+"<span class='arrow'>→</span>";
            $(".roomTQ").html(TB)
        }else if(data.TB<0){
            var TB = data.TB+"<span class='arrow'>↓</span>";
            $(".roomTQ").html(TB)
        }
        echartsSelf({
            id: "chart5",
            echartsConfig: {
                xData: data.xdatas,
                series: [{
                    type: 'line',
                    name:data.datas[0].typeName,
                    dataList: data.datas[0].dataList,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        name:data.datas[1].typeName,
                        dataList: data.datas[1].dataList,
                        typeLine: 'dashed'
                    }
                ]
            }
        });

}
//加载热源的单耗排名
function feedDh(data){

    echartsSelf({
        id: 'linechart_as',
        echartsConfig: {
            axisLabelRotate: '-30', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            axisLabelInterval:0,
            bg: 'row',
            xData: data.mapName,
            series: [{
                type: 'bar',
                dataList: data.mapValue,
                barWidth: 20
            }]
        }
    });
}

function stationDh(data){

    echartsSelf({
        id: 'piechart_as',
        echartsConfig: {
            axisLabelRotate: '-30', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            axisLabelInterval:0,
            bg: 'row',
            xData: data.mapName,
            series: [{
                type: 'bar',
                dataList: data.mapValue,
                barWidth: 20
            }]
        }
    });
}
function loadDataFun() {
    createtable();

}
window.onresize = function(){
    $(".energy-chart").resize();
    chart1.resize();
    chart2.resize();
    chart3.resize();
    chart4.resize();
    chart5.resize();

    piechart_as.resize();
    linechart_as.resize();
}

