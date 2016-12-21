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

public class MapController implements Initializable {
	
    
    @FXML
    private Canvas canvas;

    @FXML
    private Button playButton,backButton;
    
    @FXML
    private ImageView boatImage,axeImage;
    
    @FXML
    private TextField boatFieldX,axeFieldX,warnField,boatFieldY,axeFieldY;

	MapUtilities mapComp;

	Game playGame;
	
	public static int axePositionX,boatPositionX,axePositionY,boatPositionY;
	
	public static int flag = 0,boatFlag = 0,axeFlag = 0;   
	
	String a,b,c,d;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		boatImage.setImage(new Image("/Image/Boat.png"));
		axeImage.setImage(new Image("/Image/FireAxe.png"));
				
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		
		mapComp = new MapUtilities();
		
		mapComp.loadMap("/Maps/testmap.map"); //To load tilemap
		mapComp.loadTile("/Tilesets/testtileset.gif"); //To load tileset
		mapComp.drawMapViewer( graphicsContext );
		
		boatImage.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				boatFlag = 1;
			}	
		});
		
		axeImage.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				axeFlag = 1;
				
			}
			
		});
		
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {

		        int c = (int) me.getX();
		        int r = (int) me.getY();
		        System.out.println(c/16);
		        System.out.println(r/16);
		        
		        if(boatFlag == 1){
		        	
			        boatFieldX.setText(Integer.toString(c/16));
			        boatFieldY.setText(Integer.toString(r/16));
			        boatFlag = 0;
			        
		        }else if(axeFlag == 1){
		        	
			        axeFieldX.setText(Integer.toString(c/16));
			        axeFieldY.setText(Integer.toString(r/16));
			        axeFlag = 0;
		        }
		    }
		});
		
	}
	
	public void clickPlay(ActionEvent event){
		
		getInput();
		validateInput();

	}
	
	public void getInput(){
		
		a = axeFieldX.getText().trim();
		b = axeFieldY.getText().trim();
		c = boatFieldX.getText().trim();
		d = boatFieldY.getText().trim();

	}
	
	private void validateInput() {
		
		if (axeFieldX.getText() == null || axeFieldX.getText().trim().isEmpty() || axeFieldY.getText() == null || axeFieldY.getText().trim().isEmpty() || boatFieldX.getText() == null || boatFieldX.getText().trim().isEmpty() || boatFieldY.getText() == null || boatFieldY.getText().trim().isEmpty()) {
			
	        Alert fail= new Alert(AlertType.INFORMATION);
	        fail.setHeaderText("Error");
	        fail.setContentText("Some fields have no input");
	        fail.showAndWait();
			
		}else{
			
			stringToInteger();
			
			validatePosition();
			
		}
		
	}

	private void validatePosition() {

		if(MapUtilities.newmaps[axePositionY][axePositionX] >= 20 || MapUtilities.newmaps[boatPositionY][boatPositionX] >= 20){
	        Alert fail= new Alert(AlertType.INFORMATION);
	        fail.setHeaderText("Error");
	        fail.setContentText("Position is blocked");
	        fail.showAndWait();
	        
		}else{
			
			flag = 1;
			
			playGame = new Game();	
			playGame.runGame(null);
		}
	}

	private void stringToInteger() {
		
		axePositionX = Integer.parseInt(a);
		axePositionY = Integer.parseInt(b);
		boatPositionX = Integer.parseInt(c);
		boatPositionY = Integer.parseInt(d);
		
	}

	public void clickBack(ActionEvent event) throws IOException{
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

