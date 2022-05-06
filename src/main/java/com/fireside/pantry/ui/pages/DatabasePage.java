package com.fireside.pantry.ui.pages;

import com.fireside.pantry.service.RecipeService;
import com.fireside.pantry.ui.views.RecipeDetailView;
import com.fireside.pantry.ui.views.RecipeListView;
import com.fireside.pantry.app.model.Recipe;
import javafx.scene.layout.BorderPane;

import java.util.List;

/**
 * The database page that contains all the recipes as well as showing
 * a detailed view of one recipe at a time
 */
public class DatabasePage extends BorderPane {
    private static DatabasePage instance;

    private RecipeListView recipeListView;
    private RecipeDetailView recipeDetailView;

    /**
     * Private constructor which takes care of the key variables
     */
    private DatabasePage() {
        List<Recipe> recipes = RecipeService.getHomeRecipes();
        this.recipeListView = new RecipeListView(recipes);
        this.recipeDetailView = new RecipeDetailView(recipes.get(0));
    }

    /**
     * Builds the page
     * @return The page itself visually
     */
    public BorderPane build() {
        BorderPane pane = new BorderPane();
        pane.setLeft(recipeListView);
        pane.setCenter(recipeDetailView);
        return pane;
    }

    /**
     * Retrieves the recipe list view
     * @return the recipe list view
     */
    public RecipeListView getRecipeListView() {
        return recipeListView;
    }

    /**
     * Retrieves the recipe detail view
     * @return the recipe detail view
     */
    public RecipeDetailView getRecipeDetailView() {
        return recipeDetailView;
    }

    public void setRecipeListView(RecipeListView recipeListView) {
        this.recipeListView = recipeListView;
    }

    public void setRecipeDetailView(RecipeDetailView recipeDetailView) {
        this.recipeDetailView = recipeDetailView;
    }

    /**
     * Returns the object itself if created, will create if not
     * @return the object itself
     */
    public static DatabasePage getInstance() {
        if (instance == null) instance = new DatabasePage();
        return instance;
    }
}
