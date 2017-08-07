function loadDataFun() {
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

/*三级页面-能耗分析-站的各种能源类型排名*/
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

/*三级页面-能耗分析-源、网、站、线、户的各种能源类型能耗*/
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


};