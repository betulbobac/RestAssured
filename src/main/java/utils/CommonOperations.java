package utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.validator.routines.EmailValidator;
public class CommonOperations {

	public static boolean isValidEmail(String email) {
	       // create the EmailValidator instance
	       EmailValidator validator = EmailValidator.getInstance();

	       // check for valid email addresses using isValid method
	       return validator.isValid(email);
	   }
	
	
	
		/*
		 * public static void main(String[] args) { List<String> emails = new
		 * ArrayList<String>(); // valid email addresses
		 * emails.add("alice@example.com"); emails.add("alice.bob@example.com");
		 * emails.add("alice@example.me.org"); //invalid email addresses
		 * emails.add("alice.example.com"); emails.add("alice..bob@example.com");
		 * emails.add("alice@.example.com");
		 * 
		 * for (String value : emails) { System.out.println("The Email address " + value
		 * + " is " + (isValidEmail(value) ? "valid" : "invalid")); } }
		 */
}
