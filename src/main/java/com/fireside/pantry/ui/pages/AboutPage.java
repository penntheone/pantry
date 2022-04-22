package com.fireside.pantry.ui.pages;

import javafx.scene.layout.BorderPane;

public class AboutPage extends BorderPane {
    private static AboutPage instance;

    private AboutPage() {
    }

    public BorderPane build() {
        BorderPane pane = new BorderPane();
        return pane;
    }

    public static AboutPage getInstance() {
        if (instance == null) instance = new AboutPage();
        return instance;
    }

}
