<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fertilizer/head.jsp" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>历史数据</title>
	<link rel="stylesheet" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<script src="/js/jquery-1.11.3.min.js"></script>
<!-- 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
	<style>
	.container { margin:0 auto;  max-width:960px;padding:20px;}
	</style>
</head>
<body>
<script type="text/javascript">
    $(document).ready(function(){
        $.ajax({
            type : "get",
            async: false,
            url : "http://www.practice-zhao.com/student.php?id=1",
            dataType: "jsonp",
            jsonp:"callback", //请求php的参数名
            jsonpCallback: "jsonhandle",//要执行的回调函数
            success : function(data) {
                alert("age:" + data.age + "name:" + data.name);
            }

        });
    });
</script>



</body>
</html>