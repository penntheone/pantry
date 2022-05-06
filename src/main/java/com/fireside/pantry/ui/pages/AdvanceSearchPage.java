package com.fireside.pantry.ui.pages;

import com.fireside.pantry.app.model.Recipe;
import com.fireside.pantry.service.RecipeService;
import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.views.RecipeDetailView;
import com.fireside.pantry.ui.views.RecipeListView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.util.List;

public class AdvanceSearchPage extends BorderPane {
    private static AdvanceSearchPage instance;

    private RecipeListView recipeListView;
    private RecipeDetailView recipeDetailView;

    private TextField titleField;
    private TextField ingredientField;
    private TextField regionField;
    private TextField typeField;

    private AdvanceSearchPage() {
        List<Recipe> recipes = RecipeService.getHomeRecipes();
        this.recipeListView = new RecipeListView(recipes);
        this.recipeDetailView = new RecipeDetailView(recipes.get(0));
    }

    public BorderPane build() {
        HBox top = new HBox();

        titleField = new TextField();
        ingredientField = new TextField();
        regionField = new TextField();
        typeField = new TextField();

        top.getChildren().addAll(
                generateField(titleField, "Title"),
                generateField(ingredientField, "Ingredient"),
                generateField(regionField, "Region"),
                generateField(typeField, "Type")
                );

        top.setSpacing(50);
        top.setStyle("-fx-padding: 20");
        top.setMaxHeight(30);

        BorderPane pane = new BorderPane();
        pane.setTop(top);
        pane.setLeft(recipeListView);
        pane.setCenter(recipeDetailView);
        return pane;
    }

    private VBox generateField(TextField searchField, String label) {
        VBox result = new VBox();

        Label name = new Label(label);
        result.getChildren().addAll(name, generateSearchBar(searchField));
        result.setSpacing(5);

        return result;
    }

    private HBox generateSearchBar(TextField searchField) {
        HBox result = new HBox();
        // ---------------------- Search field
        searchField.setStyle(
                "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-background-color: transparent");
        searchField.setPromptText("Enter here");
        searchField.setPrefWidth(320);
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) UIService.handleAdvanceSearch();
        });

        // ---------------------- Spacer until Right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // ---------------------- Styling
        result.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-border-color: black;" +
                        "-fx-background-color: white");
        result.setAlignment(Pos.CENTER_LEFT);
        result.getChildren().add(searchField);
        setMinWidth(300);
        setMaxWidth(500);
        setMaxHeight(100);

        searchField.prefHeightProperty().bind(result.heightProperty());

        return result;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getIngredientField() {
        return ingredientField;
    }

    public TextField getRegionField() {
        return regionField;
    }

    public TextField getTypeField() {
        return typeField;
    }

    public RecipeListView getRecipeListView() {
        return recipeListView;
    }

    public RecipeDetailView getRecipeDetailView() {
        return recipeDetailView;
    }

    public void setRecipeListView(RecipeListView recipeListView) {
        this.recipeListView = recipeListView;
    }

    public void setRecipeDetailView(RecipeDetailView recipeDetailView) {
        this.recipeDetailView = recipeDetailView;
    }

    public static AdvanceSearchPage getInstance() {
        if (instance == null) instance = new AdvanceSearchPage();
        return instance;
    }

}