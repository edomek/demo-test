package com.amazon.auto.infra.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.ActionBot;
import com.amazon.auto.infra.web.By2;
import com.amazon.auto.infra.reports.Reports;

public abstract class AbstractPage {

	protected WebDriver driver;
	protected ActionBot bot;
	
	protected String pageName;
	protected By2[] pageUniqueElements;
	
	public AbstractPage(String pageName, WebDriver driver, By2... pageUniqueElements) {
		
		this.pageName = pageName;
		this.driver = driver;
		this.pageUniqueElements = pageUniqueElements;
		this.bot = new ActionBot(driver);
		
		assertInPage();
	}
	
	public void assertInPage() {
		
		try {
			for (By2 by2 : pageUniqueElements) {
				bot.waitForElementToBeVisible(by2);
			}
			
			Reports.report("*** On page: " + pageName);
		}
		catch (TimeoutException e) {
			Reports.report("Not on the expected page: " + pageName);
			throw e;
		}
	}
}
