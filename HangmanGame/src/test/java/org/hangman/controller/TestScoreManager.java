package org.hangman.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.hangman.helper.Scoremanager;
import org.hangman.model.Score;
import org.junit.Test;

public class TestScoreManager {

	
	
	@Test
	public void testgetTenTopScore() {
		List<Score> scores = Scoremanager.getTenTopScore();
		assertEquals(10, scores.size());
		assertTrue(scores.get(0).getScore() > scores.get(1).getScore());
	}
	
	@Test
	public void testaddNewScore() {
		List<Score> scores = Scoremanager.getTenTopScore();
		int maxScore = scores.get(0).getScore()+1;
		Random rand = new Random();
		int nbWord = rand.nextInt(100);
		
		String tmpPseudo =  "test"+rand.nextInt(100);
		Scoremanager.addScore(maxScore, nbWord,tmpPseudo);
		
		Score newMaxScore = Scoremanager.getTenTopScore().get(0);
		assertEquals(tmpPseudo, newMaxScore.getPseudo());
		assertEquals(maxScore, newMaxScore.getScore());
		assertEquals(nbWord, newMaxScore.getNbWord());

	}
}
