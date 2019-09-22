<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="dataChart" id="ph" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
function ph(){
	$(".dataChart").hide();
	$("#ph").show();
	jQuery.ajax({
		url:'/climatic/getPHList.do',
		data:$('#dataForm11').serialize(),
		type:"POST",
		success:function (data) {
	
		    Highcharts.chart('ph', {
		        chart: {
		            zoomType: 'x',
		            backgroundColor:'#F9D9B3'
		        },
		        title: {
		            text: 'PH值'
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
		                text: 'PH值'
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
		            name: 'PH',
		            data: data
		        }]
		    });
		}
	});
}
</script>
