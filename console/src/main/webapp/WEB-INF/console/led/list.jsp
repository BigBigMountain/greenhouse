<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>lyyh-right</title>
<script type="text/javascript">
	function add(){
		window.location.href="/led/toAdd.do";
	}
	function modify(p_id){
		window.location.href="/led/toUpdate.do?p_id="+p_id;
	}
	function del(p_id){
		if (confirm("你确定删除吗？")) {  
		window.location.href="/led/del.do?p_id="+p_id;
        }  
	}
	function showLed(p_id){
		$.ajax({
			url:'/led/showLed.do?p_id='+p_id,
			type:"GET",
			success:function (data) {
				alert(data);
			}
		})
	}
	function stopLed(){
		$.ajax({
			url:'/led/stopLed.do',
			type:'GET',
			success:function(data){
				alert(data);
			},
		})
	}

</script>
</head>

<body>
  	    <div class="box-positon">
        	 <h1>当前位置:LED灯设置界面</h1>
        	 <div style="float: right">
        	 	<input type="button" value="添加" onclick="add()"/>
        	 </div>
        	 <div style="float: right">
        	 	<input type="button" value="停止" onclick="stopLed()"/>
        	 </div>
        </div>
<div class="body-box">
	
<div>
	<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th width="30px">ID</th>
			<th width="70px">名称</th>
			<th width="250px">简述</th>
			<th width="30px">是否显示</th>
			<th width="40px">修改/删除</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${ledPrograms }" var="program">
		<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${program.p_id }"/></td>
			<td>${program.p_id }</td>
			<td align="center">${program.p_name }</td>
			<td align="center">${program.p_info }</td>
			<td align="center">
					<c:if test="${program.p_state==0 }">否</c:if>
					<c:if test="${program.p_state==1 }">是</c:if>
			</td>
			<td align="center">
					<input type="button" value="启动" onclick="showLed(${program.p_id})"/>||<input type="button" value="修改" onclick="modify(${program.p_id})"/>||<input type="button" value="删除" onclick="del(${program.p_id})"/>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</div>
</div>
</body>
</html>