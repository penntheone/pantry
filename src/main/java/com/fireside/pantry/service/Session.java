package com.fireside.pantry.service;

import com.fireside.pantry.app.control.UserManager;
import com.fireside.pantry.util.AuthUtils;
import com.fireside.pantry.util.objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Session {

    private static final Logger logger = LoggerFactory.getLogger(Session.class);
    private static Session instance;

    private LocalDateTime sessionStart;
    private LocalDateTime sessionEnd;
    private User user;

    private Session() {
        this.sessionStart = LocalDateTime.now();
    }

    public boolean authorizeUser(String username, String password) throws Exception {
        User user = UserManager.getUserByUsername(username);
        if (user.getUsername().equals(""))
            throw new NullPointerException("User does not exist");
        if (user.getAuthString().equals(AuthUtils.getHash(password))) {
            logger.info("Authorized user -> " + user.toString());
            this.user = user;
            return true;
        }
        return false;
    }

    public void end() {
        this.sessionEnd = LocalDateTime.now();
    }

    // |----- Getters --------------------

    public boolean userAuthorized() {
        return (user != null);
    }

    public User getAuthorizedUser() throws NullPointerException {
        if (user == null)
            throw new NullPointerException("No user has been authorized");
        return user;
    }

    public static Session getInstance() {
        if (Session.instance == null)
            Session.instance = new Session();
        return Session.instance;
    }
}
