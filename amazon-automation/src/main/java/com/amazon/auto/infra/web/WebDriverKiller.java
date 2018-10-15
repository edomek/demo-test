package com.amazon.auto.infra.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WebDriverKiller {

	public static void killWebdrivers() throws Exception{
		
		String[] webDriverProcessesNames = {"chromedriver.exe", "geckodriver.exe", "MicrosoftWebDriver.exe", "IEDriverServer.exe"};

		for (String webDriverProcessName : webDriverProcessesNames) {
			
			if (isProcessRunning(webDriverProcessName)) {
				Runtime.getRuntime().exec("taskkill /F /IM " + webDriverProcessName);
				System.out.println(webDriverProcessName + " WebDriver process was killed.");
			}
		}
	}
	
	private static boolean isProcessRunning(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec("tasklist");
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;

		while ((line = reader.readLine()) != null) {
			if (line.contains(serviceName)) {
				return true;
			}
		}

		return false;
	}
}