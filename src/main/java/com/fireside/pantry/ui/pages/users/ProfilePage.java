package com.fireside.pantry.ui.pages.users;

import com.fireside.pantry.app.model.Ingredient;
import com.fireside.pantry.app.model.Recipe;
import com.fireside.pantry.service.RecipeService;
import com.fireside.pantry.ui.views.RecipeListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ProfilePage extends BorderPane {
    private static final String BUTTON_STYLE =
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 5;" +
            "-fx-border-color: black;" +
            "-fx-background-color: white;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;" +
            "-fx-font-size: 15;" +
            "-fx-font-family: Arial";

    private static ProfilePage instance;

    private static String username;

    private ProfilePage() {

    }

    public BorderPane build() {
        Label welcomeLabel = new Label("Welcome, [Penn]");
        welcomeLabel.setFont(new Font("Arial Bold", 25));



        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(BUTTON_STYLE);

        Button addRecipeButton = new Button("Add recipe");
        addRecipeButton.setStyle(BUTTON_STYLE);

        Label preferencesLabel = new Label("Preferences");

        Label allergiesLabel = new Label("Allergies");
        allergiesLabel.setFont(new Font("Arial", 25));
        Region allergiesSpacer = new Region();
        HBox.setHgrow(allergiesSpacer, Priority.ALWAYS);
        Button allergiesAddButton = new Button("Add");
        allergiesAddButton.setStyle(BUTTON_STYLE);

        HBox allergiesTitle = new HBox(allergiesLabel, allergiesSpacer, allergiesAddButton);

        ListView<Ingredient> allergiesList = new ListView<>();

        VBox center = new VBox(
                welcomeLabel,
                addRecipeButton, preferencesLabel, allergiesTitle, allergiesList, logoutButton);
        center.setPrefWidth(600);
        center.setStyle("-fx-padding: 50;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        BorderPane pane = new BorderPane();
        pane.setCenter(center);
        pane.setRight(new RecipeListView(RecipeService.getHomeRecipes()));

        return pane;
    }

    public static ProfilePage getInstance() {
        if (instance == null) instance = new ProfilePage();
        return instance;
    }
}