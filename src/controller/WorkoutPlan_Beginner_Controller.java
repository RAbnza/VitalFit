package controller;

import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private WebView workoutVideoFrame;
    private int currentVideoIndex = 0;
    private List<String> WorkoutTitle = Arrays.asList(
    		
    		);
    
    
    private List<String> warmUpURL = Arrays.asList(
    		// Use the embed URL
    		"https://www.youtube.com/embed/wJM7e0g-W6c?autoplay=1"
    );
    private List<String> beginnerURLs = Arrays.asList(
    		//Day #1:
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
    	if (currentVideoIndex == 1) {
    		//TODO: Refresh the Page
    		
    		//TODO: 
    	}
    }

    @FXML
    private void playBtn_Clicked(MouseEvent event) {
    	
    	//Warm-Up YouTube Video
        try {
            // Update the workout title
            workoutTitleText.setText("Exercise - Warm Up");

            // Check if the video list is empty
            if (warmUpURL.isEmpty()) {
                System.out.println("Error: No videos found in the list!");
                return;
            }

            // Hide the play button and load the first video
            playBtn.setVisible(false);
            playYouTubeVideo(warmUpURL.get(currentVideoIndex));
            currentVideoIndex++;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading or playing the video.");
        }
        
        
    }
    
    //Call this Function to play the video
    private void playYouTubeVideo(String url) {
        // Load the URL into the WebView
        WebEngine webEngine = workoutVideoFrame.getEngine();
        webEngine.load(url); 
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
