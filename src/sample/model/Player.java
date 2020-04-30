package sample.model;

import java.util.ArrayList;

public class Player {
    private int score = 10000;
    private int gold = 100;
    private ArrayList<Building> buildings = new ArrayList<>();



    public void BuyBuilding(String type){
        Building newBuilding = BuildingsFactory.getInstance(type);
        if(this.gold >= newBuilding.price){
            this.buildings.add(newBuilding);
        }
    }

}