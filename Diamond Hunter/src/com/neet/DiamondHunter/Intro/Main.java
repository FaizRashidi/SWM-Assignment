package com.neet.DiamondHunter.Intro;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/neet/DiamondHunter/FXML/MenuIntro.fxml"));
			Scene introScene = new Scene(root);
			introScene.getStylesheets().add(getClass().getResource("/com/neet/DiamondHunter/CSS/Intro.css").toExternalForm());
			primaryStage.setTitle("Diamond Hunter");
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
