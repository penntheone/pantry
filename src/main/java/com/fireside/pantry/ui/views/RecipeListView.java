package com.fireside.pantry.ui.views;

import com.fireside.pantry.ui.components.RecipeCard;
import com.fireside.pantry.util.objects.Recipe;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class RecipeListView extends ScrollPane {

    private List<Recipe> recipes;

    public RecipeListView(List<Recipe> recipes) {
        this.recipes = recipes;
        VBox content = new VBox();
        List<Node> cards = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            cards.add(new RecipeCard(recipes.get(i)));
            if (i < recipes.size() - 1)
                cards.add(new Separator(Orientation.HORIZONTAL));
        }
        content.getChildren().addAll(cards);
        this.setContent(content);
    }
}