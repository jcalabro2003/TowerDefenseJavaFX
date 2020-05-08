package sample.model;
//
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Projectile extends GameObject implements Movable, Stop, Runnable{

    private PNJ target;
    private int velocity;
    private int effect;
    private ArrayList<StoppedObserver> observers = new ArrayList<>();
    private Thread t;
    private int sleeptime = 50;
    private static final Object myKey = new Object();
    private static final Object myKey2 = new Object();
    private double theta;
    private String type;


    public Projectile(int velocity, PNJ target, ImageView imageView, Tower tower){
        super();
        synchronized (myKey){
            addObserver(map);
            this.target = target;
            this.effect = tower.getEffect();
            this.velocity = velocity;
            posX = tower.getPosX();
            posY = tower.getPosY();
            type = tower.getType();
            this.imageView = imageView;
            imageView.setY(posY);
            imageView.setX(posX);
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
            imageView.setRotate(Math.toDegrees(theta) + 152);
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

    public int getEffect() {
        return effect;
    }



    @Override
    public void run() {
        while (getDistance(target) > 10 && target.isAlive()) {
            move(null);
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyObserver();
        if (target != null && target.isAlive()) {
            if (type.equals("basic")) {
                target.receiveDamage(this.effect);
            } else if (type.equals("slow")) {
                try {
                    target.setSpeed(effect);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        map.getChildren().removeAll(imageView);
    }
}