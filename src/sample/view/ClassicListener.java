package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.model.Map;

public class ClassicListener implements EventHandler <MouseEvent> {
    private Map map;
    private HBox hBoxMessage;

    public ClassicListener(Map map, HBox hBoxMessage) {
        this.map = map;
        this.hBoxMessage = hBoxMessage;
    }

    @Override
    public void handle(MouseEvent event) {
        RectTowersListener.setClassicReady(true, hBoxMessage);
        RectTowersListener.setSlowReady(false, hBoxMessage);
    }
}
