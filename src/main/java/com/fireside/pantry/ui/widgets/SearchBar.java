package com.fireside.pantry.ui.widgets;

import com.fireside.pantry.service.UIService;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class SearchBar extends HBox {
    private final TextField searchField;
    private final ChoiceBox<String> filters;

    public SearchBar() {
        // Search Field ------------------------------------------------
        this.searchField = new TextField();
        searchField.setStyle(
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-background-color: transparent");
        searchField.setPromptText("Enter Recipe Name");
        searchField.setPrefWidth(320);
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) UIService.handleSearch();
        });

        // Filter choice box ---------------------------------------
        this.filters = new ChoiceBox<>();
        filters.setPrefWidth(150);
//        filters.getItems().add("Select Options");
        filters.getItems().add("Recipe Title");
        filters.getItems().add("Ingredient");
        filters.getItems().add("Region");
        filters.getItems().add("Type");

        // Default value
        filters.setValue("Options");

        // Spacer ------------------------------------------------
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Search Button ------------------------------------------------
        ImageView searchIcon = new ImageView(new Image("asset/icon/magnifying-glass.png"));
        searchIcon.setFitHeight(20); searchIcon.setFitWidth(20);
        Button searchButton = new Button("", searchIcon);
        searchButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-cursor: hand;");
        searchButton.setOnAction(action -> UIService.handleSearch());

        setStyle("-fx-padding: 5;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 2;" +
                "-fx-border-radius: 30;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white");
        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(searchField, spacer, filters, searchButton);
        setMinWidth(300);
        setMaxWidth(500);
        setMinHeight(35);
        setMaxHeight(55);
    }

    public String getText() {
        return this.searchField.getText();
    }

    public String getFilters() {return this.filters.getValue();}
}
