package sample.model;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

public class PNJFactory {

    public static PNJ getInstance(@NotNull String type){
        ImageView imageView;
        PNJ res = null;
        switch (type){
            case ("basic"):
                imageView = LoadingImage.loadImage("Mario.png");
                res = new PNJ(30, 3, imageView);
                break;
            case ("tank"):
                imageView = LoadingImage.loadImage("Yoshi.png");
                res = new PNJ(100, 1, imageView);
                break;
            case ("fast"):
                imageView = LoadingImage.loadImage("Toad.png");
                res = new PNJ(10, 10, imageView);
                break;
            default: break;
        }
        return res;
    }
}
