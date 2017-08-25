/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/17<BR>
 */

function loadDataFun() {
    initAssessment();
    initTable();
    initChart01(0);
    $.each($(".button-group").find("a"), function(sindex, sitem) {
        $(this).click(function() {
            $(this).addClass("button-group-act").siblings().removeClass("button-group-act");
            var type = parseInt(sindex)+1;
            $("#energytype").val(type);
            initChart01(sindex);
            initAssessment();
        });

    });
}

/*三级页面-用能单位-能源用量数据查询*/
function initChart01(index){
    var data = $("#searchTools").serialize()+"&type="+$("#type").val()+"&energyType="+$("#energytype").val();
    $.ajax({
        url: _web + '/third/energy/unit/energyDetail',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            chart01Fun(result.object);
        }
    });
}

/*三级页面-用能单位-表单数据查询*/
function initTable(){
    createtable();
    var data = $("#searchTools").serialize()+"&type="+$("#type").val();
    $.ajax({
        url: _web + '/third/energy/unit/unitTableList',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: data,
        dataType: "json",
        success: function (result) {
            thirdTable(result.object);
        }
    });

}

/*三级页面-用能单位-能源用量排名*/
function initAssessment(){
    var data = $("#searchTools").serialize()+"&type="+$("#type").val()+"&energyType="+$("#energytype").val();
    $.ajax({
        url: _web + '/third/energy/unit/unitAssessment',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:  data,
        dataType: "json",
        success: function (result) {
            echartsSelf({
                id: 'piechart_as',
                echartsConfig: {
                    axisLabelRotate: '-50', //倾斜角度
                    xAxisBoundaryGap: true,
                    dataZoom: true,
                    dataZoomstartValue: 0,
                    axisLabelInterval:0,
                    dataZoomendValue: 9,
                    bg: 'row',
                    xData: result.object.name,
                    series: [
                        {
                            type: 'bar',
                            dataList: result.object.cur,
                            barWidth: 20
                        }
                    ]
                }
            });
        }
    });
    $.ajax({
        url: _web + '/third/energy/unit/unitAllAssessment',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:  data,
        dataType: "json",
        success: function (result) {
            echartsSelf({
                id: 'linechart_as',
                echartsConfig: {
                    axisLabelRotate: '0', //倾斜角度
                    xAxisBoundaryGap: true,
                    dataZoom: true,

                    xData: result.object.date,
                    series: [
                        {
                            type: 'line',
                            dataList: result.object.data[0].currentYear,
                            barWidth: 20

                        }
                    ]
                }
            });
        }
    });
}

/*三级页面-用能单位-能源用量对比*/
function chart01Fun(data) {
    echartsSelf({
        id: "groupEnergyChart",
        echartsConfig: {
            dataZoom: true,
            xAxisBoundaryGap: true,
            axisLabelRotate: '-50', //倾斜角度
            axisLabelInterval:0,
            xData: data.name,
            series: [{
                type: 'line',
                dataList: data.tqb,
                typeLine: 'solid',
                yAxisIndex: 1
            },
                {
                    type: 'bar',
                    dataList: data.plan,
                    barWidth: 20,
                    barColor: 'rgba(59,150,219,0.4)'
                },
                {
                    type: 'bar',
                    dataList: data.cur,
                    barWidth: 20,
                    barColor: 'rgba(59,150,219,1)'
                },
                {
                    type: 'bar',
                    dataList: data.tq,
                    barWidth: 20,
                    barColor: 'rgba(50,187,182,1)'
                }
            ]
        }
    });
}