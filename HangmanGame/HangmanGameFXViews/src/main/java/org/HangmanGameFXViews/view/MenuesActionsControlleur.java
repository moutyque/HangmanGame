package org.HangmanGameFXViews.view;



import org.HangmanGameFXViews.Main;
import org.hangman.helper.PropertiesLoader;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class MenuesActionsControlleur {
	private Stage stageDialogue;
	private Main main;

	@FXML
	private Menu about;
	@FXML
	private MenuBar menusBar;
	@FXML
	private MenuItem dummyMenuItem;
	@FXML
	private Menu files;
	
	@FXML
	private MenuItem newGame;
	@FXML
	private MenuItem scores;
	@FXML
	private MenuItem rules;
	@FXML
	private MenuItem exit;
	
	@FXML
	public void initialize() {
		
		files.setText(PropertiesLoader.getInstance().getProperties().getProperty("Menu.Files"));
		about.setText(PropertiesLoader.getInstance().getProperties().getProperty("Menu.About"));
		
		newGame.setText(PropertiesLoader.getInstance().getProperties().getProperty("Menu.Files.New"));
		newGame.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		
		scores.setText(PropertiesLoader.getInstance().getProperties().getProperty("Menu.Files.Score"));
		scores.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		
		rules.setText(PropertiesLoader.getInstance().getProperties().getProperty("Menu.Files.Rules"));
		rules.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
		
		exit.setText(PropertiesLoader.getInstance().getProperties().getProperty("Menu.Files.Exit"));
		exit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

	}
	
	@FXML
	public void switchToRules() {
		main.switchToRules();
	}
	
	@FXML
	public void switchToAbout() {
		
		dummyMenuItem.setDisable(true);
		main.switchToAbout();
		dummyMenuItem.setDisable(false);

	}
	
	@FXML
	public void switchToNew() {
		main.switchToNew();
	}
	@FXML
	public void switchToScore() {
		main.switchToScore();
	}

	public Stage getStageDialogue() {
		return stageDialogue;
	}

	public void setStageDialogue(Stage stageDialogue) {
		this.stageDialogue = stageDialogue;
	}

	@FXML
	public void exit() {
		stageDialogue.close();
	}

	public void setMainClass(Main m) {
		main = m;
		stageDialogue = main.getStagePrincipal();
	}
}
