package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WorkoutPlan_Beginner_Controller {

    @FXML
    private Text NameText11;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Text dashboardBtn;

    @FXML
    private Text durationText;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button markAsDoneBtn;

    @FXML
    private Text numOfSetsText;

    @FXML
    private ImageView playBtn;

    @FXML
    private Text profileBtn;

    @FXML
    private Text progressBtn;

    @FXML
    private Text resourcesBtn;

    @FXML
    private Text workoutDayText;

    @FXML
    private Text workoutDescriptionText;

    @FXML
    private Text workoutPlanBtn;

    @FXML
    private Text workoutTitleText;

    @FXML
    private MediaView workoutVideoFrame;
    
    @FXML
    void background_Clicked(MouseEvent event) {

    }

    @FXML
    void dashboardBtn_Clicked(MouseEvent event) {
    	//Change to Dashboard
        try {
            // Load the Balance Due FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Dashboard.fxml"));
            Parent DashboardRoot = loader.load();

            // Get the current stage (window) from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(DashboardRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void markAsDoneBtn_Clicked(ActionEvent event) {

    }
    
    @FXML
    void playBtn_Clicked(MouseEvent event) {
    	
    }

    @FXML
    void profileBtn_Clicked(MouseEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Profile.fxml"));
            Parent profileRoot = loader.load();

            // Switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(profileRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void progressBtn(MouseEvent event) {

    }

    @FXML
    void resourcesBtn_Clicked(MouseEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Resources.fxml"));
            Parent ResourcesRoot = loader.load();

            // Switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(ResourcesRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void workoutPlanBtn_Clicked(MouseEvent event) {

    }

}
