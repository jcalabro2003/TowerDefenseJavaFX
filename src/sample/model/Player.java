package sample.model;

import java.util.ArrayList;

public class Player implements StoppedObserver{
    private static int healthPoints;
    private static int gold;
    private ArrayList<Building> buildings = new ArrayList<>();

    public Player(){
        healthPoints = Settings.HEALTH_POINTS;
        gold = Settings.MONEY_AMOUNT;
        PNJ.getObservers().add(this);
    }

    //public void BuyBuilding(String type){
      //  Building newBuilding = BuildingsFactory.getInstance(type);
        //if(this.gold >= newBuilding.price){
          //  this.buildings.add(newBuilding);
        //}
    //}

    public static int getHealthPoints() {
        return healthPoints;
    }

    public static int getGold() {
        return gold;
    }

    @Override
    public void react(GameObject o) {
        if (((PNJ) o).isAlive()){
            healthPoints--;
        }
    }
}