package com.fireside.pantry.ui.views;

import com.fireside.pantry.app.Session;
import com.fireside.pantry.app.control.RequestController;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class AddRecipeView extends BorderPane {
    private static AddRecipeView instance;

    private AddRecipeView() {
        BorderPane card = new BorderPane();

        Label status = new Label();

        Button submitButton = AppButton.rectangularButtonWithText(
            "Submit", "asset/icon/plus.png", 20);

        Button exitButton = AppButton.circularButtonNoText("asset/icon/cross.png");

        exitButton.setOnMouseClicked(action -> AddRecipeView.getInstance().toBack());
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox top = new HBox(submitButton, status, spacer, exitButton);
        top.setMaxHeight(10);
        top.setAlignment(Pos.BASELINE_CENTER);
        top.setSpacing(20);


        BundledBox titleBox         = new BundledBox("Title *", "black",  false);
        BundledBox regionBox        = new BundledBox("Region *", "black",  false);
        BundledBox categoryBox      = new BundledBox("Category *", "black",  false);
        BundledBox imageURLBox      = new BundledBox("Image URL", "black",  true);
        BundledBox youtubeBox       = new BundledBox("Youtube URL", "black",  true);

        BundledBox instructionBox   = new BundledBox("Instruction *", "black",  false);
        instructionBox.getTextArea().setPrefHeight(100);
        BundledBox ingredientsBox   = new BundledBox("Ingredients *", "black",  false);
        ingredientsBox.getTextArea().setPrefHeight(100);


        ObservableList<BundledBox> boxes = FXCollections.observableArrayList(
                titleBox, regionBox, categoryBox, imageURLBox, youtubeBox, instructionBox, ingredientsBox);

        VBox boxesView = new VBox();
        boxesView.getChildren().addAll(boxes);
        boxesView.setSpacing(10);
        boxesView.setStyle("-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;");

        submitButton.setOnMouseClicked(action -> {
            boolean valid = true;

            for (BundledBox i : boxes) {
                if (!i.isValid()) {
                    valid = false;
                    i.setColor("red");
                } else {
                    i.setColor("black");
                }
            }

            if (!valid) status.setText("Some fields cannot be left empty.");
            else {
                RequestController.createRequest(
                        Session.getInstance().getAuthorizedUser().getId(),
                        titleBox.getInput(),
                        categoryBox.getInput(),
                        regionBox.getInput(),
                        instructionBox.getInput(),
                        imageURLBox.getInput(),
                        youtubeBox.getInput()
                );
                status.setText("Done!");
            }
        });

        ScrollPane scrollPane = new ScrollPane(boxesView);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-background-color: white;" +
                "-fx-background-insets: 0 0 0 0");

        for (BundledBox i : boxes) {
            i.getTextArea().maxWidthProperty().bind(scrollPane.widthProperty());
        }

        Region spacerCenter = new Region();
        spacerCenter.setMinHeight(20);

        VBox center = new VBox(spacerCenter, scrollPane);
        center.setStyle("-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;");

        card.setTop(top);
        card.setCenter(center);
        card.setMaxWidth(500); // 553
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

    private static class BundledBox extends VBox {

        private final TextArea textArea = new TextArea();
        private final boolean nullable;

        public BundledBox(String titleText, String color, boolean nullable) {
            setColor(color);
            textArea.setPrefHeight(30);
            HBox.setHgrow(textArea, Priority.ALWAYS);

            this.nullable = nullable;
            Label title = new Label(titleText);
            title.setFont(new Font("Arial Bold", 15));

            getChildren().addAll(title, textArea);
            setSpacing(10);
        }

        public boolean isValid() {
            return nullable || !textArea.getText().isBlank();
        }

        public String getInput() {
            return textArea.getText();
        }

        public TextArea getTextArea() {
            return textArea;
        }

        public void setColor(String color) {
            textArea.setStyle(
                    "-fx-focus-color: transparent;" +
                    "-fx-faint-focus-color: transparent;" +
                    "-fx-background-color: white;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: " + color);
        }
    }

    public static AddRecipeView getInstance() {
        if (instance == null) instance = new AddRecipeView();
        return instance;
    }
}
