package com.amazon.auto.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.By2;

public class AmazonLandingPage extends AbstractPage {

	private static final By2 searchBox = new By2("Search box", By.id("twotabsearchtextbox"));
	private static final By2 goButton = new By2("'Go' button", By.cssSelector("input[value='Go']"));
	private static final By2 helloSignInLink = new By2("'Hello. Sign in' link", By.id("nav-link-accountList"));
	private static final By2 signInButton = new By2("'Sign in' button", By.className("nav-action-inner"));
	
	public AmazonLandingPage(WebDriver driver) {
		super("Amazon Landing Page", driver, searchBox, goButton);
	}
	
	public void writeToSearchbox(String searchString) {
		
		bot.writeToElement(searchBox, searchString);
	}
	
	public AmazonSearchResultsPage clickGoButton() {
		
		bot.click(goButton);
		return new AmazonSearchResultsPage(driver);
	}
	
	public void hoverOverHelloSignInLink() {
		bot.hoverOverElement(helloSignInLink);
	}
	
	public AmazonSignInEmailInputPage clickSignInButton() {
		bot.click(signInButton);
		return new AmazonSignInEmailInputPage(driver);
	}
}
