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
			.games {
				margin: 30px 2% 200px 2%;
			}
			.gameContainer {
				display: flex;
				padding-bottom: 50px;
			}
			.game {
				flex-shrink: 1;
				flex-grow: 1;
				margin: 0 0 75px 0;
			}
			.left{
				flex-grow: 0;
				flex-shrink: 0;
				width: 300px;
				height: 400px;
				margin: 0 20px 50px 0;
			}
			.cover {
				width: 300px;
				height: 350px;
				background-size: contain;
				background-repeat: no-repeat;
			}
			.release {

			}
			.explanation {
				max-width: 650px;
				margin: 15px auto;
				color: #666;
				padding: 0 15px 0 15px;
			}
			@media all and (max-width: 800px) {
				.cover, .left {
					width: 200px;
				}
			}
			@media all and (max-width: 500px) {
				.cover, .left {
					width: 150px;
				}
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
						<li class="active"><a href="#">Game stats</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="https://github.com/NLthijs48/UTTimetabling">Code on Github</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="explanation">
			<p>
				TODO
			</p>
		</div>

		<div class="graphs">

		</div>

		<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://code.highcharts.com/stock/highstock.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script>
			<?php include("courseTypes.php"); ?>
			$(function() {
				$(".graphs").append("<div id='courseTypes'></div>");
				$('#courseTypes').highcharts({
					chart: {
						type: 'column'
					},
					title: {
						text: 'Lecture types per quarter'
					},
					/*
					subtitle: {
						text: 'things'
					},
					*/
					xAxis: {
						categories: [
							'2013-2014: Q1',
							'2013-2014: Q2',
							'2013-2014: Q3',
							'2013-2014: Q4',
							'2014-2015: Q1',
							'2014-2015: Q2',
							'2014-2015: Q3',
							'2014-2015: Q4',
							'2015-2016: Q1',
							'2015-2016: Q2',
							'2015-2016: Q3',
							'2015-2016: Q4'
						],
						crosshair: true
					},
					yAxis: {
						min: 0,
						title: {
							text: 'Activity count'
						}
					},
					tooltip: {
						headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
						pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
							'<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
						footerFormat: '</table>',
						shared: true,
						useHTML: true
					},
					plotOptions: {
						column: {
							pointPadding: 0,
							borderWidth: 0
						}
					},
					series: courseTypes
				});
			});
		</script>
	</body>
</html>
