package com.fireside.pantry.app;

import com.fireside.pantry.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Session {

    private static final Logger logger = LoggerFactory.getLogger(Session.class);
    private static Session instance;

    private LocalDateTime sessionStart;
    private LocalDateTime sessionEnd;
    private User user;

    /**
     * Constructor that starts the session
     */
    private Session() {
        this.sessionStart = LocalDateTime.now();
    }

    /**
     * Ends session, destructor of sorts
     */
    public void end() {
        this.sessionEnd = LocalDateTime.now();
    }

    // |----- Getters --------------------

    /**
     * retrieves whether a user has been authorized or not
     * @return whether a user has been authorized or not
     */
    public boolean userAuthorized() {
        return (user != null);
    }

    /**
     * retrieves a user if one has been authorized
     * @return the authorized user
     */
    public User getAuthorizedUser() throws NullPointerException {
        if (user == null)
            throw new NullPointerException("No user has been authorized");
        return user;
    }

    /**
     * Returns the object itself if created, will create if not
     * @return the object itself
     */
    public static Session getInstance() {
        if (Session.instance == null)
            Session.instance = new Session();
        return Session.instance;
    }
}
