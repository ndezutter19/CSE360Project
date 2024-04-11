package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NurseScreenController {
	
	@FXML
	private void switchToLogInScreen(ActionEvent event) throws IOException {
		UserSession.clear(); // Clear the current user's session
	    // Load LogIn FXML file
	    Parent logInRoot = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
	    Scene logInScene = new Scene(logInRoot);

	    // Get Stage information
	    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	    // Set scene on the stage to switch to the LogIn screen
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

	private TextField nurseNotes;


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

				"Visit Number: %s%nToday's Date: %s%nFirst Name: %s%nLast Name: %s%nDate of Birth: %s%nWeight: %s%nHeight: %s%nBody Temperature %s%nBlood Pressure: %s%nNurse Notes: %s",

				visitNumberField.getText(),
				dateField.getText(),

				firstNameField.getText(),

				lastNameField.getText(),

				dobField.getText(),

				weightField.getText(),

				heightField.getText(),

				tempField.getText(),

				bpField.getText(),
				
				nurseNotes.getText()

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

@FXML
private TextArea visitHistoryTextArea;




public void displayVisitHistory() {
	
	String patientName = firstNameField.getText();
    // Assuming the files are stored in the same directory as the application
    String directoryPath = System.getProperty("user.dir");
    File directory = new File(directoryPath);
    File[] files = directory.listFiles((dir, name) -> name.startsWith(patientName) && name.endsWith("_PatientInfo.txt"));

    StringBuilder visitHistory = new StringBuilder();

    if (files != null) {

        for (File file : files) {

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    visitHistory.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle file reading error
            }
        }
    }
    
 // Display the combined visit history in the text area
    System.out.print("Printed");
    visitHistoryTextArea.setText(visitHistory.toString());


}


	
}
