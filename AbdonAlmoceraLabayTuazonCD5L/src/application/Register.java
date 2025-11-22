public class Register {
  private Scene scene;
	private GridPane root;
	
	TextField username = new TextField();
	TextField email = new TextField();
	TextField first = new TextField();
	TextField middle = new TextField();
	TextField last = new TextField();
	ComboBox<String> usertype = new ComboBox<String>();
	PasswordField password = new PasswordField();
	
	Text error = new Text("All textboxes \n(except the middle name) \nshould be filled up!!");
	
	public Create_New_User() {
		this.root = new GridPane();
		this.scene = new Scene(root, 300, 600);
	}
	
	public void start(Stage stage, Main main) {
		Text username_t = new Text("Username:");
		Text email_t = new Text("Email Address");
		Text first_t = new Text("First Name:");
		Text middle_t = new Text("Middle Name");
		Text last_t = new Text("Last Name");
		Text usertype_t = new Text("User Type");
		Text password_t = new Text("Password:");
		
		Button create = new Button("Login");
    Button cancel = new Button("Cancel");
    
    usertype.getItems().addAll("PROFESSOR", "STUDENT", "PREFECT");
    
    create.setOnMouseClicked(new EventHandler<MouseEvent>() {//create button function
			public void handle(MouseEvent arg0) {
				if(username.getCharacters().toString().equals("")
				|| email.getCharacters().toString().equals("")
				|| first.getCharacters().toString().equals("")
				|| last.getCharacters().toString().equals("")
				|| usertype.getValue() == null
				|| password.getCharacters().toString().equals("")) {
					
					root.add(error, 0, 7);
					stage.setScene(scene);
					stage.show();
				}
				else {
					main.sampleList.add(new User(username.getCharacters().toString(),
							email.getCharacters().toString(),
							first.getCharacters().toString(),
							middle.getCharacters().toString(),
							last.getCharacters().toString(),
							usertype.getValue(),
							password.getCharacters().toString()));
					
					ObservableList<User> data = FXCollections.observableArrayList(main.sampleList);
			        main.table.setItems(data);
			        stage.close();
				}
			}
		});
        
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				stage.close();
			}
		});
        
        HBox hbox = new HBox();
		root.setStyle("-fx-padding: 12;");
		
		hbox.getChildren().add(create);
		hbox.getChildren().add(cancel);
		
		this.root.add(username_t, 0, 0);
		this.root.add(email_t, 0, 1);
		this.root.add(first_t, 0, 2);
		this.root.add(middle_t, 0, 3);
		this.root.add(last_t, 0, 4);
		this.root.add(usertype_t, 0, 5);
		this.root.add(password_t, 0, 6);
		
		this.root.add(username, 1, 0);
		this.root.add(email, 1, 1);
		this.root.add(first, 1, 2);
		this.root.add(middle, 1, 3);
		this.root.add(last, 1, 4);
		this.root.add(usertype, 1, 5);
		this.root.add(password, 1, 6);
		
		this.root.add(hbox, 1, 7);
		
		stage.setTitle("Create New User");
		stage.setScene(this.scene);
		stage.show();
	}

}
