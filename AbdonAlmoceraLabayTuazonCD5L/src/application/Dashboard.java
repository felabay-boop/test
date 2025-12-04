package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Dashboard{
	TableView<Course> table = new TableView<>();

	TableView<Student> planner = new TableView<>();

	//////////////////////////////////////////////////
	
	ArrayList<Student> fullList = new ArrayList<>();

	//the arraylist for the searched items
	ArrayList<Course> searched = new ArrayList<>();
	
	public void start(Stage stage) {
        planner.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Student, String> colCode   = new TableColumn<>("Course Code");
		TableColumn<Student, String> colTitle   = new TableColumn<>("Course Title");
		TableColumn<Student, String> colUnits   = new TableColumn<>("Units");
		TableColumn<Student, String> colSection   = new TableColumn<>("Section");
		TableColumn<Student, ArrayList<String>> colDays   = new TableColumn<>("Days");
		TableColumn<Student, String> colTimes   = new TableColumn<>("Times");
		TableColumn<Student, String> colRooms   = new TableColumn<>("Rooms");

		colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
		colUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
		colSection.setCellValueFactory(new PropertyValueFactory<>("section"));
		colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
		colTimes.setCellValueFactory(new PropertyValueFactory<>("timeframe"));
		colRooms.setCellValueFactory(new PropertyValueFactory<>("room"));

		planner.getColumns().addAll(
            colCode, colTitle, colUnits, colSection, colDays, colTimes, colRooms
        );
		
		ObservableList<Student> data = FXCollections.observableArrayList(fullList);
        planner.setItems(data);
        
		
		//HBox hbox = new HBox();
		VBox root = new VBox(table);
		root.setStyle("-fx-padding: 12;");
		
		/*hbox.getChildren().add(cnu);
		hbox.getChildren().add(ds);
		hbox.getChildren().add(exit);
		hbox.getChildren().add(login);*/
		
		//root.setTop(hbox);
		//root.setContent(table);
		
		stage.setScene(new Scene(root, 900, 320));
		stage.setTitle("Hogwarts User Accounts");
		stage.show();
	}

	private void searchEngine(String codesearch, String timesearch){
		for(Course i : fullList){
			if(i.getCourseCode().contains(search)){
				searched.add(i);
				continue;
			}
		}
	}
}
