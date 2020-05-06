package org.HangmanGameController;

import static org.junit.Assert.assertEquals;

import org.hangman.model.Round;
import org.junit.runner.RunWith;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class TestRound {

	
    @TestWith({
        "accroissons,accroissons,___________",
        "accro�t,accroit,_______",
        "accr�mes,accrumes,________",
        "accul�,accule,______",
        "accul�rent,acculerent,__________",
        "accultur�tes,acculturates,____________",
        "�-c�t�,a-cote,_-____"
    })
	public void testWordConvertion(String word,String expectedConvertedWord, String expectedGuessWord) {
		Round game = new Round(word);
		assertEquals(expectedConvertedWord, game.getConvertedWord());
		assertEquals(expectedGuessWord, game.getGuessWord());

	}
    
    
    @TestWith({
        "accroissons,acroisn,accroissons,100",
        "accro�t,acroit,accro�t,100",
        "accr�mes,aczwysteo,acc___es,10",
        "accul�,rtywxvbnz,______,0"
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
