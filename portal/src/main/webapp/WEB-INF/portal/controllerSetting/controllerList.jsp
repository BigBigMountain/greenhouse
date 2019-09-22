<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>控制器设置</title>

<script type="text/javascript">
$(function(){
	 var msg = "${msg}";
		if(msg!=null && msg != ''){
			alert(msg);
		} 
});

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 控制器  - 列表</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th >温室名称</th>
			<th >控制器IP</th>
			<th width="80px">端口号</th>
			<th >操作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${controllers }" var="controller">
		<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<form action="/controllerSetting/saveOrUpdateController.do" method="post">
				<td align="center">
					<input type="hidden" name = "id" value="${controller.id }" />
					<input type="hidden" name = "houseId" value="${controller.houseId }" />
					<input type="hidden" name = "zoneId" value="${controller.zoneId }" />
					${controller.houseName }</td>
				<td align="center"><input name = "ip" value = "${controller.ip }"/></td>
				<td align="center"><input name = "port" value = "${controller.port }"/></td>
				<td align="center"><input type="submit" value="保存"/></td>
			</form>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>

</body>
</html>