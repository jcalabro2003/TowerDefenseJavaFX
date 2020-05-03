package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import sample.model.PNJ;

public class SpeedButtonListener implements EventHandler<MouseEvent> {
    private int iteration = 1;

    @Override
    public void handle(MouseEvent event) {

        if(iteration % 4 != 0){
            PNJ.setSleeptime(PNJ.getSleeptime()/2);
        } else{
            PNJ.setSleeptime(PNJ.getMaxSleepTime());
        }
        iteration++;
    }
}
