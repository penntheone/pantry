package com.fireside.pantry.ui.pages;

import com.fireside.pantry.service.UIService;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import static javafx.scene.layout.GridPane.setConstraints;

public class UserPage extends BorderPane {
    private static UserPage instance;

    private final TextField userField;
    private final PasswordField passwordField;

    private UserPage() {
        userField = new TextField();
        passwordField = new PasswordField();
    }

    public BorderPane build() {
        Label userLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        HBox userFieldText = generateField(userField);
        HBox passwordFieldText = generateField(passwordField);

        userFieldText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) UIService.handleLogin();
        });

        GridPane center = new GridPane();
        center.getChildren().addAll(userLabel, userFieldText, passwordLabel, passwordFieldText);
        setConstraints(userLabel, 0, 0);
        setConstraints(userFieldText, 1, 0);
        setConstraints(passwordLabel, 0, 1);
        setConstraints(passwordFieldText, 1, 1);
        center.setHgap(20);

        center.setAlignment(Pos.CENTER);
        center.setMaxWidth(300);
        center.setPrefWidth(300);

        BorderPane pane = new BorderPane(center);
        VBox.setVgrow(pane, Priority.ALWAYS);
        pane.setStyle("-fx-padding: 10;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        return pane;
    }

    private HBox generateField(TextField textField) {

        HBox textFieldHolder = new HBox(textField);
        textField.prefWidthProperty().bind(textFieldHolder.widthProperty());
        textFieldHolder.setStyle("-fx-padding: 5;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 2;" +
                "-fx-border-radius: 30;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white");
        textFieldHolder.setPrefWidth(200);
        textFieldHolder.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) UIService.handleLogin();
        });

        return textFieldHolder;
    }

    public static UserPage getInstance() {
        if (instance == null) instance = new UserPage();
        return instance;
    }
}
