package sample.view;

import sample.model.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class StartButtonListener implements EventHandler<MouseEvent> {
    private Map pane;

    public StartButtonListener(Map pane){
        this.pane = pane;
    }

    @Override
    public void handle(MouseEvent event) {
        pane.initWaves();
    }
}
