package com.fireside.pantry.service;

import com.fireside.pantry.app.Session;
import com.fireside.pantry.app.control.UserController;
import com.fireside.pantry.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public static void authorize(String username, String password) throws Exception {
        User user = UserController.getUserByUsername(username);
        if (!user.getUsername().equals(username))
            throw new IllegalArgumentException("User does not exist");
        if (!user.getAuthString().equals(getHash(password)))
            throw new IllegalArgumentException("Invalid password provided");
        Session.getInstance().setAuthorizedUser(user);
        logger.info(String.format("Authorized user [%s]", user.getUsername()));
    }

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
