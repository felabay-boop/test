package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
	ArrayList<Course> sampleList = new ArrayList<>();
	
	public void start(Stage stage) {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        /*TableColumn<User, String> colUsername   = new TableColumn<>("Username");
        TableColumn<User, String> colEmail      = new TableColumn<>("Email Address");
        TableColumn<User, String> colFirstName  = new TableColumn<>("First Name");
        TableColumn<User, String> colMiddleName = new TableColumn<>("Middle Name");
        TableColumn<User, String> colLastName   = new TableColumn<>("Last Name");
        TableColumn<User, String> colUserType   = new TableColumn<>("User Type");
        TableColumn<User, String> colPassword   = new TableColumn<>("Password");

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("maskedpassword"));

		

        table.getColumns().addAll(
            colUsername, colEmail, colFirstName, colMiddleName, colLastName, colUserType, colPassword
        );

        ObservableList<User> data = FXCollections.observableArrayList(sampleList);
        table.setItems(data);
        */

		TableColumn<User, String> colCode   = new TableColumn<>("Course Code");
		TableColumn<User, String> colTitle   = new TableColumn<>("Course Title");
		TableColumn<User, int> colUnits   = new TableColumn<>("Units");
		TableColumn<User, String> colSection   = new TableColumn<>("Section");
		TableColumn<User, ArrayList<String>> colDays   = new TableColumn<>("Days");
		TableColumn<User, String> colTimes   = new TableColumn<>("Times");
		TableColumn<User, String> colRooms   = new TableColumn<>("Rooms");

		colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
		colUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
		colSection.setCellValueFactory(new PropertyValueFactory<>("section"));
		colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
		colTimes.setCellValueFactory(new PropertyValueFactory<>("timeframe"));
		colRooms.setCellValueFactory(new PropertyValueFactory<>("room"));

		ObservableList<User> data = FXCollections.observableArrayList(sampleList);
        table.setItems(data);
        ////////////////////////////////////////////////////
        
		
		HBox hbox = new HBox();
		BorderPane root = new BorderPane();
		root.setStyle("-fx-padding: 12;");
		
		hbox.getChildren().add(cnu);
		hbox.getChildren().add(ds);
		hbox.getChildren().add(exit);
		hbox.getChildren().add(login);
		
		root.setTop(hbox);
		root.setCenter(table);
		
		stage.setScene(new Scene(root, 900, 320));
		stage.setTitle("Hogwarts User Accounts");
		stage.show();
	}
}

