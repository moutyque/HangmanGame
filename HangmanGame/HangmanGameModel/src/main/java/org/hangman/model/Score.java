package org.hangman.model;

public 	class Score {
	private final int score;
	private final int nbWord ;
	private final String pseudo;

	public int getScore() {
		return score;
	}

	public String getPseudo() {
		return pseudo;
	}

	public Score(int inScore,int inNbWord,String inPseudo) {
		this.score = inScore;
		this.nbWord = inNbWord;
		this.pseudo = inPseudo;
	}

	public int getNbWord() {
		return nbWord;
	}

	@Override
	public String toString() {

		return String.format("%s -> %s Pts (%s mots)", this.pseudo,this.score,this.nbWord);
	}
}
