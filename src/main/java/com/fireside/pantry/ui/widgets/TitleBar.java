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

public class TitleBar extends HBox {
    private static TitleBar instance;

    Label titleLabel;
    SearchBar searchBar;

    private TitleBar() {
        this.searchBar = new SearchBar();
        // Button
        ImageView menuIcon = new ImageView(new Image("asset/icon/bar.png"));
        menuIcon.setFitHeight(25); menuIcon.setFitWidth(25);
        Button menuButton = new Button("", menuIcon);
        menuButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-cursor: hand;");
        menuButton.setOnAction(action -> UIService.openMenu());

        HBox sep = new HBox();
        sep.setPrefWidth(10);

        titleLabel = new Label("Database");
        titleLabel.setFont(new Font("Arial", 25));

        // ============================================================= Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(
                menuButton,
                sep,
                titleLabel,
                spacer,
                searchBar
        );
        setAlignment(Pos.CENTER_LEFT);
//        setAlignment(Pos.BASELINE_CENTER);
        setStyle("-fx-padding: 10;" +
                "-fx-background-color: white");
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }

    public static TitleBar getInstance() {
        if (instance == null)
            instance = new TitleBar();
        return instance;
    }
}
