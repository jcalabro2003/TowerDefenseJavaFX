package sample.model;

import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Tower extends Building implements  Upgradable, StoppedObserver {

    private int effect;
    private int range;
    private int reloading;
    private static ArrayList<PNJ> pnjs = new ArrayList<>();
    private int level;
    private static final Object mykey = new Object();
    private String type;

    public Tower(int effect, int range, int reloading,ImageView imageView, double x, double y, String type) {
        super();
        this.effect = effect;
        this.type = type;
        this.range = range;
        this.reloading = reloading;
        this.level = 1;
        this.imageView = imageView;
        posX = x;
        posY = y;
        imageView.setX(posX);
        imageView.setY(posY);


        Timeline timer = new Timeline(new KeyFrame(Duration.millis(reloading), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (pnjs.size() > 0){
                    shoot();
                }
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }

    public static void addPnj(PNJ pnj){
        pnjs.add(pnj);
    }

    public String getType() {
        return type;
    }

    public boolean isInRange(PNJ pnj){
        boolean res = false;
        if (pnj.getDistance(this) < range){
            res = true;
        }
        return res;
    }

    public PNJ getNearest(){
        PNJ res = null;
        double distance = 0;
        for(int i=0; i<pnjs.size(); i++){
            if (i == 0){
                distance = pnjs.get(i).getDistance(this);
                res = pnjs.get(i);
            } else {
                if (distance > pnjs.get(i).getDistance(this)){
                    distance = pnjs.get(i).getDistance(this);
                    res = pnjs.get(i);
                }
            }
        }
        System.out.println(res);
        return res;
    }

    public void shoot(){
        synchronized (mykey){
            PNJ target = getNearest();
            if (target != null && isInRange(target)){
                if (type.equals("basic")) {
                    ProjectileFactory.getInstance("basic", target, this);
                }else if(type.equals("slow")){
                    ProjectileFactory.getInstance("slow", target, this);
                }
            }
        }
    }


    @Override
    public void upgrade() {
        if (level == 1){
            level++;
            effect += effect /3;
        }
        else if(level == 2){
            level++;
            reloading -= reloading/3;
        }
        else {
            range += range/3;
        }
    }

    @Override
    public void react(GameObject o) {
        pnjs.remove(o);
    }

    public int getEffect() {
        return effect;
    }
}