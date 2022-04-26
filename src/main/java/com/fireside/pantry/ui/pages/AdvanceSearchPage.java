package com.fireside.pantry.ui.pages;

import javafx.scene.layout.BorderPane;

public class AdvanceSearchPage extends BorderPane {
    private static AdvanceSearchPage instance;

    private AdvanceSearchPage() {

    }

    public BorderPane build() {
        BorderPane pane = new BorderPane();
        return pane;
    }

    public static AdvanceSearchPage getInstance() {
        if (instance == null) instance = new AdvanceSearchPage();
        return instance;
    }

}