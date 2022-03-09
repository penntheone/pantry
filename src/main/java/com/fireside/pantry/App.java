package com.fireside.pantry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label label = new Label("Hello");
        Scene scene = new Scene(new StackPane(label), 1100, 600);

        stage.setWidth(1100);
        stage.setHeight(600);
        stage.setTitle("Pantry");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}
