package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Map extends Pane{

    private static Map instance = null;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<PNJ> pnjs = new ArrayList<>();
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Rectangle> rectPaths = new ArrayList<>();
    private ArrayList<Rectangle> rectTowers = new ArrayList<>();

    private Map() {
        super();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(225, 75));
        points.add(new Point(225, 175));
        points.add(new Point(475, 175));
        points.add(new Point(475, 50));
        points.add(new Point(950, 50));
        Path path1 = new Path(points);
        paths.add(path1);

        createField(points);

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

    private boolean isPath(int x, int y, ArrayList<Point> points) {
        boolean isPath = false;

        if ((y == 50 && x >= 0 && x <= 225) || (x==200 && y>=50 && y<=150) || (y == 150 && x >= 200 && x <= 450)
                || (x==450 && y>=50 && y<=150) || (y == 50 && x >= 450 && x <= 950) ) {
            isPath = true;
        }

        return isPath;
    }

    private void createField(ArrayList<Point> points) {
        int nbCol = 19;
        int nbLine = 10;

        int x;
        int y = 0;
        for (int i=0; i < nbLine; i++) {
            x = 0;
            for (int j=0; j < nbCol; j++) {
                Rectangle rectangle = new Rectangle(x, y, 50, 50);
                rectangle.setFill(Color.GRAY);
                this.getChildren().add(rectangle);
                if (isPath(x, y , points)) {
                    rectangle.setFill(Color.LIGHTGRAY);
                    rectPaths.add(rectangle);
                }
                else {
                    rectangle.setFill(Color.TRANSPARENT);
                    rectTowers.add(rectangle);
                }

                x = x + 50;
            }
            y = y + 50;
        }
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
    public void addPNJ(PNJ pnj){
        pnjs.add(pnj);
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public ArrayList<Rectangle> getRectPaths() {
        return rectPaths;
    }

    public ArrayList<Rectangle> getRectTowers() {
        return rectTowers;
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

    public void addPnjToMap(PNJ pnj) {
        this.getChildren().add(pnj.getImageView());
    }
}
