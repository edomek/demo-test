package com.amazon.auto.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.auto.infra.web.By2;

public class AmazonProductPage extends AbstractPage {

	private static final By2 productTitle = new By2("Product title", By.id("productTitle"));
	private static final By2 addToCartButton = new By2("'Add To Cart' Button", By.id("add-to-cart-button"));
	
	public AmazonProductPage(WebDriver driver) {
		super("Amazon Product", driver, productTitle, addToCartButton);
	}
	
	public SponsoredProductsPage clickAddToCartButton() {
		bot.click(addToCartButton);
		return new SponsoredProductsPage(driver);
	}
}
