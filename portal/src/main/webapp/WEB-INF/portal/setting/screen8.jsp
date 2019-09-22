<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/portal/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>setting - screem8Setting</title>
<script type="text/javascript">
$(function(){
	 var msg = "${errMsg}";
		if(msg!=null && msg != ''){
			alert(msg);
		}
});

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 8字屏设置 </div>
	<div class="clear"></div>
</div>

<div class="body-box">
<form id="screem8Setting" action="/screem8/saveOrUpdateScreem8Setting.do" method="post">
	<fieldset style="padding:10px;margin-left:10px;border:3px solid #7F9DB9;">
		<div style="float: left;padding:10px;">
			<input type="hidden" name="id" value="${screem8.id }" >
			端口号:
			<select name="currentPortName"  id="currentPortName"  onchange="changePort()">
				<c:forEach items="${screem8.portNames }" var="portName" >
					<option value="${portName }" <c:if test="${portName == screem8.currentPortName }">selected = "selected"</c:if> >${portName }</option>
				</c:forEach>
			</select>
			
			波特率:
			<select name="baudRate" id="baudRate" onchange="changePort()">
				<c:forTokens items="1200,2400,4800,9600,19200,38400,115200" delims="," var="rate">
					<option value="${rate }" <c:if test="${rate == screem8.baudRate }">selected = "selected"</c:if> >${rate }</option>
				</c:forTokens>
			</select>
			
			发送间隔:
			<input type="text" name="intervalTime" value="${screem8.intervalTime }" />
		</div>
		<div style="float: right">
			<input type="submit"  value="保存设置"/>
		</div>
	</fieldset>

</form>

</div>
<script type="text/javascript">
function changePort(){
	$("screem8Setting").submit();
}

</script>
</body>
</html>