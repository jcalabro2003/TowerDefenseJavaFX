package sample.controller;

import sample.Game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CancelListener  implements EventHandler<MouseEvent> {
    private int iteration = 0;

    @Override
    public void handle(MouseEvent event) {
        if (iteration == 0){
            RectTowersListener.setBombReady(true);
            iteration++;
        }

        RectTowersListener.setSlowReady(false);
        RectTowersListener.setClassicReady(false);
        RectTowersListener.setUpgradeReady(false);

        Game.menuBar.setDisable(true);
    }

}
