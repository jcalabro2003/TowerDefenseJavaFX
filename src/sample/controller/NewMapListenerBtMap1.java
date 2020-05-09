package sample.controller;

import sample.view.InfoPane;
import sample.model.ChangeMapObserver;
import sample.view.Map;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class NewMapListenerBtMap1 implements EventHandler<ActionEvent>, ChangeMapObserver {
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
    public void handle(ActionEvent event) {
        map.setInstance(typeMap);
        InfoPane.init();
    }
}
