package edash.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
	
	// Regex for validating email
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
			"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
			);
	
	// Check if a string is empty or null
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	// Validate email format 
	public static boolean isValidEmail(String email) {
		return email != null && EMAIL_PATTERN.matcher(email).matches();
	}
	
	// Validate string length
	public static boolean isValidLength(String str, int min, int max) {
		return str != null && str.length() >= min && str.length() <= max;
	}
	
	// validate numeric range
	public static boolean isValidRange(double value, double min, double max) {
		return value >= min && value <= min;
	}
	 
	// 
	
}
