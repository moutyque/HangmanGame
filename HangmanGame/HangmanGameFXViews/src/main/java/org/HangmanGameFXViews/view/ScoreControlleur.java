package org.HangmanGameFXViews.view;

import org.hangman.helper.Scoremanager;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ScoreControlleur {
	
	@FXML
	private TextArea scoreText;
	
	@FXML
	public void initialize() {
		
		StringBuilder sb = new StringBuilder();
		Scoremanager.getTenTopScore().forEach(e->sb.append(e.toString()).append("\n"));
		scoreText.setText(sb.toString());
	}
 
}
