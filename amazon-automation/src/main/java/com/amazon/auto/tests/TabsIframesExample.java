package com.amazon.auto.tests;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TabsIframesExample extends BaseTest {

	@Test
	public void iframeTest() {

		browseToUrl("file:///C:/Users/ronenby/Desktop/iframe_tabs/page1.html");

		WebElement ifrmaeElement = driver.findElement(By.id("main_iframe"));
		driver.switchTo().frame(ifrmaeElement);

		driver.findElement(By.id("p3_link")).click();
	}

	@Test
	public void tabsTest() {

		browseToUrl("file:///C:/Users/ronenby/Desktop/iframe_tabs/page1.html");

		driver.findElement(By.xpath("//a[@href='page2.html']")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		
		for (String windowHandle : windowHandles) {
			driver.switchTo().window(windowHandle);
			if (driver.getTitle().equals("Page 2")) {
				break;
			}
		}

		driver.findElement(By.id("p3_link")).click();
	}
}