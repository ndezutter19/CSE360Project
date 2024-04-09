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

public class NurseScreenController {
	
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

	private TextField dobField;

	@FXML

	private TextField dateField;

	@FXML

	private TextField weightField;

	@FXML

	private TextField heightField;

	@FXML

	private TextField tempField;

	@FXML

	private TextField bpField;

	@FXML

	private TextField visitNumberField;



	@FXML

private void NurseSavesData()

{



//	

//	if (firstNameField.getText().trim().isEmpty() ||

//			lastNameField.getText().trim().isEmpty() ||

//			dobField.getText().trim().isEmpty() ||

//			weightField.getText().trim().isEmpty() ||

//			heightField.getText().trim().isEmpty() ||

//			tempField.getText().trim().isEmpty() ||

//	bpField.getText().trim().isEmpty()) {

//

//		errorMessage.setText("All fields must be filled out to save patient data.");

//		errorMessage.setVisible(true); //we show the error message

//	} else {

//		//if not we proceed with saving the data

//		errorMessage.setVisible(false); //then we hide the error message



		//we format the patient data into a string which has all the info entered in the textfields

		String patientData = String.format(

				"Visit Number: %s%nFirst Name: %s%nLast Name: %s%nEmail: %s%nPhone Number: %s%nHealth History: %s%nInsurance ID: %s",

				visitNumberField.getText(),

				firstNameField.getText(),

				lastNameField.getText(),

				dobField.getText(),

				weightField.getText(),

				heightField.getText(),

				tempField.getText(),

				bpField.getText()

				);



//		//generating the 5-digit random unique id number between 10000 and 99999

//		int id = (int) (Math.random() * 90000) + 10000;

//		String patientId = String.valueOf(id); //int to string

		saveToFile(visitNumberField.getText(), firstNameField.getText(), patientData); //calls method which writes the data to the file



		//prints that the data has been saved to the window

//		datasaved.setText("Patient data saved! Patient ID: " + patientId);

//		GridPane.setConstraints(datasaved, 0, 8);

//		grid.getChildren().add(datasaved);

	}

//}

//}





private void saveToFile(String visitNumber, String firstname, String data) {

	String filename = firstname + "_" + visitNumber + "_PatientInfo.txt";

	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

		writer.write(data);

		System.out.println("Information saved for patient ID: " + firstname);

	} catch (IOException ex) {

		ex.printStackTrace();

		//to handle exception

	}

}


	
}
