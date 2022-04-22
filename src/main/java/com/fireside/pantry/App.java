package com.fireside.pantry;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        App.stage = stage;
        javafx.scene.Scene scene = AppScene.getInstance().build();
        scene.getStylesheets().add("style.css");

        stage.setWidth(1300);
        stage.setMinWidth(1200);
        stage.setHeight(600);
        stage.setTitle("Pantry");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}