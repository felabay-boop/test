package application;

import java.util.ArrayList;

public class Student {
	private String name;
	private int ID;
	private ArrayList<Courses> courses;
	private DegProg degProg;
	private Account account;
	
	public Student(String name, int ID, ArrayList<Courses> courses, DegProg degProg,
	Account account) {
		this.name = name; 
		this.ID = ID; 
		this.degProg; 
		this.account = account; 
		this.courses = new ArrayList<>(); 
	}
}

