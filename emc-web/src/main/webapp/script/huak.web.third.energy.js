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

    loadDataFun();

});

function loadDataFun() {



    $(".maintitle").html("水能耗"); //切换页后改这个可以改整体标题

    createtable();

    $.each($(".energy-list .energy-chart > div"), function(index, item) {
        var options = {
            id: item.id,
            echartsConfig: {

                xData: [1, 2, 3, 4, 5, 6, 7],
                series: [{
                    type: 'line',
                    dataList: [1, 4, 5, 2, 3],
                    typeLine: 'solid',

                },
                    {
                        type: 'line',
                        dataList: [2, 4, 7, 1, 4, 5],
                        typeLine: 'dashed'
                    }
                ]
            }
        };
        echartsSelf(options);
    });

    echartsSelf({
        id: "groupEnergyChart",
        echartsConfig: {
            xData: [1, 2, 3, 4, 5, 6, 7],
            series: [{
                type: 'line',
                dataList: [1, 4, 5, 2, 3],
                typeLine: 'solid',

            },
                {
                    type: 'line',
                    dataList: [2, 4, 7, 1, 4, 5],
                    typeLine: 'dashed'
                }
            ]
        }
    });

    echartsSelf({
        id: 'piechart_as',
        echartsConfig: {
            axisLabelRotate: '-50', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            bg: 'row',
            xData: ['站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3', '站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3'],
            series: [{
                type: 'bar',
                dataList: [1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3],
                barWidth: 20
            }]
        }
    });

    echartsSelf({
        id: 'linechart_as',
        echartsConfig: {
            axisLabelRotate: '-50', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            bg: 'row',
            xData: ['站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3', '站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3'],
            series: [{
                type: 'bar',
                dataList: [1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3],
                barWidth: 20

            }]
        }
    });
}