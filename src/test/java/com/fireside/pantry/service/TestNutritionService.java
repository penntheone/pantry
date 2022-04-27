package com.fireside.pantry.service;

import com.fireside.pantry.app.control.IngredientController;
import com.fireside.pantry.app.control.RecipeController;
import com.fireside.pantry.app.model.Recipe;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

public class TestNutritionService {

    @Test
    public void test_NutritionService() {
        Recipe recipe = RecipeController.getRecipeByID(95);
        System.out.println(recipe.getTitle());
        recipe.setIngredients(IngredientController.getRecipeIngredients(recipe.getId()));
        JsonObject result = NutritionService.match(recipe);

        System.out.println();
        for (String key : result.keySet()) {
            System.out.println(key + " : " + result.get(key));
        }
    }
}
