package com.amazon.auto.tests;

import org.testng.annotations.Test;

public class TestsByGroups {

	@Test(groups = { "odd" })
	public void test1() {
		System.out.println("Test 1 - pass");
	}
	
	@Test(groups = { "even" })
	public void test2() {
		System.out.println("Test 2 - pass");
	}
	
	@Test(groups = { "odd1" })
	public void test3() throws Exception {
		throw new Exception("Test 3 - fail");
	}
	
	@Test(groups = { "even" })
	public void test4() {
		System.out.println("Test 2 - pass");
	}
	
	@Test(groups = { "odd" })
	public void test5() throws Exception {
		throw new Exception("Test 5 - fail");
	}
}
