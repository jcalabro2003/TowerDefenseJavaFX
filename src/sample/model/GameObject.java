package sample.model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class GameObject {


    protected Map map = Map.getInstance();
    protected double posX;
    protected double posY;
    protected ImageView imageView;

    public GameObject(){
        map.addGameObject(this);
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
