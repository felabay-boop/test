package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Credits {
	public static void credits() {
		Stage window = new Stage(); 
		window.setTitle("Credits");
		
		VBox vbox = new VBox(); 
		vbox.setAlignment(Pos.TOP_LEFT);
		vbox.setSpacing(8);
		vbox.setPadding(new Insets(20, 20, 20, 20));
		
		Label text1 = new Label("Credits"); 
		text1.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
		
		Label text2 = new Label("Roles: "); 
		text2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		
		Label text3 = new Label("Ross Armand Almocera"); 
		text3.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
		
		Label text4 = new Label("Ross is a developer of the PlanExe Planner app. \n"
				+ "He is currently pursuing a Bachelor’s degree at the University \n"
				+ "of the Philippines - Los Baños, and is in his second year. \n"
				+ "His interests revolve around programming and game development."); 
		text4.setStyle("-fx-font-size: 12px; -fx-text-fill: gray");
		
		Label text5 = new Label("Jan Edrian Tuazon"); 
		text5.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
		
		Label text6 = new Label("Janet is one of the developers of the PlanExe app. \n"
				+ "He is currently a 2nd-year BS Computer Science (BSCS) student at \n"
				+ "the University of the Philippines - Los Baños.  His interests \n"
				+ "include programming, cybersecurity, and tech startups.");
		text6.setStyle("-fx-font-size: 12px; -fx-text-fill: gray");
		
		Label text7 = new Label("Franzuebelle Labay"); 
		text7.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
		
		Label text8 = new Label("Franz served as the designer and writer of the PlanExe \n"
				+ "app. She is currently a 3rd-year BS Development Communication \n"
				+ "student at the University of the Philippines - Los Baños. Her \n"
				+ "interests include designing and making assets for games.");
		text8.setStyle("-fx-font-size: 12px; -fx-text-fill: gray");
		
		Label text9 = new Label("Lorraine Abdon"); 
		text9.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
		
		Label text10 = new Label("Rain is a writer in the PlanExe app. She is currently \n"
				+ "a 3rd-year BS Development Communication student at the University \n"
				+ "of the Philippines - Los Baños.  Her interests include studying \n"
				+ "Computer Science and other related fields, and pets.");
		text10.setStyle("-fx-font-size: 12px; -fx-text-fill: gray");
		
		Label text11 = new Label("References: "); 
		text11.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		
		vbox.getChildren().add(text1); 
		vbox.getChildren().add(text2); 
		vbox.getChildren().add(text3); 
		vbox.getChildren().add(text4); 
		vbox.getChildren().add(text5); 
		vbox.getChildren().add(text6); 
		vbox.getChildren().add(text7); 
		vbox.getChildren().add(text8); 
		vbox.getChildren().add(text9); 
		vbox.getChildren().add(text10); 
		vbox.getChildren().add(text11); 
	
		window.setScene(new Scene(vbox, 300, 150)); 
		window.show(); 
	}
}
