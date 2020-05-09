package sample.view;
//
import javafx.scene.input.MouseEvent;
import sample.controller.RectTowersListener;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.model.*;

import java.util.ArrayList;

public class Map extends Pane implements StoppedObserver, ChangeMap {

    private static Map instance = null;
    private static String typeMap;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Rectangle> rectPaths = new ArrayList<>();
    private ArrayList<Rectangle> rectTowers = new ArrayList<>();
    private ArrayList<ChangeMapObserver> observers = new ArrayList<>();
    private ArrayList<Point> points;
    private Path path;
    private static ImageView imgMap;
    private int nbOfSpell = 0;

    private Map(String typeMap) {
        super();
        notifyObserver();
        Map.typeMap = typeMap;
        points = getPoints();
        path = new Path(points);
        paths.add(path);

        imgMap = getImgMap();
        this.getChildren().add(imgMap);

        createField(points);

        Timeline timer = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            for (GameObject o : gameObjects) {
                if (o instanceof Movable){
                    ((Movable) o).update();
                }
            }

        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public  int getNbOfSpell() {
        return nbOfSpell;
    }

    public void setNbOfSpell(int nbOfSpell) {
        this.nbOfSpell = nbOfSpell;
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
            case ("map2") :
                if (y == 50)
                    isPath = true;
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
            case ("map2") :
                points.add(new Point(225, 75));
                points.add(new Point(950, 75));
                /*
                points.add(new Point(175, 75));
                points.add(new Point(175, 325));
                points.add(new Point(775, 325));
                points.add(new Point(775, 75));
                points.add(new Point(950, 50));
                points.add(new Point(325, 75));
                points.add(new Point(325, 175));
                points.add(new Point(325, 525));
                points.add(new Point(725, 525));
                points.add(new Point(725, 50));
                 */
                break;
            default:
                break;
        }

        return points;
    }

    private ImageView getImgMap() {
        ImageView img = null;

        switch (typeMap) {
            case ("map1") :
                img = LoadingImage.loadImage("BackgroundRainobw.png",950,500);
                break;
            case ("map2"):
                img = LoadingImage.loadImage("gazon.png",950,500);
                break;
            default: break;
        }

        return  img;
    }

    private ImageView getImgPath() {
        ImageView img = null;

        switch (typeMap) {
            case ("map1") :
                img = LoadingImage.loadImage("Rainbow.png",50,50);
                break;
            case ("map2"):
                img = LoadingImage.loadImage("path2.png",50,50);
                break;
            default: break;
        }

        return  img;
    }

    public void feuRouge(){
        ImageView im = LoadingImage.loadImage("feuFouge.gif", 100, 100);
        getChildren().add(im);
        Timeline chrono = new Timeline(new KeyFrame(Duration.millis(2300), event -> {
            im.setVisible(false);
            getChildren().removeAll(im);
        }));
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

                    ImageView imgRectPath = getImgPath();
                    imgRectPath.setOpacity(0.8);
                    imgRectPath.setX(rectangle.getX());
                    imgRectPath.setY(rectangle.getY());
                    getChildren().add(imgRectPath);
                    imgRectPath.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        for (GameObject g: gameObjects){
                            if (g instanceof Spell){
                                ((Spell) g).fire(new Point(event.getX(), event.getY()));
                                break;
                            }
                        }
                    });
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
            Map.instance = new Map(Map.getTypeMap());
        }
        return Map.instance;
    }

    public void setInstance(String typeMap) {
        setTypeMap(typeMap);
        points = getPoints();
        path.setPath(points);
        this.getChildren().removeAll(imgMap);
        this.getChildren().add(getImgMap());
        Map.getInstance().createField(points);
    }

    public static String getTypeMap() {
        return typeMap;
    }

    public static void setTypeMap(String typeMap) {
        Map.typeMap = typeMap;
    }

    public void addGameObject(GameObject g) {
        gameObjects.add(g);
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public boolean isOccupied(double x, double y){
        boolean res = false;
        for (GameObject o: gameObjects) {
            if (o instanceof Building && x == o.getPosX() && y == o.getPosY()) {
                res = true;
                break;
            }
        }
        return res;
    }

    public void addObjectToMap(GameObject gameObject) {
        getChildren().add(gameObject.getImageView());
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

    @Override
    public void notifyObserver() {
        for(ChangeMapObserver o : observers){
            o.changeMap();
        }
    }

    @Override
    public void addObserver(ChangeMapObserver o) {
        observers.add(o);
    }
}