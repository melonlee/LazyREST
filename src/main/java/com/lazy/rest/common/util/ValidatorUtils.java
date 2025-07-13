package com.lazy.rest.common.util;

import java.util.regex.Pattern;

/**
 * Validation utility class
 */
public class ValidatorUtils {

    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";
    
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{4,16}$";
    
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    
    private static final String URL_PATTERN = 
        "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$";
    
    private static final String IP_PATTERN = 
        "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    
    private static final String ID_CARD_PATTERN = 
        "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    /**
     * Validate email
     */
    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    /**
     * Validate phone number (Chinese format)
     */
    public static boolean isValidPhone(String phone) {
        return Pattern.matches(PHONE_PATTERN, phone);
    }

    /**
     * Validate username (4-16 characters, letters, numbers, underscore, hyphen)
     */
    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_PATTERN, username);
    }

    /**
     * Validate password (minimum 8 characters, at least one uppercase letter, one lowercase letter and one number)
     */
    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    /**
     * Validate URL
     */
    public static boolean isValidUrl(String url) {
        return Pattern.matches(URL_PATTERN, url);
    }

    /**
     * Validate IP address
     */
    public static boolean isValidIp(String ip) {
        return Pattern.matches(IP_PATTERN, ip);
    }

    /**
     * Validate Chinese ID card number
     */
    public static boolean isValidIdCard(String idCard) {
        return Pattern.matches(ID_CARD_PATTERN, idCard);
    }

    /**
     * Validate string length
     */
    public static boolean isLengthValid(String str, int min, int max) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        return length >= min && length <= max;
    }

    /**
     * Validate number range
     */
    public static boolean isNumberInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    public static boolean isNumberInRange(long number, long min, long max) {
        return number >= min && number <= max;
    }

    public static boolean isNumberInRange(double number, double min, double max) {
        return number >= min && number <= max;
    }
} 