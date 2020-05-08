package sample.view;

import sample.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CancelListener  implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {

        Controller.changeMapButton.setDisable(true);
    }

}
