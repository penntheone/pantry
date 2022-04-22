package com.fireside.pantry.ui.pages;

import javafx.scene.layout.BorderPane;

public class MealPlanningPage extends BorderPane {
    private static MealPlanningPage instance;

    private MealPlanningPage() {

    }

    public BorderPane build() {
        BorderPane pane = new BorderPane();
        return pane;
    }

    public static MealPlanningPage getInstance() {
        if (instance == null) instance = new MealPlanningPage();
        return instance;
    }

}
