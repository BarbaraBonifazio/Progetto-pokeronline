package it.pokeronline.util;

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

	public static boolean isEmptyOrNull(String value) {
		return value == null || value == "";
	}

	public void Util(){
		
	}
	
}
