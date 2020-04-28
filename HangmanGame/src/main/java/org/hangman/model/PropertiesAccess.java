package org.hangman.model;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.hangman.views.helper.FileLoader;
//Singleton
public class PropertiesAccess {
	private static PropertiesAccess me = null;
	private Properties properties= new Properties();
	private PropertiesAccess() {
		try {
			properties = FileLoader.getPropertyFile(Constante.RESOURCES_PROPERTIES_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PropertiesAccess getInstance() {
		if(me==null) me = new PropertiesAccess();
		return me;
	}

	public Properties getProperties() {
		return properties;
	}

}
