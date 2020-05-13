package org.hangman.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.hangman.model.Score;

public abstract class Scoremanager {
	private static File pptFile = null;
	private static Properties scores = new Properties();	
	static {
		String path = PreferenceManager.getPersonFilePath().toString();
		if(!path.isEmpty()) {
			pptFile = FileLoader.getFile(path);
			setScorePpt();
		}
		else {
			pptFile = FileLoader.getFile("textfiles/score.properties");
			setScorePpt();
			createScoresFile();
			PreferenceManager.setPersonFilePath(pptFile);
			saveScore();
		}
	}

	private static void createScoresFile() {
		JFileChooser fr = new JFileChooser();
		FileSystemView fw = fr.getFileSystemView();
		File myDocument = fw.getDefaultDirectory();
		pptFile = fileWithDirectoryAssurance(myDocument+"/HangmanGame","score.properties");
		setScorePpt();
	}

	private static void setScorePpt() {
		try {
			scores.load(new FileInputStream(pptFile));
		} catch (IOException e1) {

		}
	}

	private static File fileWithDirectoryAssurance(String directory, String filename) {
		File dir = new File(directory);
		if (!dir.exists()) dir.mkdirs();
		File file =  new File(directory , filename);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public static List<Score> getTenTopScore(){
		List<Score> scoreList = new ArrayList<>();
		Set<Entry<Object, Object>> set = scores.entrySet();
		for(Entry<Object, Object> entry : set) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			scoreList.add(new Score(Integer.parseInt(value.split("\\|")[0]), Integer.parseInt(value.split("\\|")[1]), key));
		}
		scoreList.sort((a,b)->Integer.compare(b.getScore(), a.getScore()));
		int limit = Math.min(scores.size(), 10);
		return scoreList.subList(0, limit);
	}

	public static void addScore(int gameScore, int nbWords, String pseudo) {
		String value = gameScore +"|" + nbWords;
		scores.setProperty(pseudo, value);
		saveScore();


	}

	private static void saveScore() {
		try {
			scores.store(new FileOutputStream(pptFile), "");
		} catch (IOException e) {
			System.err.println("Could not set the score propeties file");
			System.err.println("check that this file existe : "+pptFile.getAbsolutePath());
			e.printStackTrace();
		}
	}
}
