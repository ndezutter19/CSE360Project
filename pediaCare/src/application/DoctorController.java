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
import java.nio.file.Files;
import java.nio.file.Path; 
import java.nio.file.Paths;


public class DoctorController {
	
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
	
	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField dobField;

	@FXML
	private TextField prescriptionsField;

	@FXML
	private TextField immunizationsField;

	@FXML
	private TextField visitDateField;

	@FXML
	private TextField physicalExamNotes;
	@FXML
	private TextField visitNumberField;


	
	
	@FXML
	private void DoctorSavesData()
	{

			String doctorFindings = String.format(
					"First Name: %s%nLast Name: %s%nDate of Birth: %s%nToday's Date: %s%nPrescriptions: %s%nImmunizations: %s%nExam Notes: %s",
				
					firstNameField.getText(),
					lastNameField.getText(),
					dobField.getText(),
					visitDateField.getText(),
					prescriptionsField.getText(),
					immunizationsField.getText(),
					physicalExamNotes.getText()
					);

			addDoctorFindings(firstNameField.getText(), doctorFindings); //calls method which writes the data to the file

		
		}
	
	
	
	private void addDoctorFindings(String firstName, String doctorFindings) {
        // Construct the file name based on the first name and visit number
        String fileName = firstName  + "_" + visitNumberField.getText() + "_PatientInfo.txt";
        Path filePath = Paths.get(fileName);

        // Check if the file exists
        if (Files.exists(filePath)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                // Append doctor's findings to the file
                writer.newLine(); // Add a new line for separation
                writer.write("Doctor's Findings:");
                writer.newLine();
                writer.write(doctorFindings);
                System.out.println("Doctor's findings added to the patient's file.");
            } catch (IOException e) {
                System.err.println("Error appending doctor's findings to the file: " + e.getMessage());
            }
        } else {
            System.out.println("Patient's file not found.");
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
