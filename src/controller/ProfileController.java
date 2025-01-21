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
    
    private String username; // Store the username for database queries
    
    public void setUsernameFromSession() {
        this.username = SessionManager.getInstance().getUsername();
        populateProfileFields();
    }
    private void populateProfileFields() {
        String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            String query = "SELECT user_name, user_gender, user_dateOfBirth, user_height, user_weight, user_bmi FROM users WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);

            // Execute query
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Populate fields with retrieved data
                nameTxtField.setText(rs.getString("user_name"));
                genderCmbBox.setValue(rs.getString("user_gender"));
                datePicker.setValue(rs.getDate("user_dateOfBirth").toLocalDate());
                heightTxtField.setText(String.valueOf(rs.getDouble("user_height")));
                weightTxtField.setText(String.valueOf(rs.getDouble("user_weight")));
                bmiTxtField.setText(String.format("%.2f", rs.getDouble("user_bmi")));

                // Disable editing
                disableEditing();
            } else {
                System.err.println("No data found for username: " + username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void disableEditing() {
        nameTxtField.setDisable(true);
        nameTxtField.setEditable(false);

        heightTxtField.setDisable(true);
        heightTxtField.setEditable(false);

        weightTxtField.setDisable(true);
        weightTxtField.setEditable(false);

        genderCmbBox.setDisable(true);
        datePicker.setDisable(true);
    }
    
    private boolean updateProfileInDatabase(String name, String gender, double height, double weight, double bmi) {
        String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            String query = "UPDATE users SET user_name = ?, user_gender = ?, user_height = ?, user_weight = ?, user_bmi = ? WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);

            // Set parameters
            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setDouble(3, height);
            pst.setDouble(4, weight);
            pst.setDouble(5, bmi);
            pst.setString(6, username); // Use logged-in username

            // Execute the update
            int result = pst.executeUpdate();
            return result > 0; // Returns true if the update was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void initialize() {
    	setUsernameFromSession();
    	
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
    	// Enable editing for all input fields
        nameTxtField.setDisable(false);
        nameTxtField.setEditable(true);

        heightTxtField.setDisable(false);
        heightTxtField.setEditable(true);

        weightTxtField.setDisable(false);
        weightTxtField.setEditable(true);

        genderCmbBox.setDisable(false);
        datePicker.setDisable(false);

        // Change button text to "Save"
        editProfileBtn.setText("Save");

        // Add a save function on button click
        editProfileBtn.setOnAction(e -> {
            try {
                // Validate inputs
                String name = nameTxtField.getText().trim();
                String gender = genderCmbBox.getValue();
                double height = Double.parseDouble(heightTxtField.getText());
                double weight = Double.parseDouble(weightTxtField.getText());

                if (height <= 0 || weight <= 0 || name.isEmpty() || gender == null) {
                    throw new IllegalArgumentException("Invalid input: Please fill all fields correctly.");
                }

                // Calculate BMI
                double bmi = weight / ((height / 100) * (height / 100)); // Height in meters

                // Save to the database
                if (updateProfileInDatabase(name, gender, height, weight, bmi)) {
                    System.out.println("Profile updated successfully!");

                    // Disable editing after saving
                    disableEditing();
                    editProfileBtn.setText("Edit Profile");
                } else {
                    System.err.println("Failed to update the profile.");
                }

            } catch (NumberFormatException ex) {
                System.err.println("Invalid input: Height and weight must be numeric.");
            } catch (IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
            }
        });
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
