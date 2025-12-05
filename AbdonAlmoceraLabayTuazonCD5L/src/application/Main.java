package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application{
	@Override
	public void start(Stage primaryStage) {
		Course.loadAllCourses();
		Register.loadAllStudents();
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//presents login dialog box
		Register.loginScreen(root);
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {//saves students when the program closes
		    Register.saveAllStudents();
		}));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
