package sample.controller;

import sample.Game;
import sample.model.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import sample.view.Map;

public class StartButtonListener implements EventHandler<MouseEvent>, ChangeMapObserver {
    private Map map = Map.getInstance();
    private static int iteration = 1;

    public StartButtonListener(){
        map.addObserver(this);
    }

    @Override
    public void handle(MouseEvent event) {
        if (Wave.isReady()){
            map.feuRouge();

            //final File file = new File("C:\\Users\\Everyone\\IdeaProjects\\TowerDefenseJavaFX\\src\\sample\\0104.mp3");

            /*final Media media = new Media(file.toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();*/

            Timeline timer = new Timeline(new KeyFrame(Duration.millis(2300), event1 -> {
                new Wave(iteration);
                iteration++;
                Preparation.setPreparationNumber(iteration);

                Game.menuBar.setDisable(true);
            }));
            timer.setCycleCount(1);
            timer.play();

        }
    }

    public static void setIteration(int iteration) {
        StartButtonListener.iteration = iteration;
    }

    @Override
    public void changeMap() {
        Map.getInstance();
    }
}
