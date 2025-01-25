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
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProgressController {

    @FXML
    private Text NameText;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Text currentDay;

    @FXML
    private Text currentStatusText;

    @FXML
    private Text currentWorkoutText;

    @FXML
    private Text dashboardBtn;

    @FXML
    private Text finishedWorkoutText;

    @FXML
    private Text heightText;

    @FXML
    private Button logoutBtn;

    @FXML
    private Text profileBtn;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text progressBtn;

    @FXML
    private Text resourcesBtn;

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
            // Fetch user data
            String userQuery = "SELECT user_name, user_dateOfBirth, user_height, user_weight, user_level, workout_day, workout_done FROM users WHERE username = ?";
            PreparedStatement userPst = conn.prepareStatement(userQuery);
            userPst.setString(1, username);

            ResultSet rs = userPst.executeQuery();

            if (rs.next()) {
                // Populate user profile fields
                NameText.setText(rs.getString("user_name"));
                heightText.setText(String.valueOf(rs.getDouble("user_height")));
                weightText.setText(String.valueOf(rs.getDouble("user_weight")));
                currentDay.setText(rs.getString("workout_day"));
                finishedWorkoutText.setText(rs.getString("workout_done"));

                // Determine user level and total workouts
                String userLevel = rs.getString("user_level").toLowerCase();
                int levelOffset;
                int totalWorkouts;

                switch (userLevel) {
                    case "beginner":
                        levelOffset = 0; // No offset
                        totalWorkouts = 10;
                        break;
                    case "intermediate":
                        levelOffset = 10; // +10 for intermediate
                        totalWorkouts = 16;
                        break;
                    case "advanced":
                        levelOffset = 26; // +26 for advanced
                        totalWorkouts = 21;
                        break;
                    default:
                        levelOffset = 0;
                        totalWorkouts = 10; // Default to beginner if user_level is invalid
                }

                // Get current workouts done
                int workoutsDone = rs.getInt("workout_done");
                int workoutCatalogIndex = workoutsDone + levelOffset;

                // Calculate progress
                double progress = (double) workoutsDone / totalWorkouts;
                progress = Math.min(progress, 1.0); // Ensure the progress does not exceed 1.0
                progressBar.setProgress(progress);

                // Fetch current workout from the catalog
                String workoutQuery = "SELECT workout_title FROM workout_catalog WHERE workout_id = ?";
                PreparedStatement workoutPst = conn.prepareStatement(workoutQuery);
                workoutPst.setInt(1, workoutCatalogIndex); // Use calculated catalog index
                ResultSet workoutRs = workoutPst.executeQuery();

                if (workoutRs.next()) {
                    // Populate current workout text
                    currentWorkoutText.setText(workoutRs.getString("workout_title"));
                } else {
                    currentWorkoutText.setText("No workout found."); // Fallback if no result
                }

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
    void progressBtn(MouseEvent event) {

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
