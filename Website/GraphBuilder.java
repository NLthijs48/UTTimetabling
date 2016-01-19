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
	public static final String DATA_TARGET = "C:\\Coding\\DataScience\\UTTimetabling\\Website\\";

    // XML databases
	public static final String UT_ACTIVITIES = "data1.xml";
	public static final String UT_COURSES = "courses1.xml";
	public static final String UT_STUDENT_TIMES = "data3.xml";
	public static final String UT_TEACHER_TIMES = "data5.xml";

	// Dates and times
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private List<String> quarters = Arrays.asList("2013-08-20", "2013-11-09", "2014-01-30", "2014-04-16", "2014-08-20", "2014-11-09", "2015-01-30", "2015-04-16", "2015-08-20", "2015-11-09", "2016-01-30", "2016-04-16");
	private List<Long> quarterDates;
	private long endOf1314 = 1408485600000L;
	private long endOf1415 = 1429135200000L;
	private long endOf1516 = 1460000000000L;

	public GraphBuilder() {
		quarterDates = new ArrayList<>();
		try {
			for (String quarter : quarters) {
				quarterDates.add(format.parse(quarter).getTime());
			}
		} catch (ParseException e) {
			error("Could not parse quarter dates:");
			e.printStackTrace(System.err);
		}

		makeOutput();
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
			makeCollegeHoursGraph(writer);
			makeTeacherCountGraph(writer);
			makeCourseTypeGraph(writer);
		} catch (IOException e) {
			error("Something went wrong while writing the result file: " + output.getAbsolutePath());
			e.printStackTrace(System.err);
		}
	}

	public void makeCourseTypeGraph(BufferedWriter writer) {
		progress("Creating course type data");
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		// Guesses for translations of the internal codes
		Map<String, String> translate = new HashMap<>();
		translate.put("COL", "Colstruction");
		translate.put("WC", "Practical");
		translate.put("HC", "Lecture");
		translate.put("TOETS", "Exam");
		translate.put("BOEK", "Book");
		translate.put("PRS", "Presentation");
		translate.put("WG", "Workshop");
		translate.put("ZMB", "Self Study Supervised");
		translate.put("ZGB", "Self Study Unsupervised");
		translate.put("ASM", "Academic Research Skills");
		translate.put("DTOETS", "Diagnostic Exam");

		Document document = loadDocument(UT_COURSES);
		if(document == null) {
			return;
		}
		for(long date : quarterDates) {
			result.put(date, new TreeMap<String, Long>());
		}
		NodeList activities = document.getDocumentElement().getElementsByTagName("activity");
		for(int i=0; i<activities.getLength(); i++) {
			Node activity = activities.item(i);
			long quarterKey = getQuarterKey(getNodeValue(activity, "dateGiven"));
			if(quarterKey == -1) {
				//error("wrong date: "+ getNodeValue(activity, "dateGiven"));
				continue;
			}
			Map<String, Long> quarter = result.get(quarterKey);
			if(quarter == null) {
				quarter = new TreeMap<>();
				result.put(quarterKey, quarter);
			}
			String type = getNodeValue(activity, "type");
			Long count = quarter.get(type);
			if(count == null) {
				count = 0L;
			}
			count++;
			if(translate.containsKey(type)) {
				type = translate.get(type);
			}
			quarter.put(type, count);
		}
		printBarGraph(writer, result, "courseTypes", "Lecture types per quarter", "Activity count", endOf1516, 1);
	}

	public void makeCollegeHoursGraph(BufferedWriter writer) {
		progress("Creating course type data");
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		// Only the first 8
		for (int i=0; i<8; i++) {
			result.put(quarterDates.get(i), new TreeMap<String, Long>());
		}
		List<String> types = Arrays.asList("teacher", "set");
		List<String> names = Arrays.asList("Teachers", "Students");
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
				long quarterKey = getQuarterKey(getNodeValue(activity.getParentNode(), "dategiven"));
				if (quarterKey == -1) {
					error("wrong date: " + getNodeValue(activity.getParentNode(), "dategiven"));
					continue;
				}
				Map<String, Long> quarter = result.get(quarterKey);
				if (quarter == null) {
					quarter = new TreeMap<>();
					result.put(quarterKey, quarter);
				}
				long contactMinutes = asLong(getNodeValue(activity, "contactminutes"));
				long collegeMinutes = asLong(getNodeValue(activity, "collegeminutes"));
				if (contactMinutes == -1 || collegeMinutes == -1) {
					error("  incorrect contactminutes: " + getNodeValue(activity, "contactminutes") + " or collegeminutes: " + getNodeValue(activity, "collegeminutes"));
					continue;
				}
				Long count = quarter.get(names.get(t) + ": lecture hours@"+names.get(t));
				if (count == null) {
					count = 0L;
				}
				count += contactMinutes;
				quarter.put(names.get(t) + ": lecture hours@" + names.get(t), count);
				count = quarter.get(names.get(t) + ": wasted hours@" + names.get(t));
				if (count == null) {
					count = 0L;
				}
				if (collegeMinutes - contactMinutes < 0) {
					error("incorrect matchup: cm=" + collegeMinutes + ", contM=" + contactMinutes);
					continue;
				}
				count += collegeMinutes - contactMinutes;
				quarter.put(names.get(t)+": wasted hours@" + names.get(t), count);
			}
		}
		printBarGraph(writer, result, "wastedTime", "Lecture versus wasted hours per quarter (students and teachers)", "Hours", endOf1415, 60);
	}

	public void makeTeacherCountGraph(BufferedWriter writer) {
		progress("Creating course type data");
		Document document = loadDocument(UT_TEACHER_TIMES);
		if (document == null) {
			return;
		}
		NodeList activities = document.getDocumentElement().getElementsByTagName("teacher");
		Map<Long, Set<String>> teachers = new TreeMap<>();
		for (int i = 0; i < activities.getLength(); i++) {
			Node activity = activities.item(i);
			long quarterKey = getQuarterKey(getNodeValue(activity.getParentNode(), "dategiven"));
			if (quarterKey == -1) {
				//error("wrong date: "+ getNodeValue(activity, "dateGiven"));
				continue;
			}
			String name = getNodeValue(activity, "name");
			Set<String> teachSet = teachers.get(quarterKey);
			if(teachSet == null) {
				teachSet = new HashSet<>();
				teachers.put(quarterKey, teachSet);
			}
			teachSet.add(name);
		}
		Map<Long, Map<String, Long>> result = new TreeMap<>();
		for(Map.Entry<Long, Set<String>> teachPart : teachers.entrySet()) {
			Map<String, Long> map = new TreeMap<>();
			map.put("Teacher count", (long)teachPart.getValue().size());
			result.put(teachPart.getKey(), map);
		}
		printBarGraph(writer, result, "teacherCount", "Teachers per quartile", "Teacher count", endOf1415, 1);
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
				String barType = parts[0];
				String stack = "default";
				if(parts.length >= 2) {
					stack = parts[1];
				}
				writer.write(type + ".push({name: \"" + barType + "\", stack: \""+stack+"\", data: [");
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

	public long getQuarterKey(String date) {
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(0);
		try {
			calendar.setTime(format.parse(date));
		} catch(ParseException e) {
			error("Incorrect date: "+date);
			return -1;
		}

		for (int i=0; i<quarterDates.size()-1; i++) {
			if(calendar.getTimeInMillis() >= quarterDates.get(i)
					&& calendar.getTimeInMillis() < quarterDates.get(i+1)) {
				//progress("quarter "+i);
				return quarterDates.get(i);
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
