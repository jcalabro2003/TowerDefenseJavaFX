package sample.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Scanner;

public class PauseButtonListener implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Game paused. Press enter to resume");
        Scanner sc = new Scanner(System.in);
        String str = "default";

        try {
            while(str.charAt(0) != '\n') {
                str = sc.nextLine();
            }
        }
        catch(java.lang.StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
