package com.fireside.pantry.ui.pages.users;

import com.fireside.pantry.app.Session;
import com.fireside.pantry.service.RecipeService;
import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.components.IngredientCell;
import com.fireside.pantry.ui.views.AddIngredientView;
import com.fireside.pantry.ui.views.AddRecipeView;
import com.fireside.pantry.ui.views.RecipeListView;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * The profile page that contains the user's profile and
 * allows them to add recipes
 */
public class ProfilePage extends BorderPane {
    private static ProfilePage instance;

    /**
     * Private constructor
     */
    private ProfilePage() {
    }

    /**
     * Builds the page
     * @return The page itself visually
     */
    public BorderPane build() {
        String firstName = Session.getInstance().getAuthorizedUser().getFirstName();
        Label welcomeLabel = new Label("Welcome to Pantry, " + firstName);
        welcomeLabel.setFont(new Font("Arial Bold", 25));

        Label welcomeSubtitle = new Label("Manage your allergies and preferences, or request a recipe here.");
        welcomeSubtitle.setFont(new Font("Arial", 15));

        VBox top = new VBox(welcomeLabel, welcomeSubtitle);
        top.setStyle("-fx-padding: 10");

        Button logoutButton = AppButton.rectangularButtonWithText(
                "Logout", "asset/icon/back-arrow.png", 5);
        logoutButton.setOnMouseClicked(action -> UIService.handleLogout());
        VBox bottom = new VBox(logoutButton);
        bottom.setStyle("-fx-padding: 10");

        Button allergiesAddButton = AppButton.circularButtonNoText("asset/icon/plus.png");
        allergiesAddButton.setOnMouseClicked(action -> AddIngredientView.getInstance().toFront());
        HBox allergiesTitle = generateTitle("Allergies", allergiesAddButton);

        ObservableList<String> list = FXCollections.observableArrayList(
                "Beef", "Rice", "Chicken", "Pork");
        ListView<String> allergiesListView = new ListView<>(list);

        allergiesListView.setCellFactory(param -> new IngredientCell("Cross"));
        VBox center = new VBox(allergiesTitle, allergiesListView);

        center.setPrefWidth(600);
        center.setSpacing(10);
        center.setStyle(
                "-fx-padding: 10;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        Button recipeAddButton = AppButton.circularButtonNoText("asset/icon/plus.png");
        recipeAddButton.setOnMouseClicked(action -> AddRecipeView.getInstance().toFront());

        HBox recipeTitle = generateTitle("Pending Requests", recipeAddButton);
        RecipeListView requestsView = new RecipeListView(RecipeService.getHomeRecipes());
        requestsView.setStyle(
                "    -fx-border-style: solid inside;" +
                "    -fx-border-width: 2;" +
                "    -fx-border-radius: 5;" +
                "    -fx-border-color: black;" +
                "    -fx-background-color: white;" +
                "    -fx-focus-color: transparent;" +
                "    -fx-faint-focus-color: transparent");

        VBox right = new VBox(recipeTitle, requestsView);
        right.setSpacing(10);
        right.setStyle("-fx-padding: 10;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        BorderPane pane = new BorderPane();
        pane.setTop     (top);
        pane.setBottom  (bottom);
        pane.setCenter  (center);
        pane.setRight   (right);
        pane.setStyle("-fx-padding: 30;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        return pane;
    }

    public static HBox generateTitle(String title, Button addButton) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font("Arial", 25));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        return new HBox(titleLabel, spacer, addButton);
    }

    /**
     * Returns the object itself if created, will create if not
     * @return the object itself
     */
    public static ProfilePage getInstance() {
        if (instance == null) instance = new ProfilePage();
        return instance;
    }
}