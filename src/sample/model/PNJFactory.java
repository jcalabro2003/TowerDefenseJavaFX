package sample.model;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

public class PNJFactory {

    public static PNJ getInstance(@NotNull String type){
        ImageView imageView;
        ImageView rotateImage;

        PNJ res = null;
        switch (type){
            case ("basic"):
                imageView = LoadingImage.loadImage("yoshi.png",30,30);
                rotateImage = LoadingImage.loadImage("yoshi.gif", 30, 30);
                res = new PNJ(30, 3, imageView, rotateImage);
                break;
            case ("tank"):
                imageView = LoadingImage.loadImage("Bowser.png",30,30);
                rotateImage = LoadingImage.loadImage("Bowser.gif", 30, 30);
                res = new PNJ(100, 1, imageView, rotateImage);
                break;
            case ("fast"):
                imageView = LoadingImage.loadImage("Toad.png",30,30);
                rotateImage = LoadingImage.loadImage("Toad.gif", 30, 30);
                res = new PNJ(10, 7, imageView, rotateImage);
                break;
            default: break;
        }
        return res;
    }
}
