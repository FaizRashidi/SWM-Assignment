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

public class IntroController implements Initializable {

    @FXML
    private Button playButton;
    @FXML
    private AnchorPane content;
    @FXML
    private ImageView man;

    Stage stage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	Image image = new Image("/Image/Man.jpg");
    	
    	man.setImage(image);
    	
    }
    
    public void buttonPressed(ActionEvent ae) throws IOException{
    	    	
    	Stage stage;
    	Parent root;
    	
    	stage = (Stage) playButton.getScene().getWindow(); //Get current stage
    	//Load the caller's fxml class
    	root = FXMLLoader.load(getClass().getResource("/com/neet/DiamondHunter/FXML/InterMenu.fxml"));
    	//Loading the objects in the scene
    	Scene scene = new Scene(root);
    	scene.getStylesheets().add(getClass().getResource("/com/neet/DiamondHunter/CSS/InterMenu.css").toExternalForm());
    	stage.setScene(scene);
    	stage.show();
    }

	
}
