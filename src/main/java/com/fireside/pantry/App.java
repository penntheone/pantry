package com.fireside.pantry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    private static String loginStatus;

    @Override
    public void start(Stage stage) {
        App.stage = stage;
        Scene scene = AppScene.getInstance().build();
        scene.getStylesheets().add("style.css");

        loginStatus = "None";

        stage.setWidth(1300);
        stage.setMinWidth(1200);
        stage.setHeight(600);
        stage.setMinHeight(500);
        stage.setTitle("Pantry");
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image("asset/appIcon/pantry.png"));
    }

    public static String getLoginStatus() {
        return loginStatus;
    }

    public static void setLoginStatus(String loginStatus) {
        App.loginStatus = loginStatus;
    }

    public static void main(String[] args) {
        launch();
    }
}