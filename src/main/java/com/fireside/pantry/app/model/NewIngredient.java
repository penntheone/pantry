package com.fireside.pantry.app.model;

import com.google.gson.Gson;

/**
 * Class for all new ingredients
 */
public class NewIngredient {

    private int id;
    private String name;
    private double sodium;
    private double sugar;
    private double protein;
    private double fat;
    private double saturates;
    private double energy;

    public NewIngredient() { }

    // -- Methods

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // -- Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSodium() {
        return sodium;
    }

    public double getSugar() {
        return sugar;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getSaturates() {
        return saturates;
    }

    public double getEnergy() {
        return energy;
    }

    // -- Setters

    public NewIngredient setId(int id) {
        this.id = id;
        return this;
    }

    public NewIngredient setName(String name) {
        this.name = name;
        return this;
    }

    public NewIngredient setSodium(double sodium) {
        this.sodium = sodium;
        return this;
    }

    public NewIngredient setSugar(double sugar) {
        this.sugar = sugar;
        return this;
    }

    public NewIngredient setProtein(double protein) {
        this.protein = protein;
        return this;
    }

    public NewIngredient setFat(double fat) {
        this.fat = fat;
        return this;
    }

    public NewIngredient setSaturates(double saturates) {
        this.saturates = saturates;
        return this;
    }

    public NewIngredient setEnergy(double energy) {
        this.energy = energy;
        return this;
    }
}
