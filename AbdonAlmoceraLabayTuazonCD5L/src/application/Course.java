package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

public class Course {
	//attributes
	public static ArrayList<Course> COURSES = new ArrayList<>();	//holds all courses
	private String courseCode; 
	private String courseTitle;
	private int units;
	private String section; 
	//private String time;
	private ArrayList<String> days; 
	private LocalTime startTime;
	private LocalTime endTime;
	private String room;

	public Course (String courseCode, String courseTitle, int units,
					String section, LocalTime startTime, LocalTime endTime, ArrayList<String> days, String room){
		this.courseCode = courseCode; 
		this.courseTitle = courseTitle; 
		this.units = units; 
		this.section = section; 
		this.days = days; 
		this.startTime = startTime;
		this.endTime = endTime;
		this.room = room; 
	}

	//getters for course detail display
	public String getCourseCode(){return this.courseCode; }
	public String getCourseTitle(){return this.courseTitle;}
	public int getUnits(){return this.units;}
	public String getSection(){return this.section;}
	public ArrayList<String> getDays(){return this.days;}
	public LocalTime getStartTime(){return this.startTime;}
	public LocalTime getEndTime(){return this.endTime;}
	public String getRoom(){return this.room;}
	


	//methods
	public void loadCourses() {//loads all courses into program
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get("/src/storage/courseofferings.csv"));
			//discards first 2 lines
			String line = reader.readLine();
			line = reader.readLine();
			for(line = reader.readLine(); line!=null; line = reader.readLine()) {//reads all courses
				String[] cD = line.split(",");
				String[] times = cD[4].split("-");	//splits time to startTime and endTime
				String daysdata = cD[5];
				ArrayList<String> days = new ArrayList<>();
				switch(daysdata) {
					case "Mon":
						days.add("Monday");
						break;
					case "Tues":
						days.add("Tuesday");
						break;
					case "Wed":
						days.add("Wednesday");
						break;
					case "Thurs":
						days.add("Thursday");
						break;
					case "Fri":
						days.add("Friday");
						break;
					case "TTh":
						days.add("Tuesday");
						days.add("Thursday");
						break;
					case "WF":
						days.add("Wednesday");
						days.add("Friday");
						break;
				}
				Course course = new Course(cD[0],cD[1],Integer.parseInt(cD[2]),cD[3],LocalTime.parse(times[0]),LocalTime.parse(times[1]),days,cD[5]);
				COURSES.add(course);
			}
		}
		catch(IOException e) {}
	}
	
	public void viewCourseDetails() {
		//insert implementation
	}

	boolean checkCourseInCurriculum() {
		//checks if a course is in a curriculum
	}
}










