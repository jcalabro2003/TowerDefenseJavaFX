package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import sample.view.StartButtonListener;

public class  Preparation {
    private static int duration = 60000;
    private static int maxDuration = 60000;
    private static int preparationNumber = 0;
    public int cycle = maxDuration/50;

    public Preparation() throws InterruptedException {

        Timeline timer = new Timeline(new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Wave.isReady()){
                    Wave wave =  new Wave(preparationNumber);
                    preparationNumber++;
                    StartButtonListener.setIteration(preparationNumber);
                }
            }
        }));
        timer.setCycleCount(1);
        timer.play();
    }
    public static int getPreparationNumber() {
        return preparationNumber;
    }

    public static void setPreparationNumber(int preparationNumber) {
        Preparation.preparationNumber = preparationNumber;
    }

    public static int getDuration() {
        return duration;
    }

    public static int getMaxDuration() {
        return maxDuration;
    }

    public static void setDuration(int duration) {
        Preparation.duration = duration;
    }
}
