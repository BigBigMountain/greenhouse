<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(function(){
	$.getJSON('/fertilizer/getIndexList.do?fertilizerId=${fertilizerId}', function (data) {
	
		Highcharts.setOptions({ global: { useUTC: false } }); 
	    $('#fertilizerData').highcharts({
	        chart: {
	            zoomType: 'x'
	        },
	        title: {
	            text: '数据曲线'
	        },
	        
	        xAxis: [{
	        	type: 'datetime',//时间格式
	        	categories: data.timeSpan,//时间数据数组
	        	labels: {
               		format: '{value:%H:%M}'//显示的时间格式,如果不配置的话显示的是时间戳
            	},
            	//minTickInterval:1000*60*10,
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
	                format: '{value} 单位是啥?',
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
	                format: '{value} m³/h',
	                style: {
	                    color: Highcharts.getOptions().colors[2]
	                }
	            },
	            opposite: true
	        },{ // Tertiary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: '液位',
	                style: {
	                    color: Highcharts.getOptions().colors[3]
	                }
	            },
	            labels: {
	                format: '{value} mm',
	                style: {
	                    color: Highcharts.getOptions().colors[3]
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
	            data: data.ECs,
	            marker: {
	                enabled: false
	            },
	           // dashStyle: 'shortdot',
	            tooltip: {
	                valueSuffix: ' 不知道单位'
	            }
	        },{
	            name: 'PH',
	            type: 'spline',
	            yAxis: 1,
	            data: data.PHs,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' '
	            }
	        },{
	            name: '实时流量',
	            type: 'spline',
	            yAxis: 2,
	            data: data.flows,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' m³/h'
	            }
	        },{
	            name: '液位',
	            type: 'spline',
	            yAxis: 3,
	            data: data.levels,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' mm'
	            }
	        },{
	            name: '土壤湿度',
	            type: 'spline',
	            yAxis: 3,
	            data: data.soilHumiditys,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' %'
	            }
	        }]
	    });
	});
})
</script>
