package com.fireside.pantry.app.api.mealdb;

import com.fireside.pantry.app.api.APIConfig;
import com.fireside.pantry.util.Utils;
import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TheMealDB {

    private static TheMealDB instance;
    private final APIConfig config;

    private TheMealDB() {
        this.config = Utils.loadAPIConfig("TheMealDB");
    }

    /**
     * Gets all recipes in the database
     * @return All the recipes
     */
    public List<MealDBRecipe> getAllRecipes() {
        LinkedList<MealDBRecipe> allRecipes = new LinkedList<>();

        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'q' || c == 'u' || c == 'x' || c == 'z')
                continue;
            String result = getRecipesByFirstLetter(c);
            MealDBRecipe[] recipes = new Gson().fromJson(
                    result.substring(9, result.length() - 1),
                    MealDBRecipe[].class
            );

            allRecipes.addAll(Arrays.asList(recipes));
        }
        return allRecipes;
    }

    /**
     * Gets all recipes based on the first letter as the parameter
     * @param letter The letter that is inputted by the use
     * @return The recipes starting with said letter
     */
    public String getRecipesByFirstLetter(char letter) {
        try {
            URL query = buildQueryWithParams(config.getPath("listByFirstLetter"), String.valueOf(letter));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Gets recipes based on the name in the param
     * @param name The name or phrase inputted by the user
     * @return The search results based on the param
     */
    public String getRecipeByName(String name) {
        try {
            URL query = buildQueryWithParams(config.getPath("recipeByName"), name);
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a search query based on the path
     * @param apiPath The Path selected by the user
     * @return A query url based on those results
     * @throws MalformedURLException
     */
    private URL buildQuery(String apiPath) throws MalformedURLException {
        return new URL(String.format("%s/%s%s",
                config.getUrl(),
                config.getKey(),
                apiPath
        ));
    }

    /**
     * Creates a search query based on the path and a specified query parameter
     * @param apiPath The Path selected by the user
     * @param query The specific parameter chosen by the user
     * @return A query based on the user specified parameters
     * @throws MalformedURLException
     */
    private URL buildQueryWithParams(String apiPath, String query) throws MalformedURLException {
        return new URL(String.format(buildQuery(apiPath).toString(), query));
    }

    /**
     * executes the search based on the built query
     * @param queryUrl The URL built based on what the user is searching for
     * @return the search results
     * @throws IOException
     */
    private String executeQuery(URL queryUrl) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) queryUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        StringBuilder response = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }

    /**
     * Gets the object itself
     * @return The object
     */
    public static TheMealDB getInstance() {
        if (TheMealDB.instance == null)
            TheMealDB.instance = new TheMealDB();
        return TheMealDB.instance;
    }
}
