package sample.model;
//
public abstract class Building extends GameObject {


    public Building(){
        super();
        Wave.getBuildings().add(this);
    }
}