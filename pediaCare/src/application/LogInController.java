package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label loginError;
	
	@FXML
	private void loginAndNavigate(ActionEvent event) throws IOException {
	    String username = usernameField.getText().trim();
	    String password = passwordField.getText().trim();

	    // Assume default path; adjust if necessary
	    String filePath = System.getProperty("user.dir") + "/src/users/users.txt";
	    File file = new File(filePath);

	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] userDetails = line.split(",");
	            // Expected format: username,password,accountType,firstName,lastName,dob
	            if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
	                // Match found, navigate based on account type
	            	UserSession.setUsername(username);
	                navigateToScreen(userDetails[2], event);
	                return;
	            }
	        }
	        // If no match found
	        loginError.setText("Username or password is incorrect.");
	    } catch (FileNotFoundException e) {
	        loginError.setText("Users file not found.");
	    } catch (Exception e) {
	        loginError.setText("An error occurred.");
	    }
	}

	private void navigateToScreen(String accountType, ActionEvent event) throws IOException {
	    String fxmlFile = "";
	    switch (accountType) {
	        case "Patient":
	            fxmlFile = "PatientInterface.fxml";
	            break;
	        case "Doctor":
	            fxmlFile = "DoctorOptionsScreen.fxml";
	            break;
	        case "Nurse":
	            fxmlFile = "NurseScreen.fxml";
	            break;
	        default:
	            loginError.setText("Invalid account type.");
	            return;
	    }
	    
	    

//	    Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
	    
	    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	    Parent root = loader.load();

	    // Get the controller associated with the FXML file
	    if (accountType.equals("Patient")) {
	        // For patient interface, pass the username to the controller
	        PatientInterfaceController controller = loader.getController();
	        controller.setLoggedInUsername(usernameField.getText().trim());
	    }
	    
	    
	    Scene scene = new Scene(root);

	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    window.setScene(scene);
	    window.show();
	}

}
