var studentCollegeHours = [];
studentCollegeHours.push({name: "10 college hours", stack: "default", legendIndex: "9", index: "9", data: [1496, 787, 963, 1464, 2381, 2582, 1432, 1140]});
studentCollegeHours.push({name: "9 college hours", stack: "default", legendIndex: "8", index: "8", data: [3086, 2304, 2661, 2501, 824, 109, 298, 345]});
studentCollegeHours.push({name: "2 college hours", stack: "default", legendIndex: "1", index: "1", data: [5721, 6475, 3671, 3235, 5714, 5407, 3453, 3310]});
studentCollegeHours.push({name: "6 college hours", stack: "default", legendIndex: "5", index: "5", data: [2516, 2236, 1815, 1392, 1457, 1128, 931, 923]});
studentCollegeHours.push({name: "3 college hours", stack: "default", legendIndex: "2", index: "2", data: [503, 688, 504, 282, 1315, 1977, 1550, 1736]});
studentCollegeHours.push({name: "5 college hours", stack: "default", legendIndex: "4", index: "4", data: [678, 322, 441, 375, 2642, 2952, 2001, 1235]});
studentCollegeHours.push({name: "7 college hours", stack: "default", legendIndex: "6", index: "6", data: [1707, 1240, 1248, 1613, 3707, 1487, 1359, 1195]});
studentCollegeHours.push({name: "1 college hour or less", stack: "default", legendIndex: "0", index: "0", data: [267, 218, 83, 240, 270, 365, 228, 184]});
studentCollegeHours.push({name: "11 or more college hours", stack: "default", legendIndex: "10", index: "10", data: [16, 25, 15, 29, 0, 111, 1, 4]});
studentCollegeHours.push({name: "4 college hours", stack: "default", legendIndex: "3", index: "3", data: [7895, 7791, 6213, 6009, 6705, 6604, 4240, 3690]});
studentCollegeHours.push({name: "8 college hours", stack: "default", legendIndex: "7", index: "7", data: [2184, 1414, 1815, 1603, 4446, 2953, 1728, 1980]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='studentCollegeHours'></div>");
	$('#studentCollegeHours').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'College hours per quartile'
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
				text: 'Category count, each activity of a student set'
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
		series: studentCollegeHours
	});
});




var teacherCollegeHours = [];
teacherCollegeHours.push({name: "10 college hours", stack: "default", legendIndex: "9", index: "9", data: [103, 54, 134, 197, 126, 150, 160, 195]});
teacherCollegeHours.push({name: "9 college hours", stack: "default", legendIndex: "8", index: "8", data: [169, 91, 103, 89, 48, 20, 25, 25]});
teacherCollegeHours.push({name: "2 college hours", stack: "default", legendIndex: "1", index: "1", data: [3382, 3067, 2705, 2064, 3188, 2788, 2419, 2204]});
teacherCollegeHours.push({name: "6 college hours", stack: "default", legendIndex: "5", index: "5", data: [100, 81, 62, 35, 112, 91, 102, 85]});
teacherCollegeHours.push({name: "3 college hours", stack: "default", legendIndex: "2", index: "2", data: [117, 139, 80, 81, 270, 425, 305, 424]});
teacherCollegeHours.push({name: "5 college hours", stack: "default", legendIndex: "4", index: "4", data: [14, 39, 23, 10, 171, 164, 142, 131]});
teacherCollegeHours.push({name: "7 college hours", stack: "default", legendIndex: "6", index: "6", data: [160, 111, 74, 55, 56, 67, 158, 250]});
teacherCollegeHours.push({name: "1 college hour or less", stack: "default", legendIndex: "0", index: "0", data: [70, 67, 117, 57, 123, 129, 59, 61]});
teacherCollegeHours.push({name: "11 or more college hours", stack: "default", legendIndex: "10", index: "10", data: [0, 0, 0, 0, 0, 3, 0, 2]});
teacherCollegeHours.push({name: "4 college hours", stack: "default", legendIndex: "3", index: "3", data: [1756, 1359, 1410, 1151, 1463, 1193, 1361, 941]});
teacherCollegeHours.push({name: "8 college hours", stack: "default", legendIndex: "7", index: "7", data: [45, 35, 31, 31, 186, 110, 60, 156]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='teacherCollegeHours'></div>");
	$('#teacherCollegeHours').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Teacher hours per quartile'
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
				text: 'Category count, each activity of a teacher'
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
		series: teacherCollegeHours
	});
});




var wastedTime = [];
wastedTime.push({name: "Teachers: wasted hours", stack: "Teachers", legendIndex: "3", index: "3", data: [1893, 1499, 1444, 852, 1656, 1632, 2121, 1826]});
wastedTime.push({name: "Students: lecture hours", stack: "Students", legendIndex: "0", index: "0", data: [115926, 94823, 89018, 88194, 130730, 106864, 70630, 64506]});
wastedTime.push({name: "Teachers: lecture hours", stack: "Teachers", legendIndex: "2", index: "2", data: [16587, 13278, 13124, 11147, 14782, 13168, 13016, 13030]});
wastedTime.push({name: "Students: wasted hours", stack: "Students", legendIndex: "1", index: "1", data: [27592, 19920, 18388, 16129, 33164, 26061, 17258, 14936]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='wastedTime'></div>");
	$('#wastedTime').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Lecture versus wasted hours per quartile (students and teachers)'
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




var simpleCounts = [];
simpleCounts.push({name: "Course count", stack: "Courses", legendIndex: "0", index: "0", data: [440, 579, 594, 571, 6, 0, 0, 0]});
simpleCounts.push({name: "Student set count", stack: "Students", legendIndex: "0", index: "0", data: [705, 803, 1073, 1121, 795, 1017, 1075, 980]});
simpleCounts.push({name: "Teacher count", stack: "Teachers", legendIndex: "0", index: "0", data: [469, 434, 450, 411, 496, 512, 509, 509]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='simpleCounts'></div>");
	$('#simpleCounts').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Teachers, studentsets and courses per quartile'
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
				text: 'Count'
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
		series: simpleCounts
	});
});




var collegeTime = [];
collegeTime.push({name: "Friday evening classes", stack: "default", legendIndex: "0", index: "0", data: [0, 1, 0, 0, 0, 8, 0, 2]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='collegeTime'></div>");
	$('#collegeTime').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Fiday evening classes per quartile'
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
				text: 'Count of Friday evening classes'
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
		series: collegeTime
	});
});




var courseTypes = [];
courseTypes.push({name: "Tutorial", stack: "default", legendIndex: "0", index: "0", data: [4871, 3790, 2981, 2337, 0, 0, 0, 0, 2107, 1230, 107]});
courseTypes.push({name: "Lecture", stack: "default", legendIndex: "0", index: "0", data: [5546, 4140, 4767, 4393, 0, 0, 0, 0, 3388, 1831, 323]});
courseTypes.push({name: "Project (supervised)", stack: "default", legendIndex: "0", index: "0", data: [1274, 673, 744, 1147, 0, 0, 0, 0, 1442, 873, 30]});
courseTypes.push({name: "Academic Research Skills", stack: "default", legendIndex: "0", index: "0", data: [139, 123, 19, 34, 0, 0, 0, 0, 14, 19, 5]});
courseTypes.push({name: "Question and Answer", stack: "default", legendIndex: "0", index: "0", data: [124, 155, 160, 101, 0, 0, 0, 0, 130, 173, 7]});
courseTypes.push({name: "Presentation", stack: "default", legendIndex: "0", index: "0", data: [288, 158, 130, 256, 0, 0, 0, 0, 173, 135, 28]});
courseTypes.push({name: "Self Study (unsupervised)", stack: "default", legendIndex: "0", index: "0", data: [358, 745, 307, 233, 0, 0, 0, 0, 647, 341, 6]});
courseTypes.push({name: "Colstruction", stack: "default", legendIndex: "0", index: "0", data: [1091, 861, 801, 781, 0, 0, 0, 0, 861, 469, 69]});
courseTypes.push({name: "ZZA ORATIE", stack: "default", legendIndex: "0", index: "0", data: [0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0]});
courseTypes.push({name: "Book", stack: "default", legendIndex: "0", index: "0", data: [0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 0]});
courseTypes.push({name: "Partial Exam", stack: "default", legendIndex: "0", index: "0", data: [116, 69, 107, 164, 1, 0, 0, 0, 135, 31, 3]});
courseTypes.push({name: "Workshop", stack: "default", legendIndex: "0", index: "0", data: [251, 80, 95, 15, 0, 0, 0, 0, 69, 57, 0]});
courseTypes.push({name: "Project (unsupervised)", stack: "default", legendIndex: "0", index: "0", data: [502, 206, 469, 269, 0, 0, 0, 0, 199, 441, 4]});
courseTypes.push({name: "Other (non-study related)", stack: "default", legendIndex: "0", index: "0", data: [0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0]});
courseTypes.push({name: "Practical", stack: "default", legendIndex: "0", index: "0", data: [1314, 1677, 1807, 998, 0, 0, 0, 0, 1171, 781, 122]});
courseTypes.push({name: "Exam", stack: "default", legendIndex: "0", index: "0", data: [820, 835, 1522, 1244, 9, 0, 0, 0, 643, 726, 217]});
courseTypes.push({name: "Self Study (supervised)", stack: "default", legendIndex: "0", index: "0", data: [838, 749, 136, 143, 0, 0, 0, 0, 273, 69, 10]});
courseTypes.push({name: "Other", stack: "default", legendIndex: "0", index: "0", data: [1146, 725, 483, 522, 0, 0, 0, 0, 507, 332, 38]});
$(function() {
	$(".graphs").append("<div class='timetable-chart' id='courseTypes'></div>");
	$('#courseTypes').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Lecture types per quartile'
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




