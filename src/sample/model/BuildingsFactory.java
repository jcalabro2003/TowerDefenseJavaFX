package sample.model;

import javafx.scene.image.ImageView;

public class BuildingsFactory {

    public static Building getInstance(String type, double x, double y){
        ImageView imageView;
        Building res = null;
        switch (type){
            case "basic":
                imageView = LoadingImage.loadImage("towers.png", 40 , 40);
                res = new Tower(10, 300, 2000, imageView, x, y, "basic");
                break;
            case "slow":
                imageView = LoadingImage.loadImage("pnj.png", 40, 40);
                res = new Tower(0, 300, 3000, imageView, x , y, "slow");
                break;
            default: break;
        }
        return res;
    }
}
