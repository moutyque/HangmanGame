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

import static org.hangman.model.Constante.*;


public class MainFrame extends JFrame implements Observer {
	private Dimension defaultDim = new Dimension(900,600);

	private HomePanel homePanel = new HomePanel();
	private JPanel gamePanel;
	private ScoresPanel scorePanel= new ScoresPanel();
	private RulesPanel rulesPanel= new RulesPanel();
	private AboutPanel aboutPanel= new AboutPanel();
	
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
		if(Integer.class.isInstance(arg)) {
			int param = (int)arg;
			switch(param) {
			case UPDATE_ASK_PSEUDO:
				String s = (String)JOptionPane.showInputDialog(						 
						   "Bravo, votre score fait partie du top 10, entrez un pseudo pour pouvoir le sauvegarder : ",
						   "Choix d'un pseudo");
				this.game.setPseudo(s);
				break;
			case UPDATE_DISPALY_SCORE:
				this.switchToScorePanel();
				break;
			case UPDATE_DISPALY_HOME:
				JOptionPane.showMessageDialog(this, "Vous avez perdu et votre score ne fait pas partie du top 10.");
				this.switchToHomePanel();
				break;
			case UPDATE_REFRESH_GAME_PANEL:
				this.getContentPane().repaint();
				break;
			case UPDATE_END_GAME:
				JOptionPane.showMessageDialog(this, "Vous avez perdu, le mot à trouver était : " + this.game.getCurrentRound().getWord());
				break;
			case UPDATE_END_ROUND:
				JOptionPane.showMessageDialog(this, "Vous avez gagné ce round, le mot à trouver était : " + this.game.getCurrentRound().getWord());
				break;


			}
				
			
		}
		
		

	}

	private void switchToHomePanel() {
		updatPanel(homePanel);
	}



	private void updatPanel(JPanel panel){
		
		this.setContentPane(panel);
		this.getContentPane().revalidate();

		this.getContentPane().repaint();
	}

	public void switchToGamePanel() {
		this.game.newGame();
		updatPanel(gamePanel);
	}



	public void switchToScorePanel() {
		scorePanel.updateScore();
		updatPanel(scorePanel);

	}



	public void switchToRulePanel() {
		updatPanel(rulesPanel);

	}



	public void switchToAboutPanel() {
		updatPanel(aboutPanel);

	}





}
