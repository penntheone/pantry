package com.fireside.pantry.ui.views;

import com.fireside.pantry.ui.components.RecipeCard;
import com.fireside.pantry.util.objects.Recipe;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class RecipeListView extends ScrollPane {
    VBox content;

    public RecipeListView(List<Recipe> recipes) {
        content = new VBox();
        populateListView(recipes);
        setContent(content);
        setMinWidth(500);
        setStyle("-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-background-color: white;" +
                "-fx-background-insets: 0, 0, 0, 0");
        setVbarPolicy(ScrollBarPolicy.NEVER);
    }

    public void populateListView(List<Recipe> recipes) {
        content.getChildren().clear();
        if (recipes.isEmpty()) {
            Label no_recipes = new Label("No Recipes");
            no_recipes.setFont(new Font("Arial", 15));
            VBox blank = new VBox(no_recipes);
            blank.setPrefWidth(500);
            blank.setMinHeight(700);
            VBox.setVgrow(blank, Priority.ALWAYS);
            blank.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-focus-color: transparent;" +
                    "-fx-faint-focus-color: transparent");
            blank.setAlignment(Pos.CENTER);
            content.getChildren().add(blank);
        }
        else {
            List<Node> cards = new ArrayList<>();
            for (Recipe recipe : recipes) cards.add(new RecipeCard(recipe));
            content.getChildren().addAll(cards);
        }
    }
}