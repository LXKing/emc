var echartsSelf = function(options) {

    var echartObj;
    this.init = function() {
        echartObj = echarts.init(document.getElementById(options.id));

    };
    this.createChart = function() {

        var seriesData = [];
        for(var i = 0; i < options.echartsConfig.series.length; i++) {
            var _item = options.echartsConfig.series[i];
            var seriesitem = {
                type: _item.type,
                symbol: 'circle',
                smooth: false,
                lineStyle: {
                    normal: {
                        type: _item.typeLine || 'solid'
                    }
                },
                data: _item.dataList,
                barWidth: _item.barWidth || null
            };
            seriesData.push(seriesitem);
        }
        var option = {
            dataZoom: {
                type: 'slider',
                show: options.echartsConfig.dataZoom || false,
                startValue: options.echartsConfig.dataZoomstartValue || null,
                endValue: options.echartsConfig.dataZoomendValue || null,
                zoomLock: true,
                height: 20,
                backgroundColor: '#fff',
                showDetail: false,
                showDataShadow: false

            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '15',
                top: '10',
                right: '40',
                bottom: options.echartsConfig.dataZoom ? '80' : '10',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: options.echartsConfig.xAxisBoundaryGap || false,
                axisTick: {
                    show: false
                },
                splitArea: {
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? false : true,
                    areaStyle: {
                        color: chartsColor.areacolor
                    }
                },
                splitLine: {
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? false : true,
                    lineStyle: {
                        color: chartsColor.ec1.facecolor1
                    }
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: chartsColor.ec1.facecolor2
                    }
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: chartsColor.linefontcolor,
                        fontFamily: 'arial'
                    },
                    interval:options.echartsConfig.axisLabelInterval || 'auto',
                    rotate: options.echartsConfig.axisLabelRotate || 0
                },
                data: options.echartsConfig.xData

            },
            yAxis: {
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: chartsColor.ec1.facecolor3
                    }
                },
                splitLine: {
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? true : false,
                    lineStyle: {
                        color: chartsColor.ec1.facecolor4
                    }
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: chartsColor.linefontcolor,
                        fontFamily: 'arial'
                    }

                },
                splitArea: {
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? true : false,
                    areaStyle: {
                        color: chartsColor.areacolor
                    }
                }
            },
            color: chartsColor.ec2.facecolor1,
            series: seriesData
        };
        echartObj.setOption(option);

    };
    this.init();
    this.createChart();

    return echartObj;

};