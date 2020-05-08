package sample.view;

import sample.InfoPane;
import sample.model.ChangeMapObserver;
import sample.model.Map;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class NewMapListenerBtMap1 implements EventHandler<MouseEvent>, ChangeMapObserver {
    private static String typeMap;
    private Map map = Map.getInstance();

    public NewMapListenerBtMap1(String typeMap) {
        NewMapListenerBtMap1.typeMap = typeMap;
        map.addObserver(this);
    }

    @Override
    public void changeMap() {
        Map.getInstance();
    }

    @Override
    public void handle(MouseEvent event) {
        map.setInstance(typeMap);
        InfoPane.init();
    }
}
