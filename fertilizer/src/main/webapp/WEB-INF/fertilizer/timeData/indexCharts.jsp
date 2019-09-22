<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(function(){
	$.getJSON('/fertilizer/indexCharts.do?fertilizerId=${fertilizerId}', function (data) {
//	console.log(data);
		Highcharts.setOptions({ global: { useUTC: false } }); 
	    $('#highcharts').highcharts({
	        chart: {
	            zoomType: 'x'
	        },
	        title: {
	            text: '施肥机数据'
	        },
	        xAxis: [{
	        	type: 'datetime',//时间格式
	        	categories: data.time,//时间数据数组
	        	labels: {
               		format: '{value:%d/%H:%M}' ,//显示的时间格式,如果不配置的话显示的是时间戳
            	},
	            crosshair: true
	        }],
	        
	        //y轴,多轴
	        yAxis: [{ // Secondary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: 'EC',
	                style: {
	                    color: Highcharts.getOptions().colors[0]
	                }
	            },
	            labels: {
	                format: '{value} ms/cm',
	                style: {
	                    color: Highcharts.getOptions().colors[0]
	                }
	            },
	            opposite: false,	//轴的位置,ture为右边,false左边,默认是false
	           // crosshair: true
	        }, { // Primary yAxis
	        	 title: {
		                text: 'PH',
		                style: {
		                    color: Highcharts.getOptions().colors[1]
		                }
		            },
	            labels: {
	                format: '{value}',
	                style: {
	                    color: Highcharts.getOptions().colors[1]
	                }
	            },
	            opposite: false,	//轴的位置,ture为右边,false左边,默认是false
	            //crosshair: true
	        },{ // Tertiary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: '实时流量',
	                style: {
	                    color: Highcharts.getOptions().colors[2]
	                }
	            },
	            labels: {
	                format: '{value} m³/H',
	                style: {
	                    color: Highcharts.getOptions().colors[2]
	                }
	            },
	            opposite: true
	        },{ // Tertiary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: '土壤湿度',
	                style: {
	                    color: Highcharts.getOptions().colors[3]
	                }
	            },
	            labels: {
	                format: '{value} %',
	                style: {
	                    color: Highcharts.getOptions().colors[3]
	                }
	            },
	            opposite: true
	        },{ // Tertiary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: '液位',
	                style: {
	                    color: Highcharts.getOptions().colors[4]
	                }
	            },
	            labels: {
	                format: '{value} cm',
	                style: {
	                    color: Highcharts.getOptions().colors[4]
	                }
	            },
	            opposite: true
	        }],
	        tooltip: {
	            shared: true,
	            split:true
	        },
	        legend: {
	            //layout: 'vertical',
	          //  align: 'left',
	            //x: 180,
	            verticalAlign: 'top',
	            y: 18,
	            floating: false,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
	        },
	        
	        //数据
	        series: [{
	            name: 'EC',
	            type: 'spline',
	            yAxis: 0,
	            data: data.ec,
	            marker: {
	                enabled: false
	            },
	           // dashStyle: 'shortdot',
	            tooltip: {
	                valueSuffix: ' ms/cm'
	            }
	        },{
	            name: 'PH',
	            type: 'spline',
	            yAxis: 1,
	            data: data.ph,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ''
	            }
	        },{
	            name: '实时流量',
	            type: 'spline',
	            yAxis: 2,
	            data: data.rateFlow,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' m³/H'
	            }
	        },{
	            name: '土壤湿度',
	            type: 'spline',
	            yAxis: 3,
	            data: data.soilHumidity1,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' %'
	            }
	        },{
	            name: '液位',
	            type: 'spline',
	            yAxis: 4,
	            data: data.liquidLevel,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' cm'
	            }
	        }]
	    });
	});
})
</script>
