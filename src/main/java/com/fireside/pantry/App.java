package com.fireside.pantry;

import com.fireside.pantry.ui.CardListView;
import com.fireside.pantry.ui.basicSearchBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene search = basicSearchBar.build();

        stage.setWidth(520);
        stage.setHeight(600);
        stage.setTitle("Pantry");
        stage.setScene(search);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}