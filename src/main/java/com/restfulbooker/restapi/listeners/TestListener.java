package com.restfulbooker.restapi.listeners;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.restfulbooker.restapi.annotations.FrameworkAnnotation;
import com.restfulbooker.restapi.reports.ExtentLogger;
import com.restfulbooker.restapi.reports.ExtentReport;

public class TestListener implements ITestListener, ISuiteListener {
	
	@Override
	public void onStart(ITestContext context) {
	   ExtentReport.initReports();
	  }

	@Override
	public void onFinish(ITestContext context) {
	   ExtentReport.tearDownReports();
	  }
	
	@Override
	public void onTestStart(ITestResult result) {
		String description =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
		ExtentReport.createTest(description);
		
		String[] author =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author();
		ExtentReport.addAuthor(author);
		
		String[] category =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category();
		ExtentReport.addCategory(category);
		  
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		    ExtentLogger.pass(result.getName()+" is passed.");
		  }

	@Override
	public void onTestFailure(ITestResult result) {
		    ExtentLogger.fail(result.getThrowable().toString());
		  }

}
