package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class SignUpController {

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
	
	@FXML
    private TextField firstNameField, lastNameField, dobField;
    @FXML
    private Label generatedUsernameLabel, generatedPasswordLabel;
    @FXML
    private RadioButton doctorRadio, nurseRadio, patientRadio;
    @FXML
    private ToggleGroup account;
    @FXML
    private Label messageToUserLabel;

    @FXML
    private void registerButtonAction(ActionEvent event) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String dob = dobField.getText().trim();

        // Validate inputs
        if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || !dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            messageToUserLabel.setText("Error: Missing or incorrect information.");
            return;
        }

        String username = firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase() + dob.substring(dob.length() - 4);
        String password = PasswordGenerator.generatePassword();
        
        // Check if an account type is selected
        RadioButton selectedRadioButton = (RadioButton) account.getSelectedToggle();
        if (selectedRadioButton == null) {
            messageToUserLabel.setText("Please select an account type.");
            return;
        }

        String accountType = selectedRadioButton.getText();

        // Attempt to write user information to file
        try {
            writeUserInfoToFile(username, password, accountType, firstName, lastName, dob);
            messageToUserLabel.setStyle("-fx-text-fill: green;");
            messageToUserLabel.setText("Registration successful!");
            
         // Display generated username and password
            generatedUsernameLabel.setText("Username: " + username);
            generatedPasswordLabel.setText("Password: " + password);
            
        } catch (IOException e) {
            messageToUserLabel.setText("Error: Unable to write user information.");
        } catch (IllegalArgumentException e) {
            messageToUserLabel.setText(e.getMessage());
        }
    }

    private void writeUserInfoToFile(String username, String password, String accountType, String firstName, String lastName, String dob) throws IOException, IllegalArgumentException {
        String userDirectory = System.getProperty("user.dir");
        String relativePath = "/src/users/";
        Files.createDirectories(Paths.get(userDirectory + relativePath));
        String filePath = userDirectory + relativePath + "users.txt";

        // Check if user already exists
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(firstName) && line.contains(lastName) && line.contains(dob)) {
                    	messageToUserLabel.setStyle("-fx-text-fill: green;");
                        throw new IllegalArgumentException("Error: User already exists.");
                    }
                }
            }
        }

        // Write user information to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.join(",", username, password, accountType, firstName, lastName, dob));
            writer.newLine();
        }
    }

}
