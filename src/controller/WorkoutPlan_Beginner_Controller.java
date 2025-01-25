package controller;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javafx.application.Platform;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.Event;
import javafx.scene.layout.HBox;

public class WorkoutPlan_Beginner_Controller {

    @FXML
    private Text NameText11;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Text dashboardBtn;

    @FXML
    private Text durationText;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button markAsDoneBtn;

    @FXML
    private Text numOfSetsText;

    @FXML
    private Text profileBtn;

    @FXML
    private Text progressBtn;

    @FXML
    private Text resourcesBtn;

    @FXML
    private Text workoutDayText;

    @FXML
    private Text workoutDescriptionText;

    @FXML
    private Text workoutPlanBtn;

    @FXML
    private Text workoutTitleText;

    @FXML
    private WebView workoutVideoFrame;
       
    private int workoutProgress = 0;
    
      
    private List<String> warmUpURL = Arrays.asList(
    		"https://www.youtube.com/embed/wJM7e0g-W6c?autoplay=1"
    		);
   
    private List<String> beginnerURLs = Arrays.asList(
    		//Day #1:
    		"https://www.youtube.com/embed/wJM7e0g-W6c?autoplay=1",
    		"https://www.youtube.com/embed/_gws5-2BBkg?autoplay=1",
    	    "https://www.youtube.com/embed/8lfpYwByrqA?autoplay=1",
    	    "https://www.youtube.com/embed/dJXKOaUwB1o?autoplay=1",
    	    "https://www.youtube.com/embed/N6Fv25RjGo8?autoplay=1",
    	    "https://www.youtube.com/embed/iIUe1oLbc8c?autoplay=1",
    	    
    	    //Day #2:
    	    "https://www.youtube.com/embed/DHji82G0E-0?autoplay=1",
    	    "https://www.youtube.com/embed/ci3lXPAOcuc?autoplay=1",
    	    "https://www.youtube.com/embed/p3DnicY_Y3w?autoplay=1",
    	    "https://www.youtube.com/embed/vD7Y_QbUmRs?autoplay=1",
    	    
    	    //Day #3:
    	    "https://www.youtube.com/embed/aiBV9Np9yjs?autoplay=1"
    );
    
    private String username; // Store the username for database queries

    public void setUsernameFromSession() {
        this.username = SessionManager.getInstance().getUsername();

    }
    
    //Call this Function to play the video
    private void playYouTubeVideo(String url) {
        // Load the URL into the WebView
        WebEngine webEngine = workoutVideoFrame.getEngine();
        webEngine.load(url); 
    }

    @FXML
    public void initialize() {
        setUsernameFromSession();

        String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";
        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            // Get the user's workout progress and day
            String selectUserQuery = "SELECT workout_done, workout_day FROM users WHERE username = ?";
            try (PreparedStatement selectPst = conn.prepareStatement(selectUserQuery)) {
                selectPst.setString(1, username);
                ResultSet userRs = selectPst.executeQuery();

                if (userRs.next()) {
                    workoutProgress = userRs.getInt("workout_done");
                    int workoutDay = userRs.getInt("workout_day"); // Ensure it's retrieved as an integer

                    workoutDayText.setText(String.valueOf(workoutDay));

                    // Check if the user has completed all workouts (Day 3, Exercise 10)
                    if (workoutDay == 3 && workoutProgress == 10) {
                        System.out.println("User has completed all workouts. Showing congratulations scene.");

                        // Change to the Workout Congratulations scene
                        loadScene("/layouts/WorkoutCongratulations.fxml", null);
                        return; // Exit the method to prevent further code execution
                    }
                }
                userRs.close();
            }

            // If workout progress is 0, play the warm-up video
            if (workoutProgress == 0) {
                if (!warmUpURL.isEmpty()) {
                    playYouTubeVideo(warmUpURL.get(0)); // Play the first warm-up video
                    workoutTitleText.setText("Exercise - Warm Up");
                    workoutDescriptionText.setText("Get ready with a quick warm-up!");
                } else {
                    System.err.println("Warm-up URL list is empty!");
                }
            } else {
                // Otherwise, play the next workout video based on progress
                if (workoutProgress < beginnerURLs.size()) {
                    playYouTubeVideo(beginnerURLs.get(workoutProgress));

                    // Query to get the workout title and description from the workout_catalog table
                    String selectWorkoutQuery = "SELECT workout_title, workout_description FROM workout_catalog WHERE workout_id = ?";
                    try (PreparedStatement workoutPst = conn.prepareStatement(selectWorkoutQuery)) {
                        workoutPst.setInt(1, workoutProgress); // Match workout ID
                        ResultSet workoutRs = workoutPst.executeQuery();

                        if (workoutRs.next()) {
                            workoutTitleText.setText(workoutRs.getString("workout_title"));
                            workoutDescriptionText.setText(workoutRs.getString("workout_description"));
                        } else {
                            System.out.println("No workout found for the specified workout ID.");
                        }
                        workoutRs.close();
                    }
                } else {
                    System.out.println("You have completed all beginner-level videos!");
                    // Change to Congratulations scene
                    loadScene("/layouts/WorkoutCongratulations.fxml", null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving workout progress.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error auto-playing the warm-up video.");
        }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Sign_In.fxml"));
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
    void markAsDoneBtn_Clicked(ActionEvent event) {
        String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            // Step 2: Get the current workout progress from the database
            String selectUserQuery = "SELECT workout_done FROM users WHERE username = ?";
            PreparedStatement selectPst = conn.prepareStatement(selectUserQuery);
            selectPst.setString(1, username);

            ResultSet userRs = selectPst.executeQuery();
            int workoutProgress = 0;  // Initialize workout progress

            if (userRs.next()) {
                workoutProgress = userRs.getInt("workout_done");
            }

            userRs.close();
            selectPst.close();

            // Step 3: Determine if all workouts are completed
            if (workoutProgress >= beginnerURLs.size() - 1) {
                System.out.println("You have completed all beginner-level videos!");
                
                // Change to the Workout Congratulations scene
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/WorkoutCongratulations.fxml"));
                    Parent workoutCongratulationsRoot = loader.load();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(workoutCongratulationsRoot);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return; // Exit the method to prevent further execution
            }

            // Step 1: Increment user's workout progress in the database (only if workouts are remaining)
            String updateUserQuery = "UPDATE users SET workout_done = workout_done + 1 WHERE username = ?";
            PreparedStatement updatePst = conn.prepareStatement(updateUserQuery);
            updatePst.setString(1, username);
            int rowsAffected = updatePst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Workout progress updated for user: " + username);
            } else {
                System.err.println("Failed to update workout progress for user: " + username);
            }
            updatePst.close();

            // Step 4: Get the updated workout progress and determine the workout day
            workoutProgress++; // Increment for use in determining the workout day
            int workoutDay = 0; // Default day initialization
            if (workoutProgress >= 1 && workoutProgress <= 5) {
                workoutDay = 1;
            } else if (workoutProgress >= 6 && workoutProgress <= 9) {
                workoutDay = 2;
            } else if (workoutProgress == 10) {
                workoutDay = 3;
            }

            // Update the workout_day in the users table
            String updateDayQuery = "UPDATE users SET workout_day = ? WHERE username = ?";
            PreparedStatement updateDayPst = conn.prepareStatement(updateDayQuery);
            updateDayPst.setInt(1, workoutDay);
            updateDayPst.setString(2, username);
            updateDayPst.executeUpdate();
            updateDayPst.close();

            // Step 5: Play the next workout video based on the updated progress
            if (workoutProgress < beginnerURLs.size()) {
                playYouTubeVideo(beginnerURLs.get(workoutProgress));

                // Get the workout title and description from the workout_catalog table
                String selectWorkoutQuery = "SELECT workout_title, workout_description FROM workout_catalog WHERE workout_id = ?";
                PreparedStatement workoutPst = conn.prepareStatement(selectWorkoutQuery);
                workoutPst.setInt(1, workoutProgress);

                ResultSet workoutRs = workoutPst.executeQuery();

                if (workoutRs.next()) {
                    workoutTitleText.setText(workoutRs.getString("workout_title"));
                    workoutDescriptionText.setText(workoutRs.getString("workout_description"));
                } else {
                    System.out.println("No workout found for the specified workout ID.");
                }

                workoutRs.close();
                workoutPst.close();
            }

            // Step 6: Update the workoutDayText UI element
            workoutDayText.setText(String.valueOf(workoutDay));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating or retrieving workout progress.");
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
    
    private void loadScene(String fxmlFile, Event event) {
    	// Use Platform.runLater to ensure this runs after the current stage is fully initialized
        Platform.runLater(() -> {
            try {
                // Load the Register_C FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/WorkoutCongratulations.fxml"));
                Parent WorkoutCongratulationsRoot = loader.load();

                // Get the current stage
                Stage stage = (Stage) Stage.getWindows().filtered(Window::isShowing).get(0);

                // Set the new scene
                Scene scene = new Scene(WorkoutCongratulationsRoot);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

    }

}
