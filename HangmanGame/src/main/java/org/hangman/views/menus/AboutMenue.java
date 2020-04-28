package org.hangman.views.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.hangman.views.window.MainFrame;

public class AboutMenue extends JMenuItem {
	private MainFrame frame =null;


	public AboutMenue() {
		super();
		this.setText("A propos");
		}
	
	public void setFrame(MainFrame fram) {
		this.frame = fram;		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchToAboutPanel();
			}
		});
	}



}
