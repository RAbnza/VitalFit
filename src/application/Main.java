package application;

import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file from the resources folder
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/LoadingScreen.fxml"));
            HBox root = loader.load();

            // Set the scene with the loaded FXML layout
            Scene scene = new Scene(root, 1200, 780);

            // Set the title
            primaryStage.setTitle("VitalFit: A Personalized Workout and Activity Tracker");

            // Add the application icon from an external folder
            File iconFile = new File("Resources/Images/VitalFitIcon.png");
            if (iconFile.exists()) {
                primaryStage.getIcons().add(new Image(new FileInputStream(iconFile)));
            } else {
                System.out.println("Icon file not found at: " + iconFile.getAbsolutePath());
            }

            // Show the stage
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
