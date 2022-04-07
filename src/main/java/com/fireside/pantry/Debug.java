package com.fireside.pantry;

import com.fireside.pantry.app.control.RecipeManager;
import com.fireside.pantry.util.Utils;
import com.fireside.pantry.util.objects.Recipe;

public class Debug {

    public static void main(String[] args) {
        Recipe recipe = RecipeManager.getRecipeByID(110);

        System.out.println(Utils.prettify(recipe.toString()));

        recipe.getIngredients();

    }
}
