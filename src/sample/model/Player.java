package sample.model;
//
import javafx.scene.image.ImageView;
import sample.view.InfoPane;
import sample.view.LoadingImage;
import sample.view.Map;

public class Player implements StoppedObserver{
    private static Player instance = null;
    private static int healthPoints;
    private static int gold;

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

    public static void BuyBuilding(int price){
        gold -= price;
        InfoPane.update();
    }

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
            gold += ((PNJ) o).getMaxHealth()/3;
        }
    }
}