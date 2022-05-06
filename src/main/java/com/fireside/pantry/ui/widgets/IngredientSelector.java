package com.fireside.pantry.ui.widgets;

import com.fireside.pantry.app.model.Ingredient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class IngredientSelector extends VBox {
    public IngredientSelector() {
        Label allergiesLabel = new Label("Allergies");
        allergiesLabel.setFont(new Font("Arial", 25));
        Region allergiesSpacer = new Region();
        HBox.setHgrow(allergiesSpacer, Priority.ALWAYS);
        Button allergiesAddButton = new Button("Add");
        allergiesAddButton.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-font-size: 15;" +
                "-fx-font-family: Arial");

        HBox allergiesTitle = new HBox(allergiesLabel, allergiesSpacer, allergiesAddButton);

        ListView<Ingredient> allergiesList = new ListView<>();
        this.getChildren().addAll(allergiesTitle, allergiesList);
    }
}
