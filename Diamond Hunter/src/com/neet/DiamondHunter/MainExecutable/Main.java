/*
 * 	Main.Java	v2.0  22/12/16
 */
package com.neet.DiamondHunter.MainExecutable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/*
 *  Loads the object from an FXML object and loads it into a scene.
 *	A stage is used to load the current scene.
 *	Picture of a diamond is selected as the program Icon
 */
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
	
	/*Launch the User Interface */
	public static void main(String[] args) {
		launch(args);
	}
}
