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
import javafx.stage.Stage;

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

    // Temporary storage for the data
    private static String tempName;
    private static String tempGender;
    private static String tempDateOfBirth;
    private static double tempWeight;
    private static double tempHeight;
    private static double tempBMI;

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
        // Store user data temporarily
        tempName = nameTxtField.getText();
        tempGender = genderCmbBox.getValue();
        tempDateOfBirth = datePicker.getValue().toString();
        tempWeight = Double.parseDouble(weightTxtField.getText());
        tempHeight = Double.parseDouble(heightTxtField.getText());
        tempBMI = Double.parseDouble(bmiTxtField.getText());


    	//Change to Register_C
        try {
            // Load the Balance Due FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Register_C.fxml"));
            Parent Register_CRoot = loader.load();

            // Get the current stage (window) from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(Register_CRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    	double weight = Double.parseDouble(weightTxtField.getText());
        double height = Double.parseDouble(heightTxtField.getText()) / 100; // Convert to METERS

        double bmi = weight / (height * height);
        bmiTxtField.setText(String.format("%.2f", bmi)); // Display BMI
    }

    @FXML
    void nameTxtField_Clicked(ActionEvent event) {

    }

    // Getter methods for the temporary data
    public static String getTempName() {
        return tempName;
    }

    public static String getTempGender() {
        return tempGender;
    }

    public static String getTempDateOfBirth() {
        return tempDateOfBirth;
    }

    public static double getTempWeight() {
        return tempWeight;
    }

    public static double getTempHeight() {
        return tempHeight;
    }

    public static double getTempBMI() {
        return tempBMI;
    }

}
