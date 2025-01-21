package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfileController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private TextField bmiTxtField;

    @FXML
    private Text dashboardBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Text difficultyText;

    @FXML
    private Button editDifficultyBtn;

    @FXML
    private Button editProfileBtn;

    @FXML
    private ComboBox<String> genderCmbBox;

    @FXML
    private TextField heightTxtField;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField nameTxtField;

    @FXML
    private Text progressBtn;

    @FXML
    private Text prorfileBtn;

    @FXML
    private Text prorfileBtn1;

    @FXML
    private Text prorfileBtn11;

    @FXML
    private Text resourcesBtn;

    @FXML
    private Text resourcesBtn1;

    @FXML
    private TextField weightTxtField;

    @FXML
    private Text workoutPlanBtn;
    
    @FXML
    public void initialize() {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	bmiTxtField.setFocusTraversable(false); 
    	datePicker.setFocusTraversable(false); 
    	genderCmbBox.setFocusTraversable(false); 
    	editDifficultyBtn.setFocusTraversable(false); 
    	heightTxtField.setFocusTraversable(false); 
    	nameTxtField.setFocusTraversable(false); 
    	weightTxtField.setFocusTraversable(false); 
    	editProfileBtn.setFocusTraversable(false); 
    	anchorPane.requestFocus();
    	
    	String[] genderArray = {"Male", "Female"};
    	
    	// Set items for the ComboBox using the array
        genderCmbBox.setItems(FXCollections.observableArrayList(genderArray));

    }

    @FXML
    void background_Clicked(MouseEvent event) {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	bmiTxtField.setFocusTraversable(false); 
    	datePicker.setFocusTraversable(false); 
    	genderCmbBox.setFocusTraversable(false); 
    	editDifficultyBtn.setFocusTraversable(false); 
    	heightTxtField.setFocusTraversable(false); 
    	nameTxtField.setFocusTraversable(false); 
    	weightTxtField.setFocusTraversable(false); 
    	editProfileBtn.setFocusTraversable(false); 
    	anchorPane.requestFocus();
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
    void datePicker_onAction(ActionEvent event) {

    }

    @FXML
    void editDifficultyBtn_Clicked(ActionEvent event) {
    	
    }

    @FXML
    void editProfileBtn_Clicked(ActionEvent event) {
    	
    	nameTxtField.setDisable(false);
    	nameTxtField.setEditable(true);
    	
    	
    }

    @FXML
    void genderCmbBox_onAction(ActionEvent event) {

    }

    @FXML
    void logoutBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void nameTxtField_Clicked(ActionEvent event) {

    }

    @FXML
    void progressBtn(MouseEvent event) {

    }

    @FXML
    void prorfileBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void resourcesBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void workoutPlanBtn_Clicked(MouseEvent event) {

    }

}
