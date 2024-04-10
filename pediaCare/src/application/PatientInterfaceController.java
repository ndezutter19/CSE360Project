package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
	
//    // New function: Set logged-in username
    public String loggedInUsername;
    
    @FXML
    private Label patientGreeting;
    

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
//        patientGreeting.setText("Welcome " + username + " !");
        
        
	    String filePath = System.getProperty("user.dir") + "/src/users/users.txt";
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

          String line;
          while ((line = reader.readLine()) != null) {
              String[] userDetails = line.split(",");
              if (userDetails[0].equals(username)) {
              	patientGreeting.setText("Welcome " + userDetails[3] + " !");
              	displayVisitHistory(userDetails[3]); 
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
          // Handle file reading error
      }
      
    }
    
    @FXML
    private TextArea visitHistoryTextArea;

    public void displayVisitHistory(String patientName) {
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


