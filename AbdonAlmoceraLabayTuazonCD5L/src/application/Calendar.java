package application;
	
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Calendar implements Data{
	public Calendar(){}
	
	public static Group setup(Student student) {
		try {
			//SETUP
			Group root = new Group();
			
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
			days.add(MONDAY);
			days.add(TUESDAY);
			days.add(WEDNESDAY);
			days.add(THURSDAY);
			days.add(FRIDAY);
			days.add(SATURDAY);
			
			for(int i = 1; i < 7; i++) {
				Line vborderline = new Line(50 + (i * 100), 0, 50 + (i * 100), 505);
				root.getChildren().add(vborderline);
			}
			
			for(int i = 0; i < 6; i++) {
				Text text = new Text(60 + ((i) * 100), 12.5f, days.get(i));
				text.setTextAlignment(TextAlignment.CENTER);
				root.getChildren().add(text);
			}
			Rectangle rect = new Rectangle();
			//COURSE-PLACING (THE RECTANGLES)
			ArrayList<String>	week = new ArrayList<>(Arrays.asList(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY));
			for(Course i : student.getCourses()) {
				for(int x=0;x<week.size();x++)	if(i.getDays().contains(week.get(x))) {
					double xCoord = x * 100 + 50;
					double yCoord = -1;
					if(i.getStartTime().isBefore(LocalTime.of(7, 0)))	yCoord = ((i.getStartTime().getHour() + 5)) * 40 + i.getStartTime().getMinute() * 40 / 60 + 25;
					else yCoord = ((i.getStartTime().getHour() -7) % 12) * 40 + i.getStartTime().getMinute() * 40 / 60 + 25;
					LocalTime test = i.getEndTime();
					if(i.getEndTime().isBefore(i.getStartTime())) {
						yCoord = ((i.getStartTime().getHour() - 7)) * 40 + i.getStartTime().getMinute() * 40 / 60 + 25;
						test = i.getEndTime().plusHours(12);
					}
					
					double height = ((test.getHour() - i.getStartTime().getHour()) * 40)
							+ ((test.getMinute() - i.getStartTime().getMinute()) * 40 / 60);
					
					double width = 100;
					
					rect = new Rectangle(width,height,Color.RED);
					rect.setX(xCoord);
					rect.setY(yCoord);
					Text label = new Text(i.getCourseCode() + " " + i.getSection() + "\n" + i.getRoom());
					label.setX(rect.getX()+10);
					label.setY(rect.getY()+height/2);
					label.setTextOrigin(VPos.CENTER);
					
					root.getChildren().addAll(rect,label);
				}
				
			}
			return root;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

