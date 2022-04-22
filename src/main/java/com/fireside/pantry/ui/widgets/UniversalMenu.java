package com.fireside.pantry.ui.widgets;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class UniversalMenu extends VBox {
    private static UniversalMenu instance;

    String currentPage;
    boolean activated;
    TranslateTransition menuTranslation;

    private UniversalMenu() {
        currentPage = "Database";
        activated = false;
        this.setId("menu");
        this.setPrefWidth(200);

        this.getChildren().addAll(new Button("Database"), new Button("Advance Search"), new Button("Meal Planning"));
        this.setTranslateX(-200);

        menuTranslation = new TranslateTransition(Duration.millis(500), this);

        menuTranslation.setFromX(-200);
        menuTranslation.setToX(0);
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public boolean isActivated() {
        return activated;
    }

    public void flipActivated() {
        activated = !activated;
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
