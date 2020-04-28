package org.hangman.views.window;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.hangman.views.menus.AboutMenue;
import org.hangman.views.menus.FilesMenu;
import org.hangman.views.panel.RulesPanel;
import org.hangman.views.panel.AboutPanel;
import org.hangman.views.panel.HomePanel;

public class MainFrame extends JFrame implements Observer {
	private Dimension defaultDim = new Dimension(700,500);

	private JPanel homePanel = new HomePanel();
	private JPanel gamePanel= new JPanel();
	private JPanel scorePanel= new JPanel();
	private JPanel rulesPanel= new RulesPanel();
	private JPanel aboutPanel= new AboutPanel();
	

	public MainFrame(String title)  {
		super(title);
		this.setSize(defaultDim);

		
		this.setContentPane(homePanel);


		JMenuBar bar = new JMenuBar();
		FilesMenu fileMenu = new FilesMenu();
		fileMenu.setFrame(this);
		AboutMenue aboutMenu = new AboutMenue();
		aboutMenu.setFrame(this);

		bar.add(fileMenu);
		bar.add(aboutMenu);
		this.setJMenuBar(bar);



		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	private void updatPanel(){
		this.getContentPane().repaint();
		this.getContentPane().revalidate();
	}

	public void switchToGamePanel() {
		this.setContentPane(gamePanel);	
		updatPanel();
	}



	public void switchToScorePanel() {
		this.setContentPane(scorePanel);
		updatPanel();

	}



	public void switchToRulePanel() {
		this.setContentPane(rulesPanel);
		updatPanel();

	}



	public void switchToAboutPanel() {
		this.setContentPane(aboutPanel);
		updatPanel();

	}





}
