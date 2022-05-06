package com.fireside.pantry.ui.pages;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AdvanceSearchPage extends BorderPane {
    private static AdvanceSearchPage instance;

    private AdvanceSearchPage() {

    }

    public BorderPane build() {
        VBox center = new VBox();

        center.setAlignment(Pos.CENTER_LEFT);
        center.setMaxWidth(800);

        BorderPane pane = new BorderPane(center);
        VBox.setVgrow(pane, Priority.ALWAYS);
        pane.setStyle("-fx-padding: 10;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        return pane;
    }

    public static AdvanceSearchPage getInstance() {
        if (instance == null) instance = new AdvanceSearchPage();
        return instance;
    }

}