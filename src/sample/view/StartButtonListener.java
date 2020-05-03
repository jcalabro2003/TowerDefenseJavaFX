package sample.view;

import sample.model.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class StartButtonListener implements EventHandler<MouseEvent> {
    private Map pane;
    private static int iteration = 1;

    public StartButtonListener(Map pane){
        this.pane = pane;
    }

    @Override
    public void handle(MouseEvent event) {
        if (Wave.isReady()){
            Wave wave = new Wave(iteration);
            iteration++;
            Preparation.setPreparationNumber(iteration);
        }
    }
    public int getIteration(){
        return iteration;
    }

    public static void setIteration(int iteration) {
        StartButtonListener.iteration = iteration;
    }
}
