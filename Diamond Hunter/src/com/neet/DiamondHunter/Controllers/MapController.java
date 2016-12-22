/*
 *  IntroController.java	22/12/16
 */
package com.neet.DiamondHunter.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.neet.DiamondHunter.Game.Game;
import com.neet.DiamondHunter.MapViewer.MapUtilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Muhammad Faiz Bin Mohd Rashidi
 * 
 * This Class handles the display of the map, 
 * and the setting of new position for the items.
 * Also handles the input from the user.
 *
 */
public class MapController implements Initializable {
	
	/* FXML component declarations	 */
    @FXML private Canvas canvas; 
    @FXML private Button playButton;  
    @FXML private Button backButton;    
    @FXML private ImageView axeImage;    
    @FXML private ImageView boatImage;  
    @FXML private TextField boatFieldX;
    @FXML private TextField axeFieldX;
    @FXML private TextField warnField;
    @FXML private TextField boatFieldY;
    @FXML private TextField axeFieldY;

	MapUtilities mapComponent;
	Game playGame;
	
	/*Inputs of new axe and boat coordinates*/
	public static int axePositionX;
	public static int boatPositionX;
	public static int axePositionY;
	public static int boatPositionY;
	
	/*Indicator for input*/
	public static int flag = 0;   
	public int boatFlag = 0;   
	public int axeFlag = 0;   
	
	/*user input*/
	String axeX;
	String axeY;
	String boatX;
	String boatY;

	/**
	 * This class initializes the controller 
	 * The map is loaded and displayed from the MapUtilities class
	 * This method also controls the mouse clicks for the new item positioning.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		boatImage.setImage(new Image("/Image/Boat.png"));
		axeImage.setImage(new Image("/Image/FireAxe.png"));
				
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		
		/*Drawing map*/
		mapComponent = new MapUtilities();
		mapComponent.loadMap("/Maps/testmap.map"); 				
		mapComponent.loadTile("/Tilesets/testtileset.gif"); 	
		mapComponent.drawMapViewer( graphicsContext );			
		
		/*If user clicks image of the boat*/
		boatImage.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				boatFlag = 1;
			}	
		});
		
		/*If user clicks image of the axe*/
		axeImage.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				axeFlag = 1;		
			}
		});
		
		/* If user clicks on map displayed.
		 * 
		 * Gets the coordinate of the map when mouse clicked
		 * and display into the text field
		 */
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {

		        int c = (int) me.getX();
		        int r = (int) me.getY();
		        
		        if(boatFlag == 1){
		        	
			        boatFieldX.setText(Integer.toString(r/16));
			        boatFieldY.setText(Integer.toString(c/16));
			        boatFlag = 0;  
		        }else if(axeFlag == 1){
		        	
			        axeFieldX.setText(Integer.toString(r/16));
			        axeFieldY.setText(Integer.toString(c/16));
			        axeFlag = 0;
		        }
		    }
		});	
	}
	
	/**
	 * This method used for the "play" buttonn
	 * 
	 */
	public void clickPlay(ActionEvent event){
		
		getInput();
		validateInput();
	}
	
	/**
	 * To get the input
	 */
	public void getInput(){
		
		axeX = axeFieldX.getText().trim();
		axeY = axeFieldY.getText().trim();
		boatX = boatFieldX.getText().trim();
		boatY = boatFieldY.getText().trim();
	}
	
	/**
	 * Validate if the textfields is empty 
	 * shows error if true
	 */
	private void validateInput() {
		
		if ( axeFieldX.getText() == null 
				|| axeFieldX.getText().trim().isEmpty() 
				|| axeFieldY.getText() == null 
				|| axeFieldY.getText().trim().isEmpty() 
				|| boatFieldX.getText() == null 
				|| boatFieldX.getText().trim().isEmpty() 
				|| boatFieldY.getText() == null 
				|| boatFieldY.getText().trim().isEmpty() ){
			
	        Alert fail= new Alert(AlertType.ERROR);
	        
	        fail.setHeaderText("Error");
	        fail.setContentText("Some fields have no input");
	        fail.showAndWait();	
	        
		}else
		{	
			stringToInteger();	
			validatePosition();
		}	
	}
	
	/**
	 * Converts the string of inputs into integer
	 */
	private void stringToInteger() {
		
		axePositionX = Integer.parseInt(axeX);
		axePositionY = Integer.parseInt(axeY);
		boatPositionX = Integer.parseInt(boatX);
		boatPositionY = Integer.parseInt(boatY);
	}

	/**
	 * Validates if the position is blocked by another entities
	 * Shows error if true
	 * 
	 * {@code flag} will be as a reference in PlayState.java class
	 * as indicator if new Coordinates have been inserted.
	 */
	private void validatePosition() {

		if( MapUtilities.newmaps[axePositionY][axePositionX] >= 20 
				|| MapUtilities.newmaps[boatPositionY][boatPositionX] >= 20 ){
			
	        Alert fail= new Alert(AlertType.ERROR);
	        
	        fail.setHeaderText("Error");
	        fail.setContentText("Position is blocked");
	        fail.showAndWait();
	        
		}else{
			
			flag = 1;
			
			playGame = new Game();								//Runs the game
			playGame.runGame(null);
		}
	}

	/**
	 * Used for the "Back" button
	 */
	public void clickBack(ActionEvent event) throws IOException{
		
	 	Stage stage;
    	Parent root;
    	
    	stage = (Stage) playButton.getScene().getWindow(); //Get current stage
    	root = FXMLLoader.load(getClass().getResource("/com/neet/DiamondHunter/FXML/InterMenu.fxml"));

    	Scene scene = new Scene(root);
    	
    	scene.getStylesheets().add(getClass().getResource("/com/neet/DiamondHunter/CSS/InterMenu.css").toExternalForm());
    	stage.getIcons().add(new Image("/Image/Diamond.jpg"));
    	stage.setTitle("Diamond Hunter");
    	stage.setScene(scene);
    	stage.show();
	}
}

