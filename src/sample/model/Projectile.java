package sample.model;


import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Projectile extends GameObject implements Movable, Stop, Runnable{

    private Tower tower;
    private PNJ target;
    private int velocity = 5;
    private int damage;
    private static ArrayList<StoppedObserver> observers = new ArrayList<>();
    private Thread t;
    private int sleeptime = 50;


    public Projectile(PNJ target, int damage, ImageView imageView, Tower tower){
        super();
        this.target = target;
        this.damage = damage;
        posX = tower.getPosX();
        posY = tower.getPosY();
        this.imageView = imageView;
        map.addObjectToMap(this);
        t = new Thread(this);
        t.start();

    }

    public static ArrayList<StoppedObserver> getObservers() {
        return observers;
    }


    @Override
    public void move(Point p) {                                                                                         // x et y sont les coordonées du PNJ target
        double theta = Math.atan2(target.getPosY() - this.getPosY(), target.getPosX() - this.getPosX());                //calcul l'angle entre la droite qui relie le projectile et le pnj et l'axe des x
        this.setPosX( (int) (this.posX + velocity * Math.cos(theta) ));                                                 //déplacement du projectile vers le pnj
        this.setPosY( (int) (this.posY + velocity * Math.sin(theta) ));

    }

    @Override
    public void update() {
        imageView.setY(posY);
        imageView.setX(posX);
    }


    @Override
    public void notifyObserver() {
        for(StoppedObserver stoppedObserver : observers){
            stoppedObserver.react(this);
        }
    }

    public double getDistance(@NotNull GameObject object) {
        return Math.sqrt(Math.pow(object.getPosX() - this.posX, 2) + Math.pow(object.getPosY() - this.posY, 2));
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void run(){
        while (getDistance(target) > 10){
            move(null);
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyObserver();
        target.receiveDamage(this);
    }

}