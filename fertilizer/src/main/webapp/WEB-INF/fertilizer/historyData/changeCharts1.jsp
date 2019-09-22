<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
function changeCharts(fertilizerId,type){
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
	
	$.getJSON('/fertilizer/changeCharts.do?fertilizerId='+fertilizerId+'&type='+type+'&start='+start+'&end='+end, function (data) {
//		console.log(data);
//		console.log(data.time)

		Highcharts.chart('highcharts', {
	    	chart: {
	            zoomType: 'x',
	            backgroundColor:'#F9D9B3'
	        },
	        title: {
	            text: type
	        },
	        subtitle: {
	            text: document.ontouchstart === undefined ?
	                    '蓝洋益海温室系统' : 'Pinch the chart to zoom in'
	        },
	        xAxis: {
	//            minRange: 14 * 24 * 3600000,
	            type: 'datetime',
	            categories: data.time,//时间数据数组
	            labels: {
               		format: '{value:%H:%M}'//显示的时间格式,如果不配置的话显示的是时间戳
            	},
	        },
	        yAxis: {
	            title: {
	                text: type
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
	                        [0, Highcharts.getOptions().colors[2]],
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
	            name: type,
	            data: data.values
	        }]
	    });
	});
}
</script>
