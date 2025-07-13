package com.lazy.rest.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * String utility class
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * Generate random string
     */
    public static String randomString(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
     * Generate random number string
     */
    public static String randomNumeric(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * Generate UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Convert camelCase to snake_case
     */
    public static String camelToSnake(String str) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    /**
     * Convert snake_case to camelCase
     */
    public static String snakeToCamel(String str) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(ch));
                    nextUpper = false;
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    /**
     * Mask sensitive information
     */
    public static String maskEmail(String email) {
        if (isEmpty(email)) {
            return email;
        }
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) {
            return email;
        }
        return email.charAt(0) + "***" + email.substring(atIndex);
    }

    public static String maskPhone(String phone) {
        if (isEmpty(phone)) {
            return phone;
        }
        if (phone.length() <= 7) {
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }

    /**
     * Format file size
     */
    public static String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        }
        int z = (63 - Long.numberOfLeadingZeros(size)) / 10;
        return String.format("%.1f %sB", (double)size / (1L << (z*10)), " KMGTPE".charAt(z));
    }
} 