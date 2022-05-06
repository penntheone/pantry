package com.fireside.pantry.ui.pages.users;

import com.fireside.pantry.app.control.RequestController;
import com.fireside.pantry.app.model.RecipeRequests;
import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 * The admin page which shows what admins can do
 */
public class AdminPage extends BorderPane {
    private static AdminPage instance;
    private static TableView<RecipeRequests> table;
    private static ObservableList<RecipeRequests> data;

    /**
     * Private constructor
     */
    private AdminPage() {

    }

    /**
     * Builds the page
     * @return The page itself visually
     */
    public BorderPane build() {
//        String firstName = Session.getInstance().getAuthorizedUser().getFirstName();
        Label welcomeLabel = new Label("All hail the Pantry Admin!");
        welcomeLabel.setFont(new Font("Arial Bold", 25));

        Label welcomeSubtitle = new Label("Approve and deny recipe requests here. Highlight the recipe, then press the button.");
        welcomeSubtitle.setFont(new Font("Arial", 15));

        VBox top = new VBox(welcomeLabel, welcomeSubtitle);
        top.setStyle("-fx-padding: 10");

        Button logoutButton = AppButton.rectangularButtonWithText(
                "Logout", "asset/icon/back-arrow.png", 5);
        logoutButton.setOnMouseClicked(action -> UIService.handleLogout());
        VBox bottom = new VBox(logoutButton);
        bottom.setStyle("-fx-padding: 10");

        table = new TableView();

        String[] columnTitles = {
                "RequestID",
                "UserID",
                "Title",
                "Region",
                "Category",
                "Image URL",
                "Youtube URl",
                "Instructions",
        };

        String[] propertiesTitles = {
                "id",
                "user_id",
                "title",
                "region",
                "category",
                "image_url",
                "video_url",
                "instructions",
        };

        ObservableList<RecipeRequests> data = FXCollections.observableArrayList(RequestController.getActiveRequests());

        for (int i = 0; i < 8; i ++) {
            TableColumn<RecipeRequests, String> column = new TableColumn<RecipeRequests, String>(columnTitles[i]);
            column.setMinWidth(100);
            table.getColumns().add(column);

            column.setCellValueFactory(
                    new PropertyValueFactory<>(propertiesTitles[i]));
        }

        addButtonToTable();
        table.setItems(data);

        table.setStyle(
                "-fx-border-style: solid outside;" + "-fx-border-insets: 5;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 5;" +
                        "-fx-border-color: black;" +
                        "-fx-background-color: white");

        BorderPane pane = new BorderPane();
        pane.setTop     (top);
        pane.setBottom  (bottom);
        pane.setCenter  (table);
        pane.setStyle("-fx-padding: 30;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        return pane;
    }

    private void addButtonToTable() {
        TableColumn<RecipeRequests, Void> colBtn = new TableColumn("Actions");

        Callback<TableColumn<RecipeRequests, Void>, TableCell<RecipeRequests, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<RecipeRequests, Void> call(final TableColumn<RecipeRequests, Void> param) {
                final TableCell<RecipeRequests, Void> cell = new TableCell<>() {

                    private final HBox root = new HBox(); {
                        Button addButton = AppButton.circularButtonNoText("asset/icon/plus.png");
                        addButton.setOnAction(event -> {
                            RecipeRequests selectedItem = table.getSelectionModel().getSelectedItem();
                            table.getItems().remove(selectedItem);
                        });

                         Button removeButton = AppButton.circularButtonNoText("asset/icon/cross.png");
                        removeButton.setOnAction(event -> {
                            RecipeRequests selectedItem = table.getSelectionModel().getSelectedItem();
                            table.getItems().remove(selectedItem);
                        });


                        addButton.setStyle("-fx-background-color: transparent");
                        removeButton.setStyle("-fx-background-color: transparent");

                        root.getChildren().addAll(addButton, removeButton);
                        root.setAlignment(Pos.BASELINE_CENTER);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(root);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);
    }

    /**
     * Returns the object itself if created, will create if not
     * @return the object itself
     */
    public static AdminPage getInstance() {
        if (instance == null) instance = new AdminPage();
        return instance;
    }
}