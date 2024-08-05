package com.restfulbooker.restapi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.restfulbooker.restapi.constants.FrameworkConstants;
import com.restfulbooker.restapi.enums.PropertiesType;

public class PropertyUtils {
	
	//read the content only once from properties file and store it in HashMap
	
	private static Properties properties = new Properties();
	private static Map<String, String> MAP = new HashMap<String, String>();
	
	static {
		try {
			FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getInstance().getPropertiesFilePath());
			properties.load(fileInputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		for(Map.Entry<Object, Object> temp : properties.entrySet()) {
			String key = (String) temp.getKey();
			String value = (String) temp.getValue();
			MAP.put(key, value);
		}
	}
	
	public static String getValue(PropertiesType key) {
		return MAP.get(key.name().toLowerCase());
	}

}
