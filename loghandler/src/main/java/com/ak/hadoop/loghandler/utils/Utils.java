package com.ak.hadoop.loghandler.utils;

public class Utils {
	
	public static String nullToEmpty(String string) {
		return string == null ? "" : string.trim();
	}

}
