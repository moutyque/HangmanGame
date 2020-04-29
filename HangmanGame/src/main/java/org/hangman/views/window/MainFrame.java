package org.hangman.views.window;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.hangman.controller.Game;
import org.hangman.views.menus.AboutMenue;
import org.hangman.views.menus.FilesMenu;
import org.hangman.views.panel.AboutPanel;
import org.hangman.views.panel.GamePanel;
import org.hangman.views.panel.HomePanel;
import org.hangman.views.panel.RulesPanel;
import org.hangman.views.panel.ScoresPanel;

public class MainFrame extends JFrame implements Observer {
	private Dimension defaultDim = new Dimension(900,600);

	private JPanel homePanel = new HomePanel();
	private JPanel gamePanel;
	private JPanel scorePanel= new ScoresPanel();
	private JPanel rulesPanel= new RulesPanel();
	private JPanel aboutPanel= new AboutPanel();
	
	private final Game game;

	public MainFrame(String title, Game game)  {
		
		super(title);
		this.gamePanel = new GamePanel(game);
		this.setSize(defaultDim);
		this.game = game;
		
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
		if(this.game.isOver()) {
			if(this.game.isPseudoNeeded()) {
				String s = (String)JOptionPane.showInputDialog(						 
						   "Bravo, votre score fait partie du top 10, entrez un pseudo pour pouvoir le sauvegarder : ",
						   "Choix d'un pseudo");
				this.game.setPseudo(s);
				this.switchToScorePanel();
			}
			else {
				JOptionPane.showMessageDialog(this, "Vous avez perdu et votre score ne fait pas partie du top 10.");
				this.switchToHomePanel();
			}
		}
		else {
			this.getContentPane().repaint();
		}
		

	}

	private void switchToHomePanel() {
		this.setContentPane(homePanel);
		updatPanel();
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
