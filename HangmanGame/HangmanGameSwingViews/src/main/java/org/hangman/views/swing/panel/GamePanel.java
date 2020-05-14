package org.hangman.views.swing.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hangman.controller.Game;
import org.hangman.helper.FileLoader;
import org.hangman.helper.PropertiesLoader;
import org.hangman.views.swing.font.FontFactory;
import org.hangman.views.swing.listeners.LetterButtonListener;

public class GamePanel extends JPanel {
	private final Game game;

	String text;

	private JLabel nbFoundWords = new JLabel("");
	private JLabel currentScoreLabel = new JLabel("");
	private JLabel guessWordLabel = new JLabel("");
	private List<JButton> buttons = new ArrayList<>();
	private JLabel picture = new JLabel();
	
	public GamePanel(Game game) {
		this.game = game;

		this.setLayout(new GridLayout(1,2));

		nbFoundWords.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		guessWordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		guessWordLabel.setFont(FontFactory.getWordFont());
		guessWordLabel.setForeground(Color.BLUE);

		JPanel leftPan = new JPanel();
		leftPan.setLayout(new BoxLayout(leftPan, BoxLayout.PAGE_AXIS));
		leftPan.add(nbFoundWords);
		leftPan.add(Box.createVerticalGlue());
		leftPan.add(currentScoreLabel);
		leftPan.add(Box.createVerticalGlue());
		leftPan.add(guessWordLabel);
		leftPan.add(Box.createVerticalGlue());
		
		JPanel buttonsPanel = initButtonsPanel();
		leftPan.add(buttonsPanel);
		this.add(leftPan);
		updatePicture();
		JPanel rightPan = new JPanel();
		rightPan.add(picture);
		this.add(rightPan);
		//this.add(image);
		updateLabels();

	}


	private JPanel initButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(7,1));

		JPanel line1 = new JPanel(new FlowLayout());
		for(char c : "ABCDEFG".toCharArray()) {
			JButton button = new JButton(String.valueOf(c));
			button.addActionListener(new LetterButtonListener(this.game));
			buttons.add(button);
			line1.add(button);

		}


		JPanel line2 = new JPanel(new FlowLayout());
		for(char c : "HIJKLMN".toCharArray()) {
			JButton button = new JButton(String.valueOf(c));
			button.addActionListener(new LetterButtonListener(this.game));
			buttons.add(button);
			line2.add(button);

		}

		JPanel line3 = new JPanel(new FlowLayout());
		for(char c : "OPQRSTU".toCharArray()) {
			JButton button = new JButton(String.valueOf(c));
			button.addActionListener(new LetterButtonListener(this.game));
			buttons.add(button);
			line3.add(button);

		}



		JPanel line4 = new JPanel(new FlowLayout());
		for(char c : "VWXYZ".toCharArray()) {
			JButton button = new JButton(String.valueOf(c));
			button.addActionListener(new LetterButtonListener(this.game));
			buttons.add(button);
			line4.add(button);

		}



		buttonsPanel.add(line1);
		buttonsPanel.add(line2);
		buttonsPanel.add(line3);
		buttonsPanel.add(line4);
		return buttonsPanel;
	}


	private void updateLabels() {
		nbFoundWords.setText(String.format("Nombre de mot trouve : %s", this.game.getNbFoundWord()));
		currentScoreLabel.setText(String.format("Votre score actuel est de : %s", this.game.getGameScore()));
		guessWordLabel.setText(this.game.getCurrentRound().getGuessWord().get());
	}

	private void updateButtons() {
		Set<Character> chars =  this.game.getCurrentRound().getUsedChar().get();
		for(JButton button : this.buttons) {
			if(chars.contains(button.getText().toLowerCase().toCharArray()[0])) {
				button.setEnabled(false);
			}
			else {
				button.setEnabled(true);
			}
		}


	}
	
	private void updatePicture() {
		try {

			String pictureName = "Game.picture." + this.game.getCurrentRound().getErrorsCount().get();
			picture.setIcon(new ImageIcon(ImageIO.read(FileLoader.getFile(PropertiesLoader.getInstance().getProperties().getProperty(pictureName)))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void paintComponent(Graphics g){

		updateLabels();
		updateButtons();
		updatePicture();

	}




}
