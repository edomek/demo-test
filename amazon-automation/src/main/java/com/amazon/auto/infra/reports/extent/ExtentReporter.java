package com.amazon.auto.infra.reports.extent;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporter {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	
	private static ExtentReporter INSTANCE;
	
	private ExtentReports extentReports;
	private ExtentTest extentTest;
	
	private String currentReportFolder;
	
	private ExtentReporter() {
		
		extentReports = new ExtentReports();
		
		String timeStamp = dateFormat.format(new Date());
		currentReportFolder = "extent-reports/" + timeStamp;
		
		File extentReportsFolder = new File(currentReportFolder);
		if (!extentReportsFolder.exists()) {
			extentReportsFolder.mkdirs();
		}
		
		extentReports.attachReporter(new ExtentHtmlReporter(currentReportFolder + "/report_" + timeStamp + ".html"));
	}
	
	private static ExtentReporter instance() {
		if (INSTANCE == null) {
			INSTANCE = new ExtentReporter();
		}
		return INSTANCE;
	}
	
	public static void startTest(String testName) {
		instance().extentTest = instance().extentReports.createTest(testName);
	}
	
	public static void report(String message) {
		instance().extentTest.info(message);
		instance().extentReports.flush();
	}
	
	public static void fail(String message) {
		instance().extentTest.fail(message);
		instance().extentReports.flush();
	}
	
	public static void fail(Throwable throwable) {
		instance().extentTest.fail(throwable);
		instance().extentReports.flush();
	}
	
	public static void pass(String message) {
		instance().extentTest.pass(message);
		instance().extentReports.flush();
	}
	
	public static void error(String message) {
		instance().extentTest.error(message);
		instance().extentReports.flush();
	}

	public static void skip(String message) {
		instance().extentTest.skip(message);
		instance().extentReports.flush();
	}
	
	public static void warning(String message) {
		instance().extentTest.warning(message);
		instance().extentReports.flush();
	}
	
	public static void addImageToReport(String imageFilePath, String title) throws Exception {

		File sourceFile = new File(imageFilePath);
		String fileName = sourceFile.getName();
		
		File destFile = new File(instance().currentReportFolder + "/" + fileName);
		Files.copy(sourceFile.toPath(), destFile.toPath());
		
		instance().extentTest.addScreenCaptureFromPath(fileName, title);
		instance().extentReports.flush();
	}
}
