package sample.view;
//
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import sample.Controller;
import sample.InfoPane;
import sample.model.ChangeMapObserver;
import sample.model.Map;

public class NewMapListener implements EventHandler<MouseEvent>, ChangeMapObserver {
    private static String typeMap;
    private Map map = Map.getInstance();

    public NewMapListener(String typeMap) {
        NewMapListener.typeMap = typeMap;
        map.addObserver(this);
    }

    @Override
    public void handle(MouseEvent event) {
        map.setInstance();
        InfoPane.init();
    }

    public static String getTypeMap() {
        return typeMap;
    }

    public static void setTypeMap(String typeMap) {
        NewMapListener.typeMap = typeMap;
    }

    @Override
    public void changeMap() {
        Map.getInstance();
    }
}
