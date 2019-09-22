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
        	 <h1>当前位置:LED灯添加界面</h1>
        	 
        </div>
<div class="body-box">
<form id="programForm" action="/led/add.do" method="post">
<fieldset>
	<legend>节目设置</legend>
	<fieldset >
		<legend>基本设置</legend>
		节目名称:<input type="text" name="p_name" value="节目1"/>
		节目状态:<select name="p_state">
				<option value="1">有效</option>
				<option value="0">无效</option>
			  </select>
		IP地址:<input type="text" name ="p_ip" value="192.168.0.187" />
		屏幕宽度:<input type="text" name ="p_width" value="384" size="3"/>
		屏幕高度:<input type="text" name ="p_height" value="192" size="3"/>
		<%-- 屏幕颜色:<input type="text" name ="colourType" vlaue="${ledProgram.height }" /> --%>
		屏幕颜色:
		<select name="p_colourType">
			<option value="1">单色</option>
			<option value="2" selected="selected">双基色</option>
			<option value="3">七彩</option>
			<option value="4">全彩</option>
		</select>
		
	</fieldset>
	
	<div style="float: left ;width: 150px">
		<fieldset>
			<legend>表格设置</legend>
			横坐标：<input type="text" name="t_x" value="0" size="3" /><br />
			纵坐标：<input type="text" name="t_y" value="0" size="3" /><br />
			宽&nbsp;&nbsp;&nbsp;&nbsp;度：<input type="text" name="t_w" value="384" size="3" /><br />
			高&nbsp;&nbsp;&nbsp;&nbsp;度：<input type="text" name="t_h" value="192" size="3" /><br />
			
			
			偏移量：<input type="text" name="t_os" value="0" size="3" /><br />
			行&nbsp;&nbsp;&nbsp;&nbsp;数：<input type="text" name="t_r" value="6" size="3" /><br />
			列&nbsp;&nbsp;&nbsp;&nbsp;数：<input type="text" name="t_c" value="2" size="3" /><br />
			字体大小：<input type="text" name="t_s" value="16" size="3" /><br />
			轮播时间：<input type="text" name="t_t" value="4" size="3" /><br />
		
		</fieldset>
	</div>
	<div style="float:left;width: 150px">
		<fieldset>
			<legend>温室数据选择</legend>
			温度1：
			<select name="h_t1">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select><br />
			温度2：
			<select name="h_t2">
				<option value="0">否</option>
				<option value="1" >是</option>
			</select><br />
			湿度1：
			<select name="h_h1">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select><br />
			湿度2：
			<select name="h_h2">
				<option value="0">否</option>
				<option value="1" >是</option>
			</select><br />
			光&nbsp;&nbsp;照：
			<select name="h_l">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select><br />
			C&nbsp;O&nbsp;2：
			<select name="h_c">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select><br />
			土壤温度：
			<select name="h_st">
				<option value="0">否</option>
				<option value="1">是</option>
			</select><br />
			土壤湿度：
			<select name="h_sh">
				<option value="0">否</option>
				<option value="1">是</option>
			</select><br />
		</fieldset>
	</div>
	<fieldset>
		<legend>气象数据设置</legend>
		<div >
			<select name="c_t">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select>
			温度:
			位置-X:<input type="text" name="c_tx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_ty" size="3" value="123"/><br />
		</div>
		
		<div >
			<select name="c_h">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select>
			湿度:
			位置-X:<input type="text" name="c_hx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_hy" size="3" value="123"/><br />
		</div>
		<div >	
			<select name="c_l">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select>
			光照:
			位置-X:<input type="text" name="c_lx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_ly" size="3" value="123"/><br />
		</div>
		<div >
			<select name="c_rs">
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
			雨雪:
			位置-X:<input type="text" name="c_rsx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_rsy" size="3" value="123"/><br />
		</div>
		<div >
			<select name="c_rf">
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
			雨量:
			位置-X:<input type="text" name="c_rfx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_rfy" size="3" value="123"/><br />
		</div>
		<div >
			<select name="c_ws">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select>
			风速:
			位置-X:<input type="text" name="c_wsx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_wsy" size="3" value="123"/><br />
		</div>
		<div >
			<select name="c_wd">
				<option value="0">否</option>
				<option value="1" selected="selected">是</option>
			</select>
			风向:
			位置-X:<input type="text" name="c_wdx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_wdy" size="3" value="123"/><br />
		</div>
		<div >
			<select name="c_p">
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
			气压:
			位置-X:<input type="text" name="c_px" size="3" value="123"/>
			位置-Y:<input type="text" name="c_py" size="3" value="123"/><br />
		</div>
		<div >
			<select name="c_pm">
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
			PM2.5:
			位置-X:<input type="text" name="c_pmx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_pmy" size="3" value="123"/><br />
		</div>
		<div >
			<select name="c_ph">
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
			P&nbsp;&nbsp;H:
			位置-X:<input type="text" name="c_phx" size="3" value="123"/>
			位置-Y:<input type="text" name="c_phy" size="3" value="123"/><br />
		</div>
	</fieldset>
</fieldset>

<input type="submit" value="保存" />
<input type="button" value="预览" onclick="preview()"/>

</form>
</div>
<script type="text/javascript">
	function preview(){
		
		jQuery.ajax({
			url:'/led/preview.do',
			data:$('#programForm').serialize(),
			type:"POST",
			success:function (data) {
				alert(data);
			}
		})
	}
</script>
</body>
</html>