package com.amazon.auto.tests;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.amazon.auto.infra.config.MainConfig;
import com.amazon.auto.infra.reports.Reports;
import com.amazon.auto.infra.reports.extent.ExtentReportsListener;
import com.amazon.auto.infra.web.BrowserType;
import com.amazon.auto.infra.web.WebDriverFactory;
import com.amazon.auto.infra.web.WebDriverKiller;

@Listeners(ExtentReportsListener.class)
public abstract class BaseTest {

	protected static WebDriver driver;
	
	@BeforeClass
	public void beforeClass() throws Exception {
		
		MainConfig.initConfig("src/main/resources/MainConfig.properties");
		
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			WebDriverKiller.killWebdrivers();
		}
	}
	
	@BeforeMethod
	public void beforeMethod() {
	}
	
	@AfterMethod
	public void afterMethod() {
		
		if (driver != null && MainConfig.closeBrowserAfterTest) {
			driver.quit();
			driver = null;
		}
	}
	
	@AfterClass
	public void afterClass() {
		
	}
	
	protected void browseToUrl(String url) {
		
		if (driver == null) {
			
			BrowserType browserType = BrowserType.valueOf(MainConfig.browserType);
			driver = WebDriverFactory.getWebDriver(browserType);
		}
		
		Reports.report("Browsing to URL: " + url);
		driver.get(url);
	}
	
	public static void takeScreenshot() throws Exception {
		
		if (driver != null) {
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Reports.addImageToReport(screenshotFile.getAbsolutePath(), driver.getTitle());
		}
		else {
			Reports.warning("driver == null; Can't take screenshot.");
		}
	}
}
