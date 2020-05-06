package org.hangman.views.swing.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hangman.controller.Game;

public class LetterButtonListener implements ActionListener {
	private final Game game;

	public LetterButtonListener(Game game) {
		this.game = game;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		this.game.submitChar(e.getActionCommand().charAt(0));

	}

}
