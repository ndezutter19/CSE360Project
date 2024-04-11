package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DoctorMessagesController {
	@FXML
	private Button find;
	private String log;
	@FXML
	private TextField message;
	@FXML
	private Button sendMessage;
	
	@FXML
	private TextArea messageLog;
	
	@FXML
	private TextField firstName;
	@FXML
	private void switchToDoctorOptionsScreen(ActionEvent event) throws IOException {
	    // Load the DoctorOptionsScreen FXML file
	    Parent doctorOptionsRoot = FXMLLoader.load(getClass().getResource("DoctorOptionsScreen.fxml"));
	    Scene doctorOptionsScene = new Scene(doctorOptionsRoot);

	    // Get the Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set the scene on the stage to switch to the DoctorOptions screen
	    window.setScene(doctorOptionsScene);
	    window.show();
	}
	
	@FXML
	private void switchToLogInScreen(ActionEvent event) throws IOException {		
		UserSession.clear(); // Clear the current user's session
	    // Load the LogIn FXML file
	    Parent logInRoot = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
	    Scene logInScene = new Scene(logInRoot);

	    // Get the Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set the scene on the stage to switch to the LogIn screen
	    window.setScene(logInScene);
	    window.show();
	}
	
	public void whenClicked() {
    	//System.out.print("test");
    	log="";
    	messageLog.clear();
		
		String g = firstName.getText()+"MessageLog.txt";

		 try (PrintWriter outFile = new PrintWriter(new FileWriter(g,true))) {
		        outFile.println("Doctor" + " : " + message.getText());
		        //System.out.print("test");
		        outFile.close();
		        try (BufferedReader rd = new BufferedReader(new FileReader(g))) {
		            String line;
		            
		            while ((line = rd.readLine()) != null && !line.isEmpty()) {
		            	System.out.println(line);
		                log += line + "\n" ;
		             
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        
		        //messageLog.clear();
                messageLog.setText(log);
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
    }
	
	public void findMessage() {
		messageLog.clear();
		log="";
		String g = firstName.getText()+"MessageLog.txt";
		try (FileReader f = new FileReader(g);BufferedReader rd = new BufferedReader(f)) {//when doctor clicks find message button, read messages from messageLog
	            String line;
		            while ((line = rd.readLine()) != null && !line.isEmpty()) {
		            	System.out.println(line);
		                log += line + "\n" ;
		             
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		       //clear previous messages from message log if any 
		       messageLog.clear();
		       //display messages
               messageLog.setText(log);
	}

}
