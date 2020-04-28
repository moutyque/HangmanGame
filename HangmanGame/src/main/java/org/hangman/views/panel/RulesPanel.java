package org.hangman.views.panel;

import java.awt.BorderLayout;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.hangman.model.PropertiesAccess;
import org.hangman.views.font.FontFactory;

public class RulesPanel extends JPanel {

	public RulesPanel() {
		this.setLayout(new BorderLayout());

		Properties prop = PropertiesAccess.getInstance().getProperties();

		JLabel label = new JLabel(prop.getProperty("Score.Header"),SwingConstants.CENTER);
		label.setFont(FontFactory.getHeaderFont());
		this.add(label,BorderLayout.NORTH);
		
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setLineWrap(true);
		text.setFont(FontFactory.getTextFont());
		text.append(prop.getProperty("Goal"));

		text.append(prop.getProperty("Score.0"));
		text.append(prop.getProperty("Score.1"));
		text.append(prop.getProperty("Score.2"));
		text.append(prop.getProperty("Score.3"));
		text.append(prop.getProperty("Score.4"));
		text.append(prop.getProperty("Score.5"));
		text.append(prop.getProperty("Score.6"));
		
		text.append(prop.getProperty("Score.Footer"));
		
		this.add(text,BorderLayout.CENTER);
		
		


	}



}