package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.model.Map;

import java.util.HashMap;

public class SlowListener implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setSlowReady(true);
        RectTowersListener.setClassicReady(false);
        RectTowersListener.setUpgradeReady(false);
    }
}
