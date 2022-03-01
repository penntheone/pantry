package com.fireside.pantry.api;

import com.fireside.pantry.util.Utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TheMealDB {

    private static TheMealDB instance;
    private final APIConfig config;

    private TheMealDB() {
        this.config = Utils.loadAPIConfig("TheMealDB");
    }

    public String getRecipesByFirstLetter(char letter) {
        try {
            URL query = buildQueryWithParams(config.getPath("listByFirstLetter"), String.valueOf(letter));
            return executeQuery(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private URL buildQuery(String apiPath) throws MalformedURLException {
        return new URL(String.format("%s/%s%s",
                config.getUrl(),
                config.getKey(),
                apiPath
        ));
    }

    private URL buildQueryWithParams(String apiPath, String query) throws MalformedURLException {
        return new URL(String.format(buildQuery(apiPath).toString(), query));
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

    public static TheMealDB getInstance() {
        if (TheMealDB.instance == null)
            TheMealDB.instance = new TheMealDB();
        return TheMealDB.instance;
    }
}
