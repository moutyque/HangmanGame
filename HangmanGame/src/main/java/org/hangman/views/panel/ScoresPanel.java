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

import org.hangman.model.PropertiesAccess;
import org.hangman.views.font.FontFactory;

public class ScoresPanel extends JPanel {
	private List<Score> scores =new ArrayList<>();

	private int margin = 20;

	public ScoresPanel() {
		Properties prop = PropertiesAccess.getPropeties("textfiles/score.properties");

		Set<Entry<Object, Object>> set = prop.entrySet();
		for(Entry<Object, Object> entry : set) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			scores.add(new Score(Integer.parseInt(value.split("\\|")[0]), Integer.parseInt(value.split("\\|")[1]), key));
		}
		scores.sort((a,b)->Integer.compare(b.getScore(), a.getScore()));


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

	class Score {
		private final int score;
		private final int nbWord ;
		private final String pseudo;

		public int getScore() {
			return score;
		}

		public String getPseudo() {
			return pseudo;
		}

		public Score(int inScore,int inNbWord,String inPseudo) {
			this.score = inScore;
			this.nbWord = inNbWord;
			this.pseudo = inPseudo;
		}

		public int getNbWord() {
			return nbWord;
		}

		@Override
		public String toString() {

			return String.format("%s -> %s Pts (%s mots)", this.pseudo,this.score,this.nbWord);
		}
	}
}
