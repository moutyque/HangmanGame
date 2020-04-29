package org.hangman.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.hangman.model.Score;
import org.hangman.views.helper.FileLoader;

public abstract class Scoremanager {

	public static List<Score> getTenTopScore(){
		Properties prop = PropertiesLoader.getPropeties("textfiles/score.properties");
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
		PropertiesConfiguration config;
		try {
			config = new PropertiesConfiguration(FileLoader.getFile("textfiles/score.properties"));
			config.setProperty(pseudo, value);
			config.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
		
	}
}
