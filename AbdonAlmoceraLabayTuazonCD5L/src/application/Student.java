package application;

import java.util.ArrayList;

public class Student {
	//attributes
	private String name;
	private int ID;
	private ArrayList<Courses> courses = new ArrayList<>();
	private String degprog;
	private LocalTime[][] calendar;
	
	public Student(String name, int ID, ArrayList<Courses> courses, String degprog) {
		this.name = name;
		this.ID = ID;
		//courses - i forgot how to array wait
		this.courses = courses;
		this.degprog = degprog;
	}

	//getters for account display and boolean (ex. if degprog aligns with course)
	public String getName() {return this.name;}
	public int getID() {return this.ID;}
	public ArrayList<Courses> getCourses() {return this.courses;}
	public String getDegprog() {return this.degprog;}

	//setters
		//for registering new accounts?
//	setName(){}
//	setID(){}
//	setDegProg(){}
	//we dont need them a student wont change their name or id or degree after creation
	
	//methods
	public void addCourse(Courses course){
		for(Courses c : this.courses) {//checks if student already has course
			if(c.getCourseCode().equals(course.getCourseCode())) {
				System.out.println("ERROR you already have this course");
				return;
			}
		}
		this.courses.add(course);	//goes here if no match
		System.out.println("SUCCESS course added");
	}
	public void deleteCourse(Courses course){
		for(Courses c : this.courses) {//checks if course exists
			if(c.getCourseCode().equals(course.getCourseCode())) {
				this.courses.remove(c);
				System.out.println("SUCCESS course removed");
				return;
			}
		}
		//goes here if no match
		System.out.println("ERROR you dont have this course");
	}
//	editCourse(){
//		
//	}do we need this

//	private doesAccExist(){ //boolean to check if account exists yet
//		if (this.account == NULL){
//			return false;
//		} else {
//			return true;
//		}
//	}
	
//	login(){should be on welcome i think
//		//log in details and check if student already exists in system
//		if (doesAccExist() == true){
//			//start code for logging in
//		} else {
//			//prompt to register new account using register()
//		}
//		
//	}
//	register(){should be on welcome i think
//		//setter methods(?) to input account details
//	}
	public void viewWeeklyCalendar(){
		
	}
	
	
}

