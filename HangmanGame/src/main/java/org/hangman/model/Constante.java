package org.hangman.model;

public interface Constante {
	static final String HOME_PICTURE_PATH = "pictures/homePicture.jpg";
	static final String RESOURCES_PROPERTIES_PATH = "textfiles/resources.properties";
	static final String DICO_PATH = "textfiles/dictionnaire.txt";
	static final String DEFAULT_CHAR ="*";

	static final int UPDATE_END_GAME = 1;
	static final int UPDATE_ASK_PSEUDO = 2;
	static final int UPDATE_END_ROUND = 3;
	static final int UPDATE_DISPALY_SCORE = 4;
	static final int UPDATE_DISPALY_HOME = 5;
	static final int UPDATE_REFRESH_GAME_PANEL = 6;
}
