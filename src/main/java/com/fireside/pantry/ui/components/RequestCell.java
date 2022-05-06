package com.fireside.pantry.ui.components;

import com.fireside.pantry.app.Session;
import com.fireside.pantry.app.control.IngredientController;
import com.fireside.pantry.app.control.UserController;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RequestCell extends TableCell {
    HBox root = new HBox();

    Button addButton;
    Button removeButton;

    public RequestCell() {
        addButton = AppButton.circularButtonNoText("asset/icon/plus.png");
        addButton.setOnAction(event -> {
        });

        removeButton = AppButton.circularButtonNoText("asset/icon/cross.png");
        removeButton.setOnAction(event -> {
        });


        addButton.setStyle("-fx-background-color: transparent");
        removeButton.setStyle("-fx-background-color: transparent");

        root.getChildren().addAll(addButton, removeButton);
        root.setAlignment(Pos.BASELINE_CENTER);

    }
}
