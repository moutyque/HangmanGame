package org.hangman.views.panel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.hangman.controller.PropertiesLoader;
import org.hangman.controller.Scoremanager;
import org.hangman.model.Score;
import org.hangman.views.font.FontFactory;

public class ScoresPanel extends JPanel {
	private List<Score> scores =new ArrayList<>();

	private int margin = 20;

	public ScoresPanel() {
		

		scores = Scoremanager.getTenTopScore();
		int limite = Math.min(scores.size(), 10);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(int i = 0;i<limite;i++) {
			JLabel label = new JLabel(scores.get(i).toString());
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(FontFactory.getTextFont());
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(label);
		}

		this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));

	}


}
