package application;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Student implements Data{
	//attributes
	private String name;
	private String ID;
	private ArrayList<Course> courses = new ArrayList<>();
	private String degprog;
	private ArrayList<ArrayList<LocalTime>> calendar = new ArrayList<>(Arrays.asList(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));	//array list for each day in the week
	private String username;
	private String password;
	
	public Student(String name, String ID, ArrayList<Course> courses, String degprog, String username, String password) {
		this.name = name;
		this.ID = ID;
		this.courses = courses;
		this.degprog = degprog;
		this.username = username;
		this.password = password;
	}

	//getters for account display and boolean (ex. if degprog aligns with course)
	public String getName() {return this.name;}
	public String getID() {return this.ID;}
	public ArrayList<Course> getCourses() {return this.courses;}
	public String getDegprog() {return this.degprog;}
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public ArrayList<ArrayList<LocalTime>> getCalendar() {return this.calendar;}
	
	//methods
	public void addCourse(Course course){
		if(!(this.degprog == course.getDegreeProgram())) {
			System.out.println("ERROR this course is not under your degree program");
			return;
		}
		for(Course c : this.courses) {//loops through each course
			if(c.getCourseCode().equals(course.getCourseCode())) {//checks if student already has course
				System.out.println("ERROR you already have this course");
				return;
			}
		for(String day : course.getDays()){
			if (course.getStartTime().isBefore(c.getEndTime()) &&
				course.getEndTime().isAfter(c.getStartTime()) && 
			   (c.getDays().contains(day))){//if true, the course's schedule to be added collides with an existing course's
				System.out.println("ERROR schedule collision with " + c.getCourseTitle());
				return;
				}
			}
		}
		//goes here if no match
		this.courses.add(course);
		int dayIndex = -1;
		for(String day : course.getDays()){
			switch(day){
				case MONDAY:	dayIndex = 0;	break;
				case TUESDAY:	dayIndex = 1;	break;
				case WEDNESDAY:	dayIndex = 2;	break;
				case THURSDAY:	dayIndex = 3;	break;
				case FRIDAY:	dayIndex = 4;	break;
				case SATURDAY:	dayIndex = 5;	break;
			}
			this.calendar.get(dayIndex).addAll(Arrays.asList(course.getStartTime(),course.getEndTime()));
		}
		System.out.println("SUCCESS course added");
	}
	public void deleteCourse(Course course){
		for(Course c : this.courses) {//checks if course exists
			if(c.getCourseCode().equals(course.getCourseCode())) {//course exists
				this.courses.remove(c);
				for(String day : course.getDays()){//loops through each day course has to remove all times
					int dayIndex = -1;
						switch(day){
					case MONDAY:	dayIndex = 0;	break;
					case TUESDAY:	dayIndex = 1;	break;
					case WEDNESDAY:	dayIndex = 2;	break;
					case THURSDAY:	dayIndex = 3;	break;
					case FRIDAY:	dayIndex = 4;	break;
					case SATURDAY:	dayIndex = 5;	break;
					}
						this.calendar.get(dayIndex).removeAll(Arrays.asList(course.getStartTime(),course.getEndTime()));
				}
				System.out.println("SUCCESS course removed");
				return;
			}
		}
		//goes here if no match
		System.out.println("ERROR you dont have this course");
	}

	public void viewWeeklyCalendar(){
		ArrayList<String> days = new ArrayList<>(Arrays.asList(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY));
		int index = 0;
		for (ArrayList<LocalTime> day : this.calendar) {
			System.out.println(days.get(index));
		    for (int i = 0; i < day.size(); i += 2) {
		        LocalTime start = day.get(i);
		        LocalTime end   = day.get(i + 1);
		        System.out.println(start + " - " + end);
		    }
		    index++;
		}
		for(Course c : this.courses) {
			System.out.println(c.getCourseCode() + "|" + c.getTimeframe());
		}
	}
	
	
}
