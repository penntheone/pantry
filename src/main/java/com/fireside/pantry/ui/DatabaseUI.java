package com.fireside.pantry.ui;

import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.widgets.SearchBar;
import com.fireside.pantry.ui.views.RecipeDetailView;
import com.fireside.pantry.ui.views.RecipeListView;
import com.fireside.pantry.ui.widgets.TitleBar;
import com.fireside.pantry.util.objects.Recipe;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public class DatabaseUI {

    private static DatabaseUI instance;

    // |----- Constants ----------

    private static final double DATABASE_UI_WIDTH = 1000;
    private static final double DATABASE_UI_HEIGHT = 600;

    // |----- Components ----------

    private final TitleBar titleBar;
    private RecipeListView recipeListView;
    private RecipeDetailView recipeDetailView;

    // |----- Constructors ----------

    private DatabaseUI() {
        List<Recipe> recipes = UIService.getDefaultRecipes();
        this.titleBar = new TitleBar("Database");
        this.recipeListView = new RecipeListView(recipes);
        this.recipeDetailView = new RecipeDetailView(recipes.get(0));
    }

    // |----- Methods ----------

    public Scene build() {
        VBox left = new VBox();
        left.getChildren().addAll(recipeListView);

        VBox top = new VBox(titleBar);
        Separator sep = new Separator();
        top.getChildren().add(sep);

        // Align menu view to left side
        // Align detail view to right side
        BorderPane pane = new BorderPane();
        pane.setTop(top);
        pane.setLeft(left);
        pane.setCenter(recipeDetailView);
        // TODO Hide faint gray separator.

        return new Scene(pane);
    }

    // |----- Getters ----------

    public SearchBar getSearchBar() {
        return titleBar.getSearchBar();
    }

    public RecipeListView getRecipeListView() {
        return recipeListView;
    }

    public RecipeDetailView getRecipeDetailView() {
        return recipeDetailView;
    }


    // |----- Setters ----------

    public void setRecipeListView(RecipeListView recipeListView) {
        this.recipeListView = recipeListView;
    }

    public void setRecipeDetailView(RecipeDetailView recipeDetailView) {
        this.recipeDetailView = recipeDetailView;
    }

    // |----- Static Methods ----------

    public static DatabaseUI getInstance() {
        if (DatabaseUI.instance == null)
            DatabaseUI.instance = new DatabaseUI();
        return DatabaseUI.instance;
    }
}
