package com.amazon.auto.tests;

import org.testng.annotations.Test;

import com.amazon.auto.infra.reports.Reports;

public class ScreenshotTest extends BaseTest {

	@Test
	public void screenshotTest() throws Exception {
		
		browseToUrl("http://maps.google.com");
		Reports.report("Before the screenshot..");
//		takeScreenshot();
		
		throw new Exception("This is very bad!");
	}
}
