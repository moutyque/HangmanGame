package org.hangman.controller;

import static org.junit.Assert.assertEquals;

import org.hangman.model.Round;
import org.junit.runner.RunWith;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class TestRound {

	
    @TestWith({
        "accroissons,accroissons,___________",
        "accroît,accroit,_______",
        "accrûmes,accrumes,________",
        "acculé,accule,______",
        "acculèrent,acculerent,__________",
        "acculturâtes,acculturates,____________",
        "à-côté,a-cote,_-____"
    })
	public void testWordConvertion(String word,String expectedConvertedWord, String expectedGuessWord) {
		Round game = new Round(word);
		assertEquals(expectedConvertedWord, game.getConvertedWord());
		assertEquals(expectedGuessWord, game.getGuessWord());

	}
    
    
    @TestWith({
        "accroissons,acroisn,accroissons,100",
        "accroît,acroit,accroît,100",
        "accrûmes,aczwysteo,acc___es,10",
        "acculé,rtywxvbnz,______,0"
    })
	public void testCharSubmission(String word,String submissionChars, String expectedEndGuessWord,int expectedScore) {
		Round round = new Round(word);
		for(char c : submissionChars.toCharArray()) {
			round.submitChar(c);
		}
		assertEquals(expectedEndGuessWord, round.getGuessWord());
		assertEquals(expectedScore, round.getRoundScore());

	}

}
