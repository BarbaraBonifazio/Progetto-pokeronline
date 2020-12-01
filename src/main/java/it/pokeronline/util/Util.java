package it.pokeronline.util;

import java.text.SimpleDateFormat;

public class Util {

	public static boolean isInteger(String numString) {
		try {
			Integer.parseInt(numString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isLong(String numString) {
		try {
			Long.parseLong(numString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isDouble(String numString) {
		try {
			Double.parseDouble(numString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}	
	
	
	public static boolean isDate(String dateString) {
		try {
		
			new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}	

	public static boolean isEmptyOrNull(String value) {
		return value == null || value == "";
	}

	public void Util(){
		
	}
	
}
