var studentCollegeHours = [];
studentCollegeHours.push({name: "4-6 college hours", stack: "default", legendIndex: "1", index: "1", data: [10677, 9879, 8232, 7340, 6309, 6882, 4458, 3464]});
studentCollegeHours.push({name: "7-8 college hours", stack: "default", legendIndex: "2", index: "2", data: [3891, 2654, 3063, 3216, 8153, 4440, 3087, 3175]});
studentCollegeHours.push({name: "<4 college hours", stack: "default", legendIndex: "0", index: "0", data: [6903, 7851, 4495, 4193, 11794, 11551, 7945, 7614]});
studentCollegeHours.push({name: ">8 college hours", stack: "default", legendIndex: "3", index: "3", data: [4598, 3116, 3639, 3994, 3205, 2802, 1731, 1489]});
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
teacherCollegeHours.push({name: "4-6 college hours", stack: "default", legendIndex: "1", index: "1", data: [1828, 1426, 1460, 1143, 1149, 1002, 968, 727]});
teacherCollegeHours.push({name: "7-8 college hours", stack: "default", legendIndex: "2", index: "2", data: [205, 146, 105, 86, 242, 177, 218, 406]});
teacherCollegeHours.push({name: "<4 college hours", stack: "default", legendIndex: "0", index: "0", data: [3611, 3326, 2937, 2255, 4178, 3788, 3420, 3119]});
teacherCollegeHours.push({name: ">8 college hours", stack: "default", legendIndex: "3", index: "3", data: [272, 145, 237, 286, 174, 173, 185, 222]});
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




