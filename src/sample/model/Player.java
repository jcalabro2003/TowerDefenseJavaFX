package sample.model;

import java.util.ArrayList;

public class Player implements StoppedObserver{
    private int healthPoints = Settings.HEALTH_POINTS;
    private int gold = Settings.MONEY_AMOUNT;
    private ArrayList<Building> buildings = new ArrayList<>();



    public void BuyBuilding(String type){
        Building newBuilding = BuildingsFactory.getInstance(type);
        if(this.gold >= newBuilding.price){
            this.buildings.add(newBuilding);
        }
    }

    @Override
    public void react(GameObject o) {
        healthPoints--;
    }
}