package com.restfulbooker.restapi.utils;

import com.github.javafaker.Faker;

//This class is created to define service layer for Third party libraries. Lets say tomorrow if a method name is changed then we can
//accommodate the changes in this class.

public final class FakerUtils {
	
	private FakerUtils() {
		
	}
	
	private static final Faker faker = new Faker();
	
	static String getFirstName() {
		return faker.name().firstName();
	}
	
	static String getLastName() {
		return faker.name().lastName();
	}
	
	static int getTotalPrice(int startValue, int endValue) {
		return faker.number().numberBetween(startValue, endValue);
	}
	
	static String getAdditionalFoodDetails() {
		return faker.food().sushi();
	}

}
