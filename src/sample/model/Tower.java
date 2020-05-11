package sample.model;
//
import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.view.LoadingImage;


public class Tower extends Building implements  Upgradable, StoppedObserver {

    private int effect;
    private int range;
    private int reloading;
    private static ArrayList<PNJ> pnjs = new ArrayList<>();
    private int level = 0;
    private static final Object mykey = new Object();
    private String type;
    private ImageView gif;

    public Tower(int effect, int range, int reloading,ImageView imageView, double x, double y, String type, ImageView gif) {
        super();
        this.effect = effect;
        this.type = type;
        this.range = range;
        this.reloading = reloading;
        this.imageView = imageView;
        this.gif = gif;
        gif.setVisible(false);
        map.getChildren().add(gif);
        posX = x;
        posY = y;
        imageView.setX(posX);
        imageView.setY(posY);
        gif.setX(posX);
        gif.setY(posY);



        Timeline timer = new Timeline(new KeyFrame(Duration.millis(reloading), event -> {
            gif.setVisible(false);
            imageView.setVisible(true);
            if (pnjs.size() > 0) {
                shoot();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }

    public static void addPnj(PNJ pnj){
        pnjs.add(pnj);
    }

    public static ArrayList<PNJ> getPnjs() {
        return pnjs;
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
            if(pnjs.get(i) != null){
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
        }
        return res;
    }

    public void shoot(){
        synchronized (mykey){
            PNJ target = getNearest();
            if (target != null && isInRange(target)){
                if (type.equals("basic")) {
                    ProjectileFactory.getInstance("basic", target, this);
                }else if(type.equals("slow")){
                    imageView.setVisible(false);
                    gif.setVisible(true);
                    ProjectileFactory.getInstance("slow", target, this);
                }
            }
        }
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void upgrade() {
        ImageView up = LoadingImage.loadImage("upgrade.png", 10, 10);
        if (level == 0){
            level++;
            effect += effect /2;
            reloading -= reloading/2;
            up.setX(posX + 5);
        }
        else if(level == 1){
            level++;
            reloading -= reloading/2;
            up.setX(posX + 17);
        }
        else {
            level++;
            range += range/3;
            up.setX(posX + 29);
        }
        up.setY(posY-5);
        map.getChildren().add(up);
    }

    @Override
    public void react(GameObject o) {
        pnjs.remove(o);
    }

    public int getEffect() {
        return effect;
    }
}