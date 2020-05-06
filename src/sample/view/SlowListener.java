package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.model.Map;

import java.util.HashMap;

public class SlowListener implements EventHandler<MouseEvent> {

    private Map map;
    private HBox hBoxMessage;

    public SlowListener(Map map, HBox hBoxMessage) {
        this.map = map;
        this.hBoxMessage = hBoxMessage;
    }

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setSlowReady(true, hBoxMessage);
        RectTowersListener.setClassicReady(false, hBoxMessage);
    }
}
