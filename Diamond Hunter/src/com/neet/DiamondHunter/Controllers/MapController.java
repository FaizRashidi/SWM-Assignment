package com.neet.DiamondHunter.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.neet.DiamondHunter.Game.Game;
import com.neet.DiamondHunter.MapViewer.MapUtilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
	
	public static int flag = 0;     

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Image image = new Image("/Image/");
				
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		
		mapComp = new MapUtilities();
		
		mapComp.loadMap("/Maps/testmap.map"); //To load tilemap
		mapComp.loadTile("/Tilesets/testtileset.gif"); //To load tileset
		mapComp.drawMapViewer( graphicsContext );
		
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		        System.out.println("CLICKED!");
		    }
		});
		
	}
	
	public void clickPlay(ActionEvent event){
		getInput();
		
		flag = 1;
		
		playGame = new Game();	
		playGame.runGame(null);

	}
	

	public void getInput(){
		
		axePositionX = Integer.parseInt(axeFieldX.getText());
		axePositionY = Integer.parseInt(axeFieldY.getText());
		boatPositionX = Integer.parseInt(boatFieldX.getText());
		boatPositionY = Integer.parseInt(boatFieldY.getText());


	}
	

	

}

