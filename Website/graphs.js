var wastedTime = [];
wastedTime.push({name: "Teachers: lecture hours", stack: "Teachers", data: [16587, 13278, 13124, 11147, 14782, 13168, 13016, 13030]});
wastedTime.push({name: "Students: wasted hours", stack: "Students", data: [27592, 19920, 18388, 16129, 33164, 26061, 17258, 14936]});
wastedTime.push({name: "Teachers: wasted hours", stack: "Teachers", data: [1893, 1499, 1444, 852, 1656, 1632, 2121, 1826]});
wastedTime.push({name: "Students: lecture hours", stack: "Students", data: [115926, 94823, 89018, 88194, 130730, 106864, 70630, 64506]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='wastedTime'></div>");
	$('#wastedTime').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Lecture versus wasted hours per quarter (students and teachers)'
		},
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
			allowDecimals: false,			min: 0,
			title: {
				text: 'Hours'
			}
		},
		tooltip: {
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				pointPadding: 0,
				borderWidth: 0,
				stacking: 'normal'
			}
		},
		series: wastedTime
	});
});




var teacherCount = [];
teacherCount.push({name: "Teacher count", stack: "default", data: [469, 434, 450, 411, 496, 512, 509, 509]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='teacherCount'></div>");
	$('#teacherCount').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Teachers per quartile'
		},
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
			allowDecimals: false,			min: 0,
			title: {
				text: 'Teacher count'
			}
		},
		tooltip: {
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				pointPadding: 0,
				borderWidth: 0,
				stacking: 'normal'
			}
		},
		series: teacherCount
	});
});




var courseTypes = [];
courseTypes.push({name: "Lecture", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "Academic Research Skills", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "OVO", stack: "default", data: [1146, 725, 483, 522, 0, 0, 0, 0, 507, 332, 38]});
courseTypes.push({name: "PJB", stack: "default", data: [1274, 673, 744, 1147, 0, 0, 0, 0, 1442, 873, 30]});
courseTypes.push({name: "Self Study Supervised", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "Presentation", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "ZZA OVERIG", stack: "default", data: [0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0]});
courseTypes.push({name: "Colstruction", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "PJO", stack: "default", data: [502, 206, 469, 269, 0, 0, 0, 0, 199, 441, 4]});
courseTypes.push({name: "ZZA ORATIE", stack: "default", data: [0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0]});
courseTypes.push({name: "RESP", stack: "default", data: [124, 155, 160, 101, 0, 0, 0, 0, 130, 173, 7]});
courseTypes.push({name: "Book", stack: "default", data: [0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0]});
courseTypes.push({name: "Workshop", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0]});
courseTypes.push({name: "Diagnostic Exam", stack: "default", data: [1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "Practical", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "Exam", stack: "default", data: [1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1]});
courseTypes.push({name: "PRA", stack: "default", data: [1314, 1677, 1807, 998, 0, 0, 0, 0, 1171, 781, 122]});
courseTypes.push({name: "Self Study Unsupervised", stack: "default", data: [1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='courseTypes'></div>");
	$('#courseTypes').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Lecture types per quarter'
		},
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
			allowDecimals: false,			min: 0,
			title: {
				text: 'Activity count'
			}
		},
		tooltip: {
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				pointPadding: 0,
				borderWidth: 0,
				stacking: 'normal'
			}
		},
		series: courseTypes
	});
});




