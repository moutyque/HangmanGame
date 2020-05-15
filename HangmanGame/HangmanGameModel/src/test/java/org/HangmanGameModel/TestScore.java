package org.HangmanGameModel;

import static org.junit.Assert.assertEquals;

import org.hangman.model.Score;
import org.junit.Test;

public class TestScore {

	@Test
	public void test() {
		Score score = new Score(50,1,"Quentin");
		assertEquals(50,score.getScore());
		assertEquals(1,score.getNbWord());
		assertEquals("Quentin",score.getPseudo());
		assertEquals("Quentin -> 50 Pts (1 mots)",score.toString());
	}

}
