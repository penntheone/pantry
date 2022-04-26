package com.fireside.pantry.app;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.util.objects.Recipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecipeController {

    /**
     * Retrieves the entire list of recipes
     * @return all the recipes
     */
    public static List<Recipe> getAllRecipes() {
        return getRecipes("CALL spGetAllRecipes()");
    }

    /**
     * Retrieves a range of recipes with a starting and end point given
     * @param start The first recipe in the range
     * @param end The last recipe in the range
     * @return The list of recipes from start to end
     */
    public static List<Recipe> getRangeOfRecipes(int start, int end) {
        List<Recipe> recipes = new ArrayList<>();
        for (int i = start; i < end + 1; i++) {
            recipes.add(getRecipeByID(i));
        }
        return recipes;
    }

    /**
     * Retrieves recipes with the specified ingredient
     * @param ingredient the specified ingredient
     * @return all recipes with the ingredients
     */
    public static List<Recipe> getRecipesByIngredient(String ingredient) {
        String query = String.format("CALL spGetRecipesByIngredient('%s');", ingredient);
        return getRecipes(query);
    }

    /**
     * Retrieves the recipe with the specified ID
     * @param recipeId The specific ID
     * @return The recipe with that ID
     */
    public static Recipe getRecipeByID(int recipeId) {
        String query = String.format("CALL spGetRecipeByID(%d);", recipeId);
        List<Recipe> recipes = getRecipes(query);
        if (recipes.size() == 0)
            return new Recipe();
        return recipes.get(0);
    }

    /**
     * Retrieves the recipe id based on the entered name
     * @param name the entered name
     * @return the id for the named recipe
     */
    public static int getRecipeIDByName(String name) {
        String query = String.format("CALL spGetRecipeIDByName('%s');", name);
        List<Recipe> recipes = getRecipes(query);
        if (recipes.size() == 0)
            return -1;
        return recipes.get(0).getId();
    }

    /**
     * Retrieves the recipes from a specified region
     * @param region The specified region
     * @return The recipes from that region
     */
    public static List<Recipe> getRecipesByRegion(String region) {
        String query = String.format("CALL spGetRecipeByRegion('%s');", region);
        return getRecipes(query);
    }

    /**
     * Retrieves the recipes from a specified category
     * @param category The specified category
     * @return The recipes from that category
     */
    public static List<Recipe> getRecipesByCategory(String category) {
        String query = String.format("CALL spGetRecipeByCategory('%s');", category);
        return getRecipes(query);
    }

    /**
     * Retrieves recipes based on an entered title
     * @param title The entered title
     * @return the recipes that match with the title
     */
    public static List<Recipe> getRecipesByTitle(String title) {
        String query = String.format("CALL spGetRecipeByTitle('%s');", title);
        return getRecipes(query);
    }

    /**
     * Gets recipes based on a search query
     * @param query The search query
     * @return Search results based on query
     */
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
