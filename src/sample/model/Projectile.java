package sample.model;


import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Projectile extends GameObject implements Movable, Stop, Runnable{

    private Tower tower;
    private PNJ target;
    private int velocity = 5;
    private int damage;
    private ArrayList<StoppedObserver> observers = new ArrayList<>();
    private Thread t;
    private int sleeptime = 50;
    private static final Object myKey = new Object();
    private double theta;


    public Projectile(PNJ target, ImageView imageView, Tower tower){
        super();
        synchronized (myKey){
            addObserver(map);
            System.out.println("Ctrl Z");
            this.target = target;
            this.damage = tower.getDamage();
            posX = tower.getPosX();
            posY = tower.getPosY();
            this.imageView = imageView;
            map.addObjectToMap(this);
            t = new Thread(this);
            t.start();
        }
    }



    @Override
    public void move(Point p) {
        theta = Math.atan2(target.getPosY() - this.getPosY(), target.getPosX() - this.getPosX());                       //calcul l'angle entre la droite qui relie le projectile et le pnj et l'axe des x
        this.setPosX( (int) (this.posX + velocity * Math.cos(theta) ));                                                 //déplacement du projectile vers le pnj
        this.setPosY( (int) (this.posY + velocity * Math.sin(theta) ));
    }

    @Override
    public void update() {
        if(imageView != null){
            imageView.setY(posY);
            imageView.setX(posX);
            imageView.setRotate(Math.toDegrees(theta) + 180);
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

    public double getDistance(@NotNull GameObject object) {
        return Math.sqrt(Math.pow(object.getPosX() - this.posX, 2) + Math.pow(object.getPosY() - this.posY, 2));
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void run(){
        while (getDistance(target) > 10 && target.isAlive()){
            move(null);
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyObserver();
        if(target.isAlive()){
            target.receiveDamage(this);
        }
    }
}