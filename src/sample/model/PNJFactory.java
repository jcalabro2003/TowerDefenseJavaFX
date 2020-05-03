package sample.model;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

public class PNJFactory {

    public static PNJ getInstance(@NotNull String type){
        ImageView imageView;
        PNJ res = null;
        switch (type){
            case ("basic"):
                imageView = LoadingImage.loadImage("pnj.png");
                res = new PNJ(100, 3, imageView);
                break;
            case ("tank"):
                imageView = LoadingImage.loadImage("tank.png");
                res = new PNJ(500, 1, imageView);
                break;
            case ("fast"):
                imageView = LoadingImage.loadImage("fastpnj.png");
                res = new PNJ(70, 10, imageView);
                break;
            default: break;
        }
        return res;
    }
}
