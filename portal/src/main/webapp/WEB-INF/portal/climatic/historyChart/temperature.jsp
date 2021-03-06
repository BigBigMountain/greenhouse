<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="dataChart" id="temperature" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
function temperature(){
	
	$(".dataChart").hide();
	$("#temperature").show();
	var start = $("#start").val();
	var end = $("#end").val();
	if(start == null || start == ""){
		alert("请选择起始日期");
		return false;
	}
	if(end == null || end == ""){
		alert("请选择截止日期");
		return false;
	}
	
	jQuery.ajax({
		url:'/climatic/getTemperatureList.do',
		data:$('#dataForm11').serialize(),
		type:"POST",
		success:function (data) {

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
		                        [0, Highcharts.getOptions().colors[1]],
		                        [1, Highcharts.Color(Highcharts.getOptions().colors[2]).setOpacity(0).get('rgba')]
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
		}
	});
}
</script>
