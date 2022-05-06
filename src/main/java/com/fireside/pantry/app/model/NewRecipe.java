package com.fireside.pantry.app.model;

import com.google.gson.Gson;

/**
 * Class for all new recipes
 */

public class NewRecipe {

    private String id;
    private String title;
    private String url;

    private double sodium;
    private double sugar;
    private double protein;
    private double fat;
    private double saturates;
    private double energy;

    private String instructions;
    private String[] ingredients;
    private String ingredientUnit;
    private String ingredientQuantity;

    public NewRecipe() {

    }

    // -- Methods

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // -- Getters

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
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

    public String getInstructions() {
        return instructions;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getIngredientUnit() {
        return ingredientUnit;
    }

    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    // -- Setters

    public NewRecipe setId(String id) {
        this.id = id;
        return this;
    }

    public NewRecipe setTitle(String title) {
        this.title = title;
        return this;
    }

    public NewRecipe setUrl(String url) {
        this.url = url;
        return this;
    }

    public NewRecipe setSodium(double sodium) {
        this.sodium = sodium;
        return this;
    }

    public NewRecipe setSugar(double sugar) {
        this.sugar = sugar;
        return this;
    }

    public NewRecipe setProtein(double protein) {
        this.protein = protein;
        return this;
    }

    public NewRecipe setFat(double fat) {
        this.fat = fat;
        return this;
    }

    public NewRecipe setSaturates(double saturates) {
        this.saturates = saturates;
        return this;
    }

    public NewRecipe setEnergy(double energy) {
        this.energy = energy;
        return this;
    }

    public NewRecipe setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public NewRecipe setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public NewRecipe setIngredientUnit(String ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
        return this;
    }

    public NewRecipe setIngredientQuantity(String ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
        return this;
    }
}
