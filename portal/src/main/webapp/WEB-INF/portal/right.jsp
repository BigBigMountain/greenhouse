<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>lyyh-right</title>
<script type="text/javascript">
function startCollection(){
	$.ajax({
		url:"/dataCollection/startCollect.do",
		success:function(data){
			alert(data);
		}
	})
}
function stopCollection(){
	$.ajax({
		url:"/dataCollection/stopCollect.do",
		success:function(data){
			alert(data);
		}
	})
}
</script>
</head>
<body>
	<div class="box-positon">
	 	 <div class="rpos">当前位置: 首页 - 欢迎页</div>
	</div>
<div class="body-box">
        <div class="welcom-con">
        	 <div class="we-txt">
             	  <p>
             	  
		 <br />欢迎使用温室物联网管理系统！<br />
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;  
		农业物联网是将大量的传感器节点构成监控网络， 通过各种传感器采集信息， 以帮助农民及时发现问题， <br/>
		并且准确地确定发生问题的位置， 这样农业将逐渐地从以人力为中心、依赖于孤立机械的生产模式转向以<br/>
		信息和软件为中心的生产模式，从而大量使用各种自动化、智能化、远程控制的生产设备。<br/>
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		传统农业，浇水、施肥、打药，农民全凭经验、靠感觉。如今，设施农业生产基地，看到的却是另一番景象：<br/>
		瓜果蔬菜该不该浇水？施肥、打药，怎样保持精确的浓度？温度、湿度、光照、二氧化碳浓度，如何实行按需供给？<br/>
		一系列作物在不同生长周期曾被“模糊”处理的问题，都有信息化智能监控系统实时定量“精确”把关，农民只<br/>
		需按个开关，做个选择，或是完全听“指令”，就能种好菜、养好花。<br/>
                 <br />
                  </p>
             </div>
              <!--    
                  <input type="button" value="开始" onclick="startCollection()"/><br />
                  <input type="button" value="停止" onclick="stopCollection()"/> -->
             <ul class="ms">
             	<li class="wxx">访问量</li>
             	<li class="attribute">　　　系统属性</li>
             </ul>
             <div class="ms-xx">
                 <div class="xx-xx">
             	      <table width="100%" border="0" cellspacing="0" cellpadding="0">
             	       <tr>
                        <td width="20%" height="30" align="right"></td>
                        <td width="25%"><b>PV</b></td>
                        <td width="25%"><b>IP</b></td>
                        <td width="30%"><b>独立访客</b></td>
                    </tr>
                      <tr>
                        <td height="30" align="right">今日：</td>
                     	<td>0</td>
                     	<td>0</td>
                     	<td>0</td>
                    </tr>
                      <tr>
                        <td height="30" align="right">昨日：</td>
            			<td>0</td>
                     	<td>0</td>
                     	<td>0</td>
                    </tr>
                      <tr>
                        <td height="30" align="right">本周：</td>
                  		<td>0</td>
                     	<td>0</td>
                     	<td>0</td>
                    </tr>
                      <tr>
                        <td height="30" align="right">本月：</td>
                  		<td>0</td>
                     	<td>0</td>
                     	<td>0</td>
                     </tr>
                     <tr>
                        <td height="30" align="right">累计：</td>
                  		<td>0</td>
                     	<td>0</td>
                     	<td>0</td>
                     </tr>
               </table>
                 </div>
                 <div class="attribute-xx" style="float:left">
                 	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="30%" height="30" align="right">操作系统版本：</td>
                            <td height="30"><span class="black"></span></td>
                        </tr>
                          <tr>
                            <td width="30%" height="30" align="right">操作系统类型：</td>
                            <td height="30"><span class="black"></span> </td>
                        </tr>
                          <tr>
                            <td width="30%" height="30" align="right">用户、目录：</td>
                            <td height="30"><span class="black"></span></td>
                        </tr><tr>
                            <td width="30%" height="30" align="right">JAVA运行环境：</td>
                            <td height="30"><span></span></td>
                          </tr>
                          <tr>
                            <td width="30%" height="30" align="right">JAVA虚拟机：</td>
                            <td height="30"> <span></span></td>
                        </tr>
                   </table>  
               </div>

             </div>
             
  </div>
 </div>
</body>
</html>