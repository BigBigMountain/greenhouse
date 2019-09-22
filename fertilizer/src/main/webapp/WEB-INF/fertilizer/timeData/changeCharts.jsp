<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">

function changeCharts(fertilizerId,type,unit){
	
	 $.getJSON('/fertilizer/changeCharts.do?fertilizerId='+fertilizerId+'&type='+type, function (data) {
	//	 console.log(data);
	//	 console.log(data.time)
	
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
               		format: '{value:%H:%M}'//显示的时间格式,如果不配置的话显示的是时间戳
            	},
            	//minTickInterval:1000*60*10,
	            crosshair: true
	        }],
	        
	        //y轴
	        yAxis: [{ // Secondary yAxis
	            gridLineWidth: 0,
	            title: {
	                text: type,
	                style: {
	                    color: Highcharts.getOptions().colors[0]
	                }
	            },
	            labels: {
	                format: '{value} '+unit,
	                style: {
	                    color: Highcharts.getOptions().colors[0]
	                }
	            },
	            opposite: false,	//轴的位置,ture为右边,false左边,默认是false
	           // crosshair: true
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
	            name: type,
	            type: 'spline',
	            yAxis: 0,
	            data: data.values,
	            marker: {
	                enabled: false
	            },
	           // dashStyle: 'shortdot',
	            tooltip: {
	                valueSuffix: ' '+unit
	            }
	        }]
	    });
	});
}
</script>
