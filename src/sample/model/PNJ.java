package sample.model;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;
import sample.InfoPane;

import java.util.ArrayList;
import java.util.Random;

public class PNJ extends GameObject implements Movable, Runnable, Stop{

    private int health;
    private int speed;
    private boolean alive = true;
    private Path path;
    private int pathNumber;
    private Thread t;
    private static final Object myKey = new Object();
    private static int sleepTime = 50;
    private static int maxSleepTime = 50;
    private ArrayList<StoppedObserver> observers = new ArrayList<>();
    private Player player = Player.getInstance();
    private InfoPane infoPane = InfoPane.getInstance();
    private static final Object mykey2 = new Object();



    public PNJ(int health, int speed, ImageView imageView){
        super();
        addObserver(map);
        addObserver(player);
        addObserver(infoPane);
        addTowers();
        this.health = health;
        this.speed = speed;
        this.imageView = imageView;
        map.addObjectToMap(this);
        //pathNumber = new Random().nextInt(map.getPaths().size()-1);
        //path = map.getPaths().get(pathNumber);
        path = map.getPaths().get(0);
        posX = new Random().nextInt(400) -400;
        posY = new Random().nextInt(50) +45;
        Tower.addPnj(this);
        t = new Thread(this);
        t.start();

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


    public  ArrayList<StoppedObserver> getObservers() {
        return observers;
    }

    public double getDistance(@NotNull GameObject object) {
        return Math.sqrt(Math.pow(object.getPosX() - this.posX, 2) + Math.pow(object.getPosY() - this.posY, 2));
    }
    public double getDistance(@NotNull Point p) {
        return Math.sqrt(Math.pow(p.getX() - this.posX, 2) + Math.pow(p.getY() - this.posY, 2));
    }


    @Override
    public void move(Point point) {
        synchronized (myKey) {
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
        synchronized (mykey2){
            for(StoppedObserver stoppedObserver : observers){
                stoppedObserver.react(this);
            }
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

    public void receiveDamage(@NotNull Projectile p){
        health -= p.getDamage();
        System.out.println("health: " + health);
        if (health <= 0){
            alive = false;
        }
        System.out.println(alive);
    }


    @Override
    public void run() {
        for(Point p : path.getPoints()) {
            while (getDistance(p) > 5 && alive){
                move(p);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        notifyObserver();
    }
}