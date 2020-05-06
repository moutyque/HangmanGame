package org.HangmanGameController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.hangman.helper.FileLoader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class TestPropertiesLoader {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test(expected=FileNotFoundException.class)
	public void LoadUnexistingPropertiesFile() throws FileNotFoundException {
			FileLoader.getPropertyFile("a");
		
	}
	
	@Test
	public void LoadExistingPropertiesFile() throws FileNotFoundException {
		Properties prop = FileLoader.getPropertyFile("textfiles/resources.properties");
		assertFalse(prop.isEmpty());
	}

	
	@Test
	public void LoadExistingFile() throws FileNotFoundException {
		File file = FileLoader.getFile("pictures/0.jpg");
		
		assertTrue(file.exists());
	}
	
	
	@Test
	public void LoadExistinScoreFile() throws FileNotFoundException {
		Properties prop = FileLoader.getPropertyFile("textfiles/score.properties");
		assertFalse(prop.isEmpty());
	}

}
