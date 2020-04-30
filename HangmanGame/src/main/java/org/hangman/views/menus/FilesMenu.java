package org.hangman.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.hangman.views.window.MainFrame;

public class FilesMenu extends JMenu {

	private MainFrame frame = null;
	
	
	public FilesMenu() {
		super();
		this.setText("Fichiers");
		this.setMnemonic('F');
		
	}
	public void setFrame(MainFrame fram) {
		this.frame = fram;
		
		JMenuItem newGame = new JMenuItem("Nouveau");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchToGamePanel();
				
			}
		});
		JMenuItem scoreBoard = new JMenuItem("Score");
		scoreBoard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));

		scoreBoard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchToScorePanel();
				
			}
		});
		
		
		JMenuItem rules = new JMenuItem("Regles");
		rules.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));

		rules.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchToRulePanel();
				
			}
		});
		this.add(newGame);
		this.add(scoreBoard);
		this.add(rules);
		
		
	}

	

}
