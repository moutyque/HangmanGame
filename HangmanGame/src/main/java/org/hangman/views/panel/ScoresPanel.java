package org.hangman.views.panel;

import java.awt.Component;
import java.awt.Graphics;
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

import org.hangman.helper.PropertiesLoader;
import org.hangman.helper.Scoremanager;
import org.hangman.model.Score;
import org.hangman.views.font.FontFactory;

public class ScoresPanel extends JPanel {
	private List<JLabel> labels = new ArrayList<>();
	
	private int margin = 20;

	public ScoresPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
		initScore();
	}

	private void initScore() {
		List<Score> scores = Scoremanager.getTenTopScore();
		int limite = Math.min(scores.size(), 10);
		for(int i = 0;i<limite;i++) {
			JLabel label = new JLabel(scores.get(i).toString());
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(FontFactory.getTextFont());
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			labels.add(label);
			this.add(label);
		}
	}
	
	public void updateScore() {
		
		for(JLabel label : labels) {
			this.remove(label);
		}
		
		initScore();
	}


}
