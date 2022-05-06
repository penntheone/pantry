package com.fireside.pantry.app;

import com.fireside.pantry.db.DBConfig;
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
        return gson.toJson(element.getAsJsonObject());
    }

    public static DBConfig loadDBConfig(String configName) {
        JsonReader reader = getJsonReader(String.format("db/%s.json", configName));
        return new Gson().fromJson(reader, DBConfig.class);
    }

    public static JsonReader getJsonReader(String resourceName) {
        return new JsonReader(new InputStreamReader(loadResource(resourceName)));
    }

    public static boolean isPrintable(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!isAsciiPrintable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAsciiPrintable(char ch) {
        return ch >= 32 && ch < 127;
    }
}
