package sample.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import sample.model.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class StartButtonListener implements EventHandler<MouseEvent> {
    private Map map;
    private static int iteration = 1;

    public StartButtonListener(Map pane){
        this.map = Map.getInstance();
    }

    @Override
    public void handle(MouseEvent event) {
        if (Wave.isReady()){
            map.feuRouge();
            Timeline timer = new Timeline(new KeyFrame(Duration.millis(2300), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Wave wave = new Wave(iteration);
                    iteration++;
                    Preparation.setPreparationNumber(iteration);
                }
            }));
            timer.setCycleCount(1);
            timer.play();

        }
    }
    public int getIteration(){
        return iteration;
    }

    public static void setIteration(int iteration) {
        StartButtonListener.iteration = iteration;
    }
}
