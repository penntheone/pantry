package com.fireside.pantry.service;

import com.fireside.pantry.app.api.FoodDataCentral;
import com.fireside.pantry.util.objects.Ingredient;
import com.fireside.pantry.util.objects.Recipe;
import com.google.gson.*;

import java.util.List;

public class NutritionService {

    public static JsonObject match(Recipe recipe) {
        return NutritionService.findBestMatch(recipe, NutritionService.findMatchingFoods(recipe));
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
        System.out.println("Matching : " + filtered.size());
        return filtered;
    }

    private static JsonObject findBestMatch(Recipe recipe, JsonArray array) {
        List<Ingredient> recipeIngredients = recipe.getIngredients();
        JsonObject bestMatch = array.get(0).getAsJsonObject();
        int bestMatchStat = NutritionService.getMatchStat(recipeIngredients, bestMatch);
        for (int i = 1; i < array.size(); i++) {
            JsonObject food = array.get(i).getAsJsonObject();
            int foodStat = NutritionService.getMatchStat(recipeIngredients, food);
            if (foodStat > bestMatchStat) {
                bestMatchStat = foodStat;
                bestMatch = food;
            }
        }
        System.out.println(bestMatchStat);
        return bestMatch;
    }

    private static int getMatchStat(List<Ingredient> recipeIngredients, JsonObject food) {
        String[] ingredients = NutritionService.parseIngredientString(food.get("ingredients").getAsString());
        int foodStat = 0;
        for (Ingredient ingredient : recipeIngredients)
            for (String ingredientName : ingredients)
                if (ingredient.getName().equalsIgnoreCase(ingredientName))
                    foodStat++;
        return foodStat;
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
