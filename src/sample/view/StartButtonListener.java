package sample.view;

import sample.model.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class StartButtonListener implements EventHandler<MouseEvent> {
    private Map pane;
    private int iteration = 1;

    public StartButtonListener(Map pane){
        this.pane = pane;
    }

    @Override
    public void handle(MouseEvent event) {
        Wave wave = new Wave(iteration);
        iteration++;
    }
}
