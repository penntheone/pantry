package com.fireside.pantry.ui.pages;

import com.fireside.pantry.service.RecipeService;
import com.fireside.pantry.ui.views.RecipeDetailView;
import com.fireside.pantry.ui.views.RecipeListView;
import com.fireside.pantry.util.objects.Recipe;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class DatabasePage extends BorderPane {
    private static DatabasePage instance;

    private RecipeListView recipeListView;
    private RecipeDetailView recipeDetailView;

    private DatabasePage() {
        List<Recipe> recipes = RecipeService.getHomeRecipes();
        this.recipeListView = new RecipeListView(recipes);
        this.recipeDetailView = new RecipeDetailView(recipes.get(0));
    }

    public BorderPane build() {
        BorderPane pane = new BorderPane();
        pane.setLeft(recipeListView);
        pane.setCenter(recipeDetailView);
        return pane;
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

    public static DatabasePage getInstance() {
        if (instance == null) instance = new DatabasePage();
        return DatabasePage.instance;
    }
}
