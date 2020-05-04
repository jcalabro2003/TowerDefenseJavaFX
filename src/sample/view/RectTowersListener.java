package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import sample.model.Building;
import sample.model.BuildingsFactory;
import sample.model.Map;

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

        Building building = BuildingsFactory.getInstance("classic tower", rec.getX() + 12.5 , rec.getY() + 12.5);

        map.getChildren().add(building.getImageView());
    }
}
