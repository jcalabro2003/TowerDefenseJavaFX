package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.model.Map;

public class ClassicListener implements EventHandler <MouseEvent> {
    private Map map;

    public ClassicListener(Map map) {
        this.map = map;

    }

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setClassicReady(true);
        RectTowersListener.setSlowReady(false);
        RectTowersListener.setUpgradeReady(false);
    }
}
