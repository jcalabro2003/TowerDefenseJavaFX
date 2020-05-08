package sample.view;

import sample.model.ChangeMapObserver;
import sample.model.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class ChangeMapButtonListener implements EventHandler<ActionEvent> , ChangeMapObserver {
    private Map bodyPane;

    public ChangeMapButtonListener(Map bodyPane) {
        this.bodyPane = bodyPane;
        bodyPane.addObserver(this);
    }

    @Override
    public void handle(ActionEvent event) {
        Button map1Button = new Button("MAP 1");
        Button map2Button = new Button("MAP 2");

        map1Button.setOnMouseClicked(new NewMapListenerBtMap1("map1"));
        map2Button.setOnMouseClicked(new NewMapListenerBtMap2( "map2"));

        TilePane secondaryLayout = new TilePane();
        secondaryLayout.getChildren().addAll(map1Button, map2Button);

        Scene secondScene = new Scene(secondaryLayout, 450, 300);

        Stage newWindow = new Stage();
        newWindow.setTitle("Change Map");
        newWindow.setScene(secondScene);

        newWindow.show();
    }

    @Override
    public void changeMap() {
        Map.getInstance();
    }
}
