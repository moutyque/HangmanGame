package org.hangman.helper;

import java.io.File;
import java.util.prefs.Preferences;

public abstract class PreferenceManager {
private static final String SCORE_PATH = "hangmanScorePath";
private final static Preferences prefs;

static {
	prefs = Preferences.userNodeForPackage(Scoremanager.class);
}
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public static File getPersonFilePath() {
	    String filePath = prefs.get(SCORE_PATH, null);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return new File("");
	    }
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public static void setPersonFilePath(File file) {
	    if (file != null) {
	        prefs.put(SCORE_PATH, file.getPath());
	        // Update the stage title.
	    } else {
	    	clearFilePath();
	    	// Update the stage title.
	    }
	}
	public static void clearFilePath() {
		prefs.remove(SCORE_PATH);
	}
	
}
