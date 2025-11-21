package application;

import java.util.ArrayList;

public class Students {
	//attributes
	private String name;
	private int ID;
	private ArrayList<Courses> courses;
	private DegProg degprog;
	private Account account;
	
	public Students(String name,
	int ID,
	ArrayList<Courses> courses,
	DegProg degprog,
	Account account) {
		this.name = name;
		this.ID = ID;
		//courses - i forgot how to array wait
		this.degprog = degprog;
		this.account = account;
	}

	//getters for account display and boolean (ex. if degprog aligns with course)
	public getName(){
		return this.name;
	}
	public getID(){
		return this.ID;
	}
	public getDegprog(){
		return this.degree.name;
	}

	//setters
		//for registering new accounts?
	setName(){}
	setID(){}
	setDegProg(){}

	
	//methods
	addCourse(){
		
	}
	deleteCourse(){
		
	}
	editCourse(){
		
	}

	private doesAccExist(){ //boolean to check if account exists yet
		if (this.account == NULL){
			return false;
		} else {
			return true;
		}
	}
	
	login(){
		//log in details and check if student already exists in system
		if (doesAccExist() == true){
			//start code for logging in
		} else {
			//prompt to register new account using register()
		}
		
	}
	register(){
		//setter methods(?) to input account details
	}
	public viewWeeklyCalendar(){
		
	}
	
	
}



