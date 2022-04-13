package com.fireside.pantry;

import com.fireside.pantry.ui.DatabaseUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    public static void updateScene(Scene newScene) {
        App.stage.setScene(newScene);
    }

    @Override
    public void start(Stage stage) {
        App.stage = stage;
        Scene scene = DatabaseUI.getInstance().build();
        scene.getStylesheets().add("style.css");

        stage.setWidth(1300);
        stage.setHeight(600);
        stage.setTitle("Pantry");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}