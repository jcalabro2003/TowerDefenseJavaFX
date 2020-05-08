package sample.view;

import sample.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class UpgradeListener  implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setUpgradeReady(true);
        RectTowersListener.setClassicReady(false);
        RectTowersListener.setSlowReady(false);
        RectTowersListener.setBombReady(false);

        Controller.menuBar.setDisable(true);
    }
}
