package sample.controller;
//
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import sample.model.PNJ;
import sample.model.Preparation;
import sample.model.Projectile;
import sample.model.Wave;

public class SpeedButtonListener implements EventHandler<MouseEvent> {
    private int iteration = 1;

    @Override
    public void handle(MouseEvent event) {

        if(iteration % 4 != 0){
            PNJ.setSleepTime(PNJ.getSleepTime()/2);
            Wave.setDuration(Wave.getDuration()/2);
            Preparation.setDuration(Preparation.getDuration()/2);
            Projectile.setSleepTime(Projectile.getSleepTime()/2);
        } else{
            PNJ.setSleepTime(PNJ.getMaxSleepTime());
            Wave.setDuration(Wave.getMaxDuration());
            Preparation.setDuration(Preparation.getMaxDuration());
            Projectile.setSleepTime(Projectile.getMaxSleepTime());
        }
        iteration++;
    }
}
