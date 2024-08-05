package com.restfulbooker.restapi.tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.restfulbooker.restapi.reports.ExtentLogger;
import com.restfulbooker.restapi.reports.ExtentReport;

import groovyjarjarasm.asm.commons.Method;

public class BaseTest {
	
	@BeforeSuite
	public void setUpSuite() {
		ExtentReport.initReports();
	}
	
	@BeforeMethod
	public void setUp(Method method) {
		ExtentReport.createTest(method.getName());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(!result.isSuccess()) {
			ExtentLogger.fail(result.getThrowable().toString());
		}
	}
	
	@AfterSuite
	public void tearDownSuite() {
		ExtentReport.tearDownReports();
	}

}
