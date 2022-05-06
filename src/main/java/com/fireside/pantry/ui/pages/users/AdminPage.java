package com.fireside.pantry.ui.pages.users;

import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.pages.MealPlanningPage;
import com.fireside.pantry.ui.widgets.AppButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * The admin page which shows what admins can do
 */
public class AdminPage extends BorderPane {
    private static AdminPage instance;

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

        Label welcomeSubtitle = new Label("Approve and deny recipe requests here.");
        welcomeSubtitle.setFont(new Font("Arial", 15));

        VBox top = new VBox(welcomeLabel, welcomeSubtitle);
        top.setStyle("-fx-padding: 10");

        Button logoutButton = AppButton.rectangularButtonWithText(
                "Logout", "asset/icon/back-arrow.png", 5);
        logoutButton.setOnMouseClicked(action -> UIService.handleLogout());
        VBox bottom = new VBox(logoutButton);
        bottom.setStyle("-fx-padding: 10");

        TableView<String> center = new TableView<>();

        String[] columnTitles = {
                "RequestID",
                "UserID",
                "Title",
                "Region",
                "Category",
                "Image URL",
                "Youtube URl",
                "Instructions",
                "Ingredients",
                "Actions"
        };

        //loop all in
        for (int i = 0; i < 10; i ++) {
            TableColumn<String, String> column = new TableColumn<>(columnTitles[i]);
            column.setMinWidth(100);
            column.setStyle(
                    "-fx-border-style: solid outside;" +
                            "-fx-padding: 5;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-insets: 3.5;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: black;" +
                            "-fx-background-color: white;"
            );

            center.getColumns().add(column);
        }

        center.setStyle(
                "-fx-border-style: solid outside;" + "-fx-border-insets: 5;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 5;" +
                        "-fx-border-color: black;" +
                        "-fx-background-color: white");

        BorderPane pane = new BorderPane();
        pane.setTop     (top);
        pane.setBottom  (bottom);
        pane.setCenter  (center);
        pane.setStyle("-fx-padding: 30;" +
                "-fx-background-color: white;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent");

        return pane;
    }

    /**
     * A private class which handles requests
     */
    private static class Requests {
        private final SimpleStringProperty requestID;
        private final SimpleStringProperty userID;
        private final SimpleStringProperty title;
        private final SimpleStringProperty region;
        private final SimpleStringProperty category;
        private final SimpleStringProperty imageURL;
        private final SimpleStringProperty youtubeURL;
        private final SimpleStringProperty instructions;
        private final SimpleStringProperty ingredients;

        public Requests(SimpleStringProperty requestID, SimpleStringProperty userID, SimpleStringProperty title, SimpleStringProperty region, SimpleStringProperty category, SimpleStringProperty imageURL, SimpleStringProperty youtubeURL, SimpleStringProperty instructions, SimpleStringProperty ingredients) {
            this.requestID = requestID;
            this.userID = userID;
            this.title = title;
            this.region = region;
            this.category = category;
            this.imageURL = imageURL;
            this.youtubeURL = youtubeURL;
            this.instructions = instructions;
            this.ingredients = ingredients;
        }

        public String getRequestID() {
            return requestID.get();
        }

        public SimpleStringProperty requestIDProperty() {
            return requestID;
        }

        public void setRequestID(String requestID) {
            this.requestID.set(requestID);
        }

        public String getUserID() {
            return userID.get();
        }

        public SimpleStringProperty userIDProperty() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID.set(userID);
        }

        public String getTitle() {
            return title.get();
        }

        public SimpleStringProperty titleProperty() {
            return title;
        }

        public void setTitle(String title) {
            this.title.set(title);
        }

        public String getRegion() {
            return region.get();
        }

        public SimpleStringProperty regionProperty() {
            return region;
        }

        public void setRegion(String region) {
            this.region.set(region);
        }

        public String getCategory() {
            return category.get();
        }

        public SimpleStringProperty categoryProperty() {
            return category;
        }

        public void setCategory(String category) {
            this.category.set(category);
        }

        public String getImageURL() {
            return imageURL.get();
        }

        public SimpleStringProperty imageURLProperty() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL.set(imageURL);
        }

        public String getYoutubeURL() {
            return youtubeURL.get();
        }

        public SimpleStringProperty youtubeURLProperty() {
            return youtubeURL;
        }

        public void setYoutubeURL(String youtubeURL) {
            this.youtubeURL.set(youtubeURL);
        }

        public String getInstructions() {
            return instructions.get();
        }

        public SimpleStringProperty instructionsProperty() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions.set(instructions);
        }

        public String getIngredients() {
            return ingredients.get();
        }

        public SimpleStringProperty ingredientsProperty() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients.set(ingredients);
        }
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