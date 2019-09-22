<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="dataChart" id="co2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
function getEC(){
	
	jQuery.ajax({
		url:'/fertilizer/getECList.do',
		data:$('#fertilizerData').serialize(),
		type:"POST",
		success:function (data) {
	
		    Highcharts.chart('EC', {
		        chart: {
		            zoomType: 'x',
		            backgroundColor:'#F9D9B3'
		        },
		        title: {
		            text: 'EC值'
		        },
		        subtitle: {
		            text: document.ontouchstart === undefined ?
		                    '精量施肥机' : 'Pinch the chart to zoom in'
		        },
		        xAxis: {
		            type: 'datetime'
		        },
		        yAxis: {
		            title: {
		                text: '浓度/PPM'
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
		            name: 'EC',
		            data: data
		        }]
		    });
		}
	});
}
</script>
