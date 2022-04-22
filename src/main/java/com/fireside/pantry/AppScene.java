package com.fireside.pantry;

import com.fireside.pantry.ui.widgets.SearchBar;
import com.fireside.pantry.ui.widgets.TitleBar;
import com.fireside.pantry.ui.widgets.UniversalMenu;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import com.fireside.pantry.ui.pages.DatabasePage;

public class AppScene {

    private static AppScene instance;

    // |----- Constants ----------

    private static final double DATABASE_UI_WIDTH = 1000;
    private static final double DATABASE_UI_HEIGHT = 600;

    // |----- Components ----------

    private final TitleBar titleBar;
    private final BorderPane content;
    private final UniversalMenu menu;

    // |----- Constructors ----------

    private AppScene() {
        this.titleBar = new TitleBar("Database");
        this.content = new BorderPane();
        this.menu = UniversalMenu.getInstance();
    }

    // |----- Methods ----------

    public Scene build() {
        Pane root = new Pane();
        root.setPrefSize(400, 300);


        VBox top = new VBox(titleBar);
        Separator sep = new Separator();
        top.getChildren().add(sep);

        // Align menu view to left side
        // Align detail view to right side
        BorderPane pane = new BorderPane();
        pane.setTop(top);
        pane.setCenter(content);

        content.setCenter(DatabasePage.getInstance().build());

        root.getChildren().addAll(pane, menu);
        return new Scene(root);
    }

    // |----- Getters ----------

    public SearchBar getSearchBar() {
        return titleBar.getSearchBar();
    }

    // |----- Static Methods ----------

    public static AppScene getInstance() {
        if (AppScene.instance == null)
            AppScene.instance = new AppScene();
        return AppScene.instance;
    }
}
