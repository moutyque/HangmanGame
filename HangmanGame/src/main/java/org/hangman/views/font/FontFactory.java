package org.hangman.views.font;

import java.awt.Font;

public abstract class FontFactory {

	public static Font getHeaderFont() {
		return new Font("Arial", Font.BOLD, 22);
	}
	
	public static Font getTextFont() {
		return new Font("Arial", Font.BOLD,14);
	}
	
	public static Font getWordFont() {
		return new Font("Arial", Font.BOLD,20);
	}

}
