package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import sample.model.Map;

public class ChangeMapButtonListener implements EventHandler<ActionEvent> {
    private Map map;
    private static int iteration = 1;

    public ChangeMapButtonListener(Map pane){
        this.map = Map.getInstance();
    }

    @Override
    public void handle(ActionEvent event) {
        Button map1Button = new Button("MAP 1");
        Button map2Button = new Button("MAP 2");

        TilePane secondaryLayout = new TilePane();
        secondaryLayout.getChildren().addAll(map1Button, map2Button);

        Scene secondScene = new Scene(secondaryLayout, 450, 300);

        Stage newWindow = new Stage();
        newWindow.setTitle("Change Map");
        newWindow.setScene(secondScene);



        newWindow.show();
    }
}
