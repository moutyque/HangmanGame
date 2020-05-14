package org.HangmanGameFXViews;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.HangmanGameFXViews.view.GameControlleur;
import org.HangmanGameFXViews.view.MenuesActionsControlleur;
import org.hangman.helper.PropertiesLoader;
import org.hangman.helper.Scoremanager;
import org.hangman.model.Score;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage stagePrincipal;

	public Stage getStagePrincipal() {
		return stagePrincipal;
	}

	public void switchToRules() {
		mainPane.setCenter(rulesPane);
	}

	private BorderPane mainPane;   
	private AnchorPane homePane;
	private AnchorPane rulesPane;
	private AnchorPane gamePane;
	private AnchorPane scorePane;
	private AnchorPane aboutPane;

	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		stagePrincipal.setTitle("Application de gestion de personnes");
		initialisationConteneurPrincipal();
		initialisationContenu();
		initialisationRules();
		initialisationAbout();

		initialisationGame();
		switchToHome();
		intialisationHomeDisplay();  
	}

	private void initialisationGame() {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Game.fxml"));
		try {
			gamePane = loader.load();
			GameControlleur action = loader.getController();
			action.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void switchToHome() {
		mainPane.setCenter(homePane);
	}

	private void intialisationHomeDisplay() {      
		Scene scene = new Scene(mainPane);
		stagePrincipal.setScene(scene);
		stagePrincipal.show();
	}

	private void initialisationConteneurPrincipal() {
		//TODO : add interface to controller to set the main app and refactor the init methode
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainContainer.fxml"));
		try {
			mainPane = loader.load();
			MenuesActionsControlleur action = loader.getController();
			action.setMainClass(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initialisationContenu() {

		homePane = (AnchorPane) initPane("view/Home.fxml");


	}

	private void initialisationRules() {
		rulesPane = (AnchorPane) initPane("view/Rules.fxml");
	}

	private void initialisationAbout() {		
		aboutPane = (AnchorPane) initPane("view/About.fxml");
		//TODO : do a controller for about
		TextArea txt = (TextArea) aboutPane.lookup("#text");		
		Properties prop = PropertiesLoader.getPropeties("project.properties");
		StringBuilder sb = new StringBuilder();
		sb.append("Cette application a été dévelloppé par : ").append(prop.get("author.name")).append("\n");
		sb.append("Vous pouvez le contacter via ce mail : ").append(prop.get("author.mail")).append("\n");
		sb.append("Vous pouvez trouver le code source de ce projet et y participer via github: ").append(prop.get("url")).append("\n");
		txt.setText(sb.toString());
	}

	private void initialisationScore() {
		scorePane = (AnchorPane) initPane("view/Score.fxml");
	}

	private Object  initPane(String path) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(path));
		try {
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		launch(args);
	}

	public void switchToAbout() {
		mainPane.setCenter(aboutPane);		
	}

	public void switchToNew() {
		
		mainPane.setCenter(gamePane);	

	}  
	public void switchToScore() {
		initialisationScore();
		mainPane.setCenter(scorePane);	
	}



}