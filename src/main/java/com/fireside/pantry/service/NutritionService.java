package com.fireside.pantry.service;

import com.fireside.pantry.app.api.FoodDataCentral;
import com.fireside.pantry.util.objects.Ingredient;
import com.fireside.pantry.util.objects.Recipe;
import com.google.gson.*;

import java.util.List;

public class NutritionService {

    public static void match(Recipe recipe) {

        System.out.println("|---- Recipe Ingredients ----------------------------------------");

        List<Ingredient> recipeIngredients = recipe.getIngredients();
        for (Ingredient ingredient : recipeIngredients) {
            System.out.println(ingredient.getName());
        }

        System.out.println();
        System.out.println("|---- FDC Ingredients ----------------------------------------");

        JsonArray matching = NutritionService.findMatchingFoods(recipe);

        JsonObject food = matching.get(0).getAsJsonObject();
        String[] ingredients = NutritionService.parseIngredientString(food.get("ingredients").getAsString());
        for (String ingredient : ingredients) {
            System.out.println(ingredient);
        }

        // System.out.println(Utils.prettify(food.toString()));
    }

    private static JsonArray findMatchingFoods(Recipe recipe) {
        FoodDataCentral fdc = FoodDataCentral.getInstance();
        JsonObject object = JsonParser.parseString(fdc.getFoodSearch(recipe.getTitle())).getAsJsonObject();
        JsonArray foods = object.getAsJsonArray("foods");
        JsonArray filtered = new JsonArray();
        for (int i = 0; i < foods.size(); i++) {
            JsonObject foodObj = foods.get(i).getAsJsonObject();
            boolean filter = false;
            if (!foodObj.get("dataType").toString().equals("\"Branded\"")) filter = true;
            if (foodObj.get("ingredients") == null) filter = true;
            if (!filter) filtered.add(foodObj);
        }
        return filtered;
    }

    /**
     * Does not work yet!
     *
     * @param recipe
     * @param array
     * @return
     */
    private static JsonObject findBestMatch(Recipe recipe, JsonArray array) {
        List<Ingredient> recipeIngredients = recipe.getIngredients();
        JsonObject bestMatch;
        int bestMatchStat = 0;
        for (int i = 0; i < array.size(); i++) {
            JsonObject food = array.get(i).getAsJsonObject();
            String[] ingredients = NutritionService.parseIngredientString(food.get("ingredients").getAsString());
            int foodStat = 0;

        }
        return array.get(0).getAsJsonObject();
    }

    private static String[] parseIngredientString(String ingredients) {
        StringBuilder builder = new StringBuilder();
        boolean inParen = false;
        for (int i = 0; i < ingredients.length(); i++) {
            char c = ingredients.charAt(i);

            if (c == '(') {
                builder.deleteCharAt(builder.length() - 1);
                inParen = true;
                continue;
            }

            if (c == ')') {
                inParen = false;
                continue;
            }

            if (!inParen) builder.append(c);
        }
        return builder.toString().split(", ");
    }
}
