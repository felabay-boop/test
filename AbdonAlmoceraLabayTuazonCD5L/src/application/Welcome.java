package application;

import java.util.ArrayList;

public class Welcome {
	private Scene scene;
	private GridPane root;
	private ArrayList<User> sampleList;
	TextField username = new TextField();
	TextField password = new TextField();
	
	public Login(ArrayList<Students> a) {
		this.root = new GridPane();
		this.scene = new Scene(root, 250, 150);
		this.sampleList = a;
	}
	
	public void setStageComponents(Stage stage) {
		Text username_t = new Text("Enter Username:");
		Text password_t = new Text("Enter Password:");
		
		
		Button login = new Button("Test Login");
        Button cancel = new Button("Cancel");
        
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("login", stage);
			}
		});
        
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("cancel", stage);
			}
		});
        
        HBox hbox = new HBox();
		root.setStyle("-fx-padding: 12;");
		
		hbox.getChildren().add(login);
		hbox.getChildren().add(cancel);
		
		this.root.add(hbox, 1, 2);
		this.root.add(username_t, 0, 0);
		this.root.add(password_t, 0, 1);
		this.root.add(username, 1, 0);
		this.root.add(password, 1, 1);
		
		stage.setTitle("Login");
		stage.setScene(this.scene);
		stage.show();
	}
	
	private void handleButtonClick(String btnName, Stage stage) {
		if (btnName.contentEquals("cancel")) {
			stage.close();
		}
		else if (btnName.contentEquals("login")) {
			for(int i = 0; i < sampleList.size(); i++) {
				if(sampleList.get(i).getUsername().equals(username.getCharacters().toString())) {
					if(sampleList.get(i).getPassword().equals(password.getCharacters().toString())) {
						Welcome welcome = new Welcome(sampleList.get(i).getFirstName() + " " + 
					sampleList.get(i).getLastName(), sampleList.get(i).getUserType());
						
						welcome.setStageComponents(new Stage());
						
						stage.close();
					}
				}
			}
			
			Failure fail = new Failure();
			fail.start(new Stage());
			stage.close();
			
		}
		
		
	}
	
}

