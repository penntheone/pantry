package com.fireside.pantry.ui.views;

import com.fireside.pantry.util.objects.Ingredient;
import com.fireside.pantry.util.objects.Recipe;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailView extends GridPane {

    public RecipeDetailView(Recipe recipe) {
        // Hero Image ------------------------------------------------
        VBox imagePane = new VBox();
        imagePane.setStyle("-fx-padding: 0;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 0;" +
                "-fx-border-radius: 0;" +
                "-fx-border-color: black;");

        int dimension = 200;
        imagePane.setPrefSize(dimension, dimension);
        imagePane.setMaxSize(dimension, dimension);
        imagePane.setMinSize(dimension, dimension);

        Image thumbnail = new Image(recipe.getThumb_url());
        BackgroundImage bImg = new BackgroundImage(thumbnail,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(dimension, dimension,
                        false, false, false, false));
        Background bGround = new Background(bImg);
        imagePane.setBackground(bGround);
        setConstraints(imagePane, 0, 0);

        // Header  ------------------------------------------------
        VBox headerPane = new VBox();

        Label titleNode = new Label(recipe.getTitle());
        titleNode.setFont(new Font("Arial Bold", 25));

        Label subtitleNode = new Label(recipe.getCategory() + "     |     " + recipe.getRegion());
        subtitleNode.setFont(new Font("Arial Bold Italic", 15));

        Region spacer = new Region();
        VBox.setMargin(spacer, new Insets(10, 0, 0, 0));

        Label descriptionNode = new Label("[Description] Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultrices in iaculis nunc sed augue lacus viverra vitae.");
        descriptionNode.setWrapText(true);

        headerPane.getChildren().addAll(titleNode, subtitleNode, spacer, descriptionNode);
        setConstraints(headerPane, 1, 0);

        // Ingredients  ------------------------------------------------
        VBox ingredientPane = new VBox();

        Label ingredientHeader = new Label("Ingredients");
        ingredientHeader.setFont(new Font("Arial Bold", 18));

        HBox ingredientsList = new HBox();

        try {
            VBox ingredientNamesCol = new VBox();
            List<Node> ingredientNamesNodes = new ArrayList<>();

            VBox ingredientMeasuresCol = new VBox();
            List<Node> ingredientMeasureNodes = new ArrayList<>();

            for (Ingredient i : recipe.getIngredients()) {
                Label iName = new Label(i.getName());
                iName.setFont(new Font("Arial Bold", 11));
                iName.setPrefHeight(20);
                ingredientNamesNodes.add(iName);

                Label iMeasure = new Label(i.getName());
                iMeasure.setFont(new Font("Arial Bold", 11));
                iMeasure.setPrefHeight(20);
                ingredientMeasureNodes.add(iMeasure);
            }

            ingredientNamesCol.getChildren().addAll(ingredientNamesNodes);
            ingredientMeasuresCol.getChildren().addAll(ingredientMeasureNodes);

            ingredientsList.getChildren().addAll(ingredientNamesCol, ingredientMeasuresCol);
        } catch (Exception e) {
            Label noIngredientsLabel = new Label("[No Ingredients]");
            ingredientsList.getChildren().add(noIngredientsLabel);
        }

        ingredientPane.getChildren().addAll(ingredientHeader, ingredientsList);
        setConstraints(ingredientPane, 0, 1);

        // Instructions  ------------------------------------------------

        VBox instructionPane = new VBox();
        Label instructionHeader = new Label("Instruction");
        instructionHeader.setFont(new Font("Arial Bold", 18));

        Label instruction = new Label(recipe.getInstructions());
        instruction.setWrapText(true);

        instructionPane.getChildren().addAll(instructionHeader, instruction);
        setConstraints(instructionPane, 1, 1);

        setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");
        setHgap(20);
        setVgap(20);
        getChildren().addAll(imagePane, headerPane, ingredientPane, instructionPane);
    }
}
