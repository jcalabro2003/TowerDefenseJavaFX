package sample.model;

public abstract class GameObject {


    protected Map map = Map.getInstance(1080, 720);
    protected int posX;
    protected int posY;

    public GameObject(){
        map.addGameObject(this);
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
