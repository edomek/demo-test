package com.amazon.auto.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.By2;

public class AmazonSignInPasswordInputPage extends AbstractPage {

	private static final By2 passwordInput = new By2("'Password' input", By.id("ap_password"));
	private static final By2 signInButton = new By2("'Sign in' Button", By.id("signInSubmit"));
	private static final By2 thereWasAProblemTitle = new By2("'There was a problem' title", By.xpath("//h4[text()='There was a problem']"));
	
	public AmazonSignInPasswordInputPage(WebDriver driver) {
		super("Amazon Sign In - Password Input Page", driver, passwordInput, signInButton);
	}
	
	public AmazonSignInPasswordInputPage writePassword(String password) {
		bot.writeToElement(passwordInput, password);
		return this;
	}
	
	public AmazonVerificationNeededPage clickSignInButtonAndGoToVerificationNeededPage() {
		bot.click(signInButton);
		return new AmazonVerificationNeededPage(driver);
	}
	
	public AmazonSignInPasswordInputPage clickSignInButtonAndStayOnPasswordInputPage() {
		bot.click(signInButton);
		return this;
	}
	
	public boolean isThereWasAProblemTitleDisplayed() {
		return bot.isElementDisplayed(thereWasAProblemTitle);
	}
}
