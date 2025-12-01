package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Course implements Data{
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
	private String description;
	private String degreeProgram;

	public Course (String courseCode, String courseTitle, int units,
					String section, LocalTime startTime, LocalTime endTime, ArrayList<String> days, String room, String description, String degreeProgram){
		this.courseCode 	= courseCode; 
		this.courseTitle 	= courseTitle; 
		this.units 			= units; 
		this.section 		= section; 
		this.days 			= days; 
		this.startTime 		= startTime;
		this.endTime 		= endTime;
		this.room 			= room; 
		this.description 	= description;
		this.degreeProgram	= degreeProgram;
	}

	//getters for course detail display
	public String 				getCourseCode() 	{return this.courseCode;}
	public String 				getCourseTitle() 	{return this.courseTitle;}
	public int 					getUnits() 			{return this.units;}
	public String 				getSection() 		{return this.section;}
	public ArrayList<String> 	getDays() 			{return this.days;}
	public LocalTime 			getStartTime() 		{return this.startTime;}
	public LocalTime 			getEndTime() 		{return this.endTime;}
	public String 				getRoom() 			{return this.room;}
	public String 				getDescription() 	{return this.description;}
	public String				getDegreeProgram()	{return this.degreeProgram;}
	


	//methods
	public static void loadAllCourses() {//loads all courses into program
		loadDegreeProgram(Paths.get("src/storage/ics_cmsc_courses.csv"),CMSC_COURSES,CMSC);
		loadDegreeProgram(Paths.get("src/storage/ics_mscs_courses.csv"),MSCS_COURSES,MSCS);
		loadDegreeProgram(Paths.get("src/storage/ics_mit_courses.csv"),MIT_COURSES,MIT);
		loadDegreeProgram(Paths.get("src/storage/ics_phd_courses.csv"),PHD_COURSES,PHD);
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/storage/courseofferings.csv"));){
			//discards first 2 lines
			String line = reader.readLine();
			line = reader.readLine();
			for(line = reader.readLine(); line!=null; line = reader.readLine()) {//reads all courses
				//receives data
				String[] cD = line.split(",");
				String[] times = cD[4].split("-");	//splits time to startTime and endTime
				String daysdata = cD[5];
				if(cD[6].equals("TBA"))	cD[6] = null;	//nulls room if not available
				//adds days
				ArrayList<String> days = new ArrayList<>();
				switch(daysdata) {
					case "Mon":		days.add(MONDAY);	break;
					case "Tues":	days.add(TUESDAY);	break;
					case "Wed":		days.add(WEDNESDAY);break;
					case "Thurs":	days.add(THURSDAY);	break;
					case "Fri":		days.add(FRIDAY);	break;
					case "TTh":		days.addAll(Arrays.asList(TUESDAY,THURSDAY));	break;
					case "WF":		days.addAll(Arrays.asList(WEDNESDAY,FRIDAY));	break;
					case "TBA":		days = null;	break;	//nulls days if not available
				}
				
				//initialize for adding degree program
				String degprog = null;
				String desc = null;
				//loops through each degree program																		//loops through each course
				for(ArrayList<Course> degreeProgram : Arrays.asList(CMSC_COURSES,MSCS_COURSES,MIT_COURSES,PHD_COURSES))	for(Course c : degreeProgram)	if(c.getCourseCode().equals(cD[0])) {
					degprog = c.getDegreeProgram();
					desc = c.getDescription();
				}
				
				//properly parses time
				LocalTime[] actualTimes = new LocalTime[2];
				if(cD[4].equals("TBA")) {//nulls times if not available
					cD[4] = null;
					actualTimes[0] = null;
					actualTimes[1] = null;
				}else{//time not null
					for(int i=0;i<=1;i++) {
						try{
							LocalTime time = LocalTime.parse(times[i]);
							actualTimes[i] = time;
						}
						catch(DateTimeParseException e) {
							times[i] = "0" + times[i];
							LocalTime time = LocalTime.parse(times[i]);
							actualTimes[i] = time;
						}
					}
				}
					
				//adds course
				//Course course = new Course(cD[0],cD[1],Integer.parseInt(cD[2]),cD[3],actualTimes[0],actualTimes[1],days,cD[6],desc,degprog);
				COURSES.add(new Course(cD[0],cD[1],Integer.parseInt(cD[2]),cD[3],actualTimes[0],actualTimes[1],days,cD[6],desc,degprog));
			}
		}
		catch(IOException e) {System.out.println(e);}
	}
	
	private static void loadDegreeProgram(Path p, ArrayList<Course> degreeProgramCourses, String degreeProgram) {
		try(BufferedReader reader = Files.newBufferedReader(p);){
			//discards first line
			String line = reader.readLine();
			for(line = reader.readLine(); line!=null; line = reader.readLine()) {//reads all courses
				String[] cD = line.split(",");
				if(cD[2].equals("1-3"))	cD[2] = "3";	//special case for CMSC 290 and 291 which has 1-3 units
				degreeProgramCourses.add(new Course(cD[0],cD[1],Integer.parseInt(cD[2]),null,null,null,null,null,cD[3],degreeProgram)); //specific degree program csv does not hold section, timeframe, days, or room
			}
		}
		catch(IOException e) {}
	}
	
	public void viewCourseDetails() {
		//insert implementation
	}
	
	public static void testMethod() {
		System.out.println(COURSES.size());
		for(Course c : COURSES) {
			System.out.println(c.getCourseCode() + "|" + c.getSection() + "|" + c.getRoom());
		}
	}
}
