package com.fireside.pantry.db.scripts;

import com.fireside.pantry.app.model.NewRecipe;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LoadRecipes {

    private static final Logger logger = LoggerFactory.getLogger(LoadRecipes.class);

    public static List<NewRecipe> fromJson() throws IOException, ParseException {
        LoadRecipes.logger.info("Loading recipes...");
        JSONArray json = (JSONArray) new JSONParser().parse(new FileReader("recipes.json"));
        List<NewRecipe> recipes = new ArrayList<>();
        for (Object o : json) recipes.add(parseRecipe((JSONObject) o));
        LoadRecipes.logger.info("Done loading recipes...");
        return recipes;
    }

    private static NewRecipe parseRecipe(JSONObject object) {
        Map<String, Double> nutrition = parseStringDoubleMap((JSONObject) object.get("nutr_values_per100g"));
        Gson gson = new Gson();
        return new NewRecipe()
                .setId((String) object.get("id"))
                .setTitle((String) object.get("title"))
                .setUrl((String) object.get("url"))
                .setSodium(nutrition.get("salt"))
                .setSugar(nutrition.get("sugars"))
                .setProtein(nutrition.get("protein"))
                .setFat(nutrition.get("fat"))
                .setSaturates(nutrition.get("saturates"))
                .setEnergy(nutrition.get("energy"))
                .setInstructions(gson.toJson(parseStringArray((JSONArray) object.get("instructions"))))
                .setIngredients(parseStringArray((JSONArray) object.get("ingredients")))
                .setIngredientUnit(gson.toJson(parseStringArray((JSONArray) object.get("unit"))))
                .setIngredientQuantity(gson.toJson(parseStringArray((JSONArray) object.get("quantity"))));
    }

    private static String[] parseStringArray(JSONArray jsonArray) {
        String[] array = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject iObj = (JSONObject) jsonArray.get(i);
            array[i] = (String) iObj.get("text");
        }
        return array;
    }

    private static double[] parseDoubleArray(JSONArray jsonArray) {
        double[] array = new double[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++)
            array[i] = (double) jsonArray.get(i);
        return array;
    }

    private static Map<String, Double> parseStringDoubleMap(JSONObject object) {
        Map<String, Double> map = new HashMap<>();
        for (Object key : object.keySet()) {
            map.put((String) key, (Double) object.get(key));
        }
        return map;
    }

    private static List<Map<String, Double>> parseArrayOfMaps(JSONArray array) {
        List<Map<String, Double>> maps = new LinkedList<>();
        for (Object o : array) maps.add(parseStringDoubleMap((JSONObject) o));
        return maps;
    }
}
