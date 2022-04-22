package com.fireside.pantry.service;

import com.fireside.pantry.app.RecipeController;
import com.fireside.pantry.AppScene;
import com.fireside.pantry.ui.pages.*;
import com.fireside.pantry.ui.widgets.TitleBar;
import com.fireside.pantry.ui.widgets.UniversalMenu;
import com.fireside.pantry.util.objects.Recipe;

import java.util.List;

public class UIService {

    /**
     * This method handles the operations to execute a search and update the ui. This method updates the
     * recipe list view, and the recipe detail view if there are results
     */
    public static void handleSearch() {
        handlePageSelection("Database");

        AppScene ui = AppScene.getInstance();
        String search = ui.getSearchBar().getText();
        String filter = ui.getSearchBar().getFilters();
        if (search.isBlank()) return;
        List<Recipe> recipes = switch (filter) {
            case "Ingredient" -> RecipeController.getRecipesByIngredient(search);
            case "Region" -> RecipeController.getRecipesByRegion(search);
            default -> RecipeController.getRecipesByTitle(search);
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

    /**
     * Closes the menu only if menu is activated.
     */
    public static void closeMenu() {
        UniversalMenu cur = UniversalMenu.getInstance();
        if (cur.isActivated()) {
            cur.setActivated(false);
            cur.getMenuTranslation().setRate(-1);
            cur.getMenuTranslation().play();
        }
    }

    /**
     * Opens the menu.
     */
    public static void openMenu() {
        UniversalMenu cur = UniversalMenu.getInstance();
        cur.setActivated(true);
        cur.getMenuTranslation().setRate(1);
        cur.getMenuTranslation().play();
    }

    /**
     * Switch to the requested page, update the title,
     * then close the menu if needed.
     * @param page the requested page
     */
    public static void handlePageSelection(String page) {
        switch (page) {
            case "Database"         -> AppScene.getInstance().setContent(DatabasePage.getInstance().build());
            case "Advance Search"   -> AppScene.getInstance().setContent(AdvanceSearchPage.getInstance().build());
            case "Meal Planning"    -> AppScene.getInstance().setContent(MealPlanningPage.getInstance().build());
            case "About"            -> AppScene.getInstance().setContent(AboutPage.getInstance().build());
            case "User"             -> AppScene.getInstance().setContent(UserPage.getInstance().build());
        }
        TitleBar.getInstance().setTitle(page);
        closeMenu();
    }

    public static void handleLogin() {

    }
}
