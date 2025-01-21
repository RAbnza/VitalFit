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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sign_In_Controller {

    @FXML
    private Button LoginBtn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Text registerBtn;

    @FXML
    private TextField usernameTxtField;

    @FXML
    public void initialize() {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	usernameTxtField.setFocusTraversable(false); 
    	registerBtn.setFocusTraversable(false); 
    	LoginBtn.setFocusTraversable(false); 
    	anchorPane.requestFocus();
    }
    
    @FXML
    void LoginBtn_Clicked(ActionEvent event) {
    	
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
    void background_Clicked(MouseEvent event) {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	usernameTxtField.setFocusTraversable(false); 
    	registerBtn.setFocusTraversable(false); 
    	LoginBtn.setFocusTraversable(false); 
    	anchorPane.requestFocus();
    }

    @FXML
    void registerBtn_Clicked(MouseEvent event) {
    	
    	//Change to Register_A
        try {
            // Load the Balance Due FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Register_A.fxml"));
            Parent Register_ARoot = loader.load();

            // Get the current stage (window) from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(Register_ARoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void usernameTxtField_Clicked(ActionEvent event) {

    }

}
