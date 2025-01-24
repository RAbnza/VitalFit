package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class Register_A_Controller {

    @FXML
    private Button NextBtn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Text go_to_loginBtn;

    @FXML
    private TextField usernameTxtField;

    private static String tempUsername;//temoraray storage
    private static final String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

    @FXML
    public void initialize() {
        // Request focus on the anchorPane to prevent auto-focus on the TextField
    	usernameTxtField.setFocusTraversable(false);
    	NextBtn.setFocusTraversable(false);
    }

    @FXML
    void NextBtn_Clicked(ActionEvent event) {

    	tempUsername = usernameTxtField.getText();


    	   // Check if the user exist in db
        boolean isUsernameTaken = false;

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            // Query to check user already exists
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, tempUsername);

            // Execute
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // Username exists show error
                    isUsernameTaken = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If username is taken show error message
        if (isUsernameTaken) {
            // error message if the username is already taken
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Username Error");
            alert.setHeaderText(null);
            alert.setContentText("The username is already taken. Please choose another one.");
            alert.showAndWait();
        } else {
            // If username is not taken - proceed
            try {
                // Load the Register_B FXML file
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
    }

    // Getter for the temporary username
    public static String getTempUsername() {
        return tempUsername;
    }

    @FXML
    void background_Clicked(MouseEvent event) {
    	usernameTxtField.setFocusTraversable(false);
    	NextBtn.setFocusTraversable(false);
    	anchorPane.requestFocus();
    }

    @FXML
    void go_to_loginBtn_Clicked(MouseEvent event) {

    	//Change to Sign In
        try {
            // Load the Balance Due FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Sign_In.fxml"));
            Parent Sign_InRoot = loader.load();

            // Get the current stage (window) from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(Sign_InRoot);
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
