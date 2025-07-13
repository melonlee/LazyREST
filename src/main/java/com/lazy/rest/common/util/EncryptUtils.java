package com.lazy.rest.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Encryption utility class
 */
public class EncryptUtils {

    private static final String AES_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final int DEFAULT_KEY_SIZE = 128;

    /**
     * MD5 encryption
     */
    public static String md5(String data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * SHA-256 encryption
     */
    public static String sha256(String data) {
        return DigestUtils.sha256Hex(data);
    }

    /**
     * Generate AES key
     */
    public static String generateAesKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGen.init(DEFAULT_KEY_SIZE, new SecureRandom());
        SecretKey secretKey = keyGen.generateKey();
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    /**
     * AES encryption
     */
    public static String aesEncrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key), AES_ALGORITHM));
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * AES decryption
     */
    public static String aesDecrypt(String encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key), AES_ALGORITHM));
        return new String(cipher.doFinal(Base64.decodeBase64(encryptedData)), StandardCharsets.UTF_8);
    }

    /**
     * Generate salt
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }

    /**
     * Password encryption with salt
     */
    public static String encryptPassword(String password, String salt) {
        return sha256(password + salt);
    }

    /**
     * Verify password
     */
    public static boolean verifyPassword(String password, String salt, String encryptedPassword) {
        return encryptPassword(password, salt).equals(encryptedPassword);
    }

    /**
     * Base64 encoding
     */
    public static String base64Encode(String data) {
        return Base64.encodeBase64String(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64 decoding
     */
    public static String base64Decode(String encodedData) {
        return new String(Base64.decodeBase64(encodedData), StandardCharsets.UTF_8);
    }
} 