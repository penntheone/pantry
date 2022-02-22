package com.fireside.pantry.utils;

import com.fireside.pantry.apis.APIConfig;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;

public class JsonUtils {

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
                ResourceUtils.loadResource(String.format("apis/%s.json", apiName))));
        return new Gson().fromJson(reader, APIConfig.class);
    }
}
