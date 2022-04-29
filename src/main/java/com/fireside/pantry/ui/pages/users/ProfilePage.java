package com.fireside.pantry.ui.pages.users;

import javafx.scene.layout.BorderPane;

public class ProfilePage extends BorderPane {
    private static ProfilePage instance;

    private ProfilePage() {

    }

    public BorderPane build() {
        BorderPane pane = new BorderPane();
        return pane;
    }

    public static ProfilePage getInstance() {
        if (instance == null) instance = new ProfilePage();
        return instance;
    }
}