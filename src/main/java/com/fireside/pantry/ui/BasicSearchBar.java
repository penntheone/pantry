package com.fireside.pantry.ui;

import com.fireside.pantry.app.RecipeManager;
import com.fireside.pantry.service.UIService;
import com.fireside.pantry.util.objects.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.stage.Stage;
import java.util.List;

public class BasicSearchBar {
    public static Scene build() {
        List<Recipe> recipes = RecipeManager.getRangeOfRecipes(1, 10);
        VBox pane = new VBox();
        List<Node> nodes = new ArrayList<>();

        TextField searchbar = new TextField();
        searchbar.setPromptText("Enter recipe name");
        nodes.add(searchbar);
        Button b1 = new Button("Search");
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // send input to uiService to then build cardlist view based on recipe list
                String userInput = searchbar.getText();
                UIService.searchByTitle(userInput);
            }
        };
        b1.setOnAction(buttonHandler);
        nodes.add(b1);

        pane.getChildren().addAll(nodes);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pane);

        return new Scene(scrollPane, 1100, 600);
    }
}
