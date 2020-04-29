package org.hangman.controller;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.hangman.model.Constante;
import org.hangman.views.helper.FileLoader;
//Singleton
public class PropertiesLoader {
	private static PropertiesLoader me = null;
	private Properties properties= new Properties();
	private PropertiesLoader() {
		try {
			properties = FileLoader.getPropertyFile(Constante.RESOURCES_PROPERTIES_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PropertiesLoader getInstance() {
		if(me==null) me = new PropertiesLoader();
		return me;
	}

	public Properties getProperties() {
		return properties;
	}
	
	public static Properties getPropeties(String fileName) {
		Properties prop = new Properties();
			try {
				prop = FileLoader.getPropertyFile(fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return prop;
	}

}
