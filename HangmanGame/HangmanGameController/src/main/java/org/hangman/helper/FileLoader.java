package org.hangman.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public abstract class FileLoader {

	private FileLoader() {
	}

	public static Properties getPropertyFile(String filePath) throws FileNotFoundException {
		Properties prop = new Properties();
		FileInputStream  inputStream = new FileInputStream(getFile(filePath));
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
		
		File file = new File(filePath);
		if(file.exists()) {
			return file;
		}
	    URL res = FileLoader.class.getClassLoader().getResource(filePath);
	    if (res.getProtocol().equals("jar")) {
	        try {
	            InputStream input =  FileLoader.class.getClassLoader().getResourceAsStream(filePath);
	            file = File.createTempFile("tempfile", ".tmp");
	            FileUtils.copyInputStreamToFile(input, file);
	        } catch (IOException ex) {
	           ex.printStackTrace();
	        }
	    } else {
	        //this will probably work in your IDE, but not from a JAR
	    	try {
	    		 file = new File(res.getPath());
	    	}
	    	catch(NullPointerException e) {
	    		 file = new File(FileLoader.class.getClassLoader().getSystemResource(filePath).getFile());
	    	}
	       
	    }

	    if (file != null && !file.exists()) {
	        throw new RuntimeException("Error: File " + file + " not found!");
	    }
	    
		
       return file;
	}
	
}
