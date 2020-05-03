package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Map extends Pane {

    private static Map instance = null;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<PNJ> pnjs = new ArrayList<>();
    private ArrayList<Path> paths = new ArrayList<>();

    private Map(){
        super();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(250, 100));
        points.add(new Point(250, 50));
        points.add(new Point(500, 50));
        points.add(new Point(550, 200));
        points.add(new Point(700, 300));
        points.add(new Point(950, 300));
        Path path1 = new Path(points);
        paths.add(path1);

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

    public static Map getInstance(){
        if (Map.instance == null){
            Map.instance = new Map();
        }
        return Map.instance;
    }

    public void addGameObject(GameObject g){
        gameObjects.add(g);
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }


    public void initWaves() {
        int compt = 0;
        while (compt < Settings.PNJ_NUMBER_FIRST_WAVE) {
            PNJ pnj = PNJFactory.getInstance("basic");
            pnjs.add(pnj);
            this.addPnjToMap(pnj);
            compt++;
        }
    }

    private void addPnjToMap(PNJ pnj) {
        this.getChildren().add(pnj.getImageView());
    }
}
