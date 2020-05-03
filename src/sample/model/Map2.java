package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Map2 extends Pane {
    ArrayList<PNJ> pnjs;
    private static Map2 instance = null;

    private Map2() {
        super();
        pnjs = new ArrayList<PNJ>();
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for(int i=0; i<pnjs.size(); i++) {
                    pnjs.get(i).update();

                }

            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public static Map2 getInstance(){
        if (Map2.instance == null){
            Map2.instance = new Map2();
        }
        return Map2.instance;
    }



    private void addPnjToMap(PNJ pnj) {
        this.getChildren().add(pnj.getImageView());
    }
}
