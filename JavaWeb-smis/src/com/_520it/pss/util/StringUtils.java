package com._520it.pss.util;

public class StringUtils {
	private StringUtils() {
	}

	public static Boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}
