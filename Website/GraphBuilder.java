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
    public static final String DATA_SOURCE = "C:\\Coding\\DataScience\\UTTimetabling\\CodeAndData\\XMLprocessing\\";
	public static final String DATA_TARGET = "C:\\Coding\\DataScience\\UTTimetabling\\Website\\";

    // XML databases
	public static final String UT_ACTIVITIES = "data1.xml";
	public static final String UT_COURSES = "courses1.xml";

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private List<String> quarters = Arrays.asList("2013-08-20", "2013-11-09", "2014-01-30", "2014-04-16", "2014-08-20", "2014-11-09", "2015-01-30", "2015-04-16", "2015-08-20", "2015-11-09", "2016-01-30", "2016-04-16");
	private List<Long> quarterDates;

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
		makeCourseTypeData();
	}

	public void makeCourseTypeData() {
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
		printBarGraph(result, "courseTypes");
	}

	public void printBarGraph(Map<Long, Map<String, Long>> data, String type) {
		progress("Printing bar graph: "+type);
		File output = new File(DATA_TARGET, type+".php");
		try {
			output.getParentFile().mkdirs();
			output.createNewFile();
		} catch (IOException e) {
			error("Something went wrong while creating the result file: " + output.getAbsolutePath());
			e.printStackTrace(System.err);
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
			writer.write("var " + type + " = [];\n");
			// Gather all different bar types
			Set<String> barTypes = new HashSet<>();
			for(Map<String, Long> timeData : data.values()) {
				barTypes.addAll(timeData.keySet());
			}
			// Print data serie for each bar type
			for(String barType : barTypes) {
				progress("  Printing data: " + barType);
				writer.write(type+".push({name: \""+barType+"\", data: [");
				boolean first = true;
				for(Map<String, Long> timeData : data.values()) {
					Long value = timeData.get(barType);
					// Fill in missing values
					if(value == null) {
						value = 0L;
					}
					if(first) {
						first = false;
						writer.write(value+"");
					} else {
						writer.write(", "+value);
					}
				}
				writer.write("]});\n");
			}
		} catch (IOException e) {
			error("Something went wrong while writing the result file: "+output.getAbsolutePath());
			e.printStackTrace(System.err);
		}
		progress("Done printing bar graph: "+type);
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
