package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorMessagesController {
	
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
