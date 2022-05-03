package com.fireside.pantry.ui.views;

import com.fireside.pantry.ui.components.RecipeDetailCard;
import com.fireside.pantry.app.model.Recipe;

import javafx.scene.control.ScrollPane;

public class RecipeDetailView extends ScrollPane {

    public final RecipeDetailCard detailCard;

    public RecipeDetailView(Recipe recipe) {
        this.detailCard = new RecipeDetailCard(recipe);
        setContent(detailCard);
        setStyle("-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-background-color: white;" +
                "-fx-background-insets: 0, 0, 0, 0");
        setVbarPolicy(ScrollBarPolicy.NEVER);
        setFitToWidth(true);
    }

    public RecipeDetailCard getDetailCard() {
        return detailCard;
    }
}
