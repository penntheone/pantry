package com.fireside.pantry.util;

import com.fireside.pantry.app.api.APIConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    public static InputStream loadResource(String path) {
        return Utils.class.getClassLoader().getResourceAsStream(path);
    }

    public static String prettify(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = JsonParser.parseString(json);
        if (json.charAt(0) == '[')
            return gson.toJson(element.getAsJsonArray());
        else
            return gson.toJson(element.getAsJsonObject());
    }

    public static APIConfig loadAPIConfig(String apiName) {
        JsonReader reader = new JsonReader(new InputStreamReader(
                loadResource(String.format("apis/%s.json", apiName))));
        return new Gson().fromJson(reader, APIConfig.class);
    }
}
