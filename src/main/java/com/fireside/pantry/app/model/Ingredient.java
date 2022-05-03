package com.fireside.pantry.app.model;

import com.fireside.pantry.db.Row;
import com.google.gson.Gson;

public class Ingredient {

    private final int id;
    private final String name;
    private String measure;

    public Ingredient(int id, String name, String measure) {
        this.id = id;
        this.name = name;
        this.measure = measure;
    }

    public Ingredient(Row row) throws IllegalArgumentException {
        try {
            this.id = Integer.parseInt(row.get("id"));
            this.name = row.get("name");
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
