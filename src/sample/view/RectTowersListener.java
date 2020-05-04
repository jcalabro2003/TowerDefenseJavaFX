package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import sample.model.*;

import java.util.ArrayList;

public class RectTowersListener implements EventHandler<MouseEvent> {
    private Rectangle rec;
    private Map map;

    public RectTowersListener(Rectangle rec, Map map){
        this.rec = rec;
        this.map = map;
    }

    @Override
    public void handle(MouseEvent event) {

        ArrayList< Building > buildings = new ArrayList<>();
        boolean IsBuildingCreated = false;

        System.out.println((rec.getX() + 12.5) + " " + (rec.getY() - 12.5) );

        for (Building building: buildings) {

            if (building.getPosX() == rec.getX() + 12.5 && building.getPosY() == rec.getY() - 12.5){
                System.out.println("Nique ta mère");
                IsBuildingCreated = true;
            }

        }

        if (!IsBuildingCreated) {

            Building building = BuildingsFactory.getInstance("classic tower", rec.getX() + 12.5 , rec.getY() - 12.5);
            System.out.println(building.getPosX() + " " +building.getPosY());
            buildings.add(building);
            map.getChildren().add(building.getImageView());
            System.out.println("Tqt frérot");
        }
    }
}
