package sample.model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class GameObject implements ChangeMapObserver{


    protected Map map = Map.getInstance();
    protected double posX;
    protected double posY;
    protected ImageView imageView;
    private static int nb = 0;

    public GameObject(){
        nb++;
        map.addObserver(this);
        map.addGameObject(this);
        System.out.println("objets créé " + nb);

    }

    @Override
    public void changeMap() {
        Map.getInstance();
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Node getImageView() {
        return imageView;
    }
    public void setImageView(ImageView newImage){
        imageView = newImage;
    }
}
