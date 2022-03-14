package com.fireside.pantry.app;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.util.objects.Ingredient;
import com.fireside.pantry.util.objects.Recipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecipeManager {

    public static List<Recipe> getAllRecipes() {

        return getRecipes("CALL spGetAllRecipes();");
    }

    public static List<Recipe> getRangeOfRecipes(int start, int end) {
        List<Recipe> recipes = new ArrayList<>();
        for (int i = start; i < end + 1; i++) {
            recipes.add(getRecipeByID(i));
        }
        return recipes;
    }

    public static Recipe getRecipeByID(int recipeId) {
        String query = String.format("SELECT * FROM pantry.recipes WHERE id='%d'", recipeId);
        List<Recipe> recipes = getRecipes(query);
        if (recipes.size() == 0)
            return new Recipe();
        return recipes.get(0);
    }

    public static int getRecipeIDByName(String name) {
        String query = String.format(
                "SELECT * FROM pantry.recipes WHERE title=\"%s\"",
                name
        );

        List<Recipe> recipes = getRecipes(query);
        if (recipes.size() == 0)
            return -1;
        return recipes.get(0).getId();
    }

    private static List<Recipe> getRecipes(String query) {
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            LinkedList<Recipe> recipes = new LinkedList<>();
            for (Row row : rows) {
                Recipe recipe = new Recipe(row);
                recipe.setIngredients(IngredientManager.getRecipeIngredients(recipe.getId()));
                recipes.add(recipe);
            }
            return recipes;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}
