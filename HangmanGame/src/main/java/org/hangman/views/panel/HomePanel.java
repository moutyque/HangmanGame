package org.hangman.views.panel;

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

import org.hangman.controller.PropertiesLoader;
import org.hangman.model.Constante;
import org.hangman.views.font.FontFactory;
import org.hangman.views.helper.FileLoader;

public class HomePanel extends JPanel {

	

	
	public HomePanel() {
		
		Properties prop = PropertiesLoader.getInstance().getProperties();
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel(prop.getProperty("Home.Header"),SwingConstants.CENTER);
		label.setFont(FontFactory.getHeaderFont());
		this.add(label, BorderLayout.NORTH);
		
		
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setLineWrap(true);
		text.setFont(FontFactory.getTextFont());
		text.append(prop.getProperty("Goal"));
		text.append(prop.getProperty("Home.Footer"));

		ImagePanel img = new ImagePanel();
		try {
			img.setImage(ImageIO.read(FileLoader.getFile(Constante.HOME_PICTURE_PATH)) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.add(img,BorderLayout.CENTER);
		this.add(text,BorderLayout.SOUTH);
	}


}
