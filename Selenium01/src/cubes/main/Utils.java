package cubes.main;

import java.util.Random;

public class Utils {
	
	public static int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(10000);
		
	}
	
	public static String getRandomCategoryName() {
		return "Category name "+ getRandomNumber();
	}
	
	public static String getRandomCategoryDescription() {
		String availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=[]{}|;:,.<>?";
		
		int minLength = 50;
		
		Random random = new Random();
		
		int descriptionLength = random.nextInt(Math.min(100, availableChars.length() - minLength + 1)) + minLength;
		
		StringBuilder description = new StringBuilder();
		for(int i=0;i<descriptionLength;i++) {
			int randomIndex = random.nextInt(availableChars.length());
            description.append(availableChars.charAt(randomIndex));
		}
		
		return description.toString();
	}
	
	public static String getRandomPostTitleExtension() {
		String availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=[]{}|;:,.<>?";
		
		int minLength = 20;
		
		Random random = new Random();
		
		int titleLength = random.nextInt(Math.min(50, availableChars.length() - minLength + 1)) + minLength;
		
		StringBuilder title = new StringBuilder();
		for(int i=0;i<titleLength;i++) {
			int randomIndex = random.nextInt(availableChars.length());
            title.append(availableChars.charAt(randomIndex));
            }
		return title.toString();
		
		
		
	}
	
	public static String getRandomTitle() {
		return "Post title "+ getRandomPostTitleExtension();
	}

}
