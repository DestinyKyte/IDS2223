package it.unicam.cs.ids.loyaltyPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class LoyaltyPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyPlatformApplication.class, args);
	}

	// TODO non blocca le password senza numeri
	public static boolean checkPassword(String password){
		Pattern letters = Pattern.compile("[^a-z]");
		Pattern capitalLetters = Pattern.compile(".*[A-Z].*");
		Pattern digits = Pattern.compile("[^0-9]");
		Pattern specialCharacters = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

		Matcher hasLetters = letters.matcher(password);
		Matcher hasCapitalLetters = capitalLetters.matcher(password);
		Matcher hasDigits = digits.matcher(password);
		Matcher hasSpecialCharacters = specialCharacters.matcher(password);

		return password.length()>=8
				&& hasLetters.find()
				&& hasCapitalLetters.find()
				&& hasDigits.find()
				&& hasSpecialCharacters.find();
	}

}
