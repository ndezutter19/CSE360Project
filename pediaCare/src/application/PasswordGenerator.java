package application;

import java.security.SecureRandom;

public class PasswordGenerator {

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final SecureRandom random = new SecureRandom();
    
    public static String generatePassword() {
        StringBuilder result = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(PASSWORD_ALLOW_BASE.length());
            result.append(PASSWORD_ALLOW_BASE.charAt(index));
        }
        return result.toString();
    }
}
