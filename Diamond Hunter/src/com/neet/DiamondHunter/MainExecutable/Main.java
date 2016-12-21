package com.neet.DiamondHunter.MainExecutable;

//This class contains acts as the main executable for the mapViewer and the game.
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
						
			Parent root = FXMLLoader.load(getClass().getResource("/com/neet/DiamondHunter/FXML/MenuIntro.fxml"));
			Scene introScene = new Scene(root);
			introScene.getStylesheets().add(getClass().getResource("/com/neet/DiamondHunter/CSS/Intro.css").toExternalForm());
			primaryStage.setTitle("Diamond Hunter");
			primaryStage.getIcons().add(new Image("/Image/Diamond.jpg"));
			primaryStage.setScene(introScene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
