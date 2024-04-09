package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Replace "LoginScreen.fxml" with the path to your initial FXML file
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Doctor-Patient Portal");
        primaryStage.setScene(new Scene(root, 600, 400)); // Set the window size
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

