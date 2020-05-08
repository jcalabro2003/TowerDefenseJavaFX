package sample.model;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

import javafx.util.Duration;




public class Spell extends GameObject implements Movable {
    private String type;
    private int velocity;
    private int damage;
    private boolean ready = true;
    private SpellCreator spellCreator;
    private int sleeptime = 50;
    private int range = 150;

    public Spell(int velocity, SpellCreator spellCreator, int damage) {
        super();
        this.velocity = velocity;
        this.spellCreator = spellCreator;
        this.type = spellCreator.getType();
        this.damage = damage;
        posX = spellCreator.getPosX();
        posY = spellCreator.getPosY();
        imageView = LoadingImage.loadImage("images/" + type + ".png", 25, 25);
        imageView.setVisible(false);
        map.getChildren().add(imageView);
    }

    @Override
    public void move(Point point) {
        double theta = Math.atan2(point.getY() - this.getPosY(), point.getX() - this.getPosX());
        this.setPosX((this.posX + velocity * Math.cos(theta)));
        this.setPosY((this.posY + velocity * Math.sin(theta)));
    }

    @Override
    public void update() {
        imageView.setY(posY);
        imageView.setX(posX);
        imageView.setRotate(imageView.getRotate() - 10);
    }
    public void applyDamage(Point point){
        for (PNJ pnj : Tower.getPnjs()){
            if (pnj.getDistance(point) <= range){
                pnj.receiveDamage(damage);
            }
        }
    }

    public void fire(Point point) {
        imageView.setVisible(true);
        ImageView boum = LoadingImage.loadImage("images/explosion.gif", range, range);
        Timeline chrono = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boum.setVisible(false);
                map.getChildren().removeAll(boum);
            }
        }));

        Timeline timer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.millis(sleeptime), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (Math.abs(posX - point.getX()) > 10 || Math.abs(posY - point.getY()) > 10){
                    move(point);
                    System.out.println("wow");
                } else{
                    timer.stop();
                    applyDamage(point);
                    imageView.setVisible(false);
                    boum.setX(posX - range/2);
                    boum.setY(posY - range + 30);
                    map.getChildren().add(boum);
                    posX = spellCreator.getPosX();
                    posY = spellCreator.getPosY();
                    chrono.play();
                }
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }
}
