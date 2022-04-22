package com.fireside.pantry.ui.widgets;

import com.fireside.pantry.service.UIService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

/**
 * The title bar of the app, which always float on top at all times.
 */
public class TitleBar extends HBox {
    private static TitleBar instance;

    // ============================================================= Components

    Label titleLabel;
    SearchBar searchBar;

    // ============================================================= Constructors

    private TitleBar() {
        // ---------------------- Menu button
        ImageView menuIcon = new ImageView(new Image("asset/icon/bar.png"));
        menuIcon.setFitHeight(25); menuIcon.setFitWidth(25);
        Button menuButton = new Button("", menuIcon);
        menuButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-cursor: hand;");
        menuButton.setOnAction(action -> UIService.openMenu());

        // ---------------------- Spacer 10px
        HBox sep = new HBox();
        sep.setPrefWidth(10);

        // ---------------------- Title
        titleLabel = new Label("Database");
        titleLabel.setFont(new Font("Arial", 25));

        // ---------------------- Spacer until Right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(
                menuButton,
                sep,
                titleLabel,
                spacer,
                searchBar
        );

        // ---------------------- Styling
        setAlignment(Pos.CENTER_LEFT);
        setStyle("-fx-padding: 10;" +
                "-fx-background-color: white");
    }

    // ============================================================= Getters / Setters

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }

    // ============================================================= Static instance access

    public static TitleBar getInstance() {
        if (instance == null)
            instance = new TitleBar();
        return instance;
    }
}
