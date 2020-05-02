package sample.view;

import sample.model.Map2;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class StartButtonListener implements EventHandler<MouseEvent> {
    private Map2 pane;

    public StartButtonListener(Map2 pane){
        this.pane = pane;
    }

    @Override
    public void handle(MouseEvent event) {
        pane.initWaves();
    }
}
