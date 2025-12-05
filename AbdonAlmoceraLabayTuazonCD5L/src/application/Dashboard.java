package application;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Dashboard implements Data{
	TableView<Course> table = new TableView<>();

	TableView<Student> planner = new TableView<>();

	//////////////////////////////////////////////////
	
	ArrayList<Student> fullList = new ArrayList<>();

	//the arraylist for the searched items
	ArrayList<Course> searched = new ArrayList<>();
	
	public static void showDashboard(BorderPane root, Student student) {
		BorderPane root1 = new BorderPane(); 
		root1.setStyle("-fx-background-color: #c5c8cc;"); 
		
		VBox top = new VBox(10); 
		top.setPadding(new Insets(15));
		top.setStyle("-fx-background-color: #ecf0f1");	
		
		//creates taskbar
		
		Button about = new Button("About");
		about.setStyle("-fx-pref-width: 100px; -fx-font-size: 12px; -fx-font-weight: bold"); 
		
		Button credits = new Button("Credits");
		credits.setStyle("-fx-pref-width: 100px; -fx-font-size: 12px; -fx-font-weight: bold"); 
		
		Button exit = new Button("Exit");
		exit.setStyle("-fx-pref-width: 100px; -fx-font-size: 12px; -fx-font-weight: bold"); 
		
		HBox taskbar = new HBox(about,credits,exit);
		taskbar.setStyle("-fx-pref-width: 500px;"); 
		
		root.setTop(taskbar);
		
		Text hello = new Text("Good day, " + student.getName());
		hello.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		
		VBox box = new VBox(hello);
		
		HBox contents = new HBox(box);
		
		root.setCenter(contents);
		
		root.setRight(new VBox(courseSearchEngine(root,student),removeSearchEngine(root,student)));
		//root.setRight(courseSearchEngine(root,student));	//defaults to add course
		
		root.setBottom(Calendar.setup(student));
		//root.setLeft(removeSearchEngine(root,student));
		
		Stage stage = (Stage) root.getScene().getWindow();
		stage.setFullScreen(true);
		stage.show();
		
		about.setOnAction(event -> {About.about(stage);});
		credits.setOnAction(event ->{Credits.credits(stage);});
		exit.setOnAction(event -> {stage.close();});
	}

	@SuppressWarnings("unchecked")
	private static VBox courseSearchEngine(BorderPane root, Student student){
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
		//HBox buttons = new HBox(select,exit);
		VBox box = new VBox(courseInfoTable,select);
		//event handler for select button
		select.setOnAction(event -> {
			//accesses selected course
			Course selected = courseInfoTable.getSelectionModel().getSelectedItem();
			
			//creates new tableview to show all available classes for selected course
			TableView<Course> courseClassInfoTable = new TableView<>();

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
			
			//buttons
			Button addCourse = new Button("Add Course to Planner");
			Button cancel = new Button("Go Back");
			HBox buttons2 = new HBox(addCourse,cancel);
			VBox box2 = new VBox(courseClassInfoTable,buttons2);
			//root.setRight(box2);
			root.setRight(new VBox(box2,removeSearchEngine(root,student)));
					
			//event handler for add course button
			addCourse.setOnAction(event2 -> {
				student.addCourse(courseClassInfoTable.getSelectionModel().getSelectedItem());
				//root.setLeft(removeSearchEngine(root,student));
				root.setRight(new VBox(courseSearchEngine(root,student),removeSearchEngine(root,student)));
				root.setBottom(Calendar.setup(student));
			});
			//event handler for cancel button
			cancel.setOnAction(event2 -> {
				root.setRight(box);
				root.setRight(new VBox(courseSearchEngine(root,student),removeSearchEngine(root,student)));
			});
		});
		return box;
	}
	
	@SuppressWarnings("unchecked")
	private static VBox removeSearchEngine(BorderPane root, Student student) {
		//creates table view for student's courses
		TableView<Course> courseInfoTable = new TableView<>();
		
		//creates columns
		TableColumn<Course, String> colCode   = new TableColumn<>("Course Code");
		TableColumn<Course, String> colTitle   = new TableColumn<>("Course Title");
		TableColumn<Course, String> colUnits   = new TableColumn<>("Units");
		TableColumn<Course, String> colSection = new TableColumn<>("Section");
		TableColumn<Course, String> colTime = new TableColumn<>("Timeframe");
		TableColumn<Course, String> colDays = new TableColumn<>("Day/s");
		TableColumn<Course, String> colRoom = new TableColumn<>("Room");
		colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
		colUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
		colSection.setCellValueFactory(new PropertyValueFactory<>("section"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("timeframe"));
		colDays.setCellValueFactory(new PropertyValueFactory<>("daysdata"));
		colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
		courseInfoTable.getColumns().addAll(colCode,colTitle,colUnits,colSection,colTime,colDays,colRoom);
		
		//gets all courses a student has
		for(Course c : student.getCourses())	courseInfoTable.getItems().add(c);
		
		//buttons
		Button delete = new Button("Delete Selected");
		VBox box = new VBox(courseInfoTable,delete);
		
		//event handler for delete button
		delete.setOnAction(event -> {
			student.deleteCourse(courseInfoTable.getSelectionModel().getSelectedItem());
			courseInfoTable.getItems().remove(courseInfoTable.getSelectionModel().getSelectedIndex());
			root.setBottom(Calendar.setup(student));
			});
		return box;
	}
	
	public static void showErrorAlert(String error) {
		Alert invalidlogin = new Alert(AlertType.ERROR);
		invalidlogin.setContentText(error);
		invalidlogin.showAndWait();
	}
}
