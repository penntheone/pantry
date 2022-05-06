package com.fireside.pantry.ui.components;

import com.fireside.pantry.app.Session;
import com.fireside.pantry.app.control.IngredientController;
import com.fireside.pantry.app.control.UserController;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class which handles keeping track of ingredients
 */
public class IngredientCell extends ListCell<String> {
    HBox root = new HBox();
    Label ingredientName = new Label();
    Region spacer = new Region();

    Label status = new Label();
    Button button;

    /**
     * A constructor that users to add or cross ingredients
     * @param action the specified action
     */
    public IngredientCell(String action) {
        ingredientName.setFont(new Font("Arial", 15));

        switch (action) {
            case "Cross" -> {
                button = AppButton.circularButtonNoText("asset/icon/cross.png");
                button.setOnAction(event -> {
                    UserController.addPref(
                            Session.getInstance().getAuthorizedUser().getId(),
                            IngredientController.getIngredientIDByName(getItem()),
                            0, 0);
                    getListView().getItems().remove(getItem());
                });
            }

            case "Add" -> {
                button = AppButton.circularButtonNoText("asset/icon/plus.png");
                button.setOnAction(event -> {
                    UserController.addPref(
                            Session.getInstance().getAuthorizedUser().getId(),
                            IngredientController.getIngredientIDByName(getItem()),
                            0, 1);
                    status.setText("Added");
                });
            }

            default -> button = new Button();
        }

        button.setStyle("-fx-background-color: transparent");
        status.setTextFill(Color.GREEN);

        root.getChildren().addAll(ingredientName, spacer, status, button);
        root.setAlignment(Pos.BASELINE_CENTER);
        HBox.setHgrow(spacer, Priority.ALWAYS);
    }

    /**
     * updates a specified item based on whether it is empty or not
     * @param item specified item
     * @param empty whether it is empty or not
     */
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (item != null && !empty) {
            ingredientName.setText(item);
            setGraphic(root);
        }
    }
}
