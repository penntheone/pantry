package com.fireside.pantry;

import com.fireside.pantry.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Main.logger.info("Starting session...");
        Session.getInstance();

        App.main(args);

        Main.logger.info("Ending session...");
        Session.getInstance().end();
    }
}