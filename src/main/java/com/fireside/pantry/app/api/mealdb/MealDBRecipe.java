package com.fireside.pantry.app.api.mealdb;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MealDBRecipe {

    public String idMeal;
    public String strMeal;
    public String strCategory;
    public String strArea;
    public String strInstructions;
    public String strMealThumb;
    public String strTages;
    public String strYoutube;
    public String strIngredient1;
    public String strIngredient2;
    public String strIngredient3;
    public String strIngredient4;
    public String strIngredient5;
    public String strIngredient6;
    public String strIngredient7;
    public String strIngredient8;
    public String strIngredient9;
    public String strIngredient10;
    public String strIngredient11;
    public String strIngredient12;
    public String strIngredient13;
    public String strIngredient14;
    public String strIngredient15;
    public String strIngredient16;
    public String strIngredient17;
    public String strIngredient18;
    public String strIngredient19;
    public String strIngredient20;
    
    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;
    public String strMeasure16;
    public String strMeasure17;
    public String strMeasure18;
    public String strMeasure19;
    public String strMeasure20;

    public String strSource;

    /**
     * This method is the constructor for the object, putting all the necessary
     * information into it
     * @param idMeal
     * @param strMeal
     * @param strCategory
     * @param strArea
     * @param strInstructions
     * @param strMealThumb
     * @param strTages
     * @param strYoutube
     * @param strIngredient1-20
     * @param strMeasure1-20
     * @param strSource
     */
    public MealDBRecipe(String idMeal, String strMeal, String strCategory, String strArea, String strInstructions,
                        String strMealThumb, String strTages, String strYoutube,
                        String strIngredient1, String strIngredient2, String strIngredient3, String strIngredient4,
                        String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8,
                        String strIngredient9, String strIngredient10, String strIngredient11, String strIngredient12,
                        String strIngredient13, String strIngredient14, String strIngredient15, String strIngredient16,
                        String strIngredient17, String strIngredient18, String strIngredient19, String strIngredient20,
                        String strMeasure1, String strMeasure2, String strMeasure3, String strMeasure4,
                        String strMeasure5, String strMeasure6, String strMeasure7, String strMeasure8,
                        String strMeasure9, String strMeasure10, String strMeasure11, String strMeasure12,
                        String strMeasure13, String strMeasure14, String strMeasure15, String strMeasure16,
                        String strMeasure17, String strMeasure18, String strMeasure19, String strMeasure20,
                        String strSource) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strTages = strTages;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strMeasure16 = strMeasure16;
        this.strMeasure17 = strMeasure17;
        this.strMeasure18 = strMeasure18;
        this.strMeasure19 = strMeasure19;
        this.strMeasure20 = strMeasure20;
        this.strSource = strSource;
    }

    /**
     * The getter for all the ingredients
     * @return All the ingredients for the recipe
     */
    public List<String> getIngredients() {
        LinkedList<String> ingredients = new LinkedList<>();
        ingredients.add(strIngredient1);
        ingredients.add(strIngredient2);
        ingredients.add(strIngredient3);
        ingredients.add(strIngredient4);
        ingredients.add(strIngredient5);
        ingredients.add(strIngredient6);
        ingredients.add(strIngredient7);
        ingredients.add(strIngredient8);
        ingredients.add(strIngredient9);
        ingredients.add(strIngredient10);
        ingredients.add(strIngredient11);
        ingredients.add(strIngredient12);
        ingredients.add(strIngredient13);
        ingredients.add(strIngredient14);
        ingredients.add(strIngredient15);
        ingredients.add(strIngredient16);
        ingredients.add(strIngredient17);
        ingredients.add(strIngredient18);
        ingredients.add(strIngredient19);
        ingredients.add(strIngredient20);

        while (ingredients.contains(null)) {
            ingredients.remove(null);
        }

        while (ingredients.contains("")) {
            ingredients.remove("");
        }

        while (ingredients.contains(" ")) {
            ingredients.remove(" ");
        }

        return ingredients;
    }

    /**
     * The getter method for all the recipe's measures
     * @return list of all measures
     */
    public List<String> getMeasures() {
        LinkedList<String> measures = new LinkedList<>();
        measures.add(strMeasure1);
        measures.add(strMeasure2);
        measures.add(strMeasure3);
        measures.add(strMeasure4);
        measures.add(strMeasure5);
        measures.add(strMeasure6);
        measures.add(strMeasure7);
        measures.add(strMeasure8);
        measures.add(strMeasure9);
        measures.add(strMeasure10);
        measures.add(strMeasure11);
        measures.add(strMeasure12);
        measures.add(strMeasure13);
        measures.add(strMeasure14);
        measures.add(strMeasure15);
        measures.add(strMeasure16);
        measures.add(strMeasure17);
        measures.add(strMeasure18);
        measures.add(strMeasure19);
        measures.add(strMeasure20);

        while (measures.contains(null)) {
            measures.remove(null);
        }

        while (measures.contains("")) {
            measures.remove("");
        }

        while (measures.contains(" ")) {
            measures.remove(" ");
        }
        return measures;
    }

    /**
     * Converts the object to a string
     * @return The object in string form
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
