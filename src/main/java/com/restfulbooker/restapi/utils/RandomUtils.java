package com.restfulbooker.restapi.utils;

// This class is created to separate business layer so that we can accommodate business requirements
// For example, if the requirement is to set total price between 1500 and 5000, we can set in this class and it will be separated from Service Lsayer 

public final class RandomUtils {
	
	private RandomUtils() {
		
	}
	
	public static String getFirstName() {
		return FakerUtils.getFirstName().toLowerCase();
	}
	
	public static String getLastName() {
		return FakerUtils.getLastName().toLowerCase();
	}
	
	public static int getTotalPrice() {
		return FakerUtils.getTotalPrice(1500, 5000);
	}
	
	public static String getAdditionalDetails() {
		return FakerUtils.getAdditionalFoodDetails().concat(" for Dinner");
	}

}
