<!DOCTYPE html>
<html>
	<head>
		<title>Data Science: TimeTabling</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<style>
			body {
				padding-top: 50px;
			}
			.graphs {
				margin: 30px 2% 200px 2%;
			}
			.timetable-chart {
				margin: 0 0 75px 0;
			}
			.explanation {
				max-width: 650px;
				margin: 15px auto;
				color: #666;
				padding: 0 15px 0 15px;
			}
			.credits {
				text-align: center;
				color: #666;
				font-weight: bold;
				font-size: 14px;
				padding-bottom: 10px;
			}
		</style>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand active" href="#">Data Science: TimeTabling</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Timetabling stats</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="https://github.com/NLthijs48/UTTimetabling">Code on Github</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="explanation">
			<p>
				For the XMLDB topic of the Data Science course at the University of Twente we have investigated the timetabling data of the years 2013-2014 and 2014-2015. The goal was to check if the scheduled activities meet certain guidelines (Key Performance Indicators) and to explore the data for interesting patterns.
			</p>
		</div>

		<div class="graphs">

		</div>
		<div class='credits'>Made by Thijs Wiefferink and Patrick van Looy</div>

		<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://code.highcharts.com/stock/highstock.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="graphs.js"></script>
	</body>
</html>
