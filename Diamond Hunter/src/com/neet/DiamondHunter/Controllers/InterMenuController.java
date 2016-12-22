/*
 *  InterMenuController.java	22/12/16
 */
package com.neet.DiamondHunter.Controllers;

import java.io.IOException;
import com.neet.DiamondHunter.Game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Muhammad Faiz Bin Mohd Rashidi
 * @version 2.0  22/12/16
 * 
 * This class contains the objects that will be loaded into the scene
 */
public class InterMenuController {

    @FXML private Button skipButton;
    @FXML private Button setMapButton;
    
    /**
     * @param ae is an event handler that controls what will the button do when pressed
     * 
     * This method is connected to the button "skipButton"
     * launches the Diamond Hunter Game
     */
    public void gameButtonPressed( ActionEvent ae ) throws IOException{
    	
        Game newGame;
        
    	newGame = new Game();
    	newGame.runGame(null);
    }

    /**
     * This method launches the Map Viewer Program
     */
    public void mapButtonPressed(ActionEvent ae) throws IOException{
    	
       	Stage stage;
    	Parent root;
    	
    	stage = (Stage) setMapButton.getScene().getWindow(); //Get current stage
    	root = FXMLLoader.load(getClass().getResource("/com/neet/DiamondHunter/FXML/MapView.fxml"));
    	
    	Scene scene = new Scene(root);
    	
    	scene.getStylesheets().add(getClass().getResource("/com/neet/DiamondHunter/CSS/MapViewer.css").toExternalForm());
    	stage.getIcons().add(new Image("/Image/Diamond.jpg"));
    	stage.setTitle("Diamond Hunter Map Editor");
    	stage.setScene(scene);
    	stage.show();
    }
}