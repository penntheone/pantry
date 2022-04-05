package com.fireside.pantry.ui.components;

import com.fireside.pantry.service.UIService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class SearchBar extends HBox {

    private static class SearchHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            UIService.handleSearch();
        }
    }

    private final TextField textField;
    private final Button submit;

    public SearchBar() {
        this.textField = new TextField("Enter recipe name");
        this.submit = new Button("Search");
        this.submit.setOnAction(new SearchHandler());
        this.getChildren().addAll(textField, submit);
    }

    public String getText() {
        return this.textField.getText();
    }
}
