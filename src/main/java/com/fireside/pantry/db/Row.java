package com.fireside.pantry.db;

import java.util.LinkedHashMap;
import java.util.Map;

public class Row {

    private final Map<String, String> data;

    public Row() {
        this.data = new LinkedHashMap<>();
    }

    public void add(String column, String value) throws IllegalArgumentException {
        if (data.containsKey(column))
            throw new IllegalArgumentException();
        data.put(column, value);
    }

    public String get(String column) {
        if (!data.containsKey(column))
            throw new IllegalArgumentException();
        return data.get(column);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.append(String.format("%s : %s\t", entry.getKey(), entry.getValue()));
        }
        return builder.toString();
    }
}
