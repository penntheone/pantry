package com.fireside.pantry.ui.widgets;

import com.fireside.pantry.service.UIService;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The Universal Menu, which always exists just left of the stage
 * and always ready to be summoned from the title bar.
 */
public class UniversalMenu extends VBox {
    private static UniversalMenu instance;

    // ============================================================= Constants

    private static final double MENU_WIDTH = 300;

    // ============================================================= Components

    boolean activated;
    TranslateTransition menuTranslation;

    // ============================================================= Constructors

    private UniversalMenu() {
        activated = false;

        // ---------------------- Exit button
        ImageView exitIcon = new ImageView(new Image("asset/icon/cross.png"));
        exitIcon.setFitHeight(25); exitIcon.setFitWidth(25);
        Button exitButton = new Button("", exitIcon);
        exitButton.setOnAction(action -> UIService.closeMenu());

        // ---------------------- Top spacer 40px
        Region topSpacer = new Region();
        topSpacer.setPrefHeight(40);

        // ---------------------- Page buttons
        Button databaseButton = new Button("Database");
        Button advanceSearchButton = new Button("Advance Search");
        Button mealPlanningButton = new Button("Meal Planning");

        databaseButton.setOnAction(action -> UIService.handlePageSelection("Database"));
        advanceSearchButton.setOnAction(action -> UIService.handlePageSelection("Advance Search"));
        mealPlanningButton.setOnAction(action -> UIService.handlePageSelection("Meal Planning"));

        // ---------------------- Lower spacer to bottom
        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        // ---------------------- User button
        ImageView userIcon = new ImageView(new Image("asset/icon/user.png"));
        userIcon.setFitHeight(30); userIcon.setFitWidth(30);
        Button userButton = new Button("", userIcon);
        userButton.setOnAction(action -> UIService.handlePageSelection("User"));

        getChildren().addAll(
                exitButton, topSpacer,
                databaseButton, advanceSearchButton, mealPlanningButton,
                bottomSpacer, userButton);

        // ---------------------- Menu translation controller
        menuTranslation = new TranslateTransition(Duration.millis(200), this);
        menuTranslation.setFromX(-MENU_WIDTH - 3);
        menuTranslation.setToX(0);

        // ---------------------- Styling and location setting
        setPrefWidth(MENU_WIDTH);
        setTranslateX(-MENU_WIDTH - 3);
        setStyle("-fx-padding: 20;" +
                "-fx-border-style: solid outside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 0;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");
    }

    // ============================================================= Getters / Setters

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public TranslateTransition getMenuTranslation() {
        return menuTranslation;
    }

    // ============================================================= Static instance access

    public static UniversalMenu getInstance() {
        if (UniversalMenu.instance == null)
            UniversalMenu.instance = new UniversalMenu();
        return UniversalMenu.instance;
    }
}
