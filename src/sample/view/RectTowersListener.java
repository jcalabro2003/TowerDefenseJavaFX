package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import sample.model.*;

public class RectTowersListener implements EventHandler<MouseEvent> {
    private Rectangle rec;
    private Map map;
    private static boolean classicReady = false;
    public static boolean slowReady = false;
    private Building building;

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
            if (classicReady){
                building = BuildingsFactory.getInstance("classic tower", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                classicReady = false;
            }else if (slowReady){
                building = BuildingsFactory.getInstance("slow tower", rec.getX() + 12.5 , rec.getY() + 12.5);
                map.addObjectToMap(building);
                slowReady = false;
            }

        }
    }
}
