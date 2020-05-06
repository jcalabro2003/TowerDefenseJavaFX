package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.model.Map;

public class UpgradeListener  implements EventHandler<MouseEvent> {

    private Map map;
    private HBox hBoxMessage;

    public UpgradeListener(Map map, HBox hBoxMessage) {
        this.map = map;
        this.hBoxMessage = hBoxMessage;
    }

    @Override
    public void handle(MouseEvent event) {

    }
}
