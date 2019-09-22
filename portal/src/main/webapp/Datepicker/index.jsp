﻿<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Datepicker日期选择器插件 | jQuery日历</title>
		<link rel="stylesheet" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
		<link href="css/foundation.min.css" rel="stylesheet" type="text/css">
		<link href="css/foundation-datepicker.css" rel="stylesheet" type="text/css">
		<style>
		.container { margin:0 auto;  max-width:960px;padding:20px;}
		</style>
	</head>
	<body>
		<div class="container">
			<h1>Datepicker日期选择器插件</h1>
			<h2>Datepicker</h2>
			<input type="text"  value="" id="demo-1">
			<h2>With timepicker</h2>
			<input type="text"  value="" id="demo-2">
			<h2>Month picker</h2>
			<div class="row collapse date" id="demo-3" data-date="07/2015" data-format="mm/yyyy" data-start-view="year" data-min-view="year">
				<div class="small-2 columns"> <span class="prefix"><i class="fa fa-calendar"></i></span> </div>
				<div class="small-10 columns">
					<input size="16" type="text" value="07/2015" readonly>
				</div>
			</div>
			<h2>Dependent datepicker</h2>
			<table class="table">
				<thead>
					<tr>
						<th>Check in:
							<input type="text" class="span2" value="" id="dpd1">
						</th>
						<th>Check out:
							<input type="text" class="span2" value="" id="dpd2">
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="js/foundation-datepicker.js"></script>
		<script src="js/locales/foundation-datepicker.zh-CN.js"></script>		
		<script>
		$('#demo-1').fdatepicker();
		$('#demo-2').fdatepicker({
			format: 'yyyy-mm-dd hh:ii',
			pickTime: true
		});
		$('#demo-3').fdatepicker();

		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
		var checkin = $('#dpd1').fdatepicker({
			onRender: function (date) {
				return date.valueOf() < now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function (ev) {
			if (ev.date.valueOf() > checkout.date.valueOf()) {
				var newDate = new Date(ev.date)
				newDate.setDate(newDate.getDate() + 1);
				checkout.update(newDate);
			}
			checkin.hide();
			$('#dpd2')[0].focus();
		}).data('datepicker');
		var checkout = $('#dpd2').fdatepicker({
			onRender: function (date) {
				return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function (ev) {
			checkout.hide();
		}).data('datepicker');
		</script>
	</body>
</html>