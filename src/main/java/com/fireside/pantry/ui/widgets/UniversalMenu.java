package com.fireside.pantry.ui.widgets;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import com.jfoenix.controls.JFXHamburger;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class UniversalMenu implements Initializable {

    private JFXHamburger hamburger;

    private JFXDrawer drawer;

    public UniversalMenu(JFXHamburger hamburger, JFXDrawer drawer) {
        this.hamburger = hamburger;
        this.drawer = drawer;
    }

    /**
     * Allows the adjustment of the draw to be opened or closed alongside with the
     * hamburger changing
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }
}
