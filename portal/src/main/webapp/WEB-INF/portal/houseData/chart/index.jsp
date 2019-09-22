<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(function(){
	$(".dataChart").hide();
	$("#temperature1").show();
	
	$.getJSON('/houseData/getIndexList.do?houseId=${houseId}', function (data) {
	
		Highcharts.setOptions({ global: { useUTC: false } }); 
	    $('#temperature1').highcharts({
	        chart: {
	            zoomType: 'x'
	        },
	        title: {
	            text: '温室数据'
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
	                text: '湿度',
	                style: {
	                    color: Highcharts.getOptions().colors[0]
	                }
	            },
	            labels: {
	                format: '{value} RH',
	                style: {
	                    color: Highcharts.getOptions().colors[0]
	                }
	            },
	            opposite: false,	//轴的位置,ture为右边,false左边,默认是false
	           // crosshair: true
	        }, { // Primary yAxis
	        	 title: {
		                text: '温度',
		                style: {
		                    color: Highcharts.getOptions().colors[1]
		                }
		            },
	            labels: {
	                format: '{value}°C',
	                style: {
	                    color: Highcharts.getOptions().colors[1]
	                }
	            },
	            opposite: false,	//轴的位置,ture为右边,false左边,默认是false
	            //crosshair: true
	        },{ // Tertiary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: '光照强度',
	                style: {
	                    color: Highcharts.getOptions().colors[2]
	                }
	            },
	            labels: {
	                format: '{value} LUX',
	                style: {
	                    color: Highcharts.getOptions().colors[2]
	                }
	            },
	            opposite: true
	        },{ // Tertiary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: 'CO2浓度',
	                style: {
	                    color: Highcharts.getOptions().colors[3]
	                }
	            },
	            labels: {
	                format: '{value} PPM',
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
	            name: '湿度',
	            type: 'spline',
	            yAxis: 0,
	            data: data.humiditys,
	            marker: {
	                enabled: false
	            },
	           // dashStyle: 'shortdot',
	            tooltip: {
	                valueSuffix: ' RH'
	            }
	        },{
	            name: '温度',
	            type: 'spline',
	            yAxis: 1,
	            data: data.temperatures,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' °C'
	            }
	        },{
	            name: '光照强度',
	            type: 'spline',
	            yAxis: 2,
	            data: data.lightings,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' LUX'
	            }
	        },{
	            name: 'CO2浓度',
	            type: 'spline',
	            yAxis: 3,
	            data: data.co2s,
	            marker: {
	                enabled: false
	            },
	            tooltip: {
	                valueSuffix: ' PPM'
	            }
	        }]
	    });
	});
})
</script>
