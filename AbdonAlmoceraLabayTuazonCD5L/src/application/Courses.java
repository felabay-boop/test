package application;

public class Courses {
	//attributes
	private String courseCode; 
	private String courseTitle;
	private int units;
	private String section; 
	private String time; 
	private String days; 
	private String room; 

	public Courses (String courseCode, String courseTitle, int units,
					String section, String time, String days, String room){
		this.courseCode = courseCode; 
		this.courseTitle = courseTitle; 
		this.units = units; 
		this.section = section; 
		this.time = time; 
		this.days = days; 
		this.room = room; 
	}

	//getters for course detail display
	public String getCourseCode(){
		return this.courseCode; 
	}
	public String getCourseTitle(){
		return this.courseTitle;
	}
	public int getUnits(){
		return this.units;
	}
	public String getSection(){
		return this.section;
	}
	public String getTime() {
		return this.time;
	}
	public String getDays(){
		return this.days;
	}
	public String getRoom(){
		return this.room;
	}


	//methods
	public void viewCourseDetails() {
		//insert implementation
	}

	boolean checkCourseInCurriculum() {
		//checks if a course is in a curriculum
	}
}





