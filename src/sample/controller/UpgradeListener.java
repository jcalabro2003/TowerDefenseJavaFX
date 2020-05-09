package sample.controller;

import sample.Game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class UpgradeListener  implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setUpgradeReady(true);
        RectTowersListener.setClassicReady(false);
        RectTowersListener.setSlowReady(false);
        RectTowersListener.setBombReady(false);

        Game.menuBar.setDisable(true);
    }
}
