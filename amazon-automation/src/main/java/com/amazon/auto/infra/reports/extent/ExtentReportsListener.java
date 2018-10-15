package com.amazon.auto.infra.reports.extent;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.amazon.auto.infra.reports.CliReporter;
import com.amazon.auto.tests.BaseTest;


public class ExtentReportsListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		CliReporter.report("---------------------------------------------");
		CliReporter.report("Test start: " + testName(result));
		CliReporter.report("---------------------------------------------");
		ExtentReporter.startTest(testName(result));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		CliReporter.report("---------------------------------------------");
		CliReporter.report("Test success: " + testName(result));
		CliReporter.report("---------------------------------------------");
		ExtentReporter.pass("Test success: " + testName(result));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			BaseTest.takeScreenshot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CliReporter.report("---------------------------------------------");
		CliReporter.report("Test failure: " + testName(result));
		CliReporter.report("---------------------------------------------");
		
		ExtentReporter.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		CliReporter.report("---------------------------------------------");
		CliReporter.report("Test skipped: " + testName(result));
		CliReporter.report("---------------------------------------------");
		ExtentReporter.skip("Test skipped: " + testName(result));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}
	
	private String testName(ITestResult result) {
		
		String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
		
		if (testName != null && !testName.isEmpty()) {
			return testName;
		}
		else {
			return result.getMethod().getMethodName();
		}
	}
}
