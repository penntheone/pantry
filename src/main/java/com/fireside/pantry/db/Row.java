package com.fireside.pantry.db;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class which handles rows of information from the database
 */
public class Row {

    private final Map<String, String> data;

    /**
     * Constructor method
     */
    public Row() {
        this.data = new LinkedHashMap<>();
    }

    /**
     *
     * @param column
     * @param value
     * @throws IllegalArgumentException
     */
    public void add(String column, String value) throws IllegalArgumentException {
        if (data.containsKey(column))
            throw new IllegalArgumentException();
        data.put(column, value);
    }

    /**
     * Retrieves data from specified column if it exists
     * @param column specified column
     * @return data from column if it exists
     */
    public String get(String column) {
        if (!data.containsKey(column))
            throw new IllegalArgumentException();
        return data.get(column);
    }

    /**
     * Retrieves columns from row
     * @return columns from row
     */
    public String[] getColumns() {
        return this.data.keySet().toArray(new String[0]);
    }

    /**
     * Converts row to string
     * @return row as string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.append(String.format("%s : %s\t", entry.getKey(), entry.getValue()));
        }
        return builder.toString();
    }
}
