package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Register implements Data{//class for registering and holding student data
	public static ArrayList<Student> STUDENTS = new ArrayList<>();
	public static void addStudent(Student s) {STUDENTS.add(s);}
	
	public static void saveAllStudents() {
		System.out.println("Saving...");
		try {//saves list of all students
			ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/storage/save.txt")));
			out.writeObject(STUDENTS);
			out.close();
		}
		catch(IOException e) {e.printStackTrace();}
		System.out.println("Saved");
	}
	@SuppressWarnings("unchecked")
	public static void loadAllStudents() {
		System.out.println("Loading...");
		try {//loads entire student list
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("src/storage/save.txt")));
			STUDENTS = (ArrayList<Student>) in.readObject();
			in.close();
		}
		catch(Exception e) {e.printStackTrace();}
		System.out.println("Loaded");
	}
	
	public static void loginScreen(Stage stage) {
		//creates dialog box for login and register
		Dialog<Void> loginDialog = new Dialog<Void>();
		GridPane loginDetails = new GridPane();
		
		//creates content for gridpane
		Text usertext = new Text("Username:");
		Text passtext = new Text("Password:");
		TextField userinput = new TextField();
		PasswordField passinput = new PasswordField();
		Text orRegister = new Text("or make a new account: ");
		Button registerButton = new Button("Register");
		
		//adds content to gridpane
		loginDetails.add(usertext, 0, 0);
		loginDetails.add(passtext, 0, 1);
		loginDetails.add(userinput, 1, 0);
		loginDetails.add(passinput, 1, 1);
		loginDetails.add(orRegister, 0, 2);
		loginDetails.add(registerButton, 1, 2);
		
		//adds ok/confirm button for login
		ButtonType okType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Text hello = new Text("Welcome");
		VBox loginScreen = new VBox(hello,loginDetails);
		loginDialog.getDialogPane().getButtonTypes().add(okType);
		loginDialog.getDialogPane().setContent(loginScreen);
		//changes ok button's text to "Login"
		Button okButton = (Button) loginDialog.getDialogPane().lookupButton(okType);
		okButton.setText("Login");
		
		//ensures login input fields are required
		okButton.setDisable(true);
		ChangeListener<String> loginListener = (n,m,input) -> {
			if(!userinput.getText().isEmpty() && !passinput.getText().isEmpty())	okButton.setDisable(false);
			else okButton.setDisable(true);
		};
		userinput.textProperty().addListener(loginListener);
		passinput.textProperty().addListener(loginListener);
		
		//event handler for login button
		okButton.addEventFilter(ActionEvent.ACTION, event -> {
			event.consume();//doesnt close immediately
				for(Student s : STUDENTS) {
					if(s.getUsername().equals(userinput.getText()) && s.getPassword().equals(passinput.getText())) {//login successful
						//dashboard starting method here!!!
						Dashboard.showDashboard(stage,s);
						System.out.println("VALID");
						loginDialog.close();
						return;
					}
				}
				//goes here if login unsuccessful
				System.out.println("INVALID");
				Alert invalidlogin = new Alert(AlertType.ERROR);
				invalidlogin.setContentText("Invalid username and password.");
				invalidlogin.showAndWait();
		});
		
		//event handler for register button
		registerButton.setOnAction(event ->{registerScreen();});
				
		loginDialog.showAndWait();
	}
	
	private static void registerScreen() {
		Dialog<Void> registerDialog = new Dialog<Void>();
		GridPane registerDetails = new GridPane();
		
		//creates content for gridpane
		Text usertext = new Text("Username:");
		Text nametext = new Text("Name:");
		Text IDtext = new Text("ID:");
		Text degreetext = new Text("Degree Program:");
		Text passtext = new Text("Password:");
		TextField userinput = new TextField();
		TextField nameinput = new TextField();
		TextField IDinput = new TextField();
		ComboBox<String> degreeinput = new ComboBox<String>();
		degreeinput.getItems().addAll(CMSC,MSCS,MIT,PHD);
		degreeinput.setValue(CMSC);
		PasswordField passinput = new PasswordField();
		
		//adds content to gridpane
		registerDetails.add(usertext, 0, 0);
		registerDetails.add(nametext, 0, 1);
		registerDetails.add(IDtext, 0, 2);
		registerDetails.add(degreetext, 0, 3);
		registerDetails.add(passtext, 0, 4);
		registerDetails.add(userinput, 1, 0);
		registerDetails.add(nameinput, 1, 1);
		registerDetails.add(IDinput, 1, 2);
		registerDetails.add(degreeinput, 1, 3);
		registerDetails.add(passinput, 1, 4);
		
		//adds ok/confirm and cancel buttons for register
		ButtonType okType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType cancelType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		registerDialog.getDialogPane().getButtonTypes().addAll(okType,cancelType);
		registerDialog.getDialogPane().setContent(registerDetails);
		//changes ok button's text to "Register"
		Button okButton = (Button) registerDialog.getDialogPane().lookupButton(okType);
		okButton.setText("Register");
		
		//ensures register input fields are required
		okButton.setDisable(true);
		ChangeListener<String> registerListener = (n,m,input) -> {
			if(!userinput.getText().isEmpty() && !nameinput.getText().isEmpty() && !IDinput.getText().isEmpty() && !passinput.getText().isEmpty())	okButton.setDisable(false);
			else okButton.setDisable(true);
		};
		userinput.textProperty().addListener(registerListener);
		nameinput.textProperty().addListener(registerListener);
		IDinput.textProperty().addListener(registerListener);
		passinput.textProperty().addListener(registerListener);
		
		//event handler for login button
		okButton.addEventFilter(ActionEvent.ACTION, event -> {
			event.consume();//doesnt immediately close
			//creates new student given registration details
			STUDENTS.add(new Student(nameinput.getText(),IDinput.getText(),new ArrayList<>(),degreeinput.getValue(),userinput.getText(),passinput.getText()));
			System.out.println("VALID");
			saveAllStudents();
			registerDialog.close();
		});
		registerDialog.showAndWait();
	}
}
