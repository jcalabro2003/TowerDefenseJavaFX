package sample.model;
//
import javafx.scene.image.ImageView;
import sample.view.LoadingImage;

public class ProjectileFactory {

    public static Projectile getInstance(String type, PNJ target, Tower tower){
        ImageView imageView;
        Projectile res = null;
        switch (type){
            case "basic":
                imageView = LoadingImage.loadImage("redBalle.png", 25, 25);
                res = new Projectile(7, target, imageView, tower);
                break;
            case "slow":
                imageView = LoadingImage.loadImage("redshell.gif", 15, 15);
                res = new Projectile(11, target, imageView, tower);
                break;
            default: break;
        }
        return res;
    }
}