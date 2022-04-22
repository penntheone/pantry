package com.fireside.pantry.service;

import com.fireside.pantry.app.RecipeController;
import com.fireside.pantry.AppScene;
import com.fireside.pantry.ui.pages.DatabasePage;
import com.fireside.pantry.ui.widgets.UniversalMenu;
import com.fireside.pantry.util.objects.Recipe;

import java.util.List;

public class UIService {

    /**
     * This method handles the operations to execute a search and update the ui. This method updates the
     * recipe list view, and the recipe detail view if there are results
     */
    public static void handleSearch() {
        AppScene ui = AppScene.getInstance();
        String search = ui.getSearchBar().getText();
        String filter = ui.getSearchBar().getFilters();
        if (search.isBlank()) return;
        List<Recipe> recipes = switch (filter) {
            case "Title", "Select search option" -> RecipeController.getRecipesByTitle(search);
            case "Ingredient" -> RecipeController.getRecipesByIngredient(search);
            case "Region" -> RecipeController.getRecipesByRegion(search);
            default -> RecipeController.getRecipesByCategory(search);
        };
        RecipeService.loadImages(recipes);
        DatabasePage.getInstance().getRecipeListView().populateListView(recipes);
    }

    /**
     * This method handles the operations to select a recipe and update the ui. This method updates the
     * recipe detail view
     */
    public static void handleRecipeSelect(Recipe recipe) {
        RecipeService.loadIngredients(recipe);
        DatabasePage.getInstance().getRecipeDetailView().getDetailCard().refreshDetailCard(recipe);
    }

    public static void handleMenuSelection() {
        UniversalMenu cur = UniversalMenu.getInstance();

        if (!cur.isActivated())  cur.getMenuTranslation().setRate(1);
        else                    cur.getMenuTranslation().setRate(-1);
        cur.flipActivated();
        cur.getMenuTranslation().play();
    }
}
