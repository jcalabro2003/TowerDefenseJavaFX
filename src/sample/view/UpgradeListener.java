package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import sample.model.Map;

public class UpgradeListener  implements EventHandler<MouseEvent> {

    private Map map;

    public UpgradeListener(Map map){
        this.map = map;
    }



    @Override
    public void handle(MouseEvent event) {

    }
}
