package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class About {
	public static void about() {
		Stage window = new Stage(); 
		window.setTitle("About PlanExe");
		
		VBox vbox = new VBox(); 
		vbox.setAlignment(Pos.TOP_LEFT);
		vbox.setSpacing(8);
		vbox.setPadding(new Insets(20, 20, 20, 20));
		
		Label text1 = new Label("About"); 
		text1.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
		
		Label text2 = new Label("PlanExe is a planner application that helps the Institute of Computer \n"
				+ "Science (ICS) students plan their preferred schedule for the upcoming semester. \n"
				+ "This application allows students to add, delete, and edit their courses according \n"
				+ "to their respective programs."); 
		text2.setStyle("-fx-font-size: 12px; -fx-text-fill: gray");
		
		Label text3 = new Label("How to use: "); 
		text3.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");
		
		Label text4 = new Label("To start, log in or register a username and password to enter the system. \n"
				+ "Once entered, the students will be greeted with an interface where they can do the \n"
				+ "main functionality of the app–add, delete, and edit courses, with a restriction that \n"
				+ "the students can only engage with courses from their respective degree programs.\n\n"
				
				+ "The app has buttons and other clickables to interact with appropriate labels \n"
				+ "that are easy to navigate to help students make their dream schedule efficiently.");
		text4.setStyle("-fx-font-size: 12px; -fx-text-fill: gray");
		
		Label text5 = new Label("Data Privacy: "); 
		text5.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");
		
		Label text6 = new Label("In line with RA 10173, the Data Privacy Act, all personal information \n"
				+ "inputted by the user is strictly confidential, is not sold to third parties, and is \n"
				+ "only used to personalize and navigate the app.");
		text6.setStyle("-fx-font-size: 12px; -fx-text-fill: gray");
		
		vbox.getChildren().add(text1); 
		vbox.getChildren().add(text2); 
		vbox.getChildren().add(text3); 
		vbox.getChildren().add(text4); 
		vbox.getChildren().add(text5); 
		vbox.getChildren().add(text6); 
	
		window.setScene(new Scene(vbox, 300, 150)); 
		window.show(); 
	}
}
