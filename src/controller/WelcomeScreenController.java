package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeScreenController {

    @FXML
    private Button nextBtn;

    @FXML
    void nextBtn_Clicked(ActionEvent event) {
    	System.out.println("test");
    }

}
