package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class RectTowersListener implements EventHandler<MouseEvent> {
    private TilePane pane;

    public RectTowersListener(TilePane pane){
        this.pane = pane;
    }

    @Override
    public void handle(MouseEvent event) {
        pane.setStyle("-fx-background-color: cyan;");
    }
}
