package com.amazon.auto.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.By2;

public class AmazonSearchResultsPage extends AbstractPage {

	private static final By2 amazonMusicLink = new By2("'Amazon Music' Link", By.xpath("//a[text()='Amazon Music']"));
	
	public AmazonSearchResultsPage(WebDriver driver) {
		super("Amazon Search Results Page", driver);
	}
	
	public String getSearchResultTitle(int resultNumber) {
		
		By2 searchResultItem = new By2("Title of search result item #" + resultNumber, By.cssSelector("li#result_" + resultNumber + " h2"));
		String resultTitle = bot.getElementText(searchResultItem);
		return resultTitle;
	}
	
	public AmazonProductPage clickSearchResultTitle(int resultNumber) {
		
		By2 searchResultItem = new By2("Title of search result item #" + resultNumber, By.cssSelector("li#result_" + resultNumber + " h2"));
		bot.click(searchResultItem);
		return new AmazonProductPage(driver);
	}
	
	public void clickAmazonMusicLink() {
		bot.click(amazonMusicLink);
	}
}
