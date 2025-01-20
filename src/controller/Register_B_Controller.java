package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Register_B_Controller {

    @FXML
    private Button NextBtn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private TextField bmiTxtField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> genderCmbBox;

    @FXML
    private Button getBMIBtn;

    @FXML
    private TextField heightTxtField;

    @FXML
    private TextField nameTxtField;

    @FXML
    private TextField weightTxtField;

    @FXML
    public void initialize() {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	bmiTxtField.setFocusTraversable(false); 
    	datePicker.setFocusTraversable(false); 
    	genderCmbBox.setFocusTraversable(false); 
    	getBMIBtn.setFocusTraversable(false); 
    	heightTxtField.setFocusTraversable(false); 
    	nameTxtField.setFocusTraversable(false); 
    	weightTxtField.setFocusTraversable(false); 
    	NextBtn.setFocusTraversable(false); 
    	anchorPane.requestFocus();
    	
    	String[] genderArray = {"Male", "Female"};
    	
    	// Set items for the ComboBox using the array
        genderCmbBox.setItems(FXCollections.observableArrayList(genderArray));

    }
    
    @FXML
    void NextBtn_Clicked(ActionEvent event) {
    	//TODO: Add the values to the database
    }

    @FXML
    void background_Clicked(MouseEvent event) {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	bmiTxtField.setFocusTraversable(false); 
    	datePicker.setFocusTraversable(false); 
    	genderCmbBox.setFocusTraversable(false); 
    	getBMIBtn.setFocusTraversable(false); 
    	heightTxtField.setFocusTraversable(false); 
    	nameTxtField.setFocusTraversable(false); 
    	weightTxtField.setFocusTraversable(false); 
    	NextBtn.setFocusTraversable(false); 
    	anchorPane.requestFocus();
    }

    @FXML
    void datePicker_onAction(ActionEvent event) {

    }

    @FXML
    void genderCmbBox_onAction(ActionEvent event) {

    }

    @FXML
    void getBMIBtn_Clicked(ActionEvent event) {
    	//TODO: Compute the BMI
    }

    @FXML
    void nameTxtField_Clicked(ActionEvent event) {
    	
    }

}
