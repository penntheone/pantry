package com.fireside.pantry.app.api;

import com.fireside.pantry.util.Utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FoodDataCentral {

    private static FoodDataCentral instance;
    private final APIConfig config;

    /**
     * A constructor which loads the API config to the name
     */
    private FoodDataCentral() {
        this.config = Utils.loadAPIConfig("FoodDataCentral");
    }

    /**
     * Will attempt to get the API docs
     * @return the docs if the method is successful
     */
    public String getApiDocs() {
        try {
            URL query = buildQuery(config.getPath("docs"));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Attempts to get the food list
     * @return food list if the method is successful
     */
    public String getFoodList() {
        try {
            URL query = buildQuery(config.getPath("foodList"));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Attempts to get the food search
     * @return food search if the method is successful
     */
    public String getFoodSearch(String search) {
        try {
            URL query = buildQueryWithParams(config.getPath("foodSearch"), search);
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Attempts to get the food details based of the id
     * @param foodID the specified food id
     * @return food details of food with the id if successful
     */
    public String getFoodDetails(String foodID) {
        try {
            URL query = buildQuery(String.format(config.getPath("foodDetails"), foodID));
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
    protected URL buildQuery(String apiPath) throws MalformedURLException {
        return new URL(String.format("%s%s?api_key=%s",
                config.getUrl(),
                apiPath,
                config.getKey()
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
        return new URL(String.format("%s&query=%s",
                buildQuery(apiPath),
                query
        ));
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
    public static FoodDataCentral getInstance() {
        if (FoodDataCentral.instance == null)
            FoodDataCentral.instance = new FoodDataCentral();
        return instance;
    }
}
