package com.fireside.pantry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public static void authorize(String username, String password) {
        logger.info("Username: " + username + ", Password: " + password);
    }
}
