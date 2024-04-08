package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorOptionsScreenController {

	@FXML
	private void switchToDoctorMessages(ActionEvent event) throws IOException {
	    // Load the DoctorMessages FXML file
	    Parent doctorMessagesRoot = FXMLLoader.load(getClass().getResource("DoctorMessages.fxml"));
	    Scene doctorMessagesScene = new Scene(doctorMessagesRoot);

	    // Get the Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set the scene on the stage to switch to the DoctorMessages screen
	    window.setScene(doctorMessagesScene);
	    window.show();
	}
	
	@FXML
	private void switchToDoctorScreen(ActionEvent event) throws IOException {
	    // Load the DoctorScreen FXML file
	    Parent doctorScreenRoot = FXMLLoader.load(getClass().getResource("DoctorScreen.fxml"));
	    Scene doctorScreenScene = new Scene(doctorScreenRoot);

	    // Get the Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set the scene on the stage to switch to the DoctorScreen
	    window.setScene(doctorScreenScene);
	    window.show();
	}
	
	@FXML
	private void switchToLogInScreen(ActionEvent event) throws IOException {
	    // Load the LogIn FXML file
	    Parent logInRoot = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
	    Scene logInScene = new Scene(logInRoot);

	    // Get the Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set the scene on the stage to switch to the LogIn screen
	    window.setScene(logInScene);
	    window.show();
	}

}
