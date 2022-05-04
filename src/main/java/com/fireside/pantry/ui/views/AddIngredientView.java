package com.fireside.pantry.ui.views;

import javafx.scene.layout.Pane;

public class AddIngredientView extends Pane {
    private static AddIngredientView instance;

    private AddIngredientView() {
        setStyle("-fx-background-color: rgba(100, 100, 100, 0.5)");
    }

    public static AddIngredientView getInstance() {
        if (instance == null) instance = new AddIngredientView();
        return instance;
    }
}
