package com.fireside.pantry.app.control;

import com.fireside.pantry.app.api.FoodDataCentral;
import com.fireside.pantry.app.model.Recipe;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RecipeManagerTest {

    @Test
    public void test_GetRecipeByIngredients() {
        List<Recipe> recipes = RecipeController.getRecipesByIngredient("chicken");
        System.out.println(recipes.size());
    }

    @Test
    public void test_GetAllRecipes() {
        FoodDataCentral.getInstance();
    }
}
