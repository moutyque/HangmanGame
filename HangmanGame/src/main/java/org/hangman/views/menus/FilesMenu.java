package org.hangman.views.menus;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.hangman.views.window.MainFrame;

public class FilesMenu extends JMenu {

	private MainFrame frame = null;
	
	
	public FilesMenu() {
		super();
		this.setText("Fichiers");
		
	}
	public void setFrame(MainFrame fram) {
		this.frame = fram;
		
		JMenuItem newGame = new JMenuItem("Nouveau");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchToGamePane();
				
			}
		});
		JMenuItem scoreBoard = new JMenuItem("Score");
		scoreBoard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchToScorePane();
				
			}
		});
		
		
		JMenuItem rules = new JMenuItem("Regles");
		rules.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchToRulePane();
				
			}
		});
		this.add(newGame);
		this.add(scoreBoard);
		this.add(rules);
		
		
	}

	

}
