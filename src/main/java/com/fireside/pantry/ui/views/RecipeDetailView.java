package com.fireside.pantry.ui.views;

import com.fireside.pantry.util.objects.Recipe;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

public class RecipeDetailView extends HBox {

    private final Label title;
    private final Label category;
    private final Label region;
    private final TextArea instructions;

    public RecipeDetailView(Recipe recipe) {
        this.title = new Label(recipe.getTitle());
        this.category = new Label(recipe.getCategory());
        this.region = new Label(recipe.getRegion());
        this.instructions = new TextArea(recipe.getInstructions());
        this.getChildren().addAll(title, category, region, instructions);
    }
}
