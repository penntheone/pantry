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

    private FoodDataCentral() {
        this.config = Utils.loadAPIConfig("FoodDataCentral");
    }

    public String getApiDocs() {
        try {
            URL query = buildQuery(config.getPath("docs"));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getFoodList() {
        try {
            URL query = buildQuery(config.getPath("foodList"));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getFoodSearch(String search) {
        try {
            URL query = buildQueryWithParams(config.getPath("foodSearch"), search);
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getFoodDetails(String foodID) {
        try {
            URL query = buildQuery(String.format(config.getPath("foodDetails"), foodID));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    protected URL buildQuery(String apiPath) throws MalformedURLException {
        return new URL(String.format("%s%s?api_key=%s",
                config.getUrl(),
                apiPath,
                config.getKey()
        ));
    }

    private URL buildQueryWithParams(String apiPath, String query) throws MalformedURLException {
        return new URL(String.format("%s&query=%s",
                buildQuery(apiPath),
                query
        ));
    }

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

    public static FoodDataCentral getInstance() {
        if (FoodDataCentral.instance == null)
            FoodDataCentral.instance = new FoodDataCentral();
        return instance;
    }
}
