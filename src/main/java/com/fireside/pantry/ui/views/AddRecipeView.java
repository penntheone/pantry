package com.fireside.pantry.ui.views;

import com.fireside.pantry.ui.components.IngredientCell;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class AddRecipeView extends BorderPane {
    private static AddRecipeView instance;

    private AddRecipeView() {
            BorderPane card = new BorderPane();

            Button exitButton = AppButton.circularButtonNoText("asset/icon/cross.png");

            exitButton.setOnMouseClicked(action -> AddRecipeView.getInstance().toBack());
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            HBox top = new HBox(spacer, exitButton);
            top.setMaxHeight(10);

            VBox center = new VBox();

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

    public static AddRecipeView getInstance() {
        if (instance == null) instance = new AddRecipeView();
        return instance;
    }
}
