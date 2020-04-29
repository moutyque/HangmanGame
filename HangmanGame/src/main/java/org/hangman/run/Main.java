package org.hangman.run;

import org.hangman.controller.Game;
import org.hangman.views.window.MainFrame;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		MainFrame fram = new MainFrame("Le Pendu!",game);
		game.addObserver(fram);
		

	}

}
