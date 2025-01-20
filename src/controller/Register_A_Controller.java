package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Register_A_Controller {

    @FXML
    private Button NextBtn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private TextField usernameTxtField;

    @FXML
    public void initialize() {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	usernameTxtField.setFocusTraversable(false); 
    	NextBtn.setFocusTraversable(false); 
    }
    
    @FXML
    void NextBtn_Clicked(ActionEvent event) {
    	
    	//TODO: Add the username to Database
    	
    	
    	//Change to Register_B
        try {
            // Load the Balance Due FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Register_B.fxml"));
            Parent Register_BRoot = loader.load();

            // Get the current stage (window) from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(Register_BRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void background_Clicked(MouseEvent event) {
    	usernameTxtField.setFocusTraversable(false); 
    	NextBtn.setFocusTraversable(false); 
    	anchorPane.requestFocus();
    }

    @FXML
    void usernameTxtField_Clicked(ActionEvent event) {

    }

}
