package com.fireside.pantry;

import com.fireside.pantry.ui.ListView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = ListView.build();

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