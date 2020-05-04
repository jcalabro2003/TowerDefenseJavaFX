package sample.model;

import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

public class Tower extends Building implements  Upgradable, StoppedObserver {

    private int damage;
    private int range;
    private int reloading;
    private static ArrayList<PNJ> pnjs = new ArrayList<>();
    private int level;

    public Tower(int damage, int range, int reloading, @NotNull ImageView imageView, double x, double y) {
        super();
        PNJ.getObservers().add(this);
        this.damage = damage;
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
                shoot();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }

    public static void addPnj(PNJ pnj){
        pnjs.add(pnj);
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
        return res;
    }

    public void shoot(){
        PNJ target = getNearest();
        if (target != null){
            ProjectileFactory.getInstance("basic", target, this);
        }
    }

    @Override
    public void upgrade() {
        if (level == 1){
            level++;
            damage += damage/3;
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
        System.out.println(pnjs.size());
        pnjs.remove(o);
        System.out.println(pnjs.size());
    }
}
