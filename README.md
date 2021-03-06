### UTTimetabling
Code of the research checking the timetabling habits of the University of Twente, graphs with the results found at [http://wiefferink.me/TimeTabling/](http://wiefferink.me/TimeTabling/).

### Data description
##### XML Databases
The root database `data1.xml` is generated by the SQL queries, the other databases are generated by their parent of the list structure. The `.xq` files list which database they convert to which other one.
* `data1.xml`: Made by the SQL query: printXML.sql
  * `data2.xml`: Activities per day, student sets separated
    * `data3.xml`: Per day for each student set the contactminutes, collegeminutes and start/end time
      * `data7.xml`: Count of days that students sets have evening classes and next day early classes
  * `data4.xml`: Activities per day, teachers separated
    * `data5.xml`: Per day for each teacher the contactminutes, collegeminutes and start/end time
      * `data6.xml`: Count of days that teachers have evening classes and next day early classes
  * `data8.xml`: Per room the number of minutes used
  * `data9.xml`: Activities per day, teachers separated, each class extended by 15 minutes
    * `data10.xml`: Per day for each teacher the contactminutes, collegeminutes and start/end time (corrected end times as source)
      * `data11.xml`: wasted minutes/year per teacher
  * `data12.xml`: Activities per day, student sets separated, each class extended by 15 minutes
    * `data13.xml`: Per day for each student set the contactminutes, collegeminutes and start/end time (corrected end times as source)
      * `data14.xml`: wasted minutes/year per student set

##### XQuery scripts
Conversion between the XML databases, indicated by their file name
