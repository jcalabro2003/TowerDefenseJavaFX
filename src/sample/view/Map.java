package sample.view;

import sample.controller.RectTowersListener;
import sample.model.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Map extends Pane implements StoppedObserver, ChangeMap {

    private static Map instance = null;
    private static String typeMap;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Rectangle> rectPaths = new ArrayList<>();
    private ArrayList<Rectangle> rectTowers = new ArrayList<>();
    private ArrayList<ChangeMapObserver> observers = new ArrayList<>();
    private static ImageView imgMap;
    private int nbOfSpell = 0;

    private Map(String typeMap) {
        super();
        notifyObserver();
        Map.typeMap = typeMap;
        switch (typeMap) {
            case "map1" :
                paths.add(new Path(typeMap, "path1"));
                break;
            case "map2" :
                paths.add(new Path(typeMap, "path1"));
                paths.add(new Path(typeMap, "path2"));
                break;
        }

        imgMap = getImgMap();
        this.getChildren().add(imgMap);

        createField(paths);

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

    private void createField(ArrayList<Path> paths) {
        int nbCol = 19;
        int nbLine = 10;

        rectPaths.clear();
        rectTowers.clear();

        for (int k = 0; k < paths.size(); k++) {
            int x;
            int y = 0;
            Object key = new Object();
            for (int i=0; i < nbLine; i++) {
                x = 0;
                for (int j = 0; j < nbCol; j++) {
                    synchronized (key){
                        Rectangle rectangle = new Rectangle(x, y, 50, 50);
                        this.getChildren().add(rectangle);

                        rectTowers.add(rectangle);
                        rectangle.setFill(Color.TRANSPARENT);
                        rectangle.setOnMouseClicked(new RectTowersListener(rectangle,this));

                        if (paths.get(k).isPath(x, y)) {
                            rectPaths.add(rectangle);
                            System.out.println(1);
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
                        x = x + 50;
                    }
                }
                y = y + 50;
            }
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
        paths.clear();
        switch (typeMap) {
            case "map1" :
                paths.add(new Path(typeMap, "path1"));
                break;
            case "map2" :
                paths.add(new Path(typeMap, "path1"));
                paths.add(new Path(typeMap, "path2"));
                break;
        }
        this.getChildren().removeAll(imgMap);
        this.getChildren().add(getImgMap());
        Map.getInstance().createField(paths);
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
