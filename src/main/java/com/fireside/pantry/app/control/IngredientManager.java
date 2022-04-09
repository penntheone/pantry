package com.fireside.pantry.app.control;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.util.objects.Ingredient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IngredientManager {

    public static List<Ingredient> getAllIngredients() {
        return getIngredients("SELECT * FROM pantry.ingredients");
    }

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

    public static int getIngredientIDByName(String name) {
        String query = String.format(
                "SELECT * FROM pantry.ingredients WHERE name='%s'",
                name
        );

        List<Ingredient> ingredients = getIngredients(query);
        if (ingredients.size() == 0)
            return -1;
        return ingredients.get(0).getId();
    }

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
