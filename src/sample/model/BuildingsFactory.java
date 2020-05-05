package sample.model;

import javafx.scene.image.ImageView;

public class BuildingsFactory {

    public static Building getInstance(String type, double x, double y){
        ImageView imageView;
        Building res = null;
        switch (type){
            case "classic tower":
                imageView = LoadingImage.loadImage("towers.png", 40 , 40);
                res = new Tower(10, 300, 2000, imageView, x, y);

                break;
            case "slow tower":
                imageView = LoadingImage.loadImage("pnj.png");
                res = new Tower(0, 10, 1500, imageView, x , y);

            default:
                break;
        }
        return res;
    }
}
