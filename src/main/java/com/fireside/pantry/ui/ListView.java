package com.fireside.pantry.ui;

import com.fireside.pantry.app.RecipeManager;
import com.fireside.pantry.util.objects.Recipe;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ListView {

    public static Scene build() {
        List<Recipe> recipes = RecipeManager.getRangeOfRecipes(1, 10);

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            List<Node> cardNodes = new ArrayList<>();
            cardNodes.add(new Label(recipe.getTitle()));
            cardNodes.add(new Label(recipe.getCategory()));
            cardNodes.add(new Label(recipe.getInstructions()));
            VBox box = new VBox();
            box.getChildren().addAll(cardNodes);
            nodes.add(box);
            if (i < recipes.size() - 1)
                nodes.add(new Separator(Orientation.HORIZONTAL));
        }

        VBox pane = new VBox();
        pane.getChildren().addAll(nodes);

        return new Scene(pane, 1100, 600);
    }
}
