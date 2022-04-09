package com.fireside.pantry.service;

import com.fireside.pantry.app.control.RecipeManager;
import com.fireside.pantry.util.objects.Recipe;
import org.junit.jupiter.api.Test;

public class TestNutritionService {

    @Test
    public void test_NutritionService() {
        Recipe recipe = RecipeManager.getRecipeByID(50);
        NutritionService.match(recipe);
    }
}
