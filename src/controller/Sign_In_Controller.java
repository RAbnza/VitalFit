package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    
    private static final String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

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
        // Get the username entered by the user from a TextField
        String enteredUsername = usernameTxtField.getText();
        // Check if the username exists in the database
        boolean isValidUsername = false;

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            // Query to check if the entered username exists in the database
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, enteredUsername);

            // Execute the query
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // Username found, proceed to the dashboard
                    isValidUsername = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If username is valid, proceed to the Dashboard
        if (isValidUsername) {
            try {
                // Load the Dashboard FXML file
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
        } else {
            // Show an error message if the username is not found
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username. Please try again.");
            alert.showAndWait();
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
