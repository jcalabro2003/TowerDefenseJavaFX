package sample.model;

public abstract class GameObject {


    protected Map map = Map.getInstance();
    protected double posX;
    protected double posY;

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
}
