package sample.model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PNJ extends GameObject implements Movable, Runnable{

    private int health;
    private int speed;
    private boolean alive = true;
    private Path path;
    private int pathNumber;
    private ImageView imageView;
    private Thread t;
    private static final Object myKey = new Object();
    private static int sleepTime;
    private static int maxSleepTime;



    public PNJ(int health, int speed, ImageView imageView){
        super();
        this.health = health;
        this.speed = speed;
        this.imageView = imageView;
        map.addPNJ(this);
        map.addPnjToMap(this);
        //pathNumber = new Random().nextInt(map.getPaths().size()-1);
        //path = map.getPaths().get(pathNumber);
        path = map.getPaths().get(0);
        posX = new Random().nextInt(100) -50;
        posY = new Random().nextInt(50) +75;
        maxSleepTime = 50;
        sleepTime = maxSleepTime;
        t = new Thread(this);
        t.start();

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

    public void update() {
        imageView.setY(posY);
        imageView.setX(posX);
    }

    public void receiveDamage(@NotNull Projectile p){
        health =- p.getDamage();
        if (health <= 0){
            alive = false;
        }
    }

    public Node getImageView() {
        return imageView;
    }

    @Override
    public void run() {
        for(Point p : path.getPoints()) {
            while (getDistance(p) > 20){
                move(p);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}