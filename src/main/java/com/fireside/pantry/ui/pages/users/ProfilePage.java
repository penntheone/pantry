package com.fireside.pantry.ui.pages.users;

import com.fireside.pantry.service.RecipeService;
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

public class ProfilePage extends BorderPane {

    private static ProfilePage instance;

    private ProfilePage() {

    }

    public BorderPane build() {
//        String firstName = Session.getInstance().getAuthorizedUser().getFirstName();
        Label welcomeLabel = new Label("Welcome to Pantry, Penn.");
        welcomeLabel.setFont(new Font("Arial Bold", 25));

        Label welcomeSubtitle = new Label("Manage your allergies, or request a recipe here.");
        welcomeSubtitle.setFont(new Font("Arial", 15));

        VBox top = new VBox(welcomeLabel, welcomeSubtitle);
        top.setStyle("-fx-padding: 10");

        Button logoutButton = AppButton.rectangularButtonWithText(
                "Logout", "asset/icon/back-arrow.png", "black");
        VBox bottom = new VBox(logoutButton);
        bottom.setStyle("-fx-padding: 10");

        Button allergiesAddButton = AppButton.circularButtonNoText("asset/icon/plus.png");
        allergiesAddButton.setOnMouseClicked(action -> AddIngredientView.getInstance().toFront());
        HBox allergiesTitle = generateTitle("Allergies", allergiesAddButton);

        ObservableList<String> list = FXCollections.observableArrayList(
                "Beef", "Rice", "Chicken", "Pork", "Fritter", "Things");
        ListView<String> allergiesListView = new ListView<>(list);

        allergiesListView.setCellFactory(param -> new IngredientCell());
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

    static class IngredientCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("");
        Region pane = new Region();
        Button button = AppButton.circularButtonNoText("asset/icon/cross.png");

        public IngredientCell() {
            super();

            button.setStyle("-fx-background-color: transparent");
            label.setFont(new Font("Arial", 15));

            hbox.getChildren().addAll(label, pane, button);
            hbox.setAlignment(Pos.BASELINE_CENTER);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(event -> getListView().getItems().remove(getItem()));
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                label.setText(item);
                setGraphic(hbox);
            }
        }
    }


    public static ProfilePage getInstance() {
        if (instance == null) instance = new ProfilePage();
        return instance;
    }
}