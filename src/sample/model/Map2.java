package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Map2 extends Pane {
    ArrayList<PNJ2> pnjs;

    public Map2() {
        super();
        pnjs = new ArrayList<PNJ2>();
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for(PNJ2 w: pnjs) {
                    w.update();
                }

            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void initWaves() {
        int compt = 0;
        while (compt < Settings.PNJ_NUMBER_FIRST_WAVE) {
            PNJ2 pnj = new PNJ2(this);
            pnjs.add(pnj);
            this.addPnjToMap(pnj);
            compt++;
        }
    }

    private void addPnjToMap(PNJ2 pnj) {
        this.getChildren().add(pnj.getImageView());
    }
}
