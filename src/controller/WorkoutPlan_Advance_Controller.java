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
public class WorkoutPlan_Advance_Controller {


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
          
    private List<String> advanceURLs = Arrays.asList(
    		// Advanced - Day 1
    		"https://www.youtube.com/embed/U0pKPm0WIqU?autoplay=1",
    		"https://www.youtube.com/embed/E6jC8ruqcZo?autoplay=1",
    		"https://www.youtube.com/embed/FwilWVNs684?autoplay=1",
    		"https://www.youtube.com/embed/pUoS0LBee6w?autoplay=1",

    		// Advanced - Day 2
    		"https://www.youtube.com/embed/yRMHoF1utH0?autoplay=1",
    		"https://www.youtube.com/embed/Fy7BR1HM_uM?autoplay=1",
    		"https://www.youtube.com/embed/3FvBJRK6jsI?autoplay=1",
    		"https://www.youtube.com/embed/K1hrMY7H4P0?autoplay=1",

    		// Advanced - Day 3
    		"https://www.youtube.com/embed/wC5mvPAbfjo?autoplay=1",
    		"https://www.youtube.com/embed/v1V_oTP4DDs?autoplay=1",
    		"https://www.youtube.com/embed/GpVWvrbNiXM?autoplay=1",
    		"https://www.youtube.com/embed/b-6pYXeO0LM?autoplay=1",

    		// Advanced - Day 4
    		"https://www.youtube.com/embed/za2nKEzalBY?autoplay=1",

    		// Advanced - Day 5
    		"https://www.youtube.com/embed/OZI67txE3vY?autoplay=1",
    		"https://www.youtube.com/embed/5H2Kbyoobr4?autoplay=1",
    		"https://www.youtube.com/embed/L-U6JjkRL8U?autoplay=1",
    		"https://www.youtube.com/embed/j-q7yDVgn44?autoplay=1",

    		// Advanced - Day 6
    		"https://www.youtube.com/embed/dEFFAFjOzzI?autoplay=1",
    		"https://www.youtube.com/embed/DxPomNyPLSw?autoplay=1",
    		"https://www.youtube.com/embed/E4MLmCdAPMg?autoplay=1",
    		"https://www.youtube.com/embed/DAva9ZGTf2s?autoplay=1"
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

                    // Check if the user has completed all workouts (Day 6, Exercise 21)
                    if (workoutProgress == 21) {
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
                    numOfSetsText.setText("2 sets");
                    durationText.setText("8 minutes");
                } else {
                    System.err.println("Warm-up URL list is empty!");
                }
            } else {
                // Otherwise, play the next workout video based on progress
                if (workoutProgress <= 21) { // Ensure progress is within valid bounds
                    int adjustedWorkoutId = workoutProgress + 26; // Add 10 to match catalog offset
                    playYouTubeVideo(advanceURLs.get(workoutProgress - 1)); // -1 for zero-based index

                    // Query to get the workout title and description from the workout_catalog table
                    String selectWorkoutQuery = "SELECT workout_title, workout_description, duration, numOfSets FROM workout_catalog WHERE workout_id = ?";
                    try (PreparedStatement workoutPst = conn.prepareStatement(selectWorkoutQuery)) {
                        workoutPst.setInt(1, adjustedWorkoutId); // Use adjusted workout ID
                        ResultSet workoutRs = workoutPst.executeQuery();

                        if (workoutRs.next()) {
                            workoutTitleText.setText(workoutRs.getString("workout_title"));
                            workoutDescriptionText.setText(workoutRs.getString("workout_description"));
                            numOfSetsText.setText(workoutRs.getString("numOfSets"));
                            durationText.setText(workoutRs.getString("duration"));
                        } else {
                            System.out.println("No workout found for the specified workout ID.");
                        }
                        workoutRs.close();
                    }
                } else {
                    System.out.println("You have completed all intermediate-level videos!");
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
            // Step 1: Retrieve the current workout progress from the database
            String selectUserQuery = "SELECT workout_done FROM users WHERE username = ?";
            PreparedStatement selectPst = conn.prepareStatement(selectUserQuery);
            selectPst.setString(1, username);

            ResultSet userRs = selectPst.executeQuery();
            int workoutProgress = 0;

            if (userRs.next()) {
                workoutProgress = userRs.getInt("workout_done");
            }

            userRs.close();
            selectPst.close();

            // Step 2: Check if the user has completed all intermediate workouts
            if (workoutProgress >= 21) {
                System.out.println("You have completed all intermediate-level workouts!");

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

            // Step 3: Increment workout progress in the database
            String updateUserQuery = "UPDATE users SET workout_done = workout_done + 1 WHERE username = ?";
            PreparedStatement updatePst = conn.prepareStatement(updateUserQuery);
            updatePst.setString(1, username);
            int rowsAffected = updatePst.executeUpdate();

            if (rowsAffected > 0) {
                workoutProgress++; // Update progress locally
                System.out.println("Workout progress updated for user: " + username);
            } else {
                System.err.println("Failed to update workout progress for user: " + username);
            }
            updatePst.close();

            // Step 4: Determine the workout day based on progress
            int workoutDay = 0;
            if (workoutProgress >= 1 && workoutProgress <= 4) {
                workoutDay = 1;
            } else if (workoutProgress >= 5 && workoutProgress <= 8) {
                workoutDay = 2;
            } else if (workoutProgress >= 9 && workoutProgress <= 12) {
                workoutDay = 3;
            } else if (workoutProgress == 13) {
                workoutDay = 4;
            } else if (workoutProgress >= 14 && workoutProgress <= 17) {
                workoutDay = 5;
            } else if (workoutProgress >= 18 && workoutProgress <= 21) {
                workoutDay = 6;
            } 
            

            // Update the workout_day in the database
            String updateDayQuery = "UPDATE users SET workout_day = ? WHERE username = ?";
            PreparedStatement updateDayPst = conn.prepareStatement(updateDayQuery);
            updateDayPst.setInt(1, workoutDay);
            updateDayPst.setString(2, username);
            updateDayPst.executeUpdate();
            updateDayPst.close();

            // Step 5: Fetch and play the next workout video
            if (workoutProgress <= 21) {
                int workoutCatalogId = workoutProgress + 26; // Apply the offset for intermediate workouts
                playYouTubeVideo(advanceURLs.get(workoutProgress - 1)); // Adjust for 0-based index

                // Retrieve workout details from the workout_catalog table
                String selectWorkoutQuery = "SELECT workout_title, workout_description, duration, numOfSets FROM workout_catalog WHERE workout_id = ?";
                PreparedStatement workoutPst = conn.prepareStatement(selectWorkoutQuery);
                workoutPst.setInt(1, workoutCatalogId);

                ResultSet workoutRs = workoutPst.executeQuery();

                if (workoutRs.next()) {
                    workoutTitleText.setText(workoutRs.getString("workout_title"));
                    workoutDescriptionText.setText(workoutRs.getString("workout_description"));
                    numOfSetsText.setText(workoutRs.getString("numOfSets"));
                    durationText.setText(workoutRs.getString("duration"));
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
