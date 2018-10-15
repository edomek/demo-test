package com.amazon.auto.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.auto.infra.config.MainConfig;
import com.amazon.auto.infra.pages.AmazonLandingPage;
import com.amazon.auto.infra.pages.AmazonProductPage;
import com.amazon.auto.infra.pages.AmazonSearchResultsPage;
import com.amazon.auto.infra.pages.SponsoredProductsPage;

public class ShoppingCartTests extends BaseTest {

	@Test
	public void addItemToShoppingCart() {
		
		browseToUrl(MainConfig.baseUrl);
		
		AmazonLandingPage amazonLandingPage = new AmazonLandingPage(driver);
		amazonLandingPage.writeToSearchbox("iPhone");
		
		AmazonSearchResultsPage amazonSearchResultsPage = amazonLandingPage.clickGoButton();
		
		AmazonProductPage amazonProductPage = amazonSearchResultsPage.clickSearchResultTitle(0);
		
		SponsoredProductsPage sponsoredProductsPage = amazonProductPage.clickAddToCartButton();
		
		String shoppingCartCounter = sponsoredProductsPage.getShoppingCartCounterValue();
		Assert.assertEquals("1", shoppingCartCounter, "Shopping cart counter value should be 1");
	}
}
