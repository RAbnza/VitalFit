package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResourcesController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Text dashboardBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Text profileBtn;

    @FXML
    private Text progressBtn;

    @FXML
    private Text recommendation_duration_text_1;

    @FXML
    private Text recommendation_duration_text_11;

    @FXML
    private Text recommendation_duration_text_111;

    @FXML
    private Text recommendation_duration_text_1111;

    @FXML
    private Text recommendation_duration_text_11111;

    @FXML
    private Text recommendation_duration_text_111111;

    @FXML
    private Text recommendation_duration_text_111112;

    @FXML
    private Text recommendation_duration_text_111113;

    @FXML
    private Text recommendation_duration_text_1111131;

    @FXML
    private Text recommendation_duration_text_11111311;

    @FXML
    private Text recommendation_duration_text_111113111;

    @FXML
    private Text recommendation_duration_text_111114;

    @FXML
    private Text recommendation_duration_text_1111141;

    @FXML
    private Text recommendation_duration_text_11111411;

    @FXML
    private Text recommendation_duration_text_111114111;

    @FXML
    private Text recommendation_duration_text_1111141111;

    @FXML
    private Text recommendation_duration_text_11111411111;

    @FXML
    private Text recommendation_duration_text_111114111111;

    @FXML
    private Text recommendation_duration_text_112;

    @FXML
    private Text recommendation_duration_text_1121;

    @FXML
    private Text recommendation_duration_text_11211;

    @FXML
    private Text recommendation_duration_text_112111;

    @FXML
    private Text recommendation_duration_text_1121111;

    @FXML
    private Text recommendation_duration_text_11211111;

    @FXML
    private Text recommendation_duration_text_112111111;

    @FXML
    private Text recommendation_duration_text_1121111111;

    @FXML
    private Text recommendation_duration_text_11211111111;

    @FXML
    private Text recommendation_duration_text_112111111111;

    @FXML
    private Text recommendation_duration_text_1121111111111;

    @FXML
    private Text recommendation_duration_text_11211111111111;

    @FXML
    private Text recommendation_duration_text_112111111111111;

    @FXML
    private Text recommendation_duration_text_1121111111111111;

    @FXML
    private Text recommendation_duration_text_11211111111111111;

    @FXML
    private Text recommendation_title_text_1;

    @FXML
    private Text recommendation_title_text_11;

    @FXML
    private Text recommendation_title_text_111;

    @FXML
    private Text recommendation_title_text_1111;

    @FXML
    private Text recommendation_title_text_11111;

    @FXML
    private Text recommendation_title_text_111111;

    @FXML
    private Text recommendation_title_text_111112;

    @FXML
    private Text recommendation_title_text_111113;

    @FXML
    private Text recommendation_title_text_1111131;

    @FXML
    private Text recommendation_title_text_11111311;

    @FXML
    private Text recommendation_title_text_111113111;

    @FXML
    private Text recommendation_title_text_111114;

    @FXML
    private Text recommendation_title_text_1111141;

    @FXML
    private Text recommendation_title_text_11111411;

    @FXML
    private Text recommendation_title_text_111114111;

    @FXML
    private Text recommendation_title_text_1111141111;

    @FXML
    private Text recommendation_title_text_11111411111;

    @FXML
    private Text recommendation_title_text_111114111111;

    @FXML
    private Text recommendation_title_text_112;

    @FXML
    private Text recommendation_title_text_1121;

    @FXML
    private Text recommendation_title_text_11211;

    @FXML
    private Text recommendation_title_text_112111;

    @FXML
    private Text recommendation_title_text_1121111;

    @FXML
    private Text recommendation_title_text_11211111;

    @FXML
    private Text recommendation_title_text_112111111;

    @FXML
    private Text recommendation_title_text_1121111111;

    @FXML
    private Text recommendation_title_text_11211111111;

    @FXML
    private Text recommendation_title_text_112111111111;

    @FXML
    private Text recommendation_title_text_1121111111111;

    @FXML
    private Text recommendation_title_text_11211111111111;

    @FXML
    private Text recommendation_title_text_112111111111111;

    @FXML
    private Text recommendation_title_text_1121111111111111;

    @FXML
    private Text recommendation_title_text_11211111111111111;

    @FXML
    private Text recommendation_title_text_12;

    @FXML
    private Text recommendation_title_text_121;

    @FXML
    private Text recommendation_title_text_1211;

    @FXML
    private Text recommendation_title_text_12111;

    @FXML
    private Text recommendation_title_text_121111;

    @FXML
    private Text recommendation_title_text_1211111;

    @FXML
    private Text recommendation_title_text_1211112;

    @FXML
    private Text recommendation_title_text_1211113;

    @FXML
    private Text recommendation_title_text_12111131;

    @FXML
    private Text recommendation_title_text_121111311;

    @FXML
    private Text recommendation_title_text_1211113111;

    @FXML
    private Text recommendation_title_text_1211114;

    @FXML
    private Text recommendation_title_text_12111141;

    @FXML
    private Text recommendation_title_text_121111411;

    @FXML
    private Text recommendation_title_text_1211114111;

    @FXML
    private Text recommendation_title_text_12111141111;

    @FXML
    private Text recommendation_title_text_121111411111;

    @FXML
    private Text recommendation_title_text_1211114111111;

    @FXML
    private Text recommendation_title_text_1212;

    @FXML
    private Text recommendation_title_text_12121;

    @FXML
    private Text recommendation_title_text_121211;

    @FXML
    private Text recommendation_title_text_1212111;

    @FXML
    private Text recommendation_title_text_12121111;

    @FXML
    private Text recommendation_title_text_121211111;

    @FXML
    private Text recommendation_title_text_1212111111;

    @FXML
    private Text recommendation_title_text_12121111111;

    @FXML
    private Text recommendation_title_text_121211111111;

    @FXML
    private Text recommendation_title_text_1212111111111;

    @FXML
    private Text recommendation_title_text_12121111111111;

    @FXML
    private Text recommendation_title_text_121211111111111;

    @FXML
    private Text recommendation_title_text_1212111111111111;

    @FXML
    private Text recommendation_title_text_12121111111111111;

    @FXML
    private Text recommendation_title_text_121211111111111111;

    @FXML
    private Text resourcesBtn;

    @FXML
    private ScrollPane resourcesScrollPane;

    @FXML
    private ImageView workoutBtn1;

    @FXML
    private ImageView workoutBtn2;

    @FXML
    private ImageView workoutBtn3;

    @FXML
    private ImageView workoutBtn4;

    @FXML
    private Text workoutPlanBtn;

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

    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Progress.fxml"));
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
    void resourcesBtn_Clicked(MouseEvent event) {

    	
    }

    @FXML
    void workoutBtn1_Clicked(MouseEvent event) {

    }

    @FXML
    void workoutBtn2_Clicked(MouseEvent event) {

    }

    @FXML
    void workoutBtn3_Clicked(MouseEvent event) {

    }

    @FXML
    void workoutBtn4_Clicked(MouseEvent event) {

    }

    @FXML
    void workoutPlanBtn_Clicked(MouseEvent event) {
    	//TODO: Add Backend Here
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/WorkoutPlan_Beginner.fxml"));
            Parent WorkoutPlan_BeginnerRoot = loader.load();

            // Switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(WorkoutPlan_BeginnerRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
