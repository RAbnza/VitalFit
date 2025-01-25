package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

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
    private Text finishedWorkoutsText;

    @FXML
    private Text heightText;

    @FXML
    private Button logoutBtn;

    @FXML
    private Text nameText;

    @FXML
    private Text PhysicalLevelText;

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
    private Text TotalDaysText;
    
    @FXML
    private Text TotalDaysText1;

    @FXML
    private Text weightText;

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
            String query = "SELECT user_name, user_dateOfBirth, user_height, user_weight, user_level, workout_day, workout_done FROM users WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);

            // Execute query
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Populate fields with retrieved data
                nameText.setText(rs.getString("user_name"));
                heightText.setText(String.valueOf(rs.getDouble("user_height")));
                weightText.setText(String.valueOf(rs.getDouble("user_weight")));
                TotalDaysText.setText(rs.getString("workout_day"));
                TotalDaysText1.setText(rs.getString("workout_done"));
                PhysicalLevelText.setText(rs.getString("user_level"));

                // Retrieve date of birth and compute age
                LocalDate dateOfBirth = rs.getDate("user_dateOfBirth").toLocalDate();

                int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

                ageText.setText(String.valueOf(age));


            } else {
                System.err.println("No data found for username: " + username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void initialize() {
        setUsernameFromSession();
    }

    @FXML
    void background_Clicked(MouseEvent event) {

    }

    @FXML
    void dashboardBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void logoutBtn_Clicked(ActionEvent event) {

        // Clear the session
        SessionManager.getInstance().clearSession();

        // Redirect to Login scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Sign_in.fxml"));
            Parent loginRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loginRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void progressBtn(MouseEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Progress.fxml"));
            Parent ResourcesRoot = loader.load();

            // Switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(ResourcesRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Resources.fxml"));
            Parent ResourcesRoot = loader.load();

            // Switch scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(ResourcesRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void workoutPlanBtn_Clicked(MouseEvent event) {
        String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            // Retrieve the user level from the database
            String query = "SELECT user_level FROM users WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String userLevel = rs.getString("user_level").toLowerCase(); // Convert to lowercase for easier comparison
                String fxmlPath = "";

                // Determine the FXML file to load based on user level
                switch (userLevel) {
                    case "beginner":
                        fxmlPath = "/layouts/WorkoutPlan_Beginner.fxml";
                        break;
                    case "intermediate":
                        fxmlPath = "/layouts/WorkoutPlan_Intermediate.fxml";
                        break;
                    case "advanced":
                        fxmlPath = "/layouts/WorkoutPlan_Advance.fxml";
                        break;
                    default:
                        System.err.println("Invalid user level: " + userLevel);
                        return; // Exit if the user level is invalid
                }

                // Load the corresponding scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();

                // Switch the scene
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

            rs.close();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving user level from the database.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the workout plan scene.");
        }
    }

}
