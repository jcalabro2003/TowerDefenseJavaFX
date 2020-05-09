package sample.controller;

import sample.Game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClassicListener implements EventHandler <MouseEvent> {


    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setClassicReady(true);
        RectTowersListener.setSlowReady(false);
        RectTowersListener.setUpgradeReady(false);
        RectTowersListener.setBombReady(false);

        Game.menuBar.setDisable(true);
    }
}
