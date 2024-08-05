package com.restfulbooker.restapi.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
	
	private ExtentManager() {
		
	}
	
	private static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();
	
	static ExtentTest getTest() {
		return threadLocal.get();
	}
	
	static void setTest(ExtentTest extentTest) {
		threadLocal.set(extentTest);
	}

}
