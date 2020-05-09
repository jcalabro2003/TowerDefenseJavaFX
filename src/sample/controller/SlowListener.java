package sample.controller;

import sample.Game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SlowListener implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setSlowReady(true);
        RectTowersListener.setClassicReady(false);
        RectTowersListener.setUpgradeReady(false);
        RectTowersListener.setBombReady(false);

        Game.menuBar.setDisable(true);
    }
}
