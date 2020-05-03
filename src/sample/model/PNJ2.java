package sample.model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class PNJ2 implements Runnable {
    private Map2 map;
    private ImageView imageView;
    private Thread t;
    private static final Object myKey = new Object();

    public PNJ2(Map2 map) {
        this.map = map;
        imageView = LoadingImage.loadImage("pnj.png");
        imageView.setX(0);
        imageView.setY(100);
        t = new Thread(this);
        t.start();
    }

    public Node getImageView() {
        return imageView;
    }

    public void update() {
        imageView.setY(imageView.getY());
        imageView.setX(imageView.getX());


    }

    public void move() {
        synchronized (myKey){
            imageView.setX(imageView.getX() + 1);
        }
    }

    public void run() {
        while(true) {
            move();
            try {
                Thread.sleep(20);
            }
            catch(InterruptedException e) {
                System.out.println("probleme avec sleep");
            }
        }
    }
}
