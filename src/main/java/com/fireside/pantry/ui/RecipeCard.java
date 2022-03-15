package com.fireside.pantry.ui;

import com.fireside.pantry.util.objects.Recipe;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RecipeCard extends VBox {
    Recipe recipe;

    public RecipeCard(Recipe recipe) {
        this.recipe = recipe;

        setMinSize(200, 150);
        setPrefSize(500, 250);
        Label titleNode = new Label(recipe.getTitle());
        titleNode.setFont(new Font("Arial Bold", 18));

        Label categoryNode = new Label(recipe.getCategory());
        categoryNode.setFont(new Font("Arial Bold Italic", 11));
        categoryNode.setPrefHeight(20);

        Label instructionNode = new Label(recipe.getInstructions());
        instructionNode.setFont(new Font("Arial", 10));
        instructionNode.setWrapText(true);

        setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");

        getChildren().addAll(titleNode, categoryNode, instructionNode);
    }
}
