package application;

import javafx.application.Application;
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

public class Dashboard {
	TableView<User> table = new TableView<>();
    ArrayList<User> sampleList = new ArrayList<>();
	@Override
	
	public void start(Stage stage) {
	    sampleList.add(new User("levioxaah",  "hjgranger@hogwarts.edu",  "Hermione",  "Jean",   "Granger",   "PROFESSOR", "a"));
	    sampleList.add(new User("sunshine.daisies", "rbweasley@hogwarts.edu",      "Ronald", "Bilius", "Weasley",   "PREFECT", "b"));
	    sampleList.add(new User("theboywholived",   "hjpotter@hogwarts.edu",   "Harry",   "James",   "Potter", "STUDENT", "c"));
	    sampleList.add(new User("darklord.official",   "tmriddle@hogwarts.edu",   "Tom",   "Marvolo",   "Riddle", "STUDENT", "d"));
		
	    Path path = Paths.get("src/application/users.txt");
	    
    	try {
			//create ObjectInputStream using the newInputStream method of Files on our path argument
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			//read the object in the text file and cast it into an album
			sampleList = (ArrayList<User>)in.readObject();
			
			//close the file
			in.close();
			
		} catch (IOException e) { //catch exceptions if file does not exist or file is corrupted
		} catch (ClassNotFoundException e) { //catch exceptions if wrong file is read or the data is corrupted
			
		}
	    
		
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<User, String> colUsername   = new TableColumn<>("Username");
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
        
        ////////////////////////////////////////////////////
        //buttons
        Button cnu = new Button("Create New User");
        Button ds = new Button("Delete Selected");
        Button exit = new Button("Save and Quit");
        Button login = new Button("Test Login");
        
        cnu.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("cnu");
			}
		});
		
        ds.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("ds");
			}
		});
        
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("exit");
			}
		});
        
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				handleButtonClick("login");
			}
		});
        
        ////////////////////////////////////////////////////
        
		
		HBox hbox = new HBox();
		BorderPane root = new BorderPane(table);
		root.setStyle("-fx-padding: 12;");
		
		hbox.getChildren().add(cnu);
		hbox.getChildren().add(ds);
		hbox.getChildren().add(exit);
		hbox.getChildren().add(login);
		
		root.setTop(hbox);
		
		stage.setScene(new Scene(root, 900, 320));
		stage.setTitle("Hogwarts User Accounts");
		stage.show();
	}

	public static void main(String[] args) { 
		launch(); 
	}

	private void handleButtonClick(String btnName) {
		if(btnName.contentEquals("cnu")) {
			System.out.println("Hello World!");
		} 
		else if (btnName.contentEquals("ds")) {
			System.out.println(this.table.getSelectionModel().getSelectedItems());
			
			//deleting the user in the samplelist
			for(int i = 0; i < this.sampleList.size(); i++) {
				for(int j = 0; j < this.table.getSelectionModel().getSelectedItems().size(); j++) {
					if(this.table.getSelectionModel().getSelectedItems().get(j) == this.sampleList.get(i)) {
						this.sampleList.remove(i);
					}
				}
			}
			
			//deleting the user in the tablelist
			this.table.getItems().removeAll(
					this.table.getSelectionModel().getSelectedItems()
	        );
		}
		else if (btnName.contentEquals("exit")) {
			Path path = Paths.get("src/application/users.txt");
			
			//create the file first if it doesn't exist (go to catch if it already exists)
			try {
				Files.createFile(path);
			} catch (IOException e) {}
			
			
			//serializer
			try {
				//create ObjectOutputStream using the newOutputStream method of Files on our path argument
				ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
				
				//write the album into the stream
				out.writeObject(this.sampleList);
				
				//close the file
				out.close();
			} catch (IOException e) {
				System.out.println("\n" + e  +"\n");
			}
			
			System.exit(0);
		}
		else if (btnName.contentEquals("login")) {
			Login login = new Login(sampleList);
			login.setStageComponents(new Stage());
		}
		
		
	}
}

