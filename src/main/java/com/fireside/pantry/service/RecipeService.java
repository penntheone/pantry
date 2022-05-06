package com.fireside.pantry.service;

import com.fireside.pantry.app.control.ImageController;
import com.fireside.pantry.app.control.IngredientController;
import com.fireside.pantry.app.control.RecipeController;
import com.fireside.pantry.app.model.Recipe;

import java.util.List;

/**
 * Class for finding and showing information on recipes
 */
public class RecipeService {

    /**
     * Gets the recipes that will show up on the home screen
     * @return the list of recipes that will show up
     */
    public static List<Recipe> getHomeRecipes() {
        List<Recipe> recipes = RecipeController.getRangeOfRecipes(1, 10);
        RecipeService.loadImages(recipes);
        return recipes;
    }

    /**
     * loads the ingredients of the selected recipe
     * @param recipe the selected recipe
     */
    public static void loadIngredients(Recipe recipe) {
        recipe.setIngredients(IngredientController.getRecipeIngredients(recipe.getId()));
    }

    /**
     * loads the ingredients of the selected list of recipes
     * @param recipes the selected list of recipe
     */
    public static void loadIngredients(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            RecipeService.loadIngredients(recipe);
        }
    }

    /**
     * loads the image of the selected recipe
     * @param recipe the selected recipe
     */
    public static void loadImage(Recipe recipe) {
        recipe.setImage(ImageController.getInstance().getRecipeImage(recipe));
    }

    /**
     * loads the images of the selected list of recipes
     * @param recipes the selected list of recipe
     */
    public static void loadImages(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            RecipeService.loadImage(recipe);
        }
    }

    /**
     * loads the both the image and the ingredients of the selected recipe
     * @param recipe the selected recipe
     */
    public static void loadAllAssets(Recipe recipe) {
        RecipeService.loadIngredients(recipe);
        RecipeService.loadImage(recipe);
    }

    /**
     * loads the both the images and the ingredients of the selected list of recipes
     * @param recipes the selected list of recipes
     */
    public static void loadAllAssets(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            RecipeService.loadAllAssets(recipe);
        }
    }
}
