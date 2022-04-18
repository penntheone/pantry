package com.fireside.pantry.service;

import com.fireside.pantry.app.ImageController;
import com.fireside.pantry.app.IngredientController;
import com.fireside.pantry.app.RecipeController;
import com.fireside.pantry.util.objects.Recipe;

import java.util.List;

public class RecipeService {

    public static List<Recipe> getHomeRecipes() {
        List<Recipe> recipes = RecipeController.getRangeOfRecipes(1, 10);
        RecipeService.loadImages(recipes);
        return recipes;
    }

    public static void loadIngredients(Recipe recipe) {
        recipe.setIngredients(IngredientController.getRecipeIngredients(recipe.getId()));
    }

    public static void loadIngredients(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            RecipeService.loadIngredients(recipe);
        }
    }

    public static void loadImage(Recipe recipe) {
        recipe.setImage(ImageController.getInstance().getRecipeImage(recipe));
    }

    public static void loadImages(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            RecipeService.loadImage(recipe);
        }
    }

    public static void loadAllAssets(Recipe recipe) {
        RecipeService.loadIngredients(recipe);
        RecipeService.loadImage(recipe);
    }

    public static void loadAllAssets(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            RecipeService.loadAllAssets(recipe);
        }
    }
}
