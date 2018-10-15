package com.amazon.auto.infra.web;

import org.openqa.selenium.By;

public class By2 {

	public By by;
	public String description;
	
	public By2(String description, By by) {
		this.description = description;
		this.by = by;
	}
}
