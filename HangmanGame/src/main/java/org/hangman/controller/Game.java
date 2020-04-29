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
	

	private List<Round> rounds = new ArrayList<>();
	private List<String> dico = new ArrayList<>();
	
	
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
		if(round.getErrorsCount() > 6) {
			endGame();
		}
		else{
			nextRound();	
		}
		
	}
	
	private void nextRound() {
		// TODO Auto-generated method stub
		
	}

	private void endGame() {
		List<Score> scores = Scoremanager.getTenTopScore();
		if(scores.get(scores.size()-1).getScore() < getGameScore()) {
		
			
		}
		
		
	}

	public void newRound() {
		int wordNumber = new Random().nextInt(dico.size());
		rounds.add(new Round(dico.get(wordNumber)));
		gameScore = rounds.stream().mapToInt(Round::getRoundScore).reduce(0, (a,b)->a+b);
		
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
	
}
