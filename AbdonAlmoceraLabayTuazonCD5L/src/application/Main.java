package application;
	
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			//SETUP
			Group root = new Group();
			Scene scene = new Scene(root,650,505);
			
			Rectangle r1 = new Rectangle(650, 25, Color.AZURE);
			Rectangle r2 = new Rectangle(50, 505, Color.AZURE);
			
			Line hline = new Line(0, 25, 650, 25);
			Line vline = new Line(50, 0, 50, 505);
			
			root.getChildren().add(hline);
			root.getChildren().add(vline);
			root.getChildren().add(r1);
			root.getChildren().add(r2);
			
			for(int i = 1; i < 13; i++) {
				Line hborderline = new Line(0, 25 + (i * 40), 650, 25 + (i * 40));
				root.getChildren().add(hborderline);
			}
			
			for(int i = 0; i < 5; i++) {
				Text text = new Text(10, 25 + ((i + 1) * 40) - 15, ("" + (i + 7) + "-" + (i + 8)));
				root.getChildren().add(text);
			}
			
			Text workaroundtext = new Text(10, 25 + ((5 + 1) * 40) - 15, ("" + 12 + "-" + 1));
			root.getChildren().add(workaroundtext);
			
			for(int i = 0; i < 6; i++) {
				Text text = new Text(10, 265 + ((i + 1) * 40) - 15, ("" + (i + 1) + "-" + (i + 2)));
				root.getChildren().add(text);
			}
			
			ArrayList<String> days = new ArrayList<String>();
			days.add("Monday");
			days.add("Tuesday");
			days.add("Wednesday");
			days.add("Thursday");
			days.add("Friday");
			days.add("Saturday");
			
			for(int i = 1; i < 7; i++) {
				Line vborderline = new Line(50 + (i * 100), 0, 50 + (i * 100), 505);
				Text text = new Text(50 + (i * 100) - 50, 0, "M");
				root.getChildren().add(vborderline);
				root.getChildren().add(text);
			}
			
			for(int i = 0; i < 6; i++) {
				Text text = new Text(60 + ((i) * 100), 12.5f, days.get(i));
				text.setTextAlignment(TextAlignment.CENTER);
				root.getChildren().add(text);
			}
			
			ArrayList<Course> courses = new ArrayList<Course>();
			days.add("Monday");
			days.add("Tuesday");
			days.add("Wednesday");
			days.add("Thursday");
			days.add("Friday");
			days.add("Saturday");
			
			//COURSE-PLACING (THE RECTANGLES)
			//for() {}
			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
