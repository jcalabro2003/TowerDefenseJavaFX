package sample.model;

import sample.view.InfoPane;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;


public class PNJ extends GameObject implements Movable, Runnable, Stop{

    private int health;
    private int maxHealth;
    private double speed;
    private double maxSpeed;
    private boolean alive = true;
    private Path path;
    private int pathNumber;
    private Thread t;
    private static int sleepTime = 50;
    private static int maxSleepTime = 50;
    private ArrayList<StoppedObserver> observers = new ArrayList<>();
    private Player player = Player.getInstance();
    private InfoPane infoPane = InfoPane.getInstance();
    private static final Object myKey = new Object();
    private static final Object myKey2 = new Object();
    private static final Object myKey3 = new Object();
    private static final Object myKey4 = new Object();
    private ImageView rotateImage;


    public PNJ(int health, int speed, ImageView imageView, ImageView rotateImage){
        super();
        synchronized (myKey){
            addObserver(map);
            addObserver(player);
            addObserver(infoPane);
            addTowers();
            this.health = health;
            maxHealth = health;
            this.speed = speed;
            maxSpeed = speed;
            pathNumber = new Random().nextInt(map.getPaths().size());
            path = map.getPaths().get(pathNumber);
            posX = new Random().nextInt(1000) -1000;
            posY = 75;
            this.imageView = imageView;
            imageView.setY(posY);
            imageView.setX(posX);
            map.addObjectToMap(this);
            this.rotateImage = rotateImage;
            rotateImage.setVisible(false);
            map.getChildren().add(rotateImage);
            Tower.addPnj(this);
            t = new Thread(this);
            t.start();
        }
    }
    public void setSpeed(double speed) throws InterruptedException {
        synchronized (myKey2){
            this.speed = speed;
            if (imageView != null) imageView.setVisible(false);
            rotate();
        }
        Thread.sleep(2000);
        synchronized (myKey2){
            if (imageView != null){
                rotateImage.setVisible(false);
                this.speed = maxSpeed;
                imageView.setVisible(true);
            }
        }
    }
    public boolean isAlive() {
        return alive;
    }

    public static void setSleepTime(int sleepTime) {
        PNJ.sleepTime = sleepTime;
    }

    public static int getSleepTime() {
        return sleepTime;
    }

    public static int getMaxSleepTime() {
        return maxSleepTime;
    }

    public ImageView getRotateImage() {
        return rotateImage;
    }

    public void setRotateImage(ImageView rotateImage) {
        this.rotateImage = rotateImage;
    }

    public double getDistance(@NotNull GameObject object) {
        return Math.sqrt(Math.pow(object.getPosX() - this.posX, 2) + Math.pow(object.getPosY() - this.posY, 2));
    }
    public double getDistance(@NotNull Point p) {
        return Math.sqrt(Math.pow(p.getX() - this.posX, 2) + Math.pow(p.getY() - this.posY, 2));
    }


    @Override
    public void move(Point point) {
        synchronized (myKey3){
            double theta = Math.atan2(point.getY() - this.getPosY(), point.getX() - this.getPosX());
            this.setPosX( (this.posX + speed * Math.cos(theta)));
            this.setPosY( (this.posY + speed * Math.sin(theta)));
        }
    }

    @Override
    public void update() {
        if(imageView != null){
            imageView.setY(posY);
            imageView.setX(posX);
        }
    }


    @Override
    public void notifyObserver() {
        for(StoppedObserver stoppedObserver : observers){
            stoppedObserver.react(this);
        }
    }

    @Override
    public void addObserver(StoppedObserver o) {
        observers.add(o);
    }
    public void addTowers(){
        for(GameObject go : map.getGameObjects()){
            if(go instanceof Tower){
                addObserver((Tower) go);
            }
        }
    }

    public void receiveDamage(int damage){
        synchronized (myKey4){
            health -= damage;
            if (health <= 0) {
                alive = false;
            }
        }
    }


    @Override
    public void run() {
        for(Point p : path.getPoints()) {
            if(posY  > p.getY()) {
                imageView.setRotate(-90);
                rotateImage.setRotate(-90);
            }
            else if(posY  < p.getY()){
                imageView.setRotate(90);
                rotateImage.setRotate(90);
            }
            else if (posX  < p.getX()){
                imageView.setRotate(0);
                rotateImage.setRotate(0);
            }
            while (getDistance(p) > 5 && alive){
                move(p);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            posX = p.getX();
            posY = p.getY();
        }
        notifyObserver();
        map.getChildren().removeAll(imageView, rotateImage);
    }

    public void rotate() {
        if (rotateImage != null){
            rotateImage.setX(posX);
            rotateImage.setY(posY);
            rotateImage.setVisible(true);
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}