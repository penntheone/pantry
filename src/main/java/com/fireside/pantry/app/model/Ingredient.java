package com.fireside.pantry.app.model;

import com.fireside.pantry.db.Row;
import com.google.gson.Gson;

/**
 * Class for all ingredients
 */
public class Ingredient {

    private final int id;
    private final String name;
    private String measure;

    /**
     * Constructor based on param values
     * @param id ingredient id
     * @param name ingredient name
     * @param measure ingredient measure
     */
    public Ingredient(int id, String name, String measure) {
        this.id = id;
        this.name = name;
        this.measure = measure;
    }

    /**
     * Constructor based on row
     * @param row row ingredient is based on
     * @throws IllegalArgumentException
     */
    public Ingredient(Row row) throws IllegalArgumentException {
        try {
            this.id = Integer.parseInt(row.get("id"));
            this.name = row.get("name");
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    /**
     * Retrieves ingredient id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves ingredient name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves ingredient measure
     * @return the measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Converts ingredient to string
     * @return ingredient as string
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
