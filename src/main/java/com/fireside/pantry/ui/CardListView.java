package com.fireside.pantry.ui;

import com.fireside.pantry.app.RecipeManager;
import com.fireside.pantry.util.objects.Recipe;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CardListView {

    public static Scene build() {
        List<Recipe> recipes = RecipeManager.getRangeOfRecipes(50, 65);
        VBox pane = new VBox();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            RecipeCard recipeCard = new RecipeCard(recipe);
            nodes.add(recipeCard);
            if (i < recipes.size() - 1)
                nodes.add(new Separator(Orientation.HORIZONTAL));
        }
        pane.getChildren().addAll(nodes);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pane);

        return new Scene(scrollPane, 1100, 600);
    }
}