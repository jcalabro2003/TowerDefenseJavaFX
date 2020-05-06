package sample.view;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import sample.model.*;

public class RectTowersListener implements EventHandler<MouseEvent> {
    private Rectangle rec;
    private Map map;
    private static HBox hBoxMessage;
    private static boolean classicReady = false;
    public static boolean slowReady = false;

    public RectTowersListener(Rectangle rec, Map map){
        this.rec = rec;
        this.map = map;
    }

    public static void setClassicReady(boolean classicReady, HBox hBoxMessage) {
        RectTowersListener.classicReady = classicReady;
        RectTowersListener.hBoxMessage = hBoxMessage;
    }

    public static void setSlowReady(boolean slowReady, HBox hBoxMessage) {
        RectTowersListener.slowReady = slowReady;
        RectTowersListener.hBoxMessage = hBoxMessage;
    }

    @Override
    public void handle(MouseEvent event) {

        if (!map.isOccupied(rec.getX() + 12.5 , rec.getY() + 12.5)){
            Building building;
            if (classicReady && Player.getGold() >= Settings.CLASSIC_TOWER_PRICE) {
                building = BuildingsFactory.getInstance("basic", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                Player.BuyBuilding(Settings.CLASSIC_TOWER_PRICE);
            } else if (slowReady && Player.getGold() >= Settings.SLOW_TOWER_PRICE){
                building = BuildingsFactory.getInstance("slow", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                Player.BuyBuilding(Settings.SLOW_TOWER_PRICE);
            } else if (classicReady || slowReady) {
                ImageView im = LoadingImage.loadImage("feuFouge.gif", 25, 25);
               // hBoxMessage.setT;
            }
            classicReady = false;
            slowReady = false;
        }
    }
}
