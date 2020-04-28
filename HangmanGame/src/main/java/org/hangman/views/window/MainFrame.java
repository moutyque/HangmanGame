package org.hangman.views.window;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.hangman.views.menus.AboutMenue;
import org.hangman.views.menus.FilesMenu;
import org.hangman.views.panel.HomePan;

public class MainFrame extends JFrame implements Observer {
	private Dimension defaultDim = new Dimension(700,500);

	private JPanel homePanel = new JPanel();
	private JPanel gamePanel= new JPanel();
	private JPanel scorePanel= new JPanel();
	private JPanel rulesPane= new JPanel();
	private JPanel aboutPane= new JPanel();

	private JPanel currentPanel= new JPanel();

	public MainFrame(String title) throws HeadlessException {
		super(title);
		this.setSize(defaultDim);

		homePanel = new HomePan();
		currentPanel = homePanel;
		this.setContentPane(currentPanel);


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



	public void switchToGamePane() {
		currentPanel = gamePanel;		
	}



	public void switchToScorePane() {
		currentPanel = scorePanel;

	}



	public void switchToRulePane() {
		currentPanel = rulesPane;

	}



	public void switchToAboutPant() {
		currentPanel = aboutPane;

	}





}