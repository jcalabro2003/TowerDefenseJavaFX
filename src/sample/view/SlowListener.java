package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import sample.model.Map;

public class SlowListener implements EventHandler<MouseEvent> {
    private Map map;

    public SlowListener(Map map){
        this.map = map;
    }

    @Override
    public void handle(MouseEvent event) {
    RectTowersListener.setSlowReady(true);
    RectTowersListener.setClassicReady(false);
    }
}
