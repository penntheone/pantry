package com.fireside.pantry.service;

import com.fireside.pantry.app.control.IngredientManager;
import com.fireside.pantry.app.control.RecipeManager;
import com.fireside.pantry.ui.DatabaseUI;
import com.fireside.pantry.util.objects.Recipe;
import javafx.scene.Scene;

import java.util.List;

public class UIService {

    /**
     * This method will build and return the starting homepage. Right now that is the DatabaseUI. This is the first
     * time the DatabaseUI is instantiated (First call to DatabaseUI.getInstance())
     * @return Scene starting scene
     */
    public static Scene getStartingScene() {
        return DatabaseUI.getInstance().build();
    }

    /**
     * This method returns a default list of recipes for setting up the UI
     * @return List of recipes
     */
    public static List<Recipe> getDefaultRecipes() {
        return RecipeManager.getRangeOfRecipes(25, 35);
    }

    /**
     * This method handles the operations to execute a search and update the ui. This method updates the
     * recipe list view, and the recipe detail view if there are results
     */
    public static void handleSearch() {
        DatabaseUI ui = DatabaseUI.getInstance();
        String search = ui.getSearchBar().getText();
        String filters = ui.getSearchBar().getFilters();
        if (search.isBlank() || filters.equals("Select search option")) return;
        List<Recipe> recipes = null;
        if (filters.equals("Recipe Title")) {
            recipes = RecipeManager.getRecipesByTitle(search);
        } else if (filters.equals("Ingredient")) {
           recipes = RecipeManager.getRecipesByIngredient(search);
        } else if (filters.equals("Region")) {
            recipes = RecipeManager.getRecipesByRegion(search);
        } else {
            recipes = RecipeManager.getRecipesByCategory(search);
        }
        for (Recipe recipe : recipes) {
            recipe.setIngredients(IngredientManager.getRecipeIngredients(recipe.getId()));
        }
        ui.getRecipeListView().populateListView(recipes);
    }

    /**
     * This method handles the operations to select a recipe and update the ui. This method updates the
     * recipe detail view
     */
    public static void handleRecipeSelect(Recipe recipe) {
        DatabaseUI ui = DatabaseUI.getInstance();
        ui.getRecipeDetailView().getDetailCard().refreshDetailCard(recipe);
    }
}
