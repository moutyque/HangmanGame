package org.hangman.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class FileLoader {

	private FileLoader() {
	}

	public static Properties getPropertyFile(String filePath) throws FileNotFoundException {
		Properties prop = new Properties();
		InputStream  inputStream = FileLoader.class.getClassLoader().getResourceAsStream(filePath);
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new FileNotFoundException("property file '" + filePath + "' not found in the classpath");
		}
		return prop;
	}
	
	
	public static File getFile(String filePath)  {
       return new File(  ClassLoader.getSystemClassLoader().getResource(filePath).getFile());

		
	}
	
}
