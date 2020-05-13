package org.HangmanGameFXViews.view;



import org.HangmanGameFXViews.Main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	public void clickableMenu(ActionEvent e){
	    System.out.println("Menu clicked");
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
