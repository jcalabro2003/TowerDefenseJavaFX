package sample.view;

import sample.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClassicListener implements EventHandler <MouseEvent> {


    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setClassicReady(true);
        RectTowersListener.setSlowReady(false);
        RectTowersListener.setUpgradeReady(false);
        RectTowersListener.setBombReady(false);

        Controller.changeMapButton.setDisable(true);
    }
}
