package com.amazon.auto.infra.reports;

import org.testng.Reporter;

import com.amazon.auto.infra.reports.extent.ExtentReporter;

public class Reports {

	public static void report(String message) {
		CliReporter.report(message);
		ExtentReporter.report(message);
		Reporter.log(message);
	}
	
	public static void fail(String message) {
		CliReporter.fail(message);
		ExtentReporter.fail(message);
	}
	
	public static void warning(String message) {
		CliReporter.warning(message);
		ExtentReporter.warning(message);
	}
	
	public static void error(String message) {
		CliReporter.error(message);
		ExtentReporter.error(message);
	}
	
	public static void addImageToReport(String imagePath, String title) throws Exception {
		CliReporter.report("[ADD IMAGE]: '" + title + "' - " + imagePath);
		ExtentReporter.addImageToReport(imagePath, title);
	}
}
