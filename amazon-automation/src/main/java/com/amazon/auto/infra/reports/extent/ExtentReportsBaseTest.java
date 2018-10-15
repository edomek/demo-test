package com.amazon.auto.infra.reports.extent;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(ExtentReportsListener.class)
public abstract class ExtentReportsBaseTest {

	@BeforeSuite
	public void beforeSuite() {
		
	}
}
