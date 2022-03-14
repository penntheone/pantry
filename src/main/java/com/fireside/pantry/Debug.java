package com.fireside.pantry;

import com.fireside.pantry.app.RecipeManager;
import com.fireside.pantry.util.Utils;
import com.fireside.pantry.util.objects.Recipe;

import java.util.List;

public class Debug {

    public static void main(String[] args) {

        // Retrieving all recipes
        // Do not use this method!! It will be replaced
        // For each recipe retrieved from the database, another query is made to retrieve its ingredients
        // This method makes 278 queries!! lol
        List<Recipe> recipes = RecipeManager.getAllRecipes();

        // Retrieving recipe id by name
        int recipe_id = RecipeManager.getRecipeIDByName("Brown Stew Chicken");

        // Fetching single recipe by id
        Recipe recipe = RecipeManager.getRecipeByID(recipe_id);

        System.out.println(Utils.prettify(recipe.toString()));
    }
}
