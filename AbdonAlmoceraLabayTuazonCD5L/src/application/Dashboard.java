package application;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Dashboard implements Data{
	TableView<Course> table = new TableView<>();

	TableView<Student> planner = new TableView<>();

	//////////////////////////////////////////////////
	
	ArrayList<Student> fullList = new ArrayList<>();

	//the arraylist for the searched items
	ArrayList<Course> searched = new ArrayList<>();
	
	public static void start(Stage stage) {
		
	}

	@SuppressWarnings("unchecked")
	public static void searchEngine(Student student){
		//creates new stage
		Stage search = new Stage();
		search.setTitle("Courses in " + student.getDegprog());
		//creates table view for all courses
		TableView<Course> courseInfoTable = new TableView<>();
		
		//creates columns
		TableColumn<Course, String> colCode   = new TableColumn<>("Course Code");
		TableColumn<Course, String> colTitle   = new TableColumn<>("Course Title");
		TableColumn<Course, String> colUnits   = new TableColumn<>("Units");
		
		//creates description tooltips
		courseInfoTable.setRowFactory(table -> {
			TableRow<Course> row = new TableRow<>();
			
			row.itemProperty().addListener((n,m,course) -> {
				if(course == null)	row.setTooltip(null);
				else {
					Tooltip description = new Tooltip(course.getDescription());
					description.setShowDelay(Duration.ZERO);
					row.setTooltip(description);
				}
			});
			
			return row;
		});
		
		//fills table content
		colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
		colUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
		courseInfoTable.getColumns().addAll(colCode, colTitle, colUnits);
		ArrayList<Course> courses = null;
		//gets all courses of the student's degree program
		switch(student.getDegprog()) {
		case CMSC: courses = 	Course.CMSC_COURSES;	break;
		case MSCS: courses =	Course.MSCS_COURSES;	break;
		case MIT:  courses =	Course.MIT_COURSES;		break;
		case PHD:  courses =	Course.PHD_COURSES;		break;
		}
		for(Course c : courses)	courseInfoTable.getItems().add(c);
		
		Button select = new Button("Select");
		Button exit = new Button("Exit");
		HBox buttons = new HBox(select,exit);
		VBox box = new VBox(courseInfoTable,buttons);
		Scene scene = new Scene(box,800,600);
		search.setScene(scene);
		//event handler for select button
		select.setOnAction(event -> {
			//accesses selected course
			Course selected = courseInfoTable.getSelectionModel().getSelectedItem();
			
			//creates new tableview to show all available classes for selected course
			TableView<Course> courseClassInfoTable = new TableView<>();
					
			//TableColumn<Course, String> colCode = new TableColumn<>("Course Code");
			//TableColumn<Course, String> colUnits = new TableColumn<>("Course Units");
			TableColumn<Course, String> colSection = new TableColumn<>("Section");
			TableColumn<Course, String> colTime = new TableColumn<>("Timeframe");
			TableColumn<Course, String> colDays = new TableColumn<>("Day/s");
			TableColumn<Course, String> colRoom = new TableColumn<>("Room");
					
			colSection.setCellValueFactory(new PropertyValueFactory<>("section"));
			colTime.setCellValueFactory(new PropertyValueFactory<>("timeframe"));
			colDays.setCellValueFactory(new PropertyValueFactory<>("daysdata"));
			colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
			courseClassInfoTable.getColumns().addAll(colCode,colUnits,colSection,colTime,colDays,colRoom);
					
			//gets all available classes of selected course
			for(Course c : Course.COURSES)	if(c.getCourseCode().equals(selected.getCourseCode()))	courseClassInfoTable.getItems().add(c);
					
			Button addCourse = new Button("Add Course to Planner");
			Button cancel = new Button("Go Back");
			HBox buttons2 = new HBox(addCourse,cancel);
			VBox box2 = new VBox(courseClassInfoTable,buttons2);
			Scene scene2 = new Scene(box2,800,600);
			search.setScene(scene2);
					
			//event handler for add course button
			addCourse.setOnAction(event2 -> {student.addCourse(courseClassInfoTable.getSelectionModel().getSelectedItem());});
			//event handler for cancel button
			cancel.setOnAction(event2 -> {search.setScene(scene);});
		});
		//event handler for exit button
		exit.setOnAction(event -> {search.close();});
		search.showAndWait();
	}
	
	public static void showErrorAlert(String error) {
		Alert invalidlogin = new Alert(AlertType.ERROR);
		invalidlogin.setContentText(error);
		invalidlogin.showAndWait();
	}
}
