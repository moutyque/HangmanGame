package org.HangmanGameModel;

import java.util.HashSet;
import java.util.Set;

import org.hangman.model.Round;
import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import junit.framework.TestCase;

public class TestRound extends TestCase {


	@Test
	public void testSubmitOneRoundWithOneError() {
		Round round  = new Round("tést");
		StringProperty guessedWord = new SimpleStringProperty();
		guessedWord.set("****");
		assertEquals(guessedWord.get(),round.getGuessWord().get());
		assertEquals(0,round.getErrorsCount().get(),0);
		assertEquals("tést", round.getWord());
		assertEquals("test", round.getConvertedWord());
		assertEquals(100, round.getRoundScore(),0);
		assertFalse(round.isOver());		

		round.submitChar('t');
		guessedWord.set("t**t");
		assertEquals(guessedWord.get(),round.getGuessWord().get());
		assertEquals(0,round.getErrorsCount().get(),0);

		round.submitChar('a');
		assertEquals(1,round.getErrorsCount().get(),0);

		round.submitChar('e');
		guessedWord.set("té*t");
		assertEquals(guessedWord.get(),round.getGuessWord().get());

		round.submitChar('s');
		guessedWord.set("tést");
		assertEquals(guessedWord.get(),round.getGuessWord().get());
		assertTrue(round.isOver());		
		assertEquals(50, round.getRoundScore(),0);


		Set<Character> epxectedUsedChar = new HashSet<>();
		epxectedUsedChar.add('t');
		epxectedUsedChar.add('e');
		epxectedUsedChar.add('a');
		epxectedUsedChar.add('s');

		assertEquals(epxectedUsedChar, round.getUsedChar().getValue());
	}

	@Test
	public void testSubmitOneWordWithDash() {
		Round round  = new Round("a-d");
		StringProperty guessedWord = new SimpleStringProperty();
		guessedWord.set("*-*");
		assertEquals(guessedWord.get(),round.getGuessWord().get());

	}

	@Test
	public void testSubmitTwiceTheSameChar() {
		Round round  = new Round("a-d");
		round.submitChar('a');
		Set<Character> epxectedUsedChar = new HashSet<>();
		epxectedUsedChar.add('a');
		assertEquals(epxectedUsedChar, round.getUsedChar().getValue());


		round.submitChar('a');
		assertEquals(epxectedUsedChar, round.getUsedChar().getValue());


	}


	@Test
	public void testLostRound() {
		Round round  = new Round("test");
		assertEquals(100, round.getRoundScore(),0);
		round.submitChar('a');
		assertEquals(50, round.getRoundScore(),0);
		round.submitChar('e');
		assertEquals(50, round.getRoundScore(),0);
		round.submitChar('b');
		assertEquals(35, round.getRoundScore(),0);
		round.submitChar('c');
		assertEquals(25, round.getRoundScore(),0);
		round.submitChar('d');
		assertEquals(15, round.getRoundScore(),0);
		round.submitChar('f');
		assertEquals(10, round.getRoundScore(),0);
		round.submitChar('w');
		assertEquals(5, round.getRoundScore(),0);
		round.submitChar('z');
		assertEquals(0, round.getRoundScore(),0);
		assertTrue(round.isOver());
	}

}
