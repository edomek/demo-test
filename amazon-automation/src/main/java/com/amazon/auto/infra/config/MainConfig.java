package com.amazon.auto.infra.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MainConfig {

	public static String browserType;
	public static String baseUrl;
	public static int webdriverImplicitWaitInSeconds;
	public static int webdriverExplicitWaitInSeconds;
	public static boolean closeBrowserAfterTest;
	
	public static void initConfig(String propertiesFilePath) throws Exception {
		
		Properties prop = new Properties();
		InputStream input = new FileInputStream(propertiesFilePath);

		prop.load(input);

		browserType = prop.getProperty("browser_type");
		baseUrl = prop.getProperty("base_url");
		webdriverImplicitWaitInSeconds = Integer.parseInt(prop.getProperty("webdriver_implicit_wait_in_seconds"));
		webdriverExplicitWaitInSeconds = Integer.parseInt(prop.getProperty("webdriver_explicit_wait_in_seconds"));
		closeBrowserAfterTest = Boolean.parseBoolean(prop.getProperty("close_browser_after_test"));
	}
}
