package sample.model;
//
import javafx.scene.image.ImageView;
import sample.view.LoadingImage;

public class BuildingsFactory {

    public static Building getInstance(String type, double x, double y){
        ImageView imageView;
        ImageView gif;
        Building res = null;
        switch (type){
            case "basic":
                imageView = LoadingImage.loadImage("towers.png", 40 , 40);
                gif = LoadingImage.loadImage("towers.png", 40, 40);
                res = new Tower(10, 300, 2000, imageView, x, y, type, gif);
                break;
            case "slow":
                imageView = LoadingImage.loadImage("slowTower.png", 50, 50);
                gif = LoadingImage.loadImage("slowtower2.gif", 50, 50);
                res = new Tower(0, 300, 2000, imageView, x , y, type, gif);
                break;
            case "bomb":
                imageView = LoadingImage.loadImage("bombCanon.png", 40, 40);
                res = new SpellCreator(imageView, x, y, type);
                break;
            default: break;
        }
        return res;
    }
}
