<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="dataChart" id="soilTemperature" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
function soilTemperature(){
	$(".dataChart").hide();
	$("#soilTemperature").show();
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
		url:'/houseData/getSoilTemperatureList.do',
		data:$('#dataForm11').serialize(),
		type:"POST",
		success:function (data) {
	
		    Highcharts.chart('soilTemperature', {
		        chart: {
		            zoomType: 'x',
		            backgroundColor:'#F9D9B3'
		        },
		        title: {
		            text: '土壤温度'
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
		            name: '温度',
		            data: data
		        }]
		    });
		}
	});
}
</script>
