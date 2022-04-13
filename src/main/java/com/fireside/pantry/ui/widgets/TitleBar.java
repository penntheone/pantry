package com.fireside.pantry.ui.widgets;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class TitleBar extends HBox {
    String title;
    SearchBar searchBar;

    public TitleBar(String title) {
        this.title = title;
        this.searchBar = new SearchBar();
        // Button
        ImageView menuIcon = new ImageView(new Image("asset/icon/bar.png"));
        menuIcon.setFitHeight(20); menuIcon.setFitWidth(20);
        Button menuButton = new Button("", menuIcon);

        // ============================================================= Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bur = new HBox(menuButton);
        bur.setAlignment(Pos.BASELINE_CENTER);

        getChildren().addAll(
                bur,
                spacer,
                searchBar
        );
        setAlignment(Pos.BASELINE_CENTER);
        setStyle("-fx-background-color: white");
    }

    public String getTitle() {
        return title;
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }
}
