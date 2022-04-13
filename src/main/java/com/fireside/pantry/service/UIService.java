package com.fireside.pantry.service;

import com.fireside.pantry.app.RecipeController;
import com.fireside.pantry.ui.DatabaseUI;
import com.fireside.pantry.util.objects.Recipe;

import java.util.List;

public class UIService {

    /**
     * This method handles the operations to execute a search and update the ui. This method updates the
     * recipe list view, and the recipe detail view if there are results
     */
    public static void handleSearch() {
        DatabaseUI ui = DatabaseUI.getInstance();
        String search = ui.getSearchBar().getText();
        String filter = ui.getSearchBar().getFilters();
        if (search.isBlank()) return;
        List<Recipe> recipes = switch (filter) {
            case "Recipe Title", "Select search option" -> RecipeController.getRecipesByTitle(search);
            case "Ingredient" -> RecipeController.getRecipesByIngredient(search);
            case "Region" -> RecipeController.getRecipesByRegion(search);
            default -> RecipeController.getRecipesByCategory(search);
        };
        RecipeService.loadImages(recipes);
        ui.getRecipeListView().populateListView(recipes);
    }

    /**
     * This method handles the operations to select a recipe and update the ui. This method updates the
     * recipe detail view
     */
    public static void handleRecipeSelect(Recipe recipe) {
        RecipeService.loadIngredients(recipe);
        DatabaseUI.getInstance().getRecipeDetailView().getDetailCard().refreshDetailCard(recipe);
    }
}
