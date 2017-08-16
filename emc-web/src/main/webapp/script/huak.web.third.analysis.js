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
    loadOrgDH();
    loadFeedDH();
    loadStationDH();
    loadTable();
});
//加载水单耗明细
function loadDHdetail(){
    var type = $("#thirdType").val();
    $.ajax({
        url: _web + "/third/analysis/water/detail/"+type,
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            $(".groupTotal").text(data.reMap.ZDH);
            if(data.reMap.TQ>0){
                var TQ = data.reMap.TQ+"<span class='arrow'>↑</span>";
                $(".groupchangeRate").html(TQ);
            }else if(data.reMap.TQ==0){
                var TQ = data.reMap.TQ+"<span class='arrow'>→</span>";
                $(".groupchangeRate").html(TQ)
            }else if(data.reMap.TQ<0){
                var TQ = data.reMap.TQ+"<span class='arrow'>↓</span>";
                $(".groupchangeRate").html(TQ)
            }
            echartsSelf({
                id: "groupEnergyChart",
                echartsConfig: {
                    xData: data.xaxis,
                    series: [{
                        type: 'line',
                        dataList: data.newDate,
                        typeLine: 'solid'

                    },
                        {
                            type: 'line',
                            dataList: data.oldDate,
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
        url: _web + "/third/analysis/water/feeddh",
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            console.log(data);
            feedDh(data);
        }
    });
}
/*三级页面-各站点能源类型用量明细*/
function loadTable(){
    createtable();
    var data = $("#searchTools").serialize()+"&type="+$("#thirdType").val();
    $.ajax({
        url: _web + '/third/analysis/tablelist',
        type: 'post',
        async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            thirdTable(result.object);
        }
    });

}
//加载换热站的单耗排名
function loadStationDH() {

    $.ajax({
        url: _web + "/third/analysis/water/stationdh",
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            console.log(data);
            stationDh(data);
        }
    });
}

//加载源、网、站、线、户  的水单耗

function loadOrgDH() {

    var type = $("#thirdType").val();
    $.ajax({
        url: _web + "/third/analysis/water/org/"+type,
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            getFeedEc(data);
            getNetEc(data);
            getStationEc(data);
            getLine(data);
            getRoomEc(data);
        }
    });

    function getFeedEc(data){

        $(".feedTotal").html(data.TotalTq.YTotal);
        if(data.TotalTq.YTB>0){
            var tb = "("+data.TotalTq.YTB+"↑)";
            $(".feedTQ").html(tb);
        }else if(data.TotalTq.YTB==0){
            var tb = "("+data.TotalTq.YTB+"→)";
            $(".feedTQ").html(tb);
        }else if(data.TotalTq.YTB<0){
            var tb = "("+data.TotalTq.YTB+"↓)";
            $(".feedTQ").html(tb);
        }
        echartsSelf({
            id: "chart1",
            echartsConfig: {
                xData: data.resultData.dateLine,
                series: [{
                    type: 'line',
                    dataList: data.resultData.newFeed,
                    typeLine: 'solid'
                },
                    {
                        type: 'line',
                        dataList: data.resultData.oldFeed,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
    function getNetEc(data){
        $(".netTotal").html(data.TotalTq.WTotal);
        if(data.TotalTq.WTB>0){
            var tb = "("+data.TotalTq.WTB+"↑)";
            $(".netTQ").html(tb);
        }else if(data.TotalTq.WTB==0){
            var tb = "("+data.TotalTq.WTB+"→)";
            $(".netTQ").html(tb);
        }else if(data.TotalTq.WTB<0){
            var tb = "("+data.TotalTq.WTB+"↓)";
            $(".netTQ").html(tb);
        }
        echartsSelf({
            id: "chart2",
            echartsConfig: {
                xData: data.resultData.dateLine,
                series: [{
                    type: 'line',
                    dataList: data.resultData.newNet,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        dataList: data.resultData.oldNet,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
    function getStationEc(data){
        $(".stationTotal").html(data.TotalTq.ZTotal);
        if(data.TotalTq.ZTB>0){
            var tb = "("+data.TotalTq.ZTB+"↑)";
            $(".stationTQ").html(tb);
        }else if(data.TotalTq.ZTB==0){
            var tb = "("+data.TotalTq.ZTB+"→)";
            $(".stationTQ").html(tb);
        }else if(data.TotalTq.ZTB<0){
            var tb = "("+data.TotalTq.ZTB+"↓)";
            $(".stationTQ").html(tb);
        }
        echartsSelf({
            id: "chart3",
            echartsConfig: {
                xData: data.resultData.dateLine,
                series: [{
                    type: 'line',
                    dataList: data.resultData.newStation,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        dataList: data.resultData.oldStation,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
    function getLine(data){
        $(".lineTotal").html(data.TotalTq.XTotal);
        if(data.TotalTq.XTB>0){
            var tb = "("+data.TotalTq.XTB+"↑)";
            $(".lineTQ").html(tb);
        }else if(data.TotalTq.XTB==0){
            var tb = "("+data.TotalTq.XTB+"→)";
            $(".lineTQ").html(tb);
        }else if(data.TotalTq.XTB<0){
            var tb = "("+data.TotalTq.XTB+"↓)";
            $(".lineTQ").html(tb);
        }
        echartsSelf({
            id: "chart4",
            echartsConfig: {
                xData: data.resultData.dateLine,
                series: [{
                    type: 'line',
                    dataList: data.resultData.newLine,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        dataList: data.resultData.oldLine,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
    function getRoomEc(data){
        $(".roomTotal").html(data.TotalTq.HTotal);
        if(data.TotalTq.HTB>0){
            var tb = "("+data.TotalTq.HTB+"↑)";
            $(".roomTQ").html(tb);
        }else if(data.TotalTq.HTB==0){
            var tb = "("+data.TotalTq.HTB+"→)";
            $(".roomTQ").html(tb);
        }else if(data.TotalTq.HTB<0){
            var tb = "("+data.TotalTq.HTB+"↓)";
            $(".roomTQ").html(tb);
        }
        echartsSelf({
            id: "chart5",
            echartsConfig: {
                xData: data.resultData.dateLine,
                series: [{
                    type: 'line',
                    dataList: data.resultData.newRoom,
                    typeLine: 'solid'

                },
                    {
                        type: 'line',
                        dataList: data.resultData.oldRoom,
                        typeLine: 'dashed'
                    }
                ]
            }
        });
    }
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
//    $.each($(".energy-list .energy-chart > div"), function(index, item) {
//        var options = {
//            id: item.id,
//            echartsConfig: {
//
//                xData: [1, 2, 3, 4, 5, 6, 7],
//                series: [{
//                    type: 'line',
//                    dataList: [1, 4, 5, 2, 3],
//                    typeLine: 'solid'
//
//                },
//                    {
//                        type: 'line',
//                        dataList: [2, 4, 7, 1, 4, 5],
//                        typeLine: 'dashed'
//                    }
//                ]
//            }
//        };
//        echartsSelf(options);
    // });
//
//    echartsSelf({
//        id: "groupEnergyChart",
//        echartsConfig: {
//            xData: [1, 2, 3, 4, 5, 6, 7, 8],
//            series: [{
//                type: 'line',
//                dataList: [1, 4, 5, 2, 3, 6],
//                typeLine: 'solid'
//
//            },
//                {
//                    type: 'line',
//                    dataList: [2, 4, 7, 1, 4, 5],
//                    typeLine: 'dashed'
//                }
//            ]
//        }
//    });

//    echartsSelf({
//        id: 'piechart_as',
//        echartsConfig: {
//            axisLabelRotate: '-50', //倾斜角度
//            xAxisBoundaryGap: true,
//            dataZoom: true,
//            dataZoomstartValue: 0,
//            dataZoomendValue: 9,
//            bg: 'row',
//            xData: ['站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3', '站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3'],
//            series: [{
//                type: 'bar',
//                dataList: [1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3],
//                barWidth: 20
//            }]
//        }
//    });
//
//    echartsSelf({
//        id: 'linechart_as',
//        echartsConfig: {
//            axisLabelRotate: '-50', //倾斜角度
//            xAxisBoundaryGap: true,
//            dataZoom: true,
//            dataZoomstartValue: 0,
//            dataZoomendValue: 9,
//            bg: 'row',
//            xData: ['站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3', '站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3'],
//            series: [{
//                type: 'bar',
//                dataList: [1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3],
//                barWidth: 20
//
//            }]
//        }
//    });
}
