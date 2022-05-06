package com.fireside.pantry.db.scripts;

import com.fireside.pantry.app.model.NewIngredient;
import com.fireside.pantry.app.model.NewRecipe;
import com.fireside.pantry.db.DatabaseConnector;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadRecipes.class);

    public static void main(String[] args) {
        LoadDatabase.fromJson();
    }

    public static void fromJson() {
        try {
            LoadDatabase.logger.info("Loading database from json...");
            List<NewRecipe> recipes = LoadRecipes.fromJson();
            List<NewIngredient> ingredients = LoadIngredients.fromRecipes(recipes);

            // LoadDatabase.storeIngredients(ingredients);
            LoadDatabase.storeRecipes(recipes, ingredients);

            LoadDatabase.logger.info("Done loading database...");
        } catch (Exception exception) {
            LoadDatabase.logger.error("Unable to load database from json!", exception);
        }
    }

    private static void storeRecipes(List<NewRecipe> recipes, List<NewIngredient> ingredients) throws SQLException {
        LoadDatabase.logger.info("Storing recipes in database...");
        String[] queries = new String[recipes.size()];

        Map<String, Integer> ingredientIdMap = LoadIngredients.getIngredientIdMap(ingredients);
        Gson gson = new Gson();
        for (int i = 0; i < recipes.size(); i++) {
            NewRecipe recipe = recipes.get(i);

            // -- Build json string with ingredient ids
            String[] recipeIngredients = recipe.getIngredients();
            int[] recipeIngredientIds = new int[recipeIngredients.length];
            for (int j = 0; j < recipeIngredients.length; j++) {
                recipeIngredientIds[j] = ingredientIdMap.get(ScriptUtils.escapeString(recipeIngredients[j]));
            }
            String jsonIngredients = gson.toJson(recipeIngredientIds);

            queries[i] = String.format(
                    "INSERT INTO Recipes VALUES ('%s', '%s', '%s', %.05f, %.05f, %.05f, %.05f, %.05f, %.05f, '%s', '%s', '%s', '%s');",
                    recipe.getId(),
                    recipe.getTitle(),
                    recipe.getUrl(),
                    recipe.getSodium(),
                    recipe.getSugar(),
                    recipe.getProtein(),
                    recipe.getFat(),
                    recipe.getSaturates(),
                    recipe.getEnergy(),
                    recipe.getInstructions(),
                    jsonIngredients,
                    recipe.getIngredientUnit(),
                    recipe.getIngredientQuantity()
            );
        }
        new DatabaseConnector().batchInsert(queries);
        LoadDatabase.logger.info("Done storing recipes in database...");
    }

    private static void storeIngredients(List<NewIngredient> ingredients) throws SQLException {
        LoadDatabase.logger.info("Storing ingredients in database...");
        String[] queries = new String[ingredients.size()];
        for (int i = 0; i < ingredients.size(); i++) {
            NewIngredient ingredient = ingredients.get(i);
            queries[i] = String.format(
                    "INSERT INTO Ingredients VALUES (%d, '%s', %.05f, %.05f, %.05f, %.05f, %.05f, %.05f);",
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getSodium(),
                    ingredient.getSugar(),
                    ingredient.getProtein(),
                    ingredient.getFat(),
                    ingredient.getSaturates(),
                    ingredient.getEnergy()
            );
        }
        new DatabaseConnector().batchInsert(queries);
        LoadDatabase.logger.info("Done storing ingredients in database...");
    }
}
