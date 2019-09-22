<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(function(){
	if($("#start") == null ){
		alert("请选择起始日期");
	} else if ($("#end") == null ){
		alert("请选择结束日期");
	} else {
		changeChart("getTemperatureList","室外温度","温度/ ℃");
		Highcharts.setOptions({ global: { useUTC: false } }); 
	}
})
</script>
