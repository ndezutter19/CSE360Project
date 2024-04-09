package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PatientInterfaceController {
	
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

	private TextField firstNameField;

	@FXML

	private TextField lastNameField;

	@FXML

	private TextField phoneNoField;

	@FXML

	private TextField emailField;

	@FXML

	private TextField pharmacyField;

	@FXML

	private TextField insuranceField; 
	
	@FXML
	private void patientSavesData()

	{

			String patientData = String.format(

					"First Name: %s%nLast Name: %s%nEmail: %s%nPhone Number: %s%nHealth History: %s%nInsurance ID: %s",



					firstNameField.getText(),

					lastNameField.getText(),

					phoneNoField.getText(),

					emailField.getText(),

					pharmacyField.getText(),

					insuranceField.getText()

					);


			saveToFile(firstNameField.getText(), patientData); //calls method which writes the data to the file

		}
	
	private void saveToFile(String firstname, String data) {

		String filename = firstname + "_PatientData.txt";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

			writer.write(data);

			System.out.println("Information saved for patient ID: " + firstname);

		} catch (IOException ex) {

			ex.printStackTrace();

			//to handle exception

		}

	}

}
