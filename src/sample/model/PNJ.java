package sample.model;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PNJ extends GameObject implements Movable {

    private int health;
    private int speed;
    private boolean alive = true;
    private Path path;
    private int pathNumber;


    public PNJ(int health, int speed){
        super();
        this.health = health;
        this.speed = speed;
        pathNumber = new Random().nextInt(map.getPaths().size() -1);
        path = map.getPaths().get(pathNumber);

    }

    public double getDistance(@NotNull GameObject object) {
        return Math.sqrt(Math.pow(object.getPosX() - this.posX, 2) + Math.pow(object.getPosY() - this.posY, 2));
    }


    @Override
    public void move(int x, int y) {
        double theta = Math.atan2(y - this.getPosY(), x - this.getPosX());              // angle entre la droite (PNJ - pointCible) et l'axe des x
        this.setPosX( (int) (this.posX + speed * Math.cos(theta) ));                    // TODO: pour appeler cette méthode, il faudra faire un for (Point point: path):
        this.setPosY( (int) (this.posY + speed * Math.sin(theta) ));                    // comme ça le pnj se déplacera par tronçons
    }                                                                                   // nous il pourra se déplacer aussi en diagonale

    public void receiveDamage(@NotNull Projectile p){
        health =- p.getDamage();
        if (health <= 0){
            alive = false;
        }
    }

}