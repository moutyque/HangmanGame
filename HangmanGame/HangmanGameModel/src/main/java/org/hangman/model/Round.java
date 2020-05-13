package org.hangman.model;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Round {

	private final String word;
	private final String convertedWord;
	private StringProperty guessWord = new SimpleStringProperty();
	private IntegerProperty errorsCount = new SimpleIntegerProperty();
	public StringProperty getGuessWord() {
		return guessWord;
	}

	public void setGuessWord(StringProperty guessWord) {
		this.guessWord = guessWord;
	}

	public IntegerProperty getErrorsCount() {
		return errorsCount;
	}

	public void setErrorsCount(IntegerProperty errorsCount) {
		this.errorsCount = errorsCount;
	}

	


	private boolean isOver = false;
	public boolean isOver() {
		return isOver;
	}


	private final int[] scores = new int[]{100,50,35,25,15,10,5};
	private ObjectProperty<Set<Character>> usedChar = new SimpleObjectProperty<>();
	public ObjectProperty<Set<Character>> getUsedChar() {
		return usedChar;
	}

	public void setUsedChar(ObjectProperty<Set<Character>> usedChar) {
		this.usedChar = usedChar;
	}


	public Round(String word) {
		this.word = word;
		StringBuilder sb = new StringBuilder();
		convertedWord = unAccent(word);
		usedChar.set(new HashSet<Character>());


		for(char c : convertedWord.toCharArray()) {
			if(c=='-') {
				sb.append(c);
			}else {
				sb.append(Constante.DEFAULT_CHAR);
			}

		}
		this.guessWord.set(sb.toString());
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
		if(errorsCount.get()>6) {
			return 0;
		}else {
			return scores[errorsCount.get()];
		}
	}
	
	
	public void submitChar(char c) {
		c = Character.toLowerCase(c);
		if(!usedChar.get().contains(c)) {
			boolean isPresent = false;
			StringBuilder tmpWord = new StringBuilder(guessWord.get());

			for(int i = 0;i<convertedWord.length();i++) {
				if(c==convertedWord.charAt(i)) {
					isPresent = true;
					tmpWord.setCharAt(i, word.charAt(i));
				}				
			}
			
			usedChar.get().add(c);
			if(!isPresent) {
				int errorCount = errorsCount.get()+1;
				if(errorCount>6) isOver = true;
				errorsCount.set(errorCount);
			}
			else {
				if(!tmpWord.toString().contains(Constante.DEFAULT_CHAR))isOver = true;
				guessWord.set(tmpWord.toString());
			}
			
			
		}
		
		
		
	}
	

}
