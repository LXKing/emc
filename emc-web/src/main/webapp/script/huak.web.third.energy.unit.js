/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/17<BR>
 */
$(function() {

    loadDataFun();

});

function loadDataFun() {

    createtable();

    var data = [{
        line1: [2, 4, 7, 1, 4, 5],
        bar2: [2, 4, 5, 1, 4, 5],
        bar3: [2, 4, 6, 1, 4, 5],
        bar4: [2, 4, 7, 1, 4, 5],
    },
        {
            line1: [2, 4, 7, 1, 4, 5],
            bar2: [2, 4, 7, 1, 4, 5],
            bar3: [2, 4, 7, 1, 4, 5],
            bar4: [2, 4, 7, 1, 4, 5],
        }, {
            line1: [1, 4, 5, 2, 3],
            bar2: [2, 4, 5, 1, 4, 5],
            bar3: [2, 4, 6, 1, 4, 5],
            bar4: [2, 4, 7, 1, 4, 5],
        }, {
            line1: [1, 4, 5, 2, 3],
            bar2: [2, 4, 7, 1, 4, 5],
            bar3: [2, 4, 7, 1, 4, 5],
            bar4: [2, 4, 7, 1, 4, 5],
        }, {
            line1: [1, 4, 5, 2, 3],
            bar2: [2, 4, 5, 1, 4, 5],
            bar3: [2, 4, 6, 1, 4, 5],
            bar4: [2, 4, 7, 1, 4, 5],
        }
    ]

    chart01Fun(data[0]);

    $.each($(".button-group").find("a"), function(sindex, sitem) {
        $(this).click(function() {
            $(this).addClass("button-group-act").siblings().removeClass("button-group-act");
            chart01Fun(data[sindex]);
        });

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
            xData: ['站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3', '站1', '站2', '站3', '站4', '站5', '站6', '站7', '站1', '站2', '站3'],
            series: [{
                type: 'line',
                dataList: [1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3],
                barWidth: 20

            }]
        }
    });
}

function chart01Fun(data) {
    echartsSelf({
        id: "groupEnergyChart",
        echartsConfig: {
            dataZoom: true,
            xAxisBoundaryGap: true,
            xData: [1, 2, 3, 4, 5, 6, 7],
            series: [{
                type: 'line',
                dataList: data.line1,
                typeLine: 'solid',
                yAxisIndex: 1,
            },
                {
                    type: 'bar',
                    dataList: data.bar2,
                    barWidth: 20,
                    barColor: 'rgba(59,150,219,0.4)',
                },
                {
                    type: 'bar',
                    dataList: data.bar3,
                    barWidth: 20,
                    barColor: 'rgba(59,150,219,1)'
                },
                {
                    type: 'bar',
                    dataList: data.bar4,
                    barWidth: 20,
                    barColor: 'rgba(50,187,182,1)'
                }
            ]
        }
    });
}