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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Text ageText;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Text dashboardBtn;

    @FXML
    private Text heightText;

    @FXML
    private Button logoutBtn;

    @FXML
    private Text nameText;

    @FXML
    private Text progressBtn;

    @FXML
    private Text profileBtn;

    @FXML
    private Text recommendation_calories_text_1;

    @FXML
    private Text recommendation_calories_text_2;

    @FXML
    private Text recommendation_calories_text_3;

    @FXML
    private Text recommendation_calories_text_4;

    @FXML
    private Text recommendation_duration_text_1;

    @FXML
    private Text recommendation_duration_text_2;

    @FXML
    private Text recommendation_duration_text_3;

    @FXML
    private Text recommendation_duration_text_4;

    @FXML
    private Text recommendation_title_text_1;

    @FXML
    private Text recommendation_title_text_2;

    @FXML
    private Text recommendation_title_text_3;

    @FXML
    private Text recommendation_title_text_4;

    @FXML
    private Text resource_title_text_1;

    @FXML
    private Text resource_title_text_2;

    @FXML
    private Text resource_title_text_3;

    @FXML
    private Text resource_title_text_4;

    @FXML
    private Text resource_title_text_5;

    @FXML
    private Text resource_title_text_6;

    @FXML
    private Text resourcesBtn;

    @FXML
    private Text weightText;

    @FXML
    private Text workoutPlanBtn;

    @FXML
    void background_Clicked(MouseEvent event) {

    }

    @FXML
    void dashboardBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void logoutBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void progressBtn(MouseEvent event) {

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
    void resourcesBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void workoutPlanBtn_Clicked(MouseEvent event) {

    }

}
