package org.hangman.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import org.hangman.model.Constante;
import org.hangman.model.Round;
import org.hangman.model.Score;
import org.hangman.views.helper.FileLoader;

public class Game extends Observable {
	private int gameScore =0;
	private boolean isOver=false;
	private boolean needPseudo=false;

	public boolean isPseudoNeeded() {
		return needPseudo;
	}

	private List<Round> rounds = new ArrayList<>();
	private List<String> dico = new ArrayList<>();
	private String pseudo = "";


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
		newRound();	
	}	

	public void submitChar(char c){ 
		Round round = this.getCurrentRound();
	
		round.submitChar(c);
		setChanged();
		update();
		if(round.getErrorsCount() > 6) {
			endGame();
		}
		else if(round.isOver()){
			nextRound();	
		}
	
	}

	private synchronized void update() {
		notifyObservers();
	}
	
	private void nextRound() {
		update();
		newRound();

	}

	private void endGame() {
		List<Score> scores = Scoremanager.getTenTopScore();
		isOver = true;
		if(scores.get(scores.size()-1).getScore() < this.getGameScore()) {
			needPseudo = true;
			update();
			Scoremanager.addScore(this.getGameScore(),this.rounds.size()-1,this.pseudo);
		}
		else {
			update();
		}


	}

	public void newRound() {
		int wordNumber = new Random().nextInt(dico.size());
		rounds.add(new Round(dico.get(wordNumber)));
		gameScore = rounds.stream().mapToInt(Round::getRoundScore).reduce(0, (a,b)->a+b)-rounds.get(rounds.size()-1).getRoundScore();

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
