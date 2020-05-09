package sample.model;
//
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.controller.StartButtonListener;

public class  Preparation {
    private static int duration = 10000;
    private static int maxDuration = 10000;
    private static int preparationNumber = 0;


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
    public static void prepare(int time){
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(time), event -> {
            if (Wave.isReady()) {
                new Wave(preparationNumber);
                preparationNumber++;
                StartButtonListener.setIteration(preparationNumber);
            }
        }));
        timer.play();
    }
}
