package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

	@FXML
	private void switchToSignUpScreen(ActionEvent event) throws IOException {
	    // Load the SignUp FXML file
	    Parent signUpRoot = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
	    Scene signUpScene = new Scene(signUpRoot);

	    // Get the Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set the scene on the stage to switch to the SignUp screen
	    window.setScene(signUpScene);
	    window.show();
	}
	
	@FXML
	private void switchToDoctorOptionsScreen(ActionEvent event) throws IOException {
	    // Load the DoctorOptionsScreen FXML file
	    Parent doctorOptionsRoot = FXMLLoader.load(getClass().getResource("PatientInterface.fxml"));
	    Scene doctorOptionsScene = new Scene(doctorOptionsRoot);

	    // Get the Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set the scene on the stage to switch to the DoctorOptions screen
	    window.setScene(doctorOptionsScene);
	    window.show();
	}

}
