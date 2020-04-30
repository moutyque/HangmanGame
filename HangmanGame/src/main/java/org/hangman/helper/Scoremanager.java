package org.hangman.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.hangman.model.Score;

public abstract class Scoremanager {

	public static List<Score> getTenTopScore(){
		
		Properties prop =null;
		String path = System.getProperty("SCORE_PATH");
		
		System.out.println("path : " +path);
		if(!path.isEmpty()) {
			
            prop = PropertiesLoader.getPropeties(path);
		}
		else {
			prop = PropertiesLoader.getPropeties("textfiles/score.properties");

		}
		List<Score> scores = new ArrayList<>();
		Set<Entry<Object, Object>> set = prop.entrySet();
		for(Entry<Object, Object> entry : set) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			scores.add(new Score(Integer.parseInt(value.split("\\|")[0]), Integer.parseInt(value.split("\\|")[1]), key));
		}
		scores.sort((a,b)->Integer.compare(b.getScore(), a.getScore()));
		int limit = Math.min(scores.size(), 10);
		return scores.subList(0, limit);
	}

	public static void addScore(int gameScore, int nbWords, String pseudo) {
		String value = gameScore +"|" + nbWords;
		 FileOutputStream fileOut = null;
	        FileInputStream fileIn = null;
		try {
			
			Properties configProperty = new Properties();

			File file =null;
			String path = System.getProperty("SCORE_PATH");
			
			System.out.println("path : " +path);
			if(!path.isEmpty()) {
	            file = FileLoader.getFile(path);
			}
			else {
	            file = FileLoader.getFile("textfiles/score.properties");

			}
			Files.readAllLines(file.toPath()).stream().forEach(System.out::println);
            
            fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty(pseudo, value);
            fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, "");
            
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
		
	}
}
