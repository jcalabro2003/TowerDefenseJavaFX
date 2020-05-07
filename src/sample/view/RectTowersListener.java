package sample.view;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.model.*;

public class RectTowersListener implements EventHandler<MouseEvent> {
    private Rectangle rec;
    private Map map;
    private static HBox hBoxMessage;;
    private static boolean classicReady = false;
    public static boolean slowReady = false;
    private static boolean upgradeReady = false;
    private static Text  notEnoughMoney = new Text("not enough money");
    private Building building;
//Rectangle rectangle = new Rectangle(x, y, 50, 50);
   //rectangle.setFill(Color.TRANSPARENT);
    //rectangle.setOnMouseClicked(new RectTowersListener(rectangle,this));

    public RectTowersListener(Rectangle rec, Map map){
        this.rec = rec;
        this.map = map;
    }

    public static void setClassicReady(boolean classicReady) {
        RectTowersListener.classicReady = classicReady;
    }

    public static void setSlowReady(boolean slowReady) {
        RectTowersListener.slowReady = slowReady;
    }

    public static void setUpgradeReady(boolean upgradeReady) {
        RectTowersListener.upgradeReady = upgradeReady;
    }

    public static void sethBoxMessage(HBox hBoxMessage) {
        RectTowersListener.hBoxMessage = hBoxMessage;
        hBoxMessage.getChildren().add(notEnoughMoney);
    }

    @Override
    public void handle(MouseEvent event) {

        if (!map.isOccupied(rec.getX() + 12.5 , rec.getY() + 12.5) && (classicReady || slowReady)){
            if (classicReady && Player.getGold() >= Settings.CLASSIC_TOWER_PRICE) {
                building = BuildingsFactory.getInstance("basic", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                Player.BuyBuilding(Settings.CLASSIC_TOWER_PRICE);
            } else if (slowReady && Player.getGold() >= Settings.SLOW_TOWER_PRICE){
                building = BuildingsFactory.getInstance("slow", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                Player.BuyBuilding(Settings.SLOW_TOWER_PRICE);
            } else {
                notEnoughMoney.setFill(Color.RED);

            }
            classicReady = false;
            slowReady = false;
        } else if (upgradeReady && Player.getGold() >= 100 && building instanceof Upgradable){
            System.out.println("hé ho");
            ((Upgradable) building).upgrade();
            Player.BuyBuilding(100);
            upgradeReady = false;
        }
    }
}
