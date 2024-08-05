package com.restfulbooker.restapi.constants;

import lombok.Getter;

//Implemented Singleton Design Pattern

@Getter
public final class FrameworkConstants {
	
	private FrameworkConstants() {
		
	}
	
	private static FrameworkConstants instance = null;
	
	public static synchronized FrameworkConstants getInstance() {
		
		if(instance==null) {
			instance = new FrameworkConstants();
		}
		
		return instance;
	}
	
	private final String propertiesFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties"; 
	private final String jsonSchemaFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\jsonschemas\\booking.json";
	private final String responseJsonFolderPath = System.getProperty("user.dir")+"\\output\\";
	private final String requestJsonFolderPath = System.getProperty("user.dir")+"\\\\src\\test\\resources\\requestjson\\";

}
