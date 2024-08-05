package com.restfulbooker.restapi.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReport {
	
	private ExtentReport() {
		
	}
	
	private static ExtentReports extentReports;
	private static ExtentSparkReporter extentSparkReporter;
	private static ExtentTest extentTest;
	
	public static void initReports() {
		extentReports = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter("index.html");
		extentReports.attachReporter(extentSparkReporter);
	}
	
	public static void createTest(String name) {
		extentTest = extentReports.createTest(name);
		ExtentManager.setTest(extentTest);
	}
	
	public static void tearDownReports() {
		extentReports.flush();
	}
	
	public static void addAuthor(String[] authors) {
		
		for(String author : authors) {
			ExtentManager.getTest().assignAuthor(author);
		}
	}
	
	public static void addCategory(String[] categories) {
		
		for(String category : categories) {
			ExtentManager.getTest().assignCategory(category);
		}
		
	}
	
	

}
