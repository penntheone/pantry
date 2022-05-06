package com.fireside.pantry.ui.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * The about page which contains some background information on the app
 */
public class AboutPage extends BorderPane {
    private static AboutPage instance;

    /**
     * Private constructor which takes care of the key variables
     */
    private AboutPage() {
    }

    /**
     * Builds the page
     * @return The page itself visually
     */
    public BorderPane build() {
        Label title = new Label("Pantry App");
        title.setFont(new Font("Arial Bold", 20));

        Label subtitle = new Label("©2022 | Developed for CSE 201 at Miami University\nby the following fine people:");

        Region space = new Region();
        space.setPrefHeight(15);

        GridPane names = new GridPane();
        names.setHgap(15);

        String[] nameStrings = new String[] {
                "Anthony Brey",     "@breyad",
                "Joe Kadlic",       "@kadlicjp",
                "Pendleton Pham",   "@phamsq",
                "Nathan Sivak",     "@sivaknc",
                "Yixiao Ye",        "@yey17",
        };

        for (int i = 0; i < nameStrings.length; i += 2) {
            names.add(new Label(nameStrings[i]), 0, i/2);
            names.add(new Label(nameStrings[i+1]), 1, i/2);
        }

        Region space2 = new Region();
        space2.setPrefHeight(15);

        Label pogchamp = new Label("Penn: Please Microsoft, let me be your little Pog-champ!");

        ImageView appIcon = new ImageView(new Image("asset/appIcon/pantry.png"));
        appIcon.setFitHeight(50); appIcon.setFitWidth(50);

        Region spaceApp = new Region();
        spaceApp.setPrefHeight(15);

        Region spaceApp2 = new Region();
        spaceApp2.setPrefHeight(5);

        VBox center = new VBox(appIcon, spaceApp2, title, spaceApp, subtitle, space, names, space2, pogchamp);

        center.setAlignment(Pos.CENTER_LEFT);
        center.setMaxWidth(800);

        BorderPane pane = new BorderPane(center);
        VBox.setVgrow(pane, Priority.ALWAYS);
        pane.setStyle("-fx-padding: 10;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        return pane;
    }

    /**
     * Returns the object itself if created, will create if not
     * @return the object itself
     */
    public static AboutPage getInstance() {
        if (instance == null) instance = new AboutPage();
        return instance;
    }

}
