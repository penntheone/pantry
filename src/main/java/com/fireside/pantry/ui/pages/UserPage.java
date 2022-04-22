package com.fireside.pantry.ui.pages;

import javafx.scene.layout.BorderPane;

public class UserPage extends BorderPane {
    private static UserPage instance;

    private UserPage() {
    }

    public BorderPane build() {
        BorderPane pane = new BorderPane();
        return pane;
    }

    public static UserPage getInstance() {
        if (instance == null) instance = new UserPage();
        return instance;
    }

}
