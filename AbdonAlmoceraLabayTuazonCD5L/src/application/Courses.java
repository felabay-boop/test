package application;

public class Courses {
	private String courseCode; 
	private String courseTitle;
	private int units;
	private String section; 
	private String time; 
	private String days; 
	private String room; 

	public Courses (String courseCode, String courseTitle, int units
				   , String section, String time, String days, 
					String room){
		this.courseCode = courseCode; 
		this.courseTitle = courseTitle; 
		this.units = units; 
		this.section = section; 
		this.time = time; 
		this.days = days; 
		this.room = room; 
	}

	//getters:
	public String getCourseCode (){ return courseCode }
	public String getCourseTitle (){ return courseTitle }
	public int getUnits (){ return units }
	public String getSection (){ return section }

	public void viewCourseDetails() {
		//insert implementation
	}

	boolean checkCourseInCurriculum() {
		//checks if a course is in a curriculum
	}
}



