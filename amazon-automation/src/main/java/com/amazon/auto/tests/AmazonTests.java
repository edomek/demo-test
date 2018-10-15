package com.amazon.auto.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.auto.infra.config.MainConfig;
import com.amazon.auto.infra.entities.SearchItem;
import com.amazon.auto.infra.pages.AmazonLandingPage;
import com.amazon.auto.infra.pages.AmazonSearchResultsPage;
import com.amazon.auto.infra.reports.Reports;

public class AmazonTests extends BaseTest {

	@Test(dataProvider = "csvParamsProvider")
	public void searchFromMainLandingPage(SearchItem searchItem) {
		
		browseToUrl(MainConfig.baseUrl);
		
		AmazonLandingPage amazonLandingPage = new AmazonLandingPage(driver);
		amazonLandingPage.writeToSearchbox(searchItem.searchTerm);
		
		AmazonSearchResultsPage amazonSearchResultsPage = amazonLandingPage.clickGoButton();
		
		String searchResultTitle = amazonSearchResultsPage.getSearchResultTitle(searchItem.itemIndex);
		
		System.out.println(searchResultTitle);
		Assert.assertTrue(searchResultTitle.contains(searchItem.expectedResult), "Expecting to see '" + searchItem.expectedResult + "' in first result");
	}
	
	@Test(dataProvider = "amazonSearchTestParamsProvider1")
	public void searchFromMainLandingPage1(String searchTern, int index, String expected) {
		
		Reports.report("searchTerm = " + searchTern);
		Reports.report("itemIndex = " + index);
		Reports.report("expectedResult = " + expected);
	}
	
	
	@Test
	public void goToAmazonMusicFromSearchResultsPage() {
		
		browseToUrl(MainConfig.baseUrl);
		
		String searchString = "iPhone";
		
		AmazonLandingPage amazonLandingPage = new AmazonLandingPage(driver);
		amazonLandingPage.writeToSearchbox(searchString);
		
		AmazonSearchResultsPage amazonSearchResultsPage = amazonLandingPage.clickGoButton();
		
		amazonSearchResultsPage.clickAmazonMusicLink();
	}
	
	
	@DataProvider(name = "amazonSearchTestParamsProvider")
	public Object[][] dataProvider() {
		
		Object[][] params = new Object[3][1];
		
		SearchItem searchItem1 = new SearchItem("IPhone", 3, "IPhone 6");
		SearchItem searchItem2 = new SearchItem("Galaxy", 4, "Galaxy S9+");
		SearchItem searchItem3 = new SearchItem("Nokia", 1, "Nokia 3200");
		
		params[0][0] = searchItem1;
		params[1][0] = searchItem2;
		params[2][0] = searchItem3;

		return params;
	}
	
	@DataProvider(name = "amazonSearchTestParamsProvider1")
	public Object[][] dataProvider1() {
		
		Object[][] params = new Object[2][3];
		
		params[0][0] = "IPhone";
		params[0][1] = 3;
		params[0][2] = "IPhone 6";
		
		params[1][0] = "Galaxy";
		params[1][1] = 4;
		params[1][2] = "Galaxy S9+";

		return params;
	}
	
	@DataProvider(name = "csvParamsProvider")
	public Object[][] dataProvider2() throws Exception {
		
		FileInputStream fstream = new FileInputStream("src/main/resources/TestParams/products.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		int numOfLines = 0;
		String line;

		ArrayList<SearchItem> searchItems = new ArrayList<>();
		
		while ((line = br.readLine()) != null)   {
			
			if (numOfLines > 0) {
				
				String[] splitStr = line.split(",");
				SearchItem searchItem = new SearchItem(splitStr[0], Integer.parseInt(splitStr[1]), splitStr[2]);
				searchItems.add(searchItem);
			}
			
			numOfLines++;
		}
		
		br.close();
		
		Object[][] params = new Object[numOfLines-1][1];
		
		for (int i=0; i<numOfLines-1; i++) {
			params[i][0] = searchItems.get(i);
		}

		return params;
	}
}
