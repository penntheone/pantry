package com.fireside.pantry;

import com.fireside.pantry.service.UIService;
import com.fireside.pantry.ui.views.AddIngredientView;
import com.fireside.pantry.ui.views.AddRecipeView;
import com.fireside.pantry.ui.widgets.SearchBar;
import com.fireside.pantry.ui.widgets.TitleBar;
import com.fireside.pantry.ui.widgets.UniversalMenu;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * The main scene of the app, which includes the title bar on top,
 * the content of the page center, and a menu out of sight on the
 * left ready to be summoned. The title changed depending on the
 * current page.
 */
public class AppScene {

    private static AppScene instance;

    // ============================================================= Constants

    private static final double DATABASE_UI_WIDTH = 1000;
    private static final double DATABASE_UI_HEIGHT = 600;

    // ============================================================= Components

    private final TitleBar titleBar;
    private final UniversalMenu menu;
    private final BorderPane content;

    private final Pane root;
    private final StackPane stack;

    // ============================================================= Constructors

    private AppScene() {
        this.titleBar = TitleBar.getInstance();
        this.menu = UniversalMenu.getInstance();
        this.content = new BorderPane();
        this.root = new Pane();
        this.stack = new StackPane();
    }

    // ============================================================= Build method

    public Scene build() {
        // ---------------------- Visible
        BorderPane pane = new BorderPane();
        pane.prefHeightProperty().bind(root.heightProperty());
        pane.prefWidthProperty().bind(root.widthProperty());

        // ---------------------- Top: Title bar
        VBox top = new VBox(titleBar);
        Separator sep = new Separator();
        top.getChildren().add(sep);
        pane.setTop(top);

        // ---------------------- Center: Content
        UIService.handlePageSelection("Profile");
        pane.setCenter(content);

        // ---------------------- Menu styling
        menu.prefHeightProperty().bind(root.heightProperty());
        root.getChildren().addAll(pane, menu);

        AddIngredientView ingredientView = AddIngredientView.getInstance();
        ingredientView.minWidthProperty().bind(root.widthProperty());
        ingredientView.minHeightProperty().bind(root.heightProperty());

        AddRecipeView recipeView = AddRecipeView.getInstance();
        recipeView.minWidthProperty().bind(root.widthProperty());
        recipeView.minHeightProperty().bind(root.heightProperty());

        stack.getChildren().addAll(
                AddIngredientView.getInstance(),
                AddRecipeView.getInstance(),
                root);

        Scene result = new Scene(stack);
        result.setFill(Color.WHITE);

        return result;
    }

    // ============================================================= Getters / Setters

    public SearchBar getSearchBar() {
        return titleBar.getSearchBar();
    }

    public void setContent(Node node) {
        content.setCenter(node);
    }

    // ============================================================= Static instance access

    public static AppScene getInstance() {
        if (AppScene.instance == null)
            AppScene.instance = new AppScene();
        return AppScene.instance;
    }
}
