package com.amazon.auto.tests;

import org.testng.annotations.Test;

import com.amazon.auto.infra.reports.Reports;
import com.amazon.auto.infra.utils.AssertUtils;

public class AssertExampleTests extends BaseTest {

	@Test
	public void assertExamples() {
		
		Reports.report("Test started...");
		Reports.report("Test continues 1...");
		AssertUtils.assertTrue(false, "This must be true!", true);
		Reports.report("Test continues 2...");
	}
}
