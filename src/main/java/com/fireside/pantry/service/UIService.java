package com.fireside.pantry.service;

import com.fireside.pantry.app.RecipeManager;
import com.fireside.pantry.util.objects.Recipe;
import com.fireside.pantry.ui.CardListView;
import com.fireside.pantry.ui.basicSearchBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class UIService {

    private static List<Recipe> recipes;

    public static void searchByTitle(String name) {
        recipes = RecipeManager.getRecipesByTitle(name);
        Stage stage = new Stage();
        Scene scene = CardListView.build(recipes);
        stage.setWidth(520);
        stage.setHeight(600);
        stage.setTitle("Pantry");
        stage.setScene(scene);
        stage.show();
    }
}
