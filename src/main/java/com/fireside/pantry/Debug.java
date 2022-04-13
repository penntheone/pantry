package com.fireside.pantry;

import com.fireside.pantry.app.RecipeController;
import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.util.objects.Recipe;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Debug {

    public static void main(String[] args) {

        List<Recipe> recipes = RecipeController.getAllRecipes();
        DatabaseConnector conn = new DatabaseConnector();
        for (Recipe recipe : recipes) {
            try {
                InputStream stream = new URL(recipe.getThumbUrl()).openStream();
                conn.addImage(recipe.getId(), stream);
                System.out.println("Added image for recipe -> " + recipe.getId());

            } catch (Exception exception) {
                System.out.println("Unable to get image for recipe -> " + recipe.getId());
                exception.printStackTrace();
            }
        }
    }
}
