package com.fireside.pantry.ui.pages;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * The advance search page which allows users to do more advanced searches
 */
public class AdvanceSearchPage extends BorderPane {
    private static AdvanceSearchPage instance;

    /**
     * Private constructor
     */
    private AdvanceSearchPage() {

    }

    /**
     * Builds the page
     * @return The page itself visually
     */
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

    /**
     * Returns the object itself if created, will create if not
     * @return the object itself
     */
    public static AdvanceSearchPage getInstance() {
        if (instance == null) instance = new AdvanceSearchPage();
        return instance;
    }

}