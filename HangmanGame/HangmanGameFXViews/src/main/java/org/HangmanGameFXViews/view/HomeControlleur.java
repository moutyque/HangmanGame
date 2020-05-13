package org.HangmanGameFXViews.view;

import org.hangman.helper.PropertiesLoader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class HomeControlleur {

	@FXML
	private Label headerLabel;
	
	@FXML
	private TextArea footerText;
	
	@FXML
	private ImageView image;
	
	@FXML
	public void initialize() {
		headerLabel.setText(PropertiesLoader.getInstance().getProperties().getProperty("Home.Header"));
		image.setImage(new javafx.scene.image.Image( this.getClass().getClassLoader().getResourceAsStream("pictures/homePicture.jpg")));
		footerText.setText(PropertiesLoader.getInstance().getProperties().getProperty("Goal"));
		footerText.appendText(PropertiesLoader.getInstance().getProperties().getProperty("Home.Footer"));
	}
	
}
