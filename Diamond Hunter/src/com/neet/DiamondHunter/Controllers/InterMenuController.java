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

public class InterMenuController {

    @FXML
    private Button skipButton;

    @FXML
    private Button setMapButton;
    
    Game newGame;
    
    public void mapButtonPressed(ActionEvent ae) throws IOException{
    	
       	Stage stage;
    	Parent root;
    	
    	stage = (Stage) setMapButton.getScene().getWindow(); //Get current stage
    	//Load the caller's fxml class
    	root = FXMLLoader.load(getClass().getResource("/com/neet/DiamondHunter/FXML/MapView.fxml"));
    	//Loading the objects in the scene
    	Scene scene = new Scene(root);
    	scene.getStylesheets().add(getClass().getResource("/com/neet/DiamondHunter/CSS/MapViewer.css").toExternalForm());
    	stage.getIcons().add(new Image("/Image/Diamond.jpg"));
    	stage.setTitle("Diamond Hunter Map Editor");
    	stage.setScene(scene);
    	stage.show();
    	
    }
    
    public void gameButtonPressed( ActionEvent ae ) throws IOException{
    	
    	newGame = new Game();
    	newGame.runGame(null);
    	
    }

}