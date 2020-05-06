package sample.model;

import sample.Controller;
import sample.view.RectTowersListener;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Map extends Pane implements StoppedObserver {

    private static Map instance = null;
    private String typeMap;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Rectangle> rectPaths = new ArrayList<>();
    private ArrayList<Rectangle> rectTowers = new ArrayList<>();

    private Map(String typeMap) {
        super();

        this.typeMap = typeMap;
        ArrayList<Point> points = getPoints();
        Path path = new Path(points);
        paths.add(path);

        ImageView imgMap = getImgMap();
        this.getChildren().add(imgMap);

        createField(points);

        Timeline timer = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for (GameObject o : gameObjects) {
                    if (o instanceof Movable){
                        ((Movable) o).update();
                    }
                }

            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private boolean isPath(int x, int y, ArrayList<Point> points) {
        boolean isPath = false;

        switch (typeMap) {
            case ("map1") :
                if ((y == 50 && x >= 0 && x <= 225) || (x==200 && y>=50 && y<=150) || (y == 150 && x >= 200 && x <= 450)
                        || (x==450 && y>=50 && y<=150) || (y == 50 && x >= 450 && x <= 950) ) {
                    isPath = true;
                }
                break;
        }

        return isPath;
    }

    private ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();

        switch (typeMap) {
            case ("map1") :
                points.add(new Point(225, 75));
                points.add(new Point(225, 175));
                points.add(new Point(475, 175));
                points.add(new Point(475, 50));
                points.add(new Point(950, 50));
                break;
            default:
                break;
        }

        return points;
    }

    private ImageView getImgMap() {
        ImageView img;

        switch (typeMap) {
            case ("map1") :
                img = LoadingImage.loadImage("BackgroundRainobw.png",950,500);
                break;
            default:
                img = LoadingImage.loadImage("BackgroundRainobw.png",950,500);
        }

        return  img;
    }

    public void feuRouge(){
        ImageView im = LoadingImage.loadImage("feuFouge.gif", 100, 100);
        getChildren().add(im);
        Timeline chrono = new Timeline(new KeyFrame(Duration.millis(2300), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                im.setVisible(false);
            }
        }));
        chrono.setCycleCount(1);
        chrono.play();
    }

    private void createField(ArrayList<Point> points) {
        int nbCol = 19;
        int nbLine = 10;

        rectPaths.clear();
        rectTowers.clear();

        int x;
        int y = 0;
        for (int i=0; i < nbLine; i++) {
            x = 0;
            for (int j = 0; j < nbCol; j++) {
                Rectangle rectangle = new Rectangle(x, y, 50, 50);
                this.getChildren().add(rectangle);

                if (isPath(x, y , points)) {
                    rectPaths.add(rectangle);

                    ImageView imgRectPath = LoadingImage.loadImage("Rainbow.png",50,50);
                    imgRectPath.setOpacity(0.8);
                    imgRectPath.setX(rectangle.getX());
                    imgRectPath.setY(rectangle.getY());
                    getChildren().add(imgRectPath);
                }
                else {
                    rectTowers.add(rectangle);

                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setOnMouseClicked(new RectTowersListener(rectangle,this));
                }

                x = x + 50;
            }
            y = y + 50;
        }

        ImageView imgArrive = LoadingImage.loadImage("Arrivé.png",25,50);
        imgArrive.setX(915);
        imgArrive.setY(50);
        getChildren().add(imgArrive);

    }

    public static Map getInstance() {
        if (Map.instance == null) {
            Map.instance = new Map(Controller.getTypeMapGlobal());
        }
        return Map.instance;
    }

    public static Map setInstance() {
        Map.instance = null;
        Map.instance = Map.getInstance();

        return Map.instance;
    }

    public void addGameObject(GameObject g) {
        gameObjects.add(g);
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

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public boolean isOccupied(double x, double y){
        boolean res = false;
        for (GameObject o: gameObjects) {
            if(o instanceof Building && x == o.getPosX() && y == o.getPosY()) {
                res = true;
                System.out.println("occupé");
                System.out.println(gameObjects.size());
            }
        }
        return res;
    }

    public void addObjectToMap(GameObject gameObject) {
        this.getChildren().add(gameObject.getImageView());
    }

    @Override
    public void react(GameObject o) {
        synchronized (Stop.getKey()){
            gameObjects.remove(o);
            o.getImageView().setVisible(false);
            o.setImageView(null);
            if(o instanceof PNJ){
                ((PNJ) o).getRotateImage().setVisible(false);
                ((PNJ) o).setRotateImage(null);
            }
            o = null;
        }
    }
}