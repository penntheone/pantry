package com.fireside.pantry.ui.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class MealPlanningPage extends BorderPane {
    private static MealPlanningPage instance;
    //using StackPane in cell
    private GridPane leftSide = new GridPane();
    private GridPane table = new GridPane();
    private BorderPane pane = new BorderPane();

    //2d array show the grid pane
    /*
    [day/meal] [break] [lunch] [dinner] [Daily Calories]
    [mon]      []      []      [  ]      [cal: 0]
    [tue]
   [wed]
 [tur]
  [fir]
  [sat]
  [sun]

     */

    VBox[][] boxes = new VBox[8][5];


    private MealPlanningPage() {

    }

    public BorderPane build() {

        //left side
        this.leftSide = leftSideTable();

        //right side table
        this.table = rightSideTable();

        //borderpane
        BorderPane.setMargin(pane, new Insets(5, 5, 5, 5));

        pane.setStyle(
                "-fx-border-style: solid outside;" + "-fx-border-insets: 5;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 5;" +
                        "-fx-border-color: black;" +
                        "-fx-background-color: white;"
        );

        //pane.setCenter();
        pane.setLeft(leftSide);
        pane.setCenter(table);

        return pane;
    }

    //build left side
    public GridPane leftSideTable() {

        GridPane grid = new GridPane();

        grid.setStyle(
                "-fx-background-color: white;"
        );

        //left side boxes
        String[] labels = {"Days & Three Meals","Breakfast", "Lunch", "Dinner"};

        int times = 0;
        for (String i : labels) {
            boxes[0][times++] = labelAndBoxPackge(i, "","",null);
        }
        boxes[0][0].setPrefSize(210, 100);

        for(int i = 0; i<4; i++){
            grid.add(boxes[0][i], 0, i);
        }


        return grid;
    }

    //build right side
    public GridPane rightSideTable() {

        //gridpane
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: white;");

        //add days
        sevenDays();
        grid.add(boxes[1][0], 0, 0);
        for(int i =1; i < 7; i++) {
            grid.add(boxes[i+1][0], i, 0);
        }

        //default 1-7 days 3 meals
        for(int k = 0; k < 7;k++) {
            for (int i = 1; i < 4; i++) {

                boxes[k][i] = labelAndBoxPackge("", "", "", null);
                grid.add(boxes[k][i],k,i);
            }
        }

        return grid;

    }

    //set top table
    private void sevenDays() {

        String[] labels = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        int times = 1;
        for (String i : labels) {
            VBox loopBox = labelAndBoxPackge(i, "","",null);
            loopBox.setPrefSize(210, 100);
            boxes[times++][0] = loopBox;
        }
    }

    /*the method to build boxes
    send label name, top part, bottom, and image.

    if it is ("", "" ,"" , null)
    it will return the default one.

    if forLabel != null or "", it will creat a Vbox with the label in the middle.
    */
    private VBox labelAndBoxPackge(String forLabel, String top, String bottom, ImageView ima) {

        VBox returnBox = new VBox();

        if (forLabel.equals("") && top.equals("") && bottom.equals("") && ima == null) {

            Label labelFord = new Label(top);
            Label Calories = new Label("Calories");

            Calories.setFont(new Font("Arial", 15));
            labelFord.setFont(new Font("Arial", 15));

            VBox dtm = new VBox(8);
            dtm.setPadding(new Insets(10, 10, 10, 10));
            dtm.setPrefSize(210, 300);

            dtm.setStyle(
                    "-fx-border-style: solid outside;" + "-fx-border-insets: 5;" +
                            "-fx-padding: 5;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: black;" +
                            "-fx-background-color: white;"
            );

            VBox.setVgrow(dtm, Priority.ALWAYS);
            VBox.setVgrow(labelFord, Priority.ALWAYS);
            dtm.setAlignment(Pos.CENTER);

            labelFord.setMaxWidth(Double.MAX_VALUE);
            labelFord.setMaxHeight(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(labelFord, 0.0);
            AnchorPane.setRightAnchor(labelFord, 0.0);
            labelFord.setAlignment(Pos.TOP_CENTER);

            Calories.setMaxWidth(Double.MAX_VALUE);
            Calories.setMaxHeight(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(Calories, 0.0);
            AnchorPane.setRightAnchor(Calories, 0.0);
            Calories.setAlignment(Pos.BOTTOM_CENTER);

            ImageView dish = new ImageView(new Image("asset/icon/pngegg.png"));
            dish.setFitHeight(60);
            dish.setFitWidth(120);

            VBox.setVgrow(dish, Priority.ALWAYS);

            dtm.getChildren().addAll(labelFord, dish, Calories);
            returnBox = dtm;

        }

        else if(forLabel != null || !forLabel.equals("")){
            Label i = new Label(forLabel);
            i.setFont(new Font("Arial", 20));
            VBox dtm = new VBox(8);
            dtm.setPadding(new Insets(10, 10, 10, 10));
            dtm.setPrefSize(210, 300);

            dtm.setStyle(
                    "-fx-border-style: solid outside;" + "-fx-border-insets: 5;" +
                            "-fx-padding: 5;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: black;" +
                            "-fx-background-color: white;"
            );

            VBox.setVgrow(dtm, Priority.ALWAYS);
            VBox.setVgrow(i, Priority.ALWAYS);

            dtm.getChildren().addAll(i);

            i.setMaxWidth(Double.MAX_VALUE);
            i.setMaxHeight(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(i, 0.0);
            AnchorPane.setRightAnchor(i, 0.0);
            i.setAlignment(Pos.CENTER);

            returnBox = dtm;

        }


        return returnBox;
    }


    public static MealPlanningPage getInstance() {
        if (instance == null) instance = new MealPlanningPage();
        return instance;
    }




}