package com.fireside.pantry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    /**
     * Authorizes account of user based on input
     * @param username the inputted username
     * @param password the inputted password
     */
    public static void authorize(String username, String password) {
        logger.info("Username: " + username + ", Password: " + password);
    }

    /**
     * Hashes the password
     * @param input The password
     * @return Hashed password
     * @throws Exception
     */
    public static String getHash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, bytes);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
