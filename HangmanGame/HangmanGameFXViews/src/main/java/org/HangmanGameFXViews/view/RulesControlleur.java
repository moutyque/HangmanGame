package org.HangmanGameFXViews.view;

import java.util.Properties;

import org.hangman.helper.PropertiesLoader;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class RulesControlleur {

	@FXML
	private TextArea text;
	
	@FXML
	public void initialize() {
		Properties prop = PropertiesLoader.getInstance().getProperties();
		StringBuilder sb = new StringBuilder();
		sb.append(prop.getProperty("Goal"));
		sb.append("\n");
		sb.append("\n");

		sb.append(prop.getProperty("Score.Header"));
		sb.append("\n");

		sb.append(prop.getProperty("Score.0"));
		sb.append(prop.getProperty("Score.1"));
		sb.append(prop.getProperty("Score.2"));
		sb.append(prop.getProperty("Score.3"));
		sb.append(prop.getProperty("Score.4"));
		sb.append(prop.getProperty("Score.5"));
		sb.append(prop.getProperty("Score.6"));
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");

		sb.append(prop.getProperty("Score.Footer"));
		text.setText(sb.toString());

		
	}
}
