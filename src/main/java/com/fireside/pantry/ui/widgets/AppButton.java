package com.fireside.pantry.ui.widgets;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AppButton {
    public static Button circularButtonNoText(String imageLoc) {
        ImageView icon = new ImageView(new Image(imageLoc));
        icon.setFitHeight(15); icon.setFitWidth(15);
        Button button = new Button("", icon);
        button.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 40px; " +
                        "-fx-min-height: 40px; " +
                        "-fx-max-width: 40px; " +
                        "-fx-max-height: 40px;" +
                        "-fx-border-style: solid inside;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-insets: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-border-color: black;" +
                        "-fx-background-color: white");
        return button;
    }

    public static Button rectangularButtonWithText(String text, String imageLoc, int borderRadius) {
        ImageView icon = new ImageView(new Image(imageLoc));
        icon.setFitHeight(20); icon.setFitWidth(20);
        Button button = new Button(text, icon);
        button.setGraphicTextGap(10);
        button.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-insets: 5;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: " + borderRadius + ";" +
                        "-fx-border-color: black;" +
                        "-fx-background-color: white;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-font-size: 15;" +
                        "-fx-font-family: Arial;" +
                        "-fx-font-weight: bold"
        );
        return button;
    }
}
