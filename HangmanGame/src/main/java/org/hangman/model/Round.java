package org.hangman.model;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Round {

	private final String word;
	private final String convertedWord;
	private String guessWord;
	private int errorsCount;
	private boolean isOver = false;
	public boolean isOver() {
		return isOver;
	}


	private final int[] scores = new int[]{100,50,35,25,15,10,5};
	private Set<Character> usedChar = new HashSet<>();

	public Round(String word) {
		this.word = word;
		StringBuilder sb = new StringBuilder();
		convertedWord = unAccent(word);



		for(char c : convertedWord.toCharArray()) {
			if(c=='-') {
				sb.append(c);
			}else {
				sb.append(Constante.DEFAULT_CHAR);
			}

		}
		this.guessWord = sb.toString();
	}

	public String getGuessWord() {
		return guessWord;
	}
	public void setGuessWord(String guessWord) {
		this.guessWord = guessWord;
	}
	public int getErrorsCount() {
		return errorsCount;
	}
	public void setErrorsCount(int errorsCount) {
		this.errorsCount = errorsCount;
	}
	public String getWord() {
		return word;
	}
	public String getConvertedWord() {
		return convertedWord;
	}
	public static String unAccent(String s) {
		//
		// JDK1.5
		//   use sun.text.Normalizer.normalize(s, Normalizer.DECOMP, 0);
		//
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	public int getRoundScore() {
		if(errorsCount>6) {
			return 0;
		}else {
			return scores[errorsCount];
		}
	}
	
	
	public void submitChar(char c) {
		if(!usedChar.contains(c)) {
			boolean isPresent = false;
			StringBuilder tmpWord = new StringBuilder(guessWord);

			for(int i = 0;i<convertedWord.length();i++) {
				if(c==convertedWord.charAt(i)) {
					isPresent = true;
					tmpWord.setCharAt(i, word.charAt(i));
				}				
			}
			guessWord = tmpWord.toString();
			if(!isPresent) errorsCount++;
			usedChar.add(c);
		}
		
		if(errorsCount>6 || !guessWord.contains(Constante.DEFAULT_CHAR)) {
			isOver = true;
		}
		
	}
	

}
