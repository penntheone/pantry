package com.fireside.pantry.apis;

import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FoodDataCentral {

    private static final String apiKey = "uCiQLM0I2MFJvuypNN6UDOMVwAsu56gs8h7hvY5s";

    private static final String url = "https://api.nal.usda.gov/fdc";
    private static final String apiDocs = "/v1/json-spec";

    private static final String foodDetails = "/v1/food/%s";
    private static final String foodList = "/v1/foods/list";
    private static final String foodSearch = "/v1/foods/search";

    private static URL buildQuery(String apiPath) throws MalformedURLException {
        return new URL(String.format("%s%s?api_key=%s",
                FoodDataCentral.url,
                apiPath,
                FoodDataCentral.apiKey
        ));
    }

    private static URL buildQueryWithParams(String apiPath, String query) throws MalformedURLException {
        return new URL(String.format("%s%s?api_key=%s&query=%s",
                FoodDataCentral.url,
                apiPath,
                FoodDataCentral.apiKey,
                query
        ));
    }

    private static String executeQuery(URL queryUrl) throws IOException {
        System.out.printf("Query -> %s%n", queryUrl);
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

    public static String prettifyJson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = JsonParser.parseString(json);
        JsonArray obj;
        if (json.charAt(0) == '[')
            return gson.toJson(element.getAsJsonArray());
        else
            return gson.toJson(element.getAsJsonObject());
    }

    public static String getApiDocs() {
        try {
            URL query = buildQuery(apiDocs);
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String getFoodList() {
        try {
            URL query = buildQuery(foodList);
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String getFoodSearch(String search) {
        try {
            URL query = buildQueryWithParams(foodSearch, search);
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String getFoodDetails(String foodID) {
        try {
            URL query = buildQuery(String.format(foodDetails, foodID));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
