package com.amazon.auto.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.By2;

public class SponsoredProductsPage extends AbstractPage {

	private static final By2 sponsoredProductsTitle = new By2("'Sponsored Products' Title", By.xpath("//h2[contains(text(),'Sponsored products')]"));
	private static final By2 shoppingCartCounterSpan = new By2("Shopping cart counter span", By.id("nav-cart-count"));
	
	public SponsoredProductsPage(WebDriver driver) {
		super("Sponsored Products", driver, sponsoredProductsTitle);
	}
	
	public String getShoppingCartCounterValue() {
		return bot.getElementText(shoppingCartCounterSpan);
	}

}
