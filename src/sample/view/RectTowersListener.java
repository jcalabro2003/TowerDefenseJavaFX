package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import sample.model.*;

import java.util.Set;

public class RectTowersListener implements EventHandler<MouseEvent> {
    private Rectangle rec;
    private Map map;
    private static boolean classicReady = false;
    public static boolean slowReady = false;

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

    @Override
    public void handle(MouseEvent event) {

        if (!map.isOccupied(rec.getX() + 12.5 , rec.getY() + 12.5)){
            Building building;
            if (classicReady && Player.getGold() >= Settings.CLASSIC_TOWER_PRICE){
                building = BuildingsFactory.getInstance("basic", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                Player.BuyBuilding(Settings.CLASSIC_TOWER_PRICE);
            }else if (slowReady && Player.getGold() >= Settings.SLOW_TOWER_PRICE){
                building = BuildingsFactory.getInstance("slow", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                Player.BuyBuilding(Settings.SLOW_TOWER_PRICE);
            }
            classicReady = false;
            slowReady = false;
        }
    }
}
