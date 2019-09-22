<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(function(){
	$(".dataChart").hide();
	$("#temperature").show();
	
	$.getJSON('/climatic/getTemperatureList.do', function (data) {
		Highcharts.setOptions({ global: { useUTC: false } }); 

	    Highcharts.chart('temperature', {
	        chart: {
	            zoomType: 'x',
	            backgroundColor:'#F9D9B3'
	        },
	        title: {
	            text: '室外温度'
	        },
	        subtitle: {
	            text: document.ontouchstart === undefined ?
	                    '' : 'Pinch the chart to zoom in'
	        },
	        xAxis: {
	            type: 'datetime'
	        },
	        yAxis: {
	            title: {
	                text: '温度/ ℃'
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        plotOptions: {
	            area: {
	                fillColor: {
	                    linearGradient: {
	                        x1: 0,
	                        y1: 0,
	                        x2: 0,
	                        y2: 1
	                    },
	                    stops: [
	                        [0, Highcharts.getOptions().colors[0]],
	                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(1).get('rgba')]
	                    ]
	                },
	                marker: {
	                    radius: 2
	                },
	                lineWidth: 1,
	                states: {
	                    hover: {
	                        lineWidth: 1
	                    }
	                },
	                threshold: null
	            }
	        },
	
	        series: [{
	            type: 'area',
	            name: '温度',
	            data: data
	        }]
	    });
	});
})
</script>
