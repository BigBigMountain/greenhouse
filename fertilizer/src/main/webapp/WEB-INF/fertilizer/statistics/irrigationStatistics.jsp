<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="/js/jquery-1.11.3.min.js"></script>
    <link href="/css/datepicker/foundation-datepicker.css" rel="stylesheet" type="text/css">
    <script src="/js/datepicker/foundation-datepicker.js"></script>

    <title>setting - FertilizerList</title>
    <style type="text/css">
        #bg {
            display: none;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: #000000;
            opacity:0.4;
            filter: alpha(opacity=40);
            -moz-opacity: 0.7;
        }
        #addDiv {
            display: none;
            position: absolute;
            top:30%;bottom:70%;margin:auto;
            left:0;right:0;
            width: 400px;
            height: 400px;
            background-color: #ffffff;
        }
        .input-text {
            width:90%;
            font-size:18px;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            var msg="${msg}";
            if(msg != ""){
                alert(msg)
            }
        });

    </script>
</head>
<body>
<div class="box-positon">
    <div class="rpos">当前位置:
    	<form action="/fertilizer/getIrrigationStatistics.do" method="post">
        <select id="fertilizerId" name="fertilizerId" style="background-color:#FEFFD0 ">
            <c:forEach items="${fertilizers }" var="fertilizer">
                <option value="${fertilizer.fertilizerId }" <c:if test="${fertilizerId == fertilizer.fertilizerId }">selected="selected"</c:if>>${fertilizer.fertilizerName }</option>
            </c:forEach>
        </select>
        <select id="valveNum" name="valveNum"  style="background-color:#FEFFD0 ">
            <option ></option>
            <c:forEach begin="1" end="64" step="1" var="item" varStatus="status" >
                <option value="${item}" <c:if test="${valveNum == item}" >selected="selected"</c:if> >${item}号阀</option>
            </c:forEach>
        </select>
       	开始时间:<input type="text" name="start" value="<fmt:formatDate type="both" value="${start }" />" id="start">
		结束时间:<input type="text" name="end" value="<fmt:formatDate type="both" value="${end }" />" id="end">
		<input type="submit" value="确定">
    	</form>
    </div>
    <div class="clear"></div>
</div>
<div class="body-box">
    <table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
        <thead class="pn-lthead">
        <tr>

            <th style="height:30px;font-size:18px;">阀号</th>
            <th style="height:30px;font-size:18px;">详情</th>
            <th style="height:30px;font-size:18px;">总灌溉量</th>

        </tr>
        </thead>
        <tbody id="tbody" class="pn-ltbody"  align="center">
        <c:forEach items="${irrigationStatistics}" var="statistics" >
            <tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
                <td align="center" style="height:30px;font-size:18px;">
                        ${statistics.valveNum }号阀
                </td>
                <td align="center" style="height:30px;font-size:18px;">
                    <table cellspacing="3" cellpadding="3" width="100%" border="1" style="color: blue">
						<tr>
							<c:forEach items="${statistics.irriRecords}" var="record" varStatus="status">
                                <td>

                                    <fmt:formatDate type="both" value="${record.start}" pattern="MM月dd号                    HH:mm:ss"/> 
                                    &nbsp;&nbsp;&nbsp;---- &nbsp;&nbsp;&nbsp; 
                                    <fmt:formatDate type="both" value="${record.end}" pattern="HH:mm:ss"/><br/>
                                    	灌溉量:&nbsp;&nbsp;&nbsp;${record.irrigationVolume}

                                </td>
						<c:if test = "${status.count%5 == 0 }" >
							</tr>
							<tr>
						</c:if >
							</c:forEach>
						</tr>


                    </table>

                </td>
                <td align="center" style="height:30px;font-size:18px;">
                    ${statistics.totalIrrigation}
                </td>

            </tr>
        </c:forEach>
        </tbody>

    </table>

</div>
<div id="bg"></div>


<script type="text/javascript">
    $('#start').fdatepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        pickTime: true
    });
    $('#end').fdatepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        pickTime: true
    });

    function mouseover(i){
        i.bgColor='#eeeeee'
    }
    function mouseout(i){
        i.bgColor='#ffffff'
    }
    function inputOver(i){
        i.style.background="#FEFFD0";
    }
    function inputOut(i){
        i.style.border="0px";
        i.style.background="none";
    }

</script>
</body>
</html>