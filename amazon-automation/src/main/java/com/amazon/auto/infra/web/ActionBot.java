package com.amazon.auto.infra.web;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.auto.infra.config.MainConfig;
import com.amazon.auto.infra.reports.Reports;

public class ActionBot {

	private WebDriver driver;
	private WebDriverWait webDriverWait;
	
	public ActionBot(WebDriver driver) {
		this.driver = driver;
		this.webDriverWait = new WebDriverWait(driver, MainConfig.webdriverExplicitWaitInSeconds);
	}
	
	public void click(By2 by2) {
		
		Reports.report("Click on element: " + by2.description + " (" + by2.by + ")");
		WebElement element = driver.findElement(by2.by);
		executeJavaScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	
	public void writeToElement(By2 by2, String text) {
		
		Reports.report("Write '" + text + "' to element: " + by2.description + " (" + by2.by + ")");
		WebElement element = driver.findElement(by2.by);
		element.sendKeys(text);
	}
	
	public String getElementText(By2 by2) {
		WebElement element = driver.findElement(by2.by);
		String text = element.getText();
		Reports.report(by2.description + " text: '" + text + "'");
		return text;
	}
	
	public void waitForElementToBeVisible(By2 by2) {
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by2.by));
		Reports.report("Element is visible: " + by2.description + " (" + by2.by + ")");
	}
	
	public void switchToIframe(By2 by2) {
		Reports.report("Switch to iframe: " + by2.description + " (" + by2.by + ")");
		WebElement ifrmaeElement = driver.findElement(by2.by);
		driver.switchTo().frame(ifrmaeElement);
	}
	
	public void switchToIframe(String iframeId) {
		Reports.report("Switch to iframe by ID: " + iframeId);
		driver.switchTo().frame(iframeId);
	}
	
	public boolean isElementDisplayed(By2 by2) {
		
		List<WebElement> elements = driver.findElements(by2.by);
		if (elements.size() > 0) {
			
			boolean displayed = elements.get(0).isDisplayed();
			if (displayed) {
				Reports.report("Element displayed: " + by2.description + " (" + by2.by + ")");
				return true;
			}
			else {
				Reports.report("Element is not displayed: " + by2.description + " (" + by2.by + ")");
				return false;
			}
		}
		else {
			Reports.report("Element is not displayed: " + by2.description + " (" + by2.by + ")");
			return false;
		}
		
		/*
		try {
			WebElement element = driver.findElement(by2.by);
			return element.isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
		*/
	}
	
	public void switchToTabByTitle(String tabTitle) throws Exception {
		
		Reports.report("Switch to tab where title contains: '" + tabTitle + "'");
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		for (String windowHandle : windowHandles) {
			driver.switchTo().window(windowHandle);
			if (driver.getTitle().contains(tabTitle)) {
				return;
			}
		}
		
		throw new Exception("No tab where title contains: '" + tabTitle + "'");
	}
	
	public void switchToTabByUrl(String tabUrl) throws Exception {
		
		Reports.report("Switch to tab where URL contains: '" + tabUrl + "'");
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		for (String windowHandle : windowHandles) {
			driver.switchTo().window(windowHandle);
			if (driver.getCurrentUrl().contains(tabUrl)) {
				return;
			}
		}
		
		throw new Exception("No tab where URL contains: '" + tabUrl + "'");
	}
	
	public void hoverOverElement(By2 by2) {
		
		Reports.report("Hover over element: " + by2.description + " (" + by2.by + ")");
		
		WebElement element = driver.findElement(by2.by);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	public void executeJavaScript(String javaScript, WebElement element) {
		
		((JavascriptExecutor) driver).executeScript(javaScript, element);		
	}
}
