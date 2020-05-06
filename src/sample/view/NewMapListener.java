package sample.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import sample.Controller;
import sample.InfoPane;
import sample.model.Map;

public class NewMapListener implements EventHandler<MouseEvent> {
    private Map bodyPane;
    private String typeMap;

    public NewMapListener(Map bodyPane, String typeMap) {
        this.bodyPane = bodyPane;
        this.typeMap = typeMap;
    }

    @Override
    public void handle(MouseEvent event) {
        Controller.setTypeMapGlobal(typeMap);
        Map.setInstance();
        InfoPane.init();
        //Controller.bodyPane.setStyle("-fx-background-color: black;");
    }
}
