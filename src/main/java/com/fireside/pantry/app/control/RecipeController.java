package com.fireside.pantry.app.control;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.app.model.Recipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecipeController {

    public static List<Recipe> getAllRecipes() {
        return getRecipes("CALL spGetAllRecipes()");
    }

    public static List<Recipe> getRangeOfRecipes(int start, int end) {
        List<Recipe> recipes = new ArrayList<>();
        for (int i = start; i < end + 1; i++) {
            recipes.add(getRecipeByID(i));
        }
        return recipes;
    }

    public static List<Recipe> getRecipesByIngredient(String ingredient) {
        String query = String.format("CALL spGetRecipesByIngredient('%s');", ingredient);
        return getRecipes(query);
    }

    public static Recipe getRecipeByID(int recipeId) {
        String query = String.format("CALL spGetRecipeByID(%d);", recipeId);
        List<Recipe> recipes = getRecipes(query);
        if (recipes.size() == 0)
            return new Recipe();
        return recipes.get(0);
    }

    public static int getRecipeIDByName(String name) {
        String query = String.format("CALL spGetRecipeIDByName('%s');", name);
        List<Recipe> recipes = getRecipes(query);
        if (recipes.size() == 0)
            return -1;
        return recipes.get(0).getId();
    }

    public static List<Recipe> getRecipesByRegion(String region) {
        String query = String.format("CALL spGetRecipeByRegion('%s');", region);
        return getRecipes(query);
    }

    public static List<Recipe> getRecipesByCategory(String category) {
        String query = String.format("CALL spGetRecipeByCategory('%s');", category);
        return getRecipes(query);
    }

    /**
     * Default parameters as an empty string ("") if empty
     * @param recipeName - name of recipe
     * @param region - region
     * @param category - category
     * @return list of filtered recipes
     */
    public static List<Recipe> basicAdvancedSearch(String recipeName, String region, String category) {
        String query = String.format("CALL spBasicAdvancedSearch('%s', '%s', '%s');", recipeName, region, category);
        return getRecipes(query);
    }

    /**
     * This will filter recipes that contain ingredients the user is allergic to
     * UserID cannot be empty but all other fields should an empty string ("") if
     * nothing is there
     * @param UserId - userid of searcher
     * @param recName - name of recipe
     * @param region - region
     * @param category - category
     * @return
     */
    public static List<Recipe> advancedSearch(int UserId, String recName, String region, String category) {
        String query = String.format("CALL spBasicAdvancedSearch('%d','%s', '%s', '%s');", UserId, recName, region, category);
        return getRecipes(query);
    }

    public static List<Recipe> getRecipesByTitle(String title) {
        String query = String.format("CALL spGetRecipeByTitle('%s');", title);
        return getRecipes(query);
    }

    private static List<Recipe> getRecipes(String query) {
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            LinkedList<Recipe> recipes = new LinkedList<>();
            for (Row row : rows) {
                recipes.add(new Recipe(row));
            }
            return recipes;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}
