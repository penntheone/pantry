package com.fireside.pantry.ui;
import com.fireside.pantry.app.RecipeManager;
import com.fireside.pantry.util.objects.Recipe;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class UniversalMenu {
    public static Scene build() {
        VBox pane = new VBox();

        Label homeLabel = new Label("Home");
        homeLabel.setFont(new Font("Arial", 15));

        Label databaseLabel = new Label("Home");
        databaseLabel.setFont(new Font("Arial", 15));

        Label lookupLabel = new Label("Lookup Recipes");
        lookupLabel.setFont(new Font("Arial", 15));

        Label mealLabel = new Label("Meal Planning");
        mealLabel.setFont(new Font("Arial", 15));

        pane.getChildren().addAll(homeLabel, databaseLabel, lookupLabel, mealLabel);
        return new Scene(pane, 400, 600);
    }
}
