package application;

import java.util.ArrayList;

public interface Data {//gives variables to repeated strings to avoid typographical errors
	//courses
	ArrayList<Course> COURSES = new ArrayList<>();
	String CMSC = "BS Computer Science";
	String MSCS = "MS Computer Science";
	String MIT = "Master of Information Technology";
	String PHD = "PhD Computer Science";
	ArrayList<Course> CMSC_COURSES = new ArrayList<>();
	ArrayList<Course> MSCS_COURSES = new ArrayList<>();
	ArrayList<Course> MIT_COURSES = new ArrayList<>();
	ArrayList<Course> PHD_COURSES = new ArrayList<>();
	//days
	String MONDAY = "Monday";
	String TUESDAY = "Tuesday";
	String WEDNESDAY = "Wednesday";
	String THURSDAY = "Thursday";
	String FRIDAY = "Friday";
}
