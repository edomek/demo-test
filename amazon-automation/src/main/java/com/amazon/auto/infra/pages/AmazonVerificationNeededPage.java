package com.amazon.auto.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.By2;

public class AmazonVerificationNeededPage extends AbstractPage {

	private static final By2 verificationNeededTitle = new By2("'Verification needed' title", By.xpath("//h1[text()='Verification needed']"));
	private static final By2 sendCodeButton = new By2("'Send code' button", By.id("continue"));
	
	public AmazonVerificationNeededPage(WebDriver driver) {
		super("Verification Needed Page", driver, verificationNeededTitle, sendCodeButton);
	}
}
