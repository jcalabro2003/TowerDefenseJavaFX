package sample.model;

import static org.junit.Assert.*;
import org.junit.Test;

import javafx.scene.image.ImageView;
import sample.Game;
import sample.view.LoadingImage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestPNJ {

    @Test
    public void setMaxHealthTestNormalConstructor() {
        String[] args = new String[0];
        Game.launchTowerDefense(args);
        ImageView imageView = LoadingImage.loadImage("yoshi.png",30,30);
        ImageView rotateImage = LoadingImage.loadImage("yoshi.png", 30, 30);
        PNJ pnj = new PNJ(40, 4, imageView, rotateImage);
        assertEquals(pnj.getMaxHealth(), 6);
    }

}
