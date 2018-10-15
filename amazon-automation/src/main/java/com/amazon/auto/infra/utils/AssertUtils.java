package com.amazon.auto.infra.utils;

import org.testng.Assert;

import com.amazon.auto.infra.reports.Reports;

public class AssertUtils {

	public static void assertTrue(boolean mustBeTrue, String message) {
		assertTrue(mustBeTrue, message, false);
	}
	
	public static void assertTrue(boolean mustBeTrue, String message, boolean softAssert) {
		
		try {
			Assert.assertTrue(mustBeTrue, message);
			Reports.report("Assert True: " + message + " - OK");
		}
		catch (AssertionError e) {
			Reports.fail("Assert True failed: " + message);
			if (!softAssert) {
				throw e;
			}
		}
	}
	
	public static void assertEquals(Object actual, Object expected, String message) {
		assertEquals(actual, expected, message, false);
	}
	
	public static void assertEquals(Object actual, Object expected, String message, boolean softAssert) {
		
		try {
			Assert.assertEquals(actual, expected, message);
			Reports.report("Assert Equals: " + message + " - OK: actual = expected = " + actual);
		}
		catch (AssertionError e) {
			Reports.fail("Assert Equals: " + message + " - FAIL: actual = " + actual + "; expected = " + expected);
			if (!softAssert) {
				throw e;
			}
		}
	}
}
