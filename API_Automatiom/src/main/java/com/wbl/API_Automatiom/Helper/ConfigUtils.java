package com.wbl.API_Automatiom.Helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class ConfigUtils {
	
	public static Properties loadproperties(String filename){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(Constants.PATH + filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	

}
