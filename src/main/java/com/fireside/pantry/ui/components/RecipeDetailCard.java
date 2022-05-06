package com.fireside.pantry.ui.components;

import com.fireside.pantry.app.control.ImageController;
import com.fireside.pantry.app.control.IngredientController;
import com.fireside.pantry.app.model.Ingredient;
import com.fireside.pantry.app.model.Recipe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;

/**
 * The recipe detail cards which show up when a user clicks
 * on a specific recipe, only one of these will appear at a time
 */
public class RecipeDetailCard extends GridPane {

    static final int DIMENSION = 250;

    private final VBox imagePane;
    private final Label titleNode;
    private final Label subtitleNode;
    private final Label descriptionNode;
    private final GridPane ingredientGrid;
    private final Label instruction;

    /**
     * Constructor that sets how the detail card is displayed on the app
     * @param recipe the specified recipe
     */
    public RecipeDetailCard(Recipe recipe) {
        // Hero Image ------------------------------------------------
        imagePane = new VBox();
        imagePane.setStyle("-fx-padding: 0;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 0;" +
                "-fx-border-radius: 0;" +
                "-fx-border-color: black;");

        imagePane.setPrefSize(DIMENSION, DIMENSION);
        imagePane.setMaxSize(DIMENSION, DIMENSION);
        imagePane.setMinSize(DIMENSION, DIMENSION);

        setThumbnail(imagePane, recipe);
        setConstraints(imagePane, 0, 0);

        // Header  ------------------------------------------------
        VBox headerPane = new VBox();

        titleNode = new Label(recipe.getTitle());
        titleNode.setFont(new Font("Arial Bold", 25));

        subtitleNode = new Label(recipe.getCategory() + "     |     " + recipe.getRegion());
        subtitleNode.setFont(new Font("Arial Bold Italic", 15));

        Region spacer = new Region();
        VBox.setMargin(spacer, new Insets(10, 0, 0, 0));

        descriptionNode = new Label("[Description] Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultrices in iaculis nunc sed augue lacus viverra vitae.");
        descriptionNode.setWrapText(true);

        Region spacer2 = new Region();
        spacer2.setPrefHeight(15);

        // ---------------------- Nutrition pane
        VBox caloriesBox    = generateNutritionLabel("Calories",    "830kcal");
        VBox proteinBox     = generateNutritionLabel("Protein",     "75g");
        VBox carbBox        = generateNutritionLabel("Carb",        "32g");
        VBox fatBox         = generateNutritionLabel("Fat",         "220g");

        Label nutritionTitleLabel = new Label("Key Nutrition Facts | Per Serving");
        nutritionTitleLabel.setFont(new Font("Arial Bold", 13));

        HBox nutritionInfo = new HBox(caloriesBox, proteinBox, carbBox, fatBox);
        nutritionInfo.setSpacing(20);

        VBox nutritionPane = new VBox(nutritionTitleLabel, nutritionInfo);
        nutritionPane.setSpacing(5);
        nutritionPane.setAlignment(Pos.TOP_CENTER);
        nutritionInfo.setAlignment(Pos.BASELINE_CENTER);

        headerPane.getChildren().addAll(titleNode, subtitleNode, spacer, descriptionNode, spacer2, nutritionPane);
        setConstraints(headerPane, 1, 0);

        // Ingredients  ------------------------------------------------
        VBox ingredientPane = new VBox();

        Label ingredientHeader = new Label("Ingredients");
        ingredientHeader.setFont(new Font("Arial Bold", 18));

        ingredientGrid = new GridPane();
        setIngredientGrid(ingredientGrid, recipe);

        ingredientPane.getChildren().addAll(ingredientHeader, ingredientGrid);
        setConstraints(ingredientPane, 0, 1);

        // Instructions  ------------------------------------------------

        VBox instructionPane = new VBox();
        Label instructionHeader = new Label("Instruction");
        instructionHeader.setFont(new Font("Arial Bold", 18));

        instruction = new Label(recipe.getInstructions());
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

    /**
     * Refreshes the card, allowing it to switch to a different recipe
     * @param recipe the specified recipe
     */
    public void refreshDetailCard(Recipe recipe) {
        setThumbnail(imagePane, recipe);
        titleNode.setText(recipe.getTitle());
        subtitleNode.setText(recipe.getCategory() + "     |     " + recipe.getRegion());
//        descriptionNode.setText("");
        setIngredientGrid(ingredientGrid, recipe);
        instruction.setText(recipe.getInstructions());
    }

    /**
     * Sets ingredient grid based on recipe
     * @param ingredientGrid the grid
     * @param recipe the recipe which the ingredients belong to
     */
    private static void setIngredientGrid(GridPane ingredientGrid, Recipe recipe) {
        ingredientGrid.getChildren().clear();
        try {
            List<Ingredient> ingredientsMatrix = IngredientController.getRecipeIngredients(recipe.getId());
            for (int i = 0; i < ingredientsMatrix.size(); i++) {
                Ingredient iIngredient = ingredientsMatrix.get(i);
                Label iName = new Label(iIngredient.getName());
                iName.setFont(new Font("Arial Bold", 11));
                iName.setPrefHeight(20);

                Label iMeasure = new Label(iIngredient.getMeasure());
                iMeasure.setFont(new Font("Arial", 11));
                iMeasure.setPrefHeight(20);

                ingredientGrid.getChildren().addAll(iName, iMeasure);
                setConstraints(iName, 0, i);
                setConstraints(iMeasure, 1, i);
            }
            ingredientGrid.setHgap(20);
            ingredientGrid.setVgap(0);
        } catch (Exception ignored) {
            Label noIngredientsLabel = new Label("[No Ingredients]");
            ingredientGrid.getChildren().add(noIngredientsLabel);
        }
    }

    /**
     * Generates label based on nutrition and count
     * @param nutrition the specified nutrition
     * @param count the specified count
     * @return the label based on the information
     */
    private static VBox generateNutritionLabel(String nutrition, String count) {
        Label nutritionLabel = new Label(nutrition);
        nutritionLabel.setFont(new Font("Arial", 13));

        Label countLabel = new Label(count);
        countLabel.setFont(new Font("Arial Bold", 17));

        VBox nutritionBox = new VBox(nutritionLabel, countLabel);
        nutritionBox.setAlignment(Pos.TOP_CENTER);

        return nutritionBox;
    }

    /**
     * Sets thumbnail with the specified image pane and recipe
     * @param imagePane the pane where the thumbnail is placed
     * @param recipe the recipe it belongs to
     */
    private static void setThumbnail(VBox imagePane, Recipe recipe) {
        BackgroundImage bImg = new BackgroundImage(
                ImageController.getInstance().getRecipeImage(recipe),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(DIMENSION, DIMENSION,
                        false, false, false, false));
        Background bGround = new Background(bImg);
        imagePane.setBackground(bGround);
    }
}
