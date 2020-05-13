package org.HangmanGameController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.hangman.controller.Game;
import org.hangman.helper.FileLoader;
import org.hangman.model.Constante;
import org.hangman.model.Round;
import org.junit.Test;

public class TestGame {

	
	@Test
	public void testReadDico() throws IOException {
		File file = FileLoader.getFile(Constante.DICO_PATH);
		assertTrue(file.exists());
		List<String> dico =  Files.readAllLines(file.toPath(),Charset.forName("Cp1252"));
		
		assertFalse(dico.isEmpty());
	}
	
	
	@Test
	public void testCreateGame(){
		Game game = new Game();
		Round round = game.getCurrentRound();
		assertFalse(round.getWord().isEmpty());
		assertFalse(round.getConvertedWord().isEmpty());
		assertFalse(round.getGuessWord().get().isEmpty());
		assertEquals(0, round.getErrorsCount().get(),0);
		
	}
}
