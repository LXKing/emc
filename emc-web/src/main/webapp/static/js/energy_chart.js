

$.ajax({
	url : "json/h-3.json",
	type : "GET",
	dataType: "json",
	error : function(request) {
		alert("Connection error");
	},
	success : function(data) {
		groupEnergyChartFun(data.data.groupEnergy.data, data.data.groupEnergy.yearDate);
		waterEnergyChartFun(data.data.waterEnergy.data, data.data.waterEnergy.yearDate);
		electricEnergyChartFun(data.data.electricEnergy.data, data.data.electricEnergy.yearDate);
		gasEnergyChartFun(data.data.gas.data, data.data.gas.yearDate);
		hotEnergyChartFun(data.data.hot.data, data.data.hot.yearDate);
		coalEnergyChartFun(data.data.coal.data, data.data.coal.yearDate);
	}
});


/*集团总能耗-折线图*/
function groupEnergyChartFun(datalist, datelist){
	$("#groupEnergyChart").empty();
	groupEnergyChart = echarts.init(document.getElementById('groupEnergyChart')); 
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
				show: true,
				lineStyle: {
					color: '#dbdcdf'
				}
			},
			axisLine: {
				show: true,
				lineStyle: {
					color: '#abcd'
				}
			},
			axisLabel : {
                show:true,
                textStyle: {
                    color: '#666',
	            	fontFamily: 'arial',
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
				show: true,
				lineStyle: {
					color: '#dbdcdf'
				}
			},
			axisLabel: {
	            show:true,
                textStyle: {
                    color: '#666',
	            	fontFamily: 'arial'
                }
	        },
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
	groupEnergyChart.setOption(option);
}

function waterEnergyChartFun(datalist, datelist){
	$("#waterEnergyChart").empty();
	waterEnergyChart = echarts.init(document.getElementById('waterEnergyChart')); 
	var option = {
		tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '0',
	        top: '10',
	        right: '35',
	        bottom: '0',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisTick:{show:false},
            splitLine: {
				show: false,
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
	            	fontFamily: 'arial',
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
	        },
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
	waterEnergyChart.setOption(option);
}

function electricEnergyChartFun(datalist, datelist){
	$("#electricEnergyChart").empty();
	electricEnergyChart = echarts.init(document.getElementById('electricEnergyChart')); 
	var option = {
		tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '0',
	        top: '10',
	        right: '35',
	        bottom: '0',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisTick:{show:false},
            splitLine: {
				show: false,
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
	            	fontFamily: 'arial',
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
	        },
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
	electricEnergyChart.setOption(option);
}

/*气能耗-折线图*/
function gasEnergyChartFun(datalist, datelist){
	$("#gasEnergyChart").empty();
	gasEnergyChart = echarts.init(document.getElementById('gasEnergyChart')); 
	var option = {
		tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '0',
	        top: '10',
	        right: '35',
	        bottom: '0',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisTick:{show:false},
            splitLine: {
				show: false,
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
	            	fontFamily: 'arial',
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
	        },
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
	gasEnergyChart.setOption(option);
}

/*热能耗-折线图*/
function hotEnergyChartFun(datalist, datelist){
	$("#hotEnergyChart").empty();
	hotEnergyChart = echarts.init(document.getElementById('hotEnergyChart')); 
	var option = {
		tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '0',
	        top: '10',
	        right: '35',
	        bottom: '0',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisTick:{show:false},
            splitLine: {
				show: false,
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
	            	fontFamily: 'arial',
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
	        },
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
	hotEnergyChart.setOption(option);
}

/*煤能耗-折线图*/
function coalEnergyChartFun(datalist, datelist){
	$("#coalEnergyChart").empty();
	coalEnergyChart = echarts.init(document.getElementById('coalEnergyChart')); 
	var option = {
		tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '0',
	        top: '10',
	        right: '35',
	        bottom: '0',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisTick:{show:false},
            splitLine: {
				show: false,
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
	            	fontFamily: 'arial',
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
	        },
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
	coalEnergyChart.setOption(option);
}








/*分公司能耗占比分布图*/
var piechart = echarts.init(document.getElementById('piechart')); 
var option = {
		
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)",
			show:true
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
		
		series : [
			{
				type:'pie',
				radius : ['0', '80%'],
				silent:true,
				itemStyle : {
					normal : {
	            		color:'#fff',
						label : {
	   						show : false
						},
						labelLine : {
							show : false
						}
					}
				},
				data:[
					{value:1, name:'圈', selected:false,hoverAnimation:false,}
				]
			},
			{
				name:'分公司能耗占比',
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
						label : {show:true}
					}
				},
				color:['#c675c3', '#8d82cc', '#3b96db', '#a1b1c5', '#32bbb6', '#df614c'],
				data:[
					{value:335, name:'朝一'},
					{value:310, name:'朝二'},
					{value:251, name:'丰台'},
					{value:234, name:'东城'},
					{value:135, name:'西城'},
					{value:1048, name:'海淀'}
				]

			}
		]
	};
piechart.setOption(option);
    
/*分公司能耗趋势对比图*/

var linechart = echarts.init(document.getElementById('linechart')); 
var option = {
    
    tooltip: {
        trigger: 'axis'
    },
    grid: {
        //left: '15%',
        top: '28',
        right: '115',
        bottom: '5',
        containLabel: true
    },
 	legend: {
    	orient : 'vertical',
		right : '5%',
		top: '28',
		itemWidth:8,
		itemHeight:4,
		icon:'rect',
		itemGap:20,
        data:['朝一','朝二','丰台','东城','西城','海淀']
    },
    color:['#c675c3', '#8d82cc', '#3b96db', '#a1b1c5', '#32bbb6', '#df614c'],
    xAxis: {
        type: 'category',
        boundaryGap: false,
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
        splitArea: {
           show: true
        },
        data: ['2015-11','2015-12','2016-01','2016-02','2016-03']
        
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
        },
    },
   
    series: [
        {
            name:'朝一',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[120, 132, 101, 134, 90]
        },
        {
            name:'朝二',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[140, 112, 51, 34, 69]
        },
        {
            name:'丰台',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[220, 182, 191, 234, 290]
        },
        {
            name:'东城',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[150, 232, 201, 154, 190]
        },
        {
            name:'西城',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[320, 332, 301, 334, 390]
        },
        {
            name:'海淀',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[820, 932, 901, 934, 629]
        }
    ]
};
		
linechart.setOption(option);

/*分公司能耗同比*/
var barchart01 = echarts.init(document.getElementById('barchart01')); 
var option = {
	title:{
		subtext:'分公司能耗 (单位: GJ/㎡)',
		top:'-18px',
		left:'35px',
		subtextStyle:{
			color: '#666',
			fontStyle: 'normal',
			fontWeight: 'normal',
			fontFamily: '微软雅黑',
			fontSize: 12,
		}
	},
	tooltip: {
        trigger: 'axis',
        axisPointer : {            
            type : 'shadow'        
        }
    },
    legend: {
        data:['今年','去年'],
        itemWidth:8,
		itemHeight:4,
    	right: '40',
    	top:'10px',
		textStyle: {
            color: '#666',
            fontStyle: 'normal',
            fontWeight: 'lighter',
            fontFamily: '微软雅黑',
            fontSize: 12,
        }
    },
    grid: {
        //left: '15',
        top: '50',
        right: '45',
        bottom: '5',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        axisTick:{show:false},
        splitLine: {
			show: false,
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
            	fontFamily: 'arial',
            }
        },
        data:['朝一','朝二','丰台','东城','西城','海淀']
        
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
		splitArea: {
           show: true
        },
		axisLabel: {
            show:true,
            textStyle: {
                color: '#666',
            	fontFamily: 'arial'
            }
        },
    },
    color:['#3B96DD','#a1b1c5'],
    series: [
	    {
		    name:"今年",
			type:'bar',
			barWidth: '20',
			data:[10, 52, 200, 334, 390, 330]
	    },
	    {
		    name:"去年",
			type:'bar',
			barWidth: '20',
			data:[10, 52, 200, 334, 390, 330]
	    }
    ]
}

barchart01.setOption(option);

/*分公司能耗排名---barchart02*/

var	barchart02 = echarts.init(document.getElementById('barchart02')); 
var option = {
	title:{
		subtext:'分公司能耗 (单位: GJ/㎡)',
		top:'-18px',
		left:'35px',
		subtextStyle:{
			color: '#666',
			fontStyle: 'normal',
			fontWeight: 'normal',
			fontFamily: '微软雅黑',
			fontSize: 12,
		}
	},
	tooltip: {
        trigger: 'axis',
        axisPointer : {            
            type : 'shadow'        
        }
    },
    legend: {
        data:['今年','去年'],
        itemWidth:8,
		itemHeight:4,
    	right: '40',
    	top:'10px',
		textStyle: {
            color: '#666',
            fontStyle: 'normal',
            fontWeight: 'lighter',
            fontFamily: '微软雅黑',
            fontSize: 12,
        }
    },
    grid: {
        //left: '15',
        top: '50',
        right: '45',
        bottom: '5',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        axisTick:{show:false},
        splitLine: {
			show: false,
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
            	fontFamily: 'arial',
            }
        },
        data:['朝一','朝二','丰台','东城','西城','海淀']
        
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
		splitArea: {
           show: true
        },
		axisLabel: {
            show:true,
            textStyle: {
                color: '#666',
            	fontFamily: 'arial'
            }
        },
    },
    color:['#3B96DD'],
    series: [
    	{
			name:"分公司能耗",
			type:'bar',
			barWidth: '20',
			markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
		    },
			data:[10, 52, 200, 334, 390, 330]
    	}
    ]
}

barchart02.setOption(option);

/*能源流能耗占比分布图*/
var piechart_as = echarts.init(document.getElementById('piechart_as')); 
var option = {
		
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)",
			show:true
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
		
		series : [
			{
				type:'pie',
				radius : ['0', '80%'],
				silent:true,
				itemStyle : {
					normal : {
	            		color:'#fff',
						label : {
	   						show : false
						},
						labelLine : {
							show : false
						}
					}
				},
				data:[
					{value:1, name:'圈', selected:false,hoverAnimation:false,}
				]
			},
			{
				name:'能源流能耗占比',
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
						label : {show:true}
					}
				},
				color:['#c675c3', '#8d82cc', '#3b96db', '#32bbb6', '#df614c'],
				data:[
					{value:335, name:'一次网'},
					{value:310, name:'换热站'},
					{value:251, name:'二次网'},
					{value:135, name:'民户'},
					{value:1048, name:'供热源'}
				]

			}
		]
	};
piechart_as.setOption(option);
    
/*能源流能耗趋势对比图*/

var linechart_as = echarts.init(document.getElementById('linechart_as')); 
var option = {
    
    tooltip: {
        trigger: 'axis'
    },
    grid: {
        //left: '15%',
        top: '28',
        right: '115',
        bottom: '5',
        containLabel: true
    },
 	legend: {
    	orient : 'vertical',
		right : '5%',
		top: '28',
		itemWidth:8,
		itemHeight:4,
		icon:'rect',
		itemGap:20,
        data:['一次网','换热站','二次网','民户','供热源']
    },
    color:['#c675c3', '#8d82cc', '#3b96db', '#32bbb6', '#df614c'],
    xAxis: {
        type: 'category',
        boundaryGap: false,
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
        splitArea: {
           show: true
        },
        data: ['2015-11','2015-12','2016-01','2016-02','2016-03']
        
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
        },
    },
  
    series: [
        {
            name:'一次网',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[120, 132, 101, 134, 90]
        },
        {
            name:'换热站',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[140, 112, 51, 34, 69]
        },
        {
            name:'二次网',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[220, 182, 191, 234, 290]
        },
        
        {
            name:'民户',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[320, 332, 301, 334, 390]
        },
        {
            name:'供热源',
            type:'line',
            symbol: 'circle',
    		smooth: false,
            data:[820, 932, 901, 934, 629]
        }
    ]
};
		
linechart_as.setOption(option);

/*能源流能耗同比*/
var barchart01_as = echarts.init(document.getElementById('barchart01_as')); 
var option = {
	title:{
		subtext:'能源流能耗 (单位: GJ/㎡)',
		top:'-18px',
		left:'35px',
		subtextStyle:{
			color: '#666',
			fontStyle: 'normal',
			fontWeight: 'normal',
			fontFamily: '微软雅黑',
			fontSize: 12,
		}
	},
	tooltip: {
        trigger: 'axis',
        axisPointer : {            
            type : 'shadow'        
        }
    },
    legend: {
        data:['今年','去年'],
        itemWidth:8,
		itemHeight:4,
    	right: '40',
    	top:'10px',
		textStyle: {
            color: '#666',
            fontStyle: 'normal',
            fontWeight: 'lighter',
            fontFamily: '微软雅黑',
            fontSize: 12,
        }
    },
    grid: {
        //left: '15',
        top: '50',
        right: '45',
        bottom: '5',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        axisTick:{show:false},
        splitLine: {
			show: false,
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
            	fontFamily: 'arial',
            }
        },
        data:['供热源','一次网','换热站','二次网','民户']
        
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
		splitArea: {
           show: true
        },
		axisLabel: {
            show:true,
            textStyle: {
                color: '#666',
            	fontFamily: 'arial'
            }
        },
    },
    color:['#3B96DD','#a1b1c5'],
    series: [
	    {
		    name:"今年",
			type:'bar',
			barWidth: '20',
			data:[10, 52, 200, 390, 330]
	    },
	    {
		    name:"去年",
			type:'bar',
			barWidth: '20',
			data:[10, 52, 200, 334, 330]
	    }
    ]
}

barchart01_as.setOption(option);

window.onresize = function(){
	groupEnergyChart.resize();
	waterEnergyChart.resize();
	electricEnergyChart.resize();
	gasEnergyChart.resize();
	hotEnergyChart.resize();
	coalEnergyChart.resize();
	
	piechart.resize();
	linechart.resize();
	barchart01.resize();
	barchart02.resize();
	
	piechart_as.resize();
	linechart_as.resize();
	barchart01_as.resize();
}