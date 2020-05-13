package org.hangman.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringEscapeUtils;
import org.hangman.model.Constante;
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

	public static List<String> getDico() {

		File file = FileLoader.getFile(Constante.DICO_PATH);
		try {
			return Files.readAllLines(file.toPath(),StandardCharsets.UTF_8).stream()
					.map(StringEscapeUtils::unescapeJava)
					.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

}
