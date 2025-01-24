package controller;

import javafx.util.Duration;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
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
    private ImageView playBtn;

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
    private MediaView workoutVideoFrame;

    private MediaPlayer mediaPlayer;
    private List<String> videoPaths; 
    private int currentVideoIndex = 0; 
    @FXML
    void logoutBtn_Clicked(ActionEvent event) {

    }
    
    @FXML
    private void markAsDoneBtn_Clicked(ActionEvent event) {
    }

    @FXML
    private void playBtn_Clicked(MouseEvent event) {
        try {
         
            workoutTitleText.setText("Exercise - Warm Up");
            // List all video files in the Warm up folder
            videoPaths = Arrays.asList(
                getClass().getResource("/Videos/Warm up/1. Neck Circles.mp4").toExternalForm(),
                getClass().getResource("/Videos/Warm up/2. Shoulder Rolls.mp4").toExternalForm(),
                getClass().getResource("/Videos/Warm up/3. Arm Cross Stretch.mp4").toExternalForm(),
                getClass().getResource("/Videos/Warm up/4. Standing Side Bend.mp4").toExternalForm(),
                getClass().getResource("/Videos/Warm up/5. Standing Hamstring Stretch.mp4").toExternalForm(),
                getClass().getResource("/Videos/Warm up/6. Hip Flexor Stretch.mp4").toExternalForm(),
                getClass().getResource("/Videos/Warm up/7. Dynamic Leg Swings.mp4").toExternalForm(),
                getClass().getResource("/Videos/Warm up/8. Cat-Cow Pose.mp4").toExternalForm()
            );

            if (videoPaths.isEmpty()) {
                System.out.println("Error: No videos found in the folder!");
                return;
            }

            playVideoForDuration(videoPaths.get(currentVideoIndex));
            playBtn.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace(); 
            System.out.println("Error loading or playing the video.");
        }
    }

    private void playVideoForDuration(String videoPath) {
        // Create Media object for the current video
        Media media = new Media(videoPath);

        mediaPlayer = new MediaPlayer(media);
        workoutVideoFrame.setMediaPlayer(mediaPlayer);

        mediaPlayer.play();

        // Set the media to loop for 30 seconds (duration of the warm-up video loop)
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Create a Timeline to stop the video after 30 seconds
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(30), (e) -> {
                mediaPlayer.stop();

                if (currentVideoIndex == videoPaths.size() - 1) {
                    Platform.runLater(this::showReadyToProceedPrompt); 
                } else {
                    currentVideoIndex++;
                    playVideoForDuration(videoPaths.get(currentVideoIndex));
                }
            })
        );

        timeline.setCycleCount(1);
        timeline.play();
    }

    private void showReadyToProceedPrompt() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ready to Proceed");
        alert.setHeaderText(null);
        alert.setContentText("Warm-up is complete. Are you ready to proceed to the workout?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                startCountdownToWorkout();
            } else {
                workoutTitleText.setText("Workout Completed");
                playBtn.setVisible(true);
            }
        });
    }

    private void startCountdownToWorkout() {
        final int[] countdown = {5}; 

        // Create a Timeline for the countdown
        Timeline countdownTimeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                if (countdown[0] > 0) {
                    // Update the countdown text
                    workoutTitleText.setText("Get Ready: " + countdown[0] + " seconds");
                    countdown[0]--;
                } else {
                  
                    ((Timeline) event.getSource()).stop();

                    workoutTitleText.setText("Bodyweight Squats");
                    playBtn.setVisible(true); 
                }
            })
        );

        countdownTimeline.setCycleCount(5);
        countdownTimeline.play();
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
    @FXML
    void background_Clicked(MouseEvent event) {
        // Your code here (if needed)
    }
}
