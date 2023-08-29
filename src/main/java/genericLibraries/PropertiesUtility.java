package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to perform operations on Properrties file
 * * @author admin
 *
 */
public class PropertiesUtility {
	private Properties property;
	
	/**
	 * This method is used to intialize properties file
	 * @param filePath
	 */
	public void propertiesIntialization(String filePath)
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to read data from Properties
	 * @param key
	 * @return
	 */
	public String readFromProperties(String key) 
	{		
		return property.getProperty(key);
	}
}
