package org.hangman.views.pan;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.hangman.views.helper.FileLoader;

public class HomePan extends JPanel {

	private final String resourcesPath = "textfiles/resources.properties";
	private final String homePicturePath = "pictures/homePicture.jpg";


	public HomePan() {
		Properties prop = new Properties();
		try {
			prop = FileLoader.getPropertyFile(resourcesPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel(prop.getProperty("Home.Header"),SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 22));
		this.add(label, BorderLayout.NORTH);
		
		
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setLineWrap(true);
		text.setFont(new Font("Arial", Font.BOLD,14));
		text.append(prop.getProperty("Goal"));
		text.append(prop.getProperty("Home.Footer"));

		ImagePanel img = new ImagePanel();
		try {
			img.setImage(ImageIO.read(FileLoader.getFile(homePicturePath)) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.add(img,BorderLayout.CENTER);
		this.add(text,BorderLayout.SOUTH);
	}


}
