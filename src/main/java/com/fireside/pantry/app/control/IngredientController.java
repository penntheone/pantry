package com.fireside.pantry.app.control;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.app.model.Ingredient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Takes care of ingredient information
 */
public class IngredientController {

    /**
     * Returns the list of all the ingredients
     * @return every ingredient in the app
     */
    public static List<Ingredient> getAllIngredients() {
        return getIngredients("SELECT * FROM pantry.Ingredients");
    }

    /**
     * Returns all the ingredients for a specific recipe
     * @param recipeId id of the recipe in question
     * @return all the ingredients of the recipe
     */
    public static List<Ingredient> getRecipeIngredients(int recipeId) {
        String query = String.format("CALL spGetIngredientsByRecID(%d);", recipeId);
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (Row row : rows) {
                ingredients.add(new Ingredient(
                        Integer.parseInt(row.get("id")),
                        row.get("name"),
                        row.get("measure")
                ));
            }
            return ingredients;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Gets the ingredient id based on the ingredient name
     * @param name the specified ingredient name
     * @return the id of said ingredient
     */
    public static int getIngredientIDByName(String name) {
        String query = String.format(
                "SELECT * FROM pantry.Ingredients WHERE name='%s'",
                name
        );

        List<Ingredient> ingredients = getIngredients(query);
        if (ingredients.size() == 0)
            return -1;
        return ingredients.get(0).getId();
    }

    public static List<Ingredient> getIngredientByName(String name) {
        String query = String.format("SELECT i.id, i.name, ri.measure FROM RecipeIngredients ri\n" +
                "             JOIN pantry.Ingredients i ON ri.ingredient_id = i.id WHERE i.name " +
                "LIKE '%%%s%%'", name);
        List<Ingredient> ingredients = getIngredients(query);

        return ingredients; // Returns list of ingredient objects

        // return ingredients.get(0); // Returns one ingredient object
    }

    public static List<Ingredient> getUsersAllergies(int UserID) {
        String query = String.format("SELECT i.id, i.name, ri.measure\n" +
                "FROM RecipeIngredients ri\n" +
                "JOIN pantry.Ingredients i ON ri.ingredient_id = i.id\n" +
                "JOIN User_Preferences UP on ri.ingredient_id = UP.ingredient_id\n" +
                "WHERE UP.user_id = '%s' && UP.isAllergic = 1;", UserID);
        List<Ingredient> ingredients = getIngredients(query);

        return ingredients;
    }

    /**
     * Gets ingredients based on a search query
     * @param query The specific search query
     * @return The list of ingredients as a result
     */
    private static List<Ingredient> getIngredients(String query) {
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            LinkedList<Ingredient> ingredients = new LinkedList<>();
            for (Row row : rows) {
                ingredients.add(new Ingredient(row));
            }
            return ingredients;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}
