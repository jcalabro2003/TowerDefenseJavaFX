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
    private static int preparationNumber = 0;
    private static int time = 10;
    private static int maxTime = 10;


    public static void setPreparationNumber(int preparationNumber) {
        Preparation.preparationNumber = preparationNumber;
    }

    public static int getTime() {
        return time;
    }

    public static int getMaxTime() {
        return maxTime;
    }


    public static void setTime(int time) {
        Preparation.time = time;
    }
    public static void prepare(){
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(maxTime), event -> {
            if (Player.getHealthPoints() >= 0){
                if (Wave.isReady() ) {
                    new Wave(preparationNumber);
                    preparationNumber++;
                    StartButtonListener.setIteration(preparationNumber);
                }
            } else{
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
