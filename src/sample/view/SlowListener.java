package sample.view;

import sample.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SlowListener implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setSlowReady(true);
        RectTowersListener.setClassicReady(false);
        RectTowersListener.setUpgradeReady(false);

        Controller.changeMapButton.setDisable(true);
    }
}
