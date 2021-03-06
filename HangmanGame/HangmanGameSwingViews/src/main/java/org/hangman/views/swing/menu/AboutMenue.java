package org.hangman.views.swing.menu;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.hangman.views.swing.window.MainFrame;

public class AboutMenue extends JMenu {
	private MainFrame frame =null;


	public AboutMenue() {
		super();
		this.setText("A propos");
		this.setMnemonic('A');
		}
	
	public void setFrame(MainFrame fram) {
		this.frame = fram;		
		this.addMenuListener(new MenuListener() {
	        public void menuSelected(MenuEvent e) {
	        	frame.switchToAboutPanel();
	        }
	        public void menuDeselected(MenuEvent e) {}

	        public void menuCanceled(MenuEvent e) {}
	    });
	}



}
