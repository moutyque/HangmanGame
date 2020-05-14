package org.HangmanGameFXViews.view;

import org.hangman.helper.PropertiesLoader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
		Image img = new javafx.scene.image.Image( this.getClass().getClassLoader().getResourceAsStream("pictures/homePicture.jpg"));
		image.setImage(img);
		if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = image.getFitWidth() / img.getWidth();
            double ratioY = image.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            image.setX((image.getFitWidth() - w) / 2);
            image.setY((image.getFitHeight() - h) / 2);

        }
		footerText.setText(PropertiesLoader.getInstance().getProperties().getProperty("Goal"));
		footerText.appendText(PropertiesLoader.getInstance().getProperties().getProperty("Home.Footer"));
	}
	
}
