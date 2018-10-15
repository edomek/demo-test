package com.amazon.auto.infra.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CliReporter {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss: ");
	
	public static void report(String message) {
		System.out.println(dateFormat.format(new Date()) + message);
	}
	
	public static void fail(String message) {
		System.out.println(dateFormat.format(new Date()) + "[FAIL]: " + message);
	}
	
	public static void error(String message) {
		System.out.println(dateFormat.format(new Date()) + "[ERROR]: " + message);
	}
	
	public static void warning(String message) {
		System.out.println(dateFormat.format(new Date()) + "[WARNING]: " + message);
	}
}
