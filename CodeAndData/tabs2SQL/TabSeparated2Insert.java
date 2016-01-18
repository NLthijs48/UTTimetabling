package me.wiefferink.timetabling;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TabSeparated2Insert {

	public TabSeparated2Insert() {
		File input, output;
		// UT 2013-2014
		input = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\All timetabling activities UT 2013-2014.txt");
		output = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\All timetabling activities UT 2013-2014.sql");
		convertUT(input, output, "ut1314");
		// UT 2014-2015
        input = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\All timetabling activities UT 2014-2015.txt");
        output = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\All timetabling activities UT 2014-2015.sql");
        convertUT(input, output, "ut1415");
		// UT 2013-2014 courses
		input = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\UT_Activities_with_codes_2013-2014.txt");
		output = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\UT_Activities_with_codes_2013-2014.sql");
		convertUTCourses(input, output, "ut1314_courses");
		// UT 2013-2014 courses
		input = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\UT_Activities_with_codes_2014-2015.txt");
		output = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\UT_Activities_with_codes_2014-2015.sql");
		convertUTCourses(input, output, "ut1415_courses");
        // SAX 2013-2015
        input = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\All timetabling activities SAX 2013-2015.txt");
        output = new File("S:\\Google Drive\\UT\\Data Science\\Project XML\\Data files\\tabs2sql\\All timetabling activities SAX 2013-2015.sql");
        //convertSAX(input, output, "sax1315");
	}


	public void convertUT(File input, File output, String tableName) {
		try (
				BufferedReader reader = new BufferedReader(new FileReader(input));
				BufferedWriter writer = new BufferedWriter(new FileWriter(output))
		) {
			// Add header
			writer.write("BEGIN TRANSACTION;\n" +
					"\n" +
					"CREATE TABLE " + tableName + " (\n" +
					"    coursename text,\n" +
					"    activityType text,\n" +
					"    dateGiven date,\n" +
					"    dayGiven text,\n" +
					"    startTime time,\n" +
					"    endTime time,\n" +
					"    teacher text,\n" +
					"    size integer,\n" +
					"    studentSets text,\n" +
					"    room text\n" +
					");\n");

			// Add inserts
			String line = reader.readLine();
			while (line != null) {
				line = line.replace("\"", "");
				line = line.replace("'", "''");
				line = line.replace("\t", "','");
				line = "INSERT INTO \"" + tableName + "\" VALUES('" + line + "');\n";

				writer.write(line);
				line = reader.readLine();
			}

			// Add footer
			writer.write("ANALYZE;\nCOMMIT;");
		} catch (IOException e) {
			System.out.println("Could not read file");
			e.printStackTrace();
		}
	}

	public void convertUTCourses(File input, File output, String tableName) {
		try (
				BufferedReader reader = new BufferedReader(new FileReader(input));
				BufferedWriter writer = new BufferedWriter(new FileWriter(output))
		) {
			// Add header
			writer.write("BEGIN TRANSACTION;\n" +
					"\n" +
					"CREATE TABLE " + tableName + " (\n" +
					"    coursename text,\n" +
					"    activity text,\n" +
					"    code text,\n" +
					"    type text,\n" +
					"    date date default NULL,\n" +
					"    day text,\n" +
					"    starttime text,\n" +
					"    endtime text,\n" +
					"    size integer\n" +
					");\n");

			// Add inserts
			String line = reader.readLine();
			while (line != null) {
				boolean inString = false;
				boolean first = true;
				String checkline = line;
				while(first || inString) {
					first = false;
					for (char character : checkline.toCharArray()) {
						if (character == '\"') {
							inString = !inString;
						}
					}
					if(inString) {
						checkline = reader.readLine();
						line = line + checkline;
					}
				}
				line = line.replaceAll("\t(#[^\t]*|-)\t(#[^\t]*|-)\t", "\tNONE\t");
				line = line.replaceAll("([^\t]*\t[^\t]*\t(\\d{9}(A)?|X+|x+|ITC[^\t]*)( )*)\\t(\\d{9}(A)?|X+|x+|ITC[^\t]*)( )*\\t", "$1\t");
				line = line.replaceAll("\t(#[^\t]*|-)\t", "\t");
				line = line.replaceAll("                      ", "");
				line = line.replace("\"", "");
				line = line.replace("'", "''");
				line = line.replace("\t", "','");
				line = line.replace(",''", ",NULL");
				line = "INSERT INTO \"" + tableName + "\" VALUES('" + line + "');\n";

				writer.write(line);
				line = reader.readLine();
			}

			// Add footer
			writer.write("ANALYZE;\nCOMMIT;");
		} catch (IOException e) {
			System.out.println("Could not read file");
			e.printStackTrace();
		}
	}

    public void convertSAX(File input, File output, String tableName) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(input));
                BufferedWriter writer = new BufferedWriter(new FileWriter(output))
        ) {
            // Add header
            writer.write("BEGIN TRANSACTION;\n" +
                    "\n" +
                    "CREATE TABLE " + tableName + " (\n" +
                    "    dateGiven date,\n" +
                    "    startTime time,\n" +
                    "    endTime time,\n" +
                    "    academy text,\n" +
                    "    place text,\n" +
                    "    class text,\n" +
                    "    eduCode text,\n" +
                    "    bisonCode text,\n" +
                    "    courseName text,\n" +
                    "    teacherCode text,\n" +
                    "    teacherName text,\n" +
                    "    room text,\n" +
                    "    remarks text,\n" +
                    "    calendarWeek text,\n" +
                    "    quartileWeek text,\n" +
                    "    quartile text,\n" +
                    "    lessonWeek text,\n" +
                    "    activity text,\n" +
                    "    schoolYear text\n" +
                    ");\n");

            // Add inserts
            String line = reader.readLine();
            Pattern str = Pattern.compile(".*(\"[^\"]*\").*");
            while (line != null) {
                Matcher m = str.matcher(line);
                if(m.matches()) {
                    line = line.replace(m.group(1), m.group(1).replace("\t", " "));
                }
                line = line.replace("\"", "");
                line = line.replace("'", "''");
                line = line.replace("\t", "','");
                line = "INSERT INTO \""+tableName+"\" VALUES('" + line + "');\n";

                writer.write(line);
                line = reader.readLine();
            }

            // Add footer
            writer.write("ANALYZE;\nCOMMIT;");
        } catch (IOException e) {
            System.out.println("Could not read file");
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		new TabSeparated2Insert();
	}
}
