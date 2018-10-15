package com.amazon.auto.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.By2;

public class AmazonSignInEmailInputPage extends AbstractPage {

	private static final By2 emailInput = new By2("'Email (phone for mobile accounts)' input", By.id("ap_email"));
	private static final By2 continueButton = new By2("'Continue' Button", By.id("continue"));
	
	public AmazonSignInEmailInputPage(WebDriver driver) {
		super("Amazon Sign In - Email Input Page", driver, emailInput, continueButton);
	}
	
	public void writeEmail(String email) {
		bot.writeToElement(emailInput, email);
	}
	
	public AmazonSignInPasswordInputPage clickContinueButton() {
		bot.click(continueButton);
		return new AmazonSignInPasswordInputPage(driver);
	}
}
