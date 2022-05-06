package com.fireside.pantry.db.scripts;

import com.fireside.pantry.app.model.NewIngredient;
import com.fireside.pantry.app.model.NewRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadIngredients {

    private static final Logger logger = LoggerFactory.getLogger(LoadIngredients.class);

    public static List<NewIngredient> fromRecipes(List<NewRecipe> recipes) {
        LoadIngredients.logger.info("Loading ingredients...");
        List<String> ingredients = new ArrayList<>();
        List<NewIngredient> newIngredients = new ArrayList<>();
        for (NewRecipe recipe : recipes) {
            // -- Fetch ingredients and nutrition from recipe
            String[] recipeIngredients = recipe.getIngredients();
            for (int i = 0; i < recipeIngredients.length; i++) {
                // -- If ingredient has not been found yet, create new object
                if (!ingredients.contains(recipeIngredients[i])) {
                    ingredients.add(recipeIngredients[i]);
                    newIngredients.add(buildIngredient(recipeIngredients[i]).setId(ingredients.size()));
                }
            }
        }
        LoadIngredients.logger.info("Done loading ingredients...");
        return newIngredients;
    }

    public static Map<String, Integer> getIngredientIdMap(List<NewIngredient> ingredients) {
        Map<String, Integer> map = new HashMap<>();
        for (NewIngredient ingredient : ingredients) {
            if (!map.containsKey(ingredient.getName())) map.put(ingredient.getName(), ingredient.getId());
        }
        return map;
    }

    private static NewIngredient buildIngredient(String name) {
        return new NewIngredient()
                .setName(ScriptUtils.escapeString(name))
                .setSodium(0.0)
                .setSugar(0.0)
                .setProtein(0.0)
                .setFat(0.0)
                .setSaturates(0.0)
                .setEnergy(0.0);
    }
}
