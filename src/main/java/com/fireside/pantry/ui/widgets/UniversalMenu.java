package com.fireside.pantry.ui.widgets;

import com.fireside.pantry.service.UIService;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class UniversalMenu extends VBox {
    private static UniversalMenu instance;

    private static final double MENU_WIDTH = 300;

    boolean activated;
    TranslateTransition menuTranslation;

    private UniversalMenu() {
        activated = false;

        ImageView exitIcon = new ImageView(new Image("asset/icon/cross.png"));
        exitIcon.setFitHeight(25); exitIcon.setFitWidth(25);
        Button exitButton = new Button("", exitIcon);
        exitButton.setOnAction(action -> UIService.closeMenu());

        Button databaseButton = new Button("Database");
        Button advanceSearchButton = new Button("Advance Search");
        Button mealPlanningButton = new Button("Meal Planning");

        databaseButton.setOnAction(action -> UIService.handlePageSelection("Database"));
        advanceSearchButton.setOnAction(action -> UIService.handlePageSelection("Advance Search"));
        mealPlanningButton.setOnAction(action -> UIService.handlePageSelection("Meal Planning"));

        Region topSpacer = new Region();
        topSpacer.setPrefHeight(40);

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);



        ImageView userIcon = new ImageView(new Image("asset/icon/user.png"));
        userIcon.setFitHeight(30); userIcon.setFitWidth(30);
        Button userButton = new Button("", userIcon);
        userButton.setOnAction(action -> UIService.handlePageSelection("User"));

        getChildren().addAll(exitButton, topSpacer, databaseButton, advanceSearchButton, mealPlanningButton, bottomSpacer, userButton);

        setPrefWidth(MENU_WIDTH);

        setTranslateX(-MENU_WIDTH - 3);


        menuTranslation = new TranslateTransition(Duration.millis(200), this);

        menuTranslation.setFromX(-MENU_WIDTH - 3);
        menuTranslation.setToX(0);

        setStyle("-fx-padding: 20;" +
                "-fx-border-style: solid outside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 0;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public TranslateTransition getMenuTranslation() {
        return menuTranslation;
    }

    public void setMenuTranslation(TranslateTransition menuTranslation) {
        this.menuTranslation = menuTranslation;
    }

    public static UniversalMenu getInstance() {
        if (UniversalMenu.instance == null)
            UniversalMenu.instance = new UniversalMenu();
        return UniversalMenu.instance;
    }
}
