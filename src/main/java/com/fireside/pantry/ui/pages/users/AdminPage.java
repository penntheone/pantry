package com.fireside.pantry.ui.pages.users;

import javafx.scene.layout.BorderPane;

public class AdminPage extends BorderPane {
    private static AdminPage instance;

    private AdminPage() {

    }

    public BorderPane build() {
        BorderPane pane = new BorderPane();
        return pane;
    }

    public static AdminPage getInstance() {
        if (instance == null) instance = new AdminPage();
        return instance;
    }
}