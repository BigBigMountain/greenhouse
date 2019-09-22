<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>lyyh-right</title>
</head>
<body>
  	    <div class="box-positon">
        	 <h1>当前位置:LED灯修改界面</h1>
        	 
        </div>
<div class="body-box">
<form id="programForm" action="/led/saveOrUpdate.do" method="post">
<fieldset>
	<legend>节目设置</legend>
	<fieldset >
		<legend>基本设置</legend>

		<input type="hidden" name="p_id" value="${ledProgram.p_id }"/>
		节目名称:<input type="text" name="p_name" value="${ledProgram.p_name} "/>
		节目状态:<select name="p_state">
				<option value="1" <c:if test="${ledProgram.p_state == 1 }">selected = "selected"</c:if> >有效</option>
				<option value="0" <c:if test="${ledProgram.p_state == 0 }">selected = "selected"</c:if> >无效</option>
			  </select>
		IP地址:<input type="text" name ="p_ip" value="${ledProgram.p_ip }" />
		屏幕宽度:<input type="text" name ="p_width" value="${ledProgram.p_width }" size="3"/>
		屏幕高度:<input type="text" name ="p_height" value="${ledProgram.p_height }" size="3"/>
		<%-- 屏幕颜色:<input type="text" name ="colourType" vlaue=""${ledProgram.height }"" /> --%>
		屏幕颜色:
		<select name="p_colourType">
			<option value="1" <c:if test="${ ledProgram.p_colourType == 1}">selected = "selected"</c:if> >单色</option>
			<option value="2" <c:if test="${ ledProgram.p_colourType == 2}">selected = "selected"</c:if>>双基色</option>
			<option value="3" <c:if test="${ ledProgram.p_colourType == 3}">selected = "selected"</c:if>>七彩</option>
			<option value="4" <c:if test="${ ledProgram.p_colourType == 4}">selected = "selected"</c:if>>全彩</option>
		</select>
		
		<select name="houseId">
			<option value=0  >--请选择温室--</option>
			<c:forEach items="${houseList}" var="house" >
				<option value="${house.id}" <c:if test="${house.id == houseId }">selected = "selected"</c:if> >${house.name}</option>
			</c:forEach>
		</select>
	</fieldset>
	
<!--LedTable  -->
	<div style="float: left ;width: 150px">
		<fieldset>
			<legend>表格设置</legend>
			<input type="hidden" name="t_id" value="${ledTable.t_id }"/>
			横坐标：<input type="text" name="t_x" value="${ledTable.t_x }" size="3" /><br />
			纵坐标：<input type="text" name="t_y" value="${ledTable.t_y }" size="3" /><br />
			宽&nbsp;&nbsp;&nbsp;&nbsp;度：<input type="text" name="t_w" value="${ledTable.t_w }" size="3" /><br />
			高&nbsp;&nbsp;&nbsp;&nbsp;度：<input type="text" name="t_h" value="${ledTable.t_h }" size="3" /><br />
			
			
			偏移量：<input type="text" name="t_os" value="${ledTable.t_os }" size="3" /><br />
			行&nbsp;&nbsp;&nbsp;&nbsp;数：<input type="text" name="t_r" value="${ledTable.t_r }" size="3" /><br />
			列&nbsp;&nbsp;&nbsp;&nbsp;数：<input type="text" name="t_c" value="${ledTable.t_c }" size="3" /><br />
			字体大小：<input type="text" name="t_s" value="${ledTable.t_s }" size="3" /><br />
			轮播时间：<input type="text" name="t_t" value="${ledTable.t_t }" size="3" /><br />
		
		</fieldset>
	</div>
	<%-- <div style="float:left;width: 150px">
		<fieldset>
			<legend>温室数据选择</legend>
			<input type="hidden" name="h_id" value="${ledHouse.h_id }"/>
			温度1：
			<select name="h_t1">
				<option value="0" <c:if test="${ ledHouse.h_t1 == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_t1 == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
			温度2：
			<select name="h_t2">
				<option value="0" <c:if test="${ ledHouse.h_t2 == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_t2 == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
			湿度1：
			<select name="h_h1">
				<option value="0" <c:if test="${ ledHouse.h_h1 == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_h1 == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
			湿度2：
			<select name="h_h2">
				<option value="0" <c:if test="${ ledHouse.h_h2 == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_h2 == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
			光&nbsp;&nbsp;照：
			<select name="h_l">
				<option value="0" <c:if test="${ ledHouse.h_l == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_l == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
			C&nbsp;O&nbsp;2：
			<select name="h_c">
				<option value="0" <c:if test="${ ledHouse.h_c == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_c == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
			土壤温度：
			<select name="h_st">
				<option value="0" <c:if test="${ ledHouse.h_st == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_st == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
			土壤湿度：
			<select name="h_sh">
				<option value="0" <c:if test="${ ledHouse.h_sh == 0}">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ ledHouse.h_sh == 1}">selected = "selected"</c:if>>是</option>
			</select><br />
		</fieldset>
	</div> --%>
	<fieldset>
		<legend>气象数据设置</legend>
			<input type="hidden" name="c_id" value="${ledClimatic.c_id }"/>
		<div >
			<select name="c_n">
				<option value="0" <c:if test="${ledClimatic.c_n == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_n == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			气象信息:
			位置-X:<input type="text" name="c_nx" size="3" value="${ ledClimatic.c_nx}"/>
			位置-Y:<input type="text" name="c_ny" size="3" value="${ ledClimatic.c_ny}"/><br />
		</div>
		<div >
			<select name="c_t">
				<option value="0" <c:if test="${ledClimatic.c_t == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_t == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			温度:
			位置-X:<input type="text" name="c_tx" size="3" value="${ ledClimatic.c_tx}"/>
			位置-Y:<input type="text" name="c_ty" size="3" value="${ ledClimatic.c_ty}"/><br />
		</div>
		
		<div >
			<select name="c_h">
				<option value="0" <c:if test="${ledClimatic.c_h == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_h == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			湿度:
			位置-X:<input type="text" name="c_hx" size="3" value="${ ledClimatic.c_hx}"/>
			位置-Y:<input type="text" name="c_hy" size="3" value="${ ledClimatic.c_hy}"/><br />
		</div>
		<div >	
			<select name="c_l">
				<option value="0" <c:if test="${ledClimatic.c_l == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_l == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			光照:
			位置-X:<input type="text" name="c_lx" size="3" value="${ ledClimatic.c_lx}"/>
			位置-Y:<input type="text" name="c_ly" size="3" value="${ ledClimatic.c_ly}"/><br />
		</div>
		<div >
			<select name="c_rs">
				<option value="0" <c:if test="${ledClimatic.c_rs == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_rs == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			雨雪:
			位置-X:<input type="text" name="c_rsx" size="3" value="${ ledClimatic.c_rsx}"/>
			位置-Y:<input type="text" name="c_rsy" size="3" value="${ ledClimatic.c_rsy}"/><br />
		</div>
		<div >
			<select name="c_rf">
				<option value="0" <c:if test="${ledClimatic.c_rf == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_rf == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			雨量:
			位置-X:<input type="text" name="c_rfx" size="3" value="${ ledClimatic.c_rfx}"/>
			位置-Y:<input type="text" name="c_rfy" size="3" value="${ ledClimatic.c_rfy}"/><br />
		</div>
		<div >
			<select name="c_ws">
				<option value="0" <c:if test="${ledClimatic.c_ws == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_ws == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			风速:
			位置-X:<input type="text" name="c_wsx" size="3" value="${ ledClimatic.c_wsx}"/>
			位置-Y:<input type="text" name="c_wsy" size="3" value="${ ledClimatic.c_wsy}"/><br />
		</div>
		<div >
			<select name="c_wd">
				<option value="0" <c:if test="${ledClimatic.c_wd == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_wd == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			风向:
			位置-X:<input type="text" name="c_wdx" size="3" value="${ ledClimatic.c_wdx}"/>
			位置-Y:<input type="text" name="c_wdy" size="3" value="${ ledClimatic.c_wdy}"/><br />
		</div>
		<div >
			<select name="c_p">
				<option value="0" <c:if test="${ledClimatic.c_p == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_p == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			气压:
			位置-X:<input type="text" name="c_px" size="3" value="${ ledClimatic.c_px}"/>
			位置-Y:<input type="text" name="c_py" size="3" value="${ ledClimatic.c_py}"/><br />
		</div>
		<div >
			<select name="c_pm">
				<option value="0" <c:if test="${ledClimatic.c_pm == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_pm == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			PM2.5:
			位置-X:<input type="text" name="c_pmx" size="3" value="${ ledClimatic.c_pmx}"/>
			位置-Y:<input type="text" name="c_pmy" size="3" value="${ ledClimatic.c_pmy}"/><br />
		</div>
		<div >
			<select name="c_ph">
				<option value="0" <c:if test="${ledClimatic.c_ph == 0 }">selected = "selected"</c:if> >否</option>
				<option value="1" <c:if test="${ledClimatic.c_ph == 1 }">selected = "selected"</c:if> >是</option>
			</select>
			P&nbsp;&nbsp;H:
			位置-X:<input type="text" name="c_phx" size="3" value="${ ledClimatic.c_phx}"/>
			位置-Y:<input type="text" name="c_phy" size="3" value="${ ledClimatic.c_phy}"/><br />
		</div>
	</fieldset>
</fieldset>
	

<input type="submit" value="保存" style="position: relative;"/>
<!-- <input type="button" value="预览" onclick="preview()" /> -->
</form>
</div>
<%-- <div id="picture" style="border: 1px solid red">
	<input type="file" id="file" style="display:none;" onchange="filechange(event)" />
	<img src="" width="${ledProgram.p_width }px" height="${ledProgram.p_height }px" id="img-change"/>

</div> --%>
<script type="text/javascript">
	function preview(){
		
		jQuery.ajax({
			url:'/led/preview.do',
			data:$('#programForm').serialize(),
			type:"POST",
			success:function (data) {
				alert(data);
			}
		});
	}
		
</script>
</body>
</html>