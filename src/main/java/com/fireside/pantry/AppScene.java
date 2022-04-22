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
    private final UniversalMenu menu;
    private final BorderPane content;

    // |----- Constructors ----------

    private AppScene() {
        this.titleBar = TitleBar.getInstance();
        this.content = new BorderPane();
        this.menu = UniversalMenu.getInstance();
    }

    // |----- Methods ----------

    public Scene build() {
        BorderPane pane = new BorderPane();

        VBox top = new VBox(titleBar);
        Separator sep = new Separator();
        top.getChildren().add(sep);
        pane.setTop(top);

        content.setCenter(DatabasePage.getInstance().build());
        pane.setCenter(content);

        Pane root = new Pane();
        pane.prefHeightProperty().bind(root.heightProperty());
        pane.prefWidthProperty().bind(root.widthProperty());

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
