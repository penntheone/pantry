package com.fireside.pantry.ui.views;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class AddRecipeView extends Pane {
    private static AddRecipeView instance;

    private AddRecipeView() {
        setStyle("-fx-background-color: rgba(100, 100, 100, 0.5)");
    }

    public static AddRecipeView getInstance() {
        if (instance == null) instance = new AddRecipeView();
        return instance;
    }
}
