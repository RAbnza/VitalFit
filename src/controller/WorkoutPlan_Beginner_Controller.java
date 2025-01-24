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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    
    private List<String> intermediateURLs = Arrays.asList(
    		// Use the embed URL
    		"https://www.youtube.com/embed/ksy3Bgq1RlM?autoplay=1",
    		"https://www.youtube.com/embed/aiBV9Np9yjs?autoplay=1"
    );
    private List<String> advanceURLs = Arrays.asList(
    		// Use the embed URL
    		"https://www.youtube.com/embed/wJM7e0g-W6c?autoplay=1",
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
        
        try {
            // Check if the user's workout progress is 0
            String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";
            try (Connection conn = DriverManager.getConnection(DB_PATH)) {
                // Get the user's workout progress
                String selectUserQuery = "SELECT workout_done, workout_day FROM users WHERE username = ?";
                PreparedStatement selectPst = conn.prepareStatement(selectUserQuery);
                selectPst.setString(1, username);

                ResultSet userRs = selectPst.executeQuery();

                if (userRs.next()) {
                    workoutProgress = userRs.getInt("workout_done");
                    workoutDayText.setText(userRs.getString("workout_day"));
                }
                
                userRs.close();
                selectPst.close();
                
                // If workout progress is 0, play the warm-up video
                if (workoutProgress == 0) {
                    // Play the first warm-up video
                    if (!warmUpURL.isEmpty()) {
                        playYouTubeVideo(warmUpURL.get(0)); // Get the first video URL from the list
                        workoutTitleText.setText("Exercise - Warm Up");
                        workoutDescriptionText.setText("Get ready with a quick warm-up!");
                    } else {
                        System.err.println("Warm-up URL list is empty!");
                    }
                } else {
                    // Otherwise, skip warm-up and play the next workout video based on progress
                    if (workoutProgress < beginnerURLs.size()) {
                        // Play the next workout video
                        playYouTubeVideo(beginnerURLs.get(workoutProgress));

                        // Query to get the workout title and description from the workout_catalog table
                        String selectWorkoutQuery = "SELECT workout_title, workout_description FROM workout_catalog WHERE workout_id = ?";
                        PreparedStatement workoutPst = conn.prepareStatement(selectWorkoutQuery);
                        
                        // Set the parameter to match the current workout ID (workoutProgress)
                        workoutPst.setInt(1, workoutProgress); // Adjusting by +1 to match workout_id (assuming IDs are 1-based)

                        ResultSet workoutRs = workoutPst.executeQuery();

                        if (workoutRs.next()) {
                            workoutTitleText.setText(workoutRs.getString("workout_title"));
                            workoutDescriptionText.setText(workoutRs.getString("workout_description"));
                        } else {
                            System.out.println("No workout found for the specified workout ID.");
                        }
                        
                        workoutRs.close();
                        workoutPst.close();
                    } else {
                        System.out.println("You have completed all beginner-level videos!");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error retrieving workout progress.");
            }
            
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

    }

    @FXML
    void logoutBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void markAsDoneBtn_Clicked(ActionEvent event) {
        String DB_PATH = "jdbc:ucanaccess://./src/database/VitalFit_Database.accdb";

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            // Step 1: Increment user's workout progress in the database
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

            // Step 2: Get the updated workout progress from the database
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

            // Step 3: Determine the current workout day based on workoutProgress
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

            // Step 4: Play the next workout video based on the updated progress
            if (workoutProgress < beginnerURLs.size()) {
                // Play the next workout video
                playYouTubeVideo(beginnerURLs.get(workoutProgress));

                // Step 5: Get the workout title and description from the workout_catalog table
                String selectWorkoutQuery = "SELECT workout_title, workout_description FROM workout_catalog WHERE workout_id = ?";
                PreparedStatement workoutPst = conn.prepareStatement(selectWorkoutQuery);

                // Set the parameter to match the current workout ID (workoutProgress)
                workoutPst.setInt(1, workoutProgress); // Assuming workout_id starts at 1

                ResultSet workoutRs = workoutPst.executeQuery();

                if (workoutRs.next()) {
                    workoutTitleText.setText(workoutRs.getString("workout_title"));
                    workoutDescriptionText.setText(workoutRs.getString("workout_description"));
                } else {
                    System.out.println("No workout found for the specified workout ID.");
                }

                workoutRs.close();
                workoutPst.close();
            } else {
                System.out.println("You have completed all beginner-level videos!");
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

    }

    @FXML
    void progressBtn(MouseEvent event) {

    }

    @FXML
    void resourcesBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void workoutPlanBtn_Clicked(MouseEvent event) {

    }

}
