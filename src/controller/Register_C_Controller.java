package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

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
import javafx.stage.Stage;

public class Register_C_Controller {

    @FXML
    private Button IntermediateBtn;

    @FXML
    private Button advanceBtn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Button beginnerBtn;

    @FXML
    private Button recommendedBtn;
    private static final String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

    @FXML
    void advanceBtn_Clicked(ActionEvent event) {
        if (saveUserData("Advanced")) {
            changeToDashboard(event);
        }
    }

    @FXML
    void background_Clicked(MouseEvent event) {

    }

    @FXML
    void beginnerBtn_Clicked(ActionEvent event) {
        if (saveUserData("Beginner")) {
            changeToDashboard(event);
        }
    }

    @FXML
    void intermediateBtn_Clicked(ActionEvent event) {
        if (saveUserData("Intermediate")) {
            changeToDashboard(event);
        }
    }
    
    private boolean saveUserData(String userLevel) {
        // Get the temporary data from A&B
        String username = Register_A_Controller.getTempUsername();
        String name = Register_B_Controller.getTempName();
        String gender = Register_B_Controller.getTempGender();
        String dateOfBirth = Register_B_Controller.getTempDateOfBirth();
        double weight = Register_B_Controller.getTempWeight();
        double height = Register_B_Controller.getTempHeight();
        double bmi = Register_B_Controller.getTempBMI();

        // Save the user data
        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            String query = "INSERT INTO users (username, user_name, user_gender, user_dateOfBirth, user_weight, user_height, user_bmi, user_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);  // username
            pst.setString(2, name);  // user_name
            pst.setString(3, gender);  // user_gender
            pst.setString(4, dateOfBirth);  // user_dateOfBirth
            pst.setDouble(5, weight);  // user_weight
            pst.setDouble(6, height);  // user_height
            pst.setDouble(7, bmi);  // user_bmi
            pst.setString(8, userLevel);  // user_level

            // Execute the update to save the user data
            int result = pst.executeUpdate();
            SessionManager.getInstance().setUsername(username);

            // Check if data was inserted successfully
            if (result > 0) {
                // Data insertion was successful
                return true;
            } else {
                // Data insertion failed
                System.out.println("Error: Data insertion failed.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private void changeToDashboard(ActionEvent event) {
        // Change to Dashboard scene only after successful data insertion
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Dashboard.fxml"));
            Parent DashboardRoot = loader.load();

            // Get the DashboardController
            DashboardController dashboardController = loader.getController();

            // Switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(DashboardRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void recommendedBtn_Clicked(ActionEvent event) {
        String dateOfBirth = Register_B_Controller.getTempDateOfBirth();
        double bmi = Register_B_Controller.getTempBMI();
        
        // Calculate age based on the date of birth
        int age = calculateAge(dateOfBirth);
        String recommendedLevel = recommendLevel(age, bmi);

        if (saveUserData(recommendedLevel)) {
            changeToDashboard(event);
        }
    }
    
 
    private int calculateAge(String dateOfBirth) {
        
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears();
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return -1; 
        }
    }

    
    private String recommendLevel(int age, double bmi) {
        if (age >= 18 && age <= 29) {
            if (bmi < 18.5 || bmi >= 30) {
                return "Beginner";
            } else if (bmi >= 18.5 && bmi < 25) {
                return "Advanced";
            } else if (bmi >= 25 && bmi < 30) {
                return "Intermediate";
            }
        } else if (age >= 30 && age <= 49) {
            if (bmi < 18.5 || bmi >= 30) {
                return "Beginner";
            } else {
                return "Intermediate";
            }
        } else if (age >= 50) {
            return "Beginner";
        }
        return "Beginner"; 
    }

}
