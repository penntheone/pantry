package com.fireside.pantry.ui.pages.users;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ProfilePage extends BorderPane {
    private static ProfilePage instance;

    private static String username;

    private ProfilePage() {

    }

    public BorderPane build() {
        Label welcomeLabel = new Label("Welcome, [Penn]");
        Label quickActionLabel = new Label("Quick actions");

        Button logoutButton = new Button("Logout");
        Button addRecipeButton = new Button("Add recipe");

        Label preferencesLabel = new Label("Preferences");

        HBox center = new HBox(logoutButton);

        BorderPane pane = new BorderPane(center);
        return pane;
    }

    public static ProfilePage getInstance() {
        if (instance == null) instance = new ProfilePage();
        return instance;
    }
}