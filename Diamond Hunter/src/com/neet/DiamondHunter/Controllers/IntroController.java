/*
 *  IntroController.java	22/12/16
 */
package com.neet.DiamondHunter.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Muhammad Faiz Bin Mohd Rashidi
 * @version 2.0 22/12/16
 * 
 * This class acts as a main entry for the Diamond Hunter User Interface program
 */
public class IntroController implements Initializable {
	
	/*
	 * FXML declaration
	 */
    @FXML private Button playButton;
    @FXML private AnchorPane content;
    @FXML private ImageView man;
    
    /*
     * This method initializes the class 
     * Loads an image 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	Image image = new Image("/Image/Man.jpg");
  
    	man.setImage(image);
    	
    }
    
    /**
     * This method is used for the button "playButton"
     * Gets the player to the next scene but same stage
     */
    public void buttonPressed(ActionEvent ae) throws IOException{
    	    	
    	Stage stage;
    	Parent root;
    	
    	stage = (Stage) playButton.getScene().getWindow();  /*Uses the current stage as a new stage*/
    	root = FXMLLoader.load(getClass().getResource("/com/neet/DiamondHunter/FXML/InterMenu.fxml"));
    	
    	Scene scene = new Scene(root);
    	
    	scene.getStylesheets().add(getClass().getResource("/com/neet/DiamondHunter/CSS/InterMenu.css").toExternalForm());
    	stage.getIcons().add(new Image("/Image/Diamond.jpg"));
    	stage.setScene(scene);
    	stage.show();
    }

	
}
