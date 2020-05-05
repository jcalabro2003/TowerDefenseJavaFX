package sample.model;

import java.util.ArrayList;

public class Player implements StoppedObserver{
    private static Player instance = null;
    private static int healthPoints;
    private static int gold;
    private ArrayList<Building> buildings = new ArrayList<>();
    private Map map = Map.getInstance();

    private Player(){
        healthPoints = Settings.HEALTH_POINTS;
        gold = Settings.MONEY_AMOUNT;
    }
    public static Player getInstance(){
        if(Player.instance == null){
            Player.instance = new Player();
        }
        return Player.instance;
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
        }else{
            gold += 10;
        }
    }
}