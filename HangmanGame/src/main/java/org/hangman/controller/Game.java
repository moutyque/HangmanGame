package org.hangman.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import org.hangman.helper.FileLoader;
import org.hangman.helper.Scoremanager;
import org.hangman.model.Constante;
import org.hangman.model.Round;
import org.hangman.model.Score;

import static org.hangman.model.Constante.*;

public class Game extends Observable {
	
	private int gameScore;
	private boolean isOver;
	private boolean needPseudo;
	private List<Round> rounds = new ArrayList<>();
	private List<String> dico = new ArrayList<>();
	private String pseudo = "";
	
	public boolean isPseudoNeeded() {
		return needPseudo;
	}



	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Game() {
		File file = FileLoader.getFile(Constante.DICO_PATH);

		try {
			dico =  Files.readAllLines(file.toPath(),Charset.forName("Cp1252"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	newGame();
	}	

	public void newGame() {
		rounds = new ArrayList<>();
		pseudo = "";
		gameScore =0;
		isOver=false;
		needPseudo=false;
		newRound();	
		
	}

	public void submitChar(char c){ 
		Round round = this.getCurrentRound();
	
		round.submitChar(c);
		update(UPDATE_REFRESH_GAME_PANEL);
		
		if(round.getErrorsCount() > 6) {
			endGame();
		}
		else if(round.isOver()){
			nextRound();	
		}
	
	}

	private synchronized void update(int param) {
		setChanged();
		notifyObservers(param);
	}
	
	private void nextRound() {
		update(UPDATE_END_ROUND);
		newRound();

	}

	private void endGame() {
		
		
		
		List<Score> scores = Scoremanager.getTenTopScore();
		isOver = true;
		update(UPDATE_END_GAME);//Display the unfound word
		
		if(scores.get(scores.size()-1).getScore() < this.getGameScore()) {
			needPseudo = true;
			update(UPDATE_ASK_PSEUDO);//Query the pseudo
			Scoremanager.addScore(this.getGameScore(),this.rounds.size()-1,this.pseudo);
			update(UPDATE_DISPALY_SCORE);//Dispaly the score
		}
		else {
			update(UPDATE_DISPALY_HOME);//End game with no pseudo
		}


	}

	public void newRound() {
		
		int wordNumber = new Random().nextInt(dico.size());
		rounds.add(new Round(dico.get(wordNumber)));
		gameScore = rounds.stream().mapToInt(Round::getRoundScore).reduce(0, (a,b)->a+b)-rounds.get(rounds.size()-1).getRoundScore();
		update(UPDATE_REFRESH_GAME_PANEL);
		//System.out.println(this.getCurrentRound().getConvertedWord());
	}

	public Round getCurrentRound() {
		return rounds.get(rounds.size()-1);
	}


	public int getGameScore() {
		return gameScore;
	}
	public boolean isOver() {
		return isOver;
	}

	public int getNbFoundWord() {
		
		return this.rounds.size()-1;
	}

}
