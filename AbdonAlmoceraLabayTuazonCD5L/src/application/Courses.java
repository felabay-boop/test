package application;

public class Courses {
	private String courseCode; 
	private String courseName;
	private int units; 
	private String room; 
	private String[] days; 
	private String section; 
	private String startTime; 
	private String endTime; 

	public Courses (String courseCode, String courseName, int units
				   , String room, String section, String startTime, 
					String endTime){
		this.courseCode = courseCode; 
		this.courseName = courseName; 
		this.units = units; 
		this.room = room; 
		this.section = section; 
		this.startTime = startTime; 
		this.endTime = endTime; 
	}
}

