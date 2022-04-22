package com.fireside.pantry.ui.widgets;

import com.fireside.pantry.service.UIService;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class UniversalMenu extends VBox {
    private static UniversalMenu instance;

    private static final double MENU_WIDTH = 300;

    String currentPage;
    boolean activated;
    TranslateTransition menuTranslation;

    private UniversalMenu() {
        currentPage = "Database";
        activated = false;



        ImageView exitIcon = new ImageView(new Image("asset/icon/cross.png"));
        exitIcon.setFitHeight(25); exitIcon.setFitWidth(25);
        Button exitButton = new Button("", exitIcon);
        exitButton.setOnAction(action -> UIService.handleMenuSelection());

        Button databaseButton = new Button("Database");
        Button advanceSearchButton = new Button("Advance Search");
        Button mealPlanningButton = new Button("Meal Planning");

        databaseButton.setFont(new Font("Arial", 25));
        advanceSearchButton.setFont(new Font("Arial", 25));
        mealPlanningButton.setFont(new Font("Arial", 25));

        databaseButton.setOnAction(action -> UIService.handlePageSelection("Database"));
        advanceSearchButton.setOnAction(action -> UIService.handlePageSelection("Advance Search"));
        mealPlanningButton.setOnAction(action -> UIService.handlePageSelection("Meal Planning"));

        Region spacer = new Region();
        spacer.setPrefHeight(40);

        setPrefWidth(MENU_WIDTH);
        getChildren().addAll(exitButton, spacer, databaseButton, advanceSearchButton, mealPlanningButton);
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

    public String getCurrentPage() {
        return currentPage;
    }

    public boolean isActivated() {
        return activated;
    }

    public boolean flipActivated() {
        activated = !activated;
        return !activated;
    }

    public TranslateTransition getMenuTranslation() {
        return menuTranslation;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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
