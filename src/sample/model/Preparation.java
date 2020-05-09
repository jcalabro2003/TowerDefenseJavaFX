package sample.model;
//
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.controller.StartButtonListener;
import sample.view.InfoPane;
import sample.view.LoadingImage;
import sample.view.Map;

public class  Preparation {
    private static int duration = 10;
    private static int maxDuration = 10;
    private static int preparationNumber = 0;
    private static int time = 10;
    private static int maxTime = 10;


    public static void setPreparationNumber(int preparationNumber) {
        Preparation.preparationNumber = preparationNumber;
    }

    public static int getDuration() {
        return duration;
    }

    public static int getMaxDuration() {
        return maxDuration;
    }

    public static int getMaxTime() {
        return maxTime;
    }

    public static void setDuration(int duration) {
        Preparation.duration = duration;
    }
    public static void prepare(){
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(maxTime), event -> {
            if (Wave.isReady() && Player.getHealthPoints() >= 0) {
                new Wave(preparationNumber);
                preparationNumber++;
                StartButtonListener.setIteration(preparationNumber);
            }else{
                ImageView gameover = LoadingImage.loadImage("gameover.png",950,500);
                Map.getInstance().getChildren().add(gameover);
            }

        }));
        timer.play();
        countdown();
    }
    public static void countdown(){
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), event ->{
            time -= 1;
            InfoPane.countdown(time);
        }));
        timer.setCycleCount(maxTime -1);
        timer.play();
        maxTime -= 1;
        time = maxTime;
    }
}
