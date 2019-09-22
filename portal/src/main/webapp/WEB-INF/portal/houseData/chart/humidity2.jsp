<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="dataChart" id="humidity2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
function humidity2(){
	$(".dataChart").hide();
	$("#humidity2").show();
	jQuery.ajax({
		url:'/houseData/getHumidity2List.do',
		data:$('#dataForm11').serialize(),
		type:"POST",
		success:function (data) {
	
		    Highcharts.chart('humidity2', {
		        chart: {
		            zoomType: 'x',
		            backgroundColor:'#F9D9B3'
		        },
		        title: {
		            text: '室内湿度2'
		        },
		        subtitle: {
		            text: document.ontouchstart === undefined ?
		                    '蓝洋益海温室系统' : 'Pinch the chart to zoom in'
		        },
		        xAxis: {
		            type: 'datetime'
		        },
		        yAxis: {
		            title: {
		                text: '湿度/ RH'
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
		                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
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
		            name: '湿度',
		            data: data
		        }]
		    });
		}
	});
}
</script>
