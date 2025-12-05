package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class About {
	public static void about( ) { //send help in this area
		Stage window = new Stage(); 
		window.setTitle("About PlanExe");
		
		VBox vbox = new VBox(); 
		vbox.setAlignment(Pos.TOP_LEFT);
		vbox.setSpacing(8);
		
		Label text1 = new Label("About"); 
		text1.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
		
		Label text2 = new Label("PlanExe is a planner application that helps the \\n\"\r\n"
				+ "				+ \"Institute of Computer Science (ICS) students plan their preferred \\n\"\r\n"
				+ "				+ \"schedule for the upcoming semester. This application allows students \\n\"\r\n"
				+ "				+ \"to add, delete, and edit their courses according to their respective \\n\"\r\n"
				+ "				+ \"programs.\\n\\n\""); 
		text2.setStyle("-fx-font-size: 10px; -fx-text-fill: gray");
		
		Label text3 = new Label("How to use: "); 
		text3.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		
		Label text4 = new Label("To start, log in or register a username and password to enter the system. \\n\"\r\n"
				+ "				+ \"Once entered, the students will be greeted with an interface where they \\n\"\r\n"
				+ "				+ \"can do the main functionality of the app–add, delete, and edit courses, \\n\"\r\n"
				+ "				+ \"with a restriction that the students can only engage with courses from \\n\"\r\n"
				+ "				+ \"their respective degree programs. The app has buttons and other \\n\"\r\n"
				+ "				+ \"clickables to interact with appropriate labels that are easy to navigate \\n\"\r\n"
				+ "				+ \"to help students make their dream schedule efficiently. \\n\\n\"");
		text4.setStyle("-fx-font-size: 10px; -fx-text-fill: gray");
		
		Label text5 = new Label("Data Privacy: "); 
		text5.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		
		Label text6 = new Label("In line with RA 10173, the Data Privacy Act, all personal information \\\\n\\\"\\r\\n\"\r\n"
				+ "				+ \"				+ \\\"inputted by the user is strictly confidential, is not sold to third parties, \\\\n\\\"\\r\\n\"\r\n"
				+ "				+ \"				+ \\\"and is only used to personalize and navigate the app.\\\\n\\\"");
		
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
