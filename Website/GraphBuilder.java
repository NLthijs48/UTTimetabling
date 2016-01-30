package me.wiefferink.timetabling;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GraphBuilder {

    // Paths
    public static final String DATA_SOURCE = "C:\\Coding\\DataScience\\UTTimetabling\\CodeAndData\\XMLprocessing\\All\\";
	public static final String DATA_SOURCE_UT_1314 = "C:\\Coding\\DataScience\\UTTimetabling\\CodeAndData\\XMLprocessing\\UT 2013-2014\\";
	public static final String DATA_SOURCE_UT_1415 = "C:\\Coding\\DataScience\\UTTimetabling\\CodeAndData\\XMLprocessing\\UT 2014-2015\\";
	public static final String DATA_TARGET = "C:\\Coding\\DataScience\\UTTimetabling\\Website\\";

    // XML databases
	public static final String UT_ACTIVITIES = "data1.xml";
	public static final String UT_COURSES = "courses1.xml";
	public static final String UT_STUDENT_TIMES = "data3.xml";
	public static final String UT_TEACHER_TIMES = "data5.xml";

	// Dates and times
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
	private List<String> quartiles = Arrays.asList("2013-08-20", "2013-11-09", "2014-01-30", "2014-04-16", "2014-08-20", "2014-11-09", "2015-01-30", "2015-04-16", "2015-08-20", "2015-11-09", "2016-01-30", "2016-04-16");
	private List<Long> quartileDates;
	private long endOf1314 = 1408485600000L;
	private long endOf1415 = 1429135200000L;
	private long endOf1516 = 1460000000000L;

	public GraphBuilder() {
		Calendar startTime = Calendar.getInstance();
		quartileDates = new ArrayList<>();
		try {
			for (String quartile : quartiles) {
				quartileDates.add(format.parse(quartile).getTime());
			}
		} catch (ParseException e) {
			error("Could not parse quartile dates:");
			e.printStackTrace(System.err);
		}

		makeOutput();
		Calendar endTime = Calendar.getInstance();
		progress("Finished in "+(((double)(endTime.getTimeInMillis()-startTime.getTimeInMillis()))/1000.0)+" seconds");
	}

	/**
	 * Gather all statistics and print graphs for them
	 */
	public void makeOutput() {
		File output = new File(DATA_TARGET, "graphs.js");
		try {
			output.getParentFile().mkdirs();
			output.createNewFile();
		} catch (IOException e) {
			error("Something went wrong while creating the result file: " + output.getAbsolutePath());
			e.printStackTrace(System.err);
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
			makeStudentCollegeHoursGraph(writer);
			makeTeacherCollegeHoursGraph(writer);
			makeWastedCollegeHoursGraph(writer);
			makeSimpleCountGraphs(writer);
			makeFridayEveningClassesGraph(writer);
			makeCourseTypeGraph(writer);
		} catch (IOException e) {
			error("Something went wrong while writing the result file: " + output.getAbsolutePath());
			e.printStackTrace(System.err);
		}
	}

	public void makeCourseTypeGraph(BufferedWriter writer) {
		progress("Creating graph with college types");
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		// Guesses for translations of the internal codes
		Map<String, String> translate = new HashMap<>();
		translate.put("COL", "Colstruction");
		translate.put("WC", "Tutorial");
		translate.put("HC", "Lecture");
		translate.put("TOETS", "Exam");
		translate.put("BOEK", "Book");
		translate.put("PRS", "Presentation");
		translate.put("WG", "Workshop");
		translate.put("ZMB", "Self Study (supervised)");
		translate.put("ZGB", "Self Study (unsupervised)");
		translate.put("ASM", "Academic Research Skills");
		translate.put("DTOETS", "Partial Exam");
		translate.put("PRA", "Practical");
		translate.put("OVO", "Other");
		translate.put("ZZA OVERIG", "Other (non-study related)");
		translate.put("PJB", "Project (supervised)");
		translate.put("PJO", "Project (unsupervised)");
		translate.put("RESP", "Question and Answer");
		// translate.put("ZZA ORATIE", ""); No clue what this is supposed to be

		Document document = loadDocument(UT_COURSES);
		if(document == null) {
			return;
		}
		for(long date : quartileDates) {
			result.put(date, new TreeMap<String, Long>());
		}
		NodeList activities = document.getDocumentElement().getElementsByTagName("activity");
		for(int i=0; i<activities.getLength(); i++) {
			Node activity = activities.item(i);
			long quartileKey = getQuartileKey(getNodeValue(activity, "dateGiven"));
			if(quartileKey == -1) {
				//error("wrong date: "+ getNodeValue(activity, "dateGiven"));
				continue;
			}
			Map<String, Long> quartile = result.get(quartileKey);
			if(quartile == null) {
				quartile = new TreeMap<>();
				result.put(quartileKey, quartile);
			}
			String type = getNodeValue(activity, "type");
			if (translate.containsKey(type)) {
				type = translate.get(type);
			}
			Long count = quartile.get(type);
			if(count == null) {
				count = 0L;
			}
			count++;
			quartile.put(type, count);
		}
		printBarGraph(writer, result, "courseTypes", "Lecture types per quartile", "Activity count", endOf1516, 1);
	}

	public void makeWastedCollegeHoursGraph(BufferedWriter writer) {
		progress("Creating graph with given and wasted college hours");
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		// Only the first 8
		for (int i=0; i<8; i++) {
			result.put(quartileDates.get(i), new TreeMap<String, Long>());
		}
		List<String> types = Arrays.asList("teacher", "set");
		List<String> names = Arrays.asList("Teachers", "Students");
		List<Long> orders = Arrays.asList(1L, 0L);
		List<Document> documents = Arrays.asList(loadDocument(UT_TEACHER_TIMES), loadDocument(UT_STUDENT_TIMES));
		for(int t=0; t<types.size(); t++) {
			Document document = documents.get(t);
			if (document == null) {
				error("Incorrect document at "+t);
				return;
			}
			NodeList elements = document.getDocumentElement().getElementsByTagName(types.get(t));
			for (int i = 0; i < elements.getLength(); i++) {
				Node activity = elements.item(i);
				long quartileKey = getQuartileKey(getNodeValue(activity.getParentNode(), "dategiven"));
				if (quartileKey == -1) {
					error("wrong date: " + getNodeValue(activity.getParentNode(), "dategiven"));
					continue;
				}
				Map<String, Long> quartile = result.get(quartileKey);
				if (quartile == null) {
					quartile = new TreeMap<>();
					result.put(quartileKey, quartile);
				}
				long contactMinutes = asLong(getNodeValue(activity, "contactminutes"));
				long collegeMinutes = asLong(getNodeValue(activity, "collegeminutes"));
				if (contactMinutes == -1 || collegeMinutes == -1) {
					error("  incorrect contactminutes: " + getNodeValue(activity, "contactminutes") + " or collegeminutes: " + getNodeValue(activity, "collegeminutes"));
					continue;
				}
				Long count = quartile.get(names.get(t) + ": lecture hours|"+(orders.get(t)*2)+"@"+names.get(t));
				if (count == null) {
					count = 0L;
				}
				count += contactMinutes;
				quartile.put(names.get(t) + ": lecture hours|"+(orders.get(t)*2)+"@" + names.get(t), count);
				count = quartile.get(names.get(t) + ": wasted hours|"+(orders.get(t)*2+1)+"@" + names.get(t));
				if (count == null) {
					count = 0L;
				}
				if (collegeMinutes - contactMinutes < 0) {
					error("incorrect matchup: cm=" + collegeMinutes + ", contM=" + contactMinutes);
					continue;
				}
				count += collegeMinutes - contactMinutes;
				quartile.put(names.get(t)+": wasted hours|"+(orders.get(t)*2+1)+"@" + names.get(t), count);
			}
		}
		printBarGraph(writer, result, "wastedTime", "Lecture versus wasted hours per quartile (students and teachers)", "Hours", endOf1415, 60);
	}

	public void makeSimpleCountGraphs(BufferedWriter writer) {
		progress("Creating graph with simple counts");
		// Teachers
		Document document = loadDocument(UT_TEACHER_TIMES);
		if (document == null) {
			return;
		}
		NodeList activities = document.getDocumentElement().getElementsByTagName("teacher");
		Map<Long, Set<String>> teachers = new TreeMap<>();
		for (int i = 0; i < activities.getLength(); i++) {
			Node activity = activities.item(i);
			long quartileKey = getQuartileKey(getNodeValue(activity.getParentNode(), "dategiven"));
			if (quartileKey == -1) {
				error("wrong date: "+ getNodeValue(activity.getParentNode(), "dategiven"));
				continue;
			}
			String name = getNodeValue(activity, "name");
			Set<String> teachSet = teachers.get(quartileKey);
			if(teachSet == null) {
				teachSet = new HashSet<>();
				teachers.put(quartileKey, teachSet);
			}
			teachSet.add(name);
		}
		// Student sets
		document = loadDocument(UT_STUDENT_TIMES);
		if (document == null) {
			return;
		}
		NodeList sets = document.getDocumentElement().getElementsByTagName("set");
		Map<Long, Set<String>> studentSets = new TreeMap<>();
		for (int i = 0; i < sets.getLength(); i++) {
			Node activity = sets.item(i);
			long quartileKey = getQuartileKey(getNodeValue(activity.getParentNode(), "dategiven"));
			if (quartileKey == -1) {
				error("wrong date: "+ getNodeValue(activity.getParentNode(), "dategiven"));
				continue;
			}
			String name = getNodeValue(activity, "name");
			Set<String> studentSet = studentSets.get(quartileKey);
			if (studentSet == null) {
				studentSet = new HashSet<>();
				studentSets.put(quartileKey, studentSet);
			}
			studentSet.add(name);
		}
		// Activities
		document = loadDocument(UT_COURSES);
		if (document == null) {
			return;
		}
		NodeList dataOne = document.getDocumentElement().getElementsByTagName("activity");
		Map<Long, Set<String>> activitiesCount = new TreeMap<>();
		for (int i = 0; i < dataOne.getLength(); i++) {
			Node activity = dataOne.item(i);
			long quartileKey = getQuartileKey(getNodeValue(activity, "dateGiven"));
			if (quartileKey == -1) {
				//error("wrong date: " + getNodeValue(activity, "dateGiven"));
				continue;
			}
			String name = getNodeValue(activity, "code");
			Set<String> courses = activitiesCount.get(quartileKey);
			if (courses == null) {
				courses = new HashSet<>();
				activitiesCount.put(quartileKey, courses);
			}
			courses.add(name);
		}
		// Get results
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		for(Map.Entry<Long, Set<String>> teachPart : teachers.entrySet()) {
			Map<String, Long> map = result.get(teachPart.getKey());
			if (map == null) {
				map = new TreeMap<>();
			}
			map.put("Teacher count@Teachers", (long)teachPart.getValue().size());
			result.put(teachPart.getKey(), map);
		}
		for (Map.Entry<Long, Set<String>> studentSetPart : studentSets.entrySet()) {
			Map<String, Long> map = result.get(studentSetPart.getKey());
			if(map == null) {
				map = new TreeMap<>();
			}
			map.put("Student set count@Students", (long) studentSetPart.getValue().size());
			result.put(studentSetPart.getKey(), map);
		}
		for (Map.Entry<Long, Set<String>> courseSet : activitiesCount.entrySet()) {
			Map<String, Long> map = result.get(courseSet.getKey());
			if (map == null) {
				map = new TreeMap<>();
			}
			map.put("Course count@Courses", (long) courseSet.getValue().size());
			result.put(courseSet.getKey(), map);
		}
		printBarGraph(writer, result, "simpleCounts", "Teachers, studentsets and courses per quartile", "Count", endOf1415, 1);
	}

	public void makeStudentCollegeHoursGraph(BufferedWriter writer) {
		progress("Creating graph with number of student college hours a day");
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		Document document = loadDocument(UT_STUDENT_TIMES);
		if (document == null) {
			return;
		}
		for (long date : quartileDates) {
			result.put(date, new TreeMap<String, Long>());
		}
		NodeList sets = document.getDocumentElement().getElementsByTagName("set");
		for (int i = 0; i < sets.getLength(); i++) {
			Node set = sets.item(i);
			long quartileKey = getQuartileKey(getNodeValue(set.getParentNode(), "dategiven"));
			if (quartileKey == -1) {
				error("wrong date: "+ getNodeValue(set.getParentNode(), "dategiven"));
				continue;
			}
			Map<String, Long> quartile = result.get(quartileKey);
			if (quartile == null) {
				quartile = new TreeMap<>();
				result.put(quartileKey, quartile);
			}
			long contactMinutes = asLong(getNodeValue(set, "contactminutes"));
			if(contactMinutes == -1) {
				error("  Incorrect contactminutes: "+ getNodeValue(set, "contactminutes"));
				continue;
			}
			String type;
			if (contactMinutes < 75) {
				type = "1 college hour or less|0";
			} else if (contactMinutes < 130) {
				type = "2 college hours|1";
			} else if (contactMinutes < 180) {
				type = "3 college hours|2";
			} else if (contactMinutes < 230) {
				type = "4 college hours|3";
			} else if (contactMinutes < 275) {
				type = "5 college hours|4";
			} else if (contactMinutes < 330) {
				type = "6 college hours|5";
			} else if (contactMinutes < 380) {
				type = "7 college hours|6";
			} else if (contactMinutes < 435) {
				type = "8 college hours|7";
			} else if (contactMinutes < 485) {
				type = "9 college hours|8";
			} else if (contactMinutes < 540) {
				type = "10 college hours|9";
			} else {
				type = "11 or more college hours|10";
			}
			Long count = quartile.get(type);
			if (count == null) {
				count = 0L;
			}
			count++;
			quartile.put(type, count);
		}
		printBarGraph(writer, result, "studentCollegeHours", "College hours per quartile", "Category count, each activity of a student set", endOf1415, 1);
	}

	public void makeTeacherCollegeHoursGraph(BufferedWriter writer) {
		progress("Creating graph with number of teacher hours a day");
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		Document document = loadDocument(UT_TEACHER_TIMES);
		if (document == null) {
			return;
		}
		for (long date : quartileDates) {
			result.put(date, new TreeMap<String, Long>());
		}
		NodeList sets = document.getDocumentElement().getElementsByTagName("teacher");
		for (int i = 0; i < sets.getLength(); i++) {
			Node set = sets.item(i);
			long quartileKey = getQuartileKey(getNodeValue(set.getParentNode(), "dategiven"));
			if (quartileKey == -1) {
				error("wrong date: " + getNodeValue(set.getParentNode(), "dategiven"));
				continue;
			}
			Map<String, Long> quartile = result.get(quartileKey);
			if (quartile == null) {
				quartile = new TreeMap<>();
				result.put(quartileKey, quartile);
			}
			long contactMinutes = asLong(getNodeValue(set, "contactminutes"));
			if (contactMinutes == -1) {
				error("  Incorrect contactminutes: " + getNodeValue(set, "contactminutes"));
				continue;
			}
			String type;
			if(contactMinutes < 75) {
				type = "1 college hour or less|0";
			} else if(contactMinutes < 130) {
				type = "2 college hours|1";
			} else if (contactMinutes < 180) {
				type = "3 college hours|2";
			} else if (contactMinutes < 230) {
				type = "4 college hours|3";
			} else if (contactMinutes < 275) {
				type = "5 college hours|4";
			} else if (contactMinutes < 330) {
				type = "6 college hours|5";
			} else if (contactMinutes < 380) {
				type = "7 college hours|6";
			} else if (contactMinutes < 435) {
				type = "8 college hours|7";
			} else if (contactMinutes < 485) {
				type = "9 college hours|8";
			} else if (contactMinutes < 540) {
				type = "10 college hours|9";
			} else {
				type = "11 or more college hours|10";
			}
			Long count = quartile.get(type);
			if (count == null) {
				count = 0L;
			}
			count++;
			quartile.put(type, count);
		}
		printBarGraph(writer, result, "teacherCollegeHours", "Teacher hours per quartile", "Category count, each activity of a teacher", endOf1415, 1);
	}

	public void makeFridayEveningClassesGraph(BufferedWriter writer) {
		progress("Creating graph with number of Friday evening classes");
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		Document document = loadDocument(UT_ACTIVITIES);
		if (document == null) {
			return;
		}
		for (long date : quartileDates) {
			result.put(date, new TreeMap<String, Long>());
		}
		NodeList activities = document.getDocumentElement().getElementsByTagName("activity");
		for (int i = 0; i < activities.getLength(); i++) {
			Node set = activities.item(i);
			if(!"Friday".equals(getNodeValue(set.getParentNode(), "daygiven"))) {
				continue;
			}
			String endTime = getNodeValue(set, "endtime");
			Calendar date = Calendar.getInstance();
			try {
				Date d = timeFormat.parse(endTime);
				date.setTime(d);
			} catch (ParseException e) {
				error("  Incorrect time: "+endTime);
				continue;
			}
			// Filter evening classes (later as 18:30)
			if(date.get(Calendar.HOUR_OF_DAY) < 17 || (date.get(Calendar.HOUR_OF_DAY) == 17 && date.get(Calendar.MINUTE) <= 30)) {
				continue;
			}

			long quartileKey = getQuartileKey(getNodeValue(set.getParentNode(), "dategiven"));
			if (quartileKey == -1) {
				error("wrong date: " + getNodeValue(set.getParentNode(), "dateGiven"));
				continue;
			}
			Map<String, Long> quartile = result.get(quartileKey);
			if (quartile == null) {
				quartile = new TreeMap<>();
				result.put(quartileKey, quartile);
			}
			Long count = quartile.get("Friday evening classes");
			if (count == null) {
				count = 0L;
			}
			count++;
			quartile.put("Friday evening classes", count);
		}
		printBarGraph(writer, result, "collegeTime", "Fiday evening classes per quartile", "Count of Friday evening classes", endOf1415, 1);
	}

	/**
	 * Print a bar graph
	 * @param writer File
	 * @param data The data
	 * @param type Unique identifier
	 * @param title Title
     * @param vAxis Vertical axis
	 * @param scale Divide results by this number
     */
	public void printBarGraph(BufferedWriter writer, Map<Long, Map<String, Long>> data, String type, String title, String vAxis, long until, double scale) {
		progress("  Printing bar graph: "+type);
		try {
			writer.write("var " + type + " = [];\n");
			// Gather all different bar types
			Set<String> barTypes = new HashSet<>();
			for(Map<String, Long> timeData : data.values()) {
				barTypes.addAll(timeData.keySet());
			}
			// Print data serie for each bar type
			for(String compoundBarType : barTypes) {
				String[] parts = compoundBarType.split("@");
				String stack = "default";
				String[] parts2 = parts[0].split("\\|");
				long legendIndex = 0;
				String barType = parts2[0];
				if(parts2.length > 1) {
					legendIndex = asLong(parts2[1]);
				}
				if(parts.length >= 2) {
					stack = parts[1];
				}
				writer.write(type + ".push({name: \"" + barType + "\", stack: \""+stack+"\", legendIndex: \""+legendIndex+"\", index: \""+legendIndex+"\", data: [");
				boolean first = true;
				for (Map.Entry<Long, Map<String, Long>> timeData : data.entrySet()) {
					if(timeData.getKey() > until) {
						continue;
					}
					Long value = timeData.getValue().get(compoundBarType);
					// Fill in missing values
					if (value == null) {
						value = 0L;
					}
					value = (long)(((double)value)/scale);
					if (first) {
						first = false;
						writer.write(value + "");
					} else {
						writer.write(", " + value);
					}
				}
				writer.write("]});\n");
			}
			// Print chart options
			writer.write("$(function() {\n" +
					"\t$(\".graphs\").append(\"<div class='timetable-chart' id='" + type + "'></div>\");\n" +
					"\t$('#" + type + "').highcharts({\n" +
					"\t\tchart: {\n" +
					"\t\t\ttype: 'column'\n" +
					"\t\t},\n" +
					"\t\ttitle: {\n" +
					"\t\t\ttext: '" + title + "'\n" +
					"\t\t},\n" +
					"\t\txAxis: {\n" +
					"\t\t\tcategories: [\n" +
					"\t\t\t\t'2013-2014: Q1',\n" +
					"\t\t\t\t'2013-2014: Q2',\n" +
					"\t\t\t\t'2013-2014: Q3',\n" +
					"\t\t\t\t'2013-2014: Q4',\n" +
					"\t\t\t\t'2014-2015: Q1',\n" +
					"\t\t\t\t'2014-2015: Q2',\n" +
					"\t\t\t\t'2014-2015: Q3',\n" +
					"\t\t\t\t'2014-2015: Q4',\n" +
					"\t\t\t\t'2015-2016: Q1',\n" +
					"\t\t\t\t'2015-2016: Q2',\n" +
					"\t\t\t\t'2015-2016: Q3',\n" +
					"\t\t\t\t'2015-2016: Q4'\n" +
					"\t\t\t],\n" +
					"\t\t\tcrosshair: true\n" +
					"\t\t},\n" +
					"\t\tyAxis: {\n" +
					"\t\t\tallowDecimals: false," +
					"\t\t\tmin: 0,\n" +
					"\t\t\ttitle: {\n" +
					"\t\t\t\ttext: '" + vAxis + "'\n" +
					"\t\t\t}\n" +
					"\t\t},\n" +
					"\t\ttooltip: {\n" +
					"\t\t\tshared: true,\n" +
					"\t\t\tuseHTML: true\n" +
					"\t\t},\n" +
					"\t\tplotOptions: {\n" +
					"\t\t\tcolumn: {\n" +
					"\t\t\t\tpointPadding: 0,\n" +
					"\t\t\t\tborderWidth: 0,\n" +
					"\t\t\t\tstacking: 'normal'\n" +
					"\t\t\t}\n" +
					"\t\t},\n" +
					"\t\tseries: "+type+"\n" +
					"\t});\n" +
					"});\n\n\n\n\n");
		} catch (IOException e) {
			error("Something went wrong while writing the result file: "+type);
			e.printStackTrace(System.err);
		}
	}


	public String getNodeValue(Node parent, String toGet) {
		NodeList childs = parent.getChildNodes();
		for(int i=0; i<childs.getLength(); i++) {
			if(childs.item(i).getNodeName().equals(toGet)) {
				return childs.item(i).getTextContent();
			}
		}
		return null;
	}

	public long asLong(String value) {
		long result = -1;
		try {
			result = Long.parseLong(value);
		} catch (NumberFormatException ignore) {}
		return result;
	}

	public long getQuartileKey(String date) {
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(0);
		try {
			calendar.setTime(format.parse(date));
		} catch(ParseException e) {
			error("Incorrect date: "+date);
			return -1;
		}

		for (int i=0; i<quartileDates.size()-1; i++) {
			if(calendar.getTimeInMillis() >= quartileDates.get(i)
					&& calendar.getTimeInMillis() < quartileDates.get(i+1)) {
				//progress("quartile "+i);
				return quartileDates.get(i);
			}
		}
		return -1;
	}

	public Document loadDocument(String file) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			return docBuilder.parse(new File(DATA_SOURCE, file));
		} catch (IOException|SAXException|ParserConfigurationException e) {
			error("Could not load document: "+file);
		}
		return null;
	}

	public static void main(String[] args) {
		new GraphBuilder();
	}

	/**
	 * Print message to the standard output
	 *
	 * @param message The message to print
	 */
	public static void progress(String message) {
		System.out.println(message);
	}

	/**
	 * Print message to the error output
	 *
	 * @param message The message to print
	 */
	public static void error(String message) {
		System.err.println(message);
	}
}
