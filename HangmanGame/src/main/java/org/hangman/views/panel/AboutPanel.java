package org.hangman.views.panel;

import java.awt.BorderLayout;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.hangman.helper.PropertiesLoader;
import org.hangman.views.font.FontFactory;

public class AboutPanel extends JPanel {

	
	public AboutPanel() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("A propos", SwingConstants.CENTER);
		label.setFont(FontFactory.getHeaderFont());
		this.add(label,BorderLayout.NORTH);
		
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		txt.setLineWrap(true);
		Properties prop = PropertiesLoader.getPropeties("project.properties");
		txt.append("Auteur : ");
		txt.append(prop.getProperty("author.name"));
		txt.append("\n");
		txt.append("Version : ");
		txt.append(prop.getProperty("version"));
		txt.append("\n");
		txt.append("If you want to participate please find the project here : ");
		txt.append(prop.getProperty("url"));
		txt.append("\n");
		this.add(txt,BorderLayout.CENTER);


	}
	
}
