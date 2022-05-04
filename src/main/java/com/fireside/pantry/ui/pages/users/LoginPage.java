package com.fireside.pantry.ui.pages.users;

import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static javafx.scene.layout.GridPane.setConstraints;

public class LoginPage extends BorderPane {
    private static LoginPage instance;

    private static TextField usernameField;
    private static PasswordField passwordField;

    private static Label status;

    private LoginPage() {
        usernameField = new TextField();
        passwordField = new PasswordField();
        status = new Label();
    }

    public BorderPane build() {
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(new Font("Arial", 14));

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font("Arial", 14));


        HBox usernameFieldText = generateField(usernameField);
        HBox passwordFieldText = generateField(passwordField);

        usernameFieldText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) UIService.handleLogin();
        });

        GridPane loginFields = new GridPane();
        loginFields.getChildren().addAll(usernameLabel, usernameFieldText, passwordLabel, passwordFieldText);
        setConstraints(usernameLabel, 0, 0);
        setConstraints(usernameFieldText, 1, 0);
        setConstraints(passwordLabel, 0, 1);
        setConstraints(passwordFieldText, 1, 1);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(70);
        loginFields.getColumnConstraints().add(col1);

        loginFields.setHgap(20);

        Label loginTitle = new Label("Welcome to Pantry!");
        loginTitle.setFont(new Font("Arial Bold", 16));

        Label loginSubtitle = new Label("Login to set your preferences and more.");
        loginSubtitle.setFont(new Font("Arial", 16));

        Region spacer = new Region();
        spacer.setPrefHeight(40);


        Button loginButton = AppButton.circularButtonNoText("asset/icon/arrow.png");
        loginButton.setOnAction(action -> UIService.handleLogin());

        Region spacer2 = new Region();
        spacer2.setPrefHeight(30);

        Region spacerBottom = new Region();
        HBox.setHgrow(spacerBottom, Priority.ALWAYS);

        status.setFont(new Font("Arial Bold", 12));
        status.setTextFill(Color.DARKRED);

        HBox bottom = new HBox(spacerBottom, status, loginButton);
        bottom.setAlignment(Pos.CENTER_LEFT);
        bottom.setSpacing(10);

        VBox center = new VBox(loginTitle, loginSubtitle, spacer, loginFields, spacer2, bottom);

        center.setAlignment(Pos.CENTER_LEFT);
        center.setMaxWidth(300);

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
        textFieldHolder.setPrefWidth(600);
        textFieldHolder.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) UIService.handleLogin();
        });

        return textFieldHolder;
    }

    public static String getUsername() {
        return usernameField.getText();
    }

    public static String getPassword() {
        return passwordField.getText();
    }

    public static void setStatus(String newStatus) {
        status.setText(newStatus);
    }

    public static LoginPage getInstance() {
        if (instance == null) instance = new LoginPage();
        return instance;
    }
}