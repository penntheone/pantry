package com.fireside.pantry.ui.views;

import com.fireside.pantry.app.model.Ingredient;
import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.components.IngredientCell;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.util.List;

public class AddIngredientView extends BorderPane {
    private static AddIngredientView instance;

    private final TextField searchField = new TextField();
    private final ObservableList<String> result = FXCollections.observableArrayList();

    private AddIngredientView() {
        BorderPane card = new BorderPane();

        Button exitButton = AppButton.circularButtonNoText("asset/icon/cross.png");

        exitButton.setOnMouseClicked(action -> AddIngredientView.getInstance().toBack());
        Region spacer = new Region(); HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox top = new HBox(generateSearchBar(), spacer, exitButton);
        top.setMaxHeight(10);

        result.addAll("Beef", "Rice", "Chicken", "Pork", "Fritter", "Things");
        ListView<String> resultListView = new ListView<>(result);
        resultListView.setCellFactory(param -> new IngredientCell("Add"));
        resultListView.setStyle(
                "    -fx-padding: 3;" +
                "    -fx-border-style: solid inside;" +
                "    -fx-border-width: 2;" +
                "    -fx-border-radius: 5 5 28 28;" +
                "    -fx-border-color: black;" +
                "    -fx-background-color: white;" +
                "    -fx-focus-color: transparent;" +
                "    -fx-faint-focus-color: transparent");
        VBox.setVgrow(resultListView, Priority.ALWAYS);

        Region spacerCenter = new Region();
        spacerCenter.setMinHeight(20);

        VBox center = new VBox(spacerCenter, resultListView);

        card.setTop(top);
        card.setCenter(center);
        card.setMaxWidth(700);
        card.setMaxHeight(500);

        card.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 30;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white;" +
                "-fx-background-radius: 30;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        setCenter(card);
        setStyle("-fx-background-color: rgba(100, 100, 100, 0.5)");

    }

    private HBox generateSearchBar() {
        HBox result = new HBox();
        // ---------------------- Search field
        searchField.setStyle(
                "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-background-color: transparent");
        searchField.setPromptText("Enter Ingredient Name");
        searchField.setPrefWidth(320);
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) UIService.handleIngredientSearch();
        });

        // ---------------------- Spacer until Right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // ---------------------- Search button
        ImageView searchIcon = new ImageView(new Image("asset/icon/magnifying-glass.png"));
        searchIcon.setFitHeight(20); searchIcon.setFitWidth(20);
        Button searchButton = new Button("", searchIcon);
        searchButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-cursor: hand;");
        searchButton.setOnAction(action -> UIService.handleIngredientSearch());

        // ---------------------- Styling
        result.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 30;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white");
        result.setAlignment(Pos.CENTER_LEFT);
        result.getChildren().addAll(searchField, spacer, searchButton);
        setMinWidth(300);
        setMaxWidth(500);

        searchField.prefHeightProperty().bind(result.heightProperty());

        return result;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public void populateListView(List<Ingredient> ingredients) {
        result.clear();
        for (Ingredient i : ingredients) {
            System.out.println(i);
            result.add(i.getName());
        }
    }

    public static AddIngredientView getInstance() {
        if (instance == null) instance = new AddIngredientView();
        return instance;
    }
}
