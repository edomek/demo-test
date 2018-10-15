package com.amazon.auto.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.auto.infra.config.MainConfig;
import com.amazon.auto.infra.pages.AmazonLandingPage;
import com.amazon.auto.infra.pages.AmazonSignInEmailInputPage;
import com.amazon.auto.infra.pages.AmazonSignInPasswordInputPage;
import com.amazon.auto.infra.utils.AssertUtils;
import com.amazon.auto.tests.enums.AmazonLoginResult;

public class AmazonLoginTests extends BaseTest {
	
	@Test(dataProvider = "csvParamsProvider")
	public void loginTest(String loginEmail, String loginPassword, AmazonLoginResult expectedLoginResult) {
		
		browseToUrl(MainConfig.baseUrl);
		
		AmazonLandingPage amazonLandingPage = new AmazonLandingPage(driver);
		
		amazonLandingPage.hoverOverHelloSignInLink();
		AmazonSignInEmailInputPage amazonSignInEmailInputPage = amazonLandingPage.clickSignInButton();
		
		amazonSignInEmailInputPage.writeEmail(loginEmail);
		AmazonSignInPasswordInputPage amazonSignInPasswordInputPage = amazonSignInEmailInputPage.clickContinueButton();
		
		amazonSignInPasswordInputPage.writePassword(loginPassword);
		
		if (expectedLoginResult == AmazonLoginResult.VERIFICATION_CODE) {
			amazonSignInPasswordInputPage.clickSignInButtonAndGoToVerificationNeededPage();
		}
		else if (expectedLoginResult == AmazonLoginResult.THERE_WAS_A_PROBLEM) {
			amazonSignInPasswordInputPage.clickSignInButtonAndStayOnPasswordInputPage();
			AssertUtils.assertTrue(amazonSignInPasswordInputPage.isThereWasAProblemTitleDisplayed(), "Expect to see 'There was a problem message'");
		}
	}
	
	@DataProvider(name = "csvParamsProvider")
	public Object[][] dataProvider() throws Exception {
		
		FileInputStream fstream = new FileInputStream("src/main/resources/TestParams/login_test_params.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		int numOfLines = 0;
		String line;

		ArrayList<String> lines = new ArrayList<>();
		
		while ((line = br.readLine()) != null)   {
			if (numOfLines > 0) {
				lines.add(line);
			}
			numOfLines++;
		}
		br.close();
		
		Object[][] params = new Object[numOfLines-1][3];
		
		for (int i=0; i<numOfLines-1; i++) {
			
			String[] splitStr = lines.get(i).split(",");
			
			params[i][0] = splitStr[0]; // email
			params[i][1] = splitStr[1]; // password
			params[i][2] = AmazonLoginResult.valueOf(splitStr[2]); // expected result
		}

		return params;
	}
}
