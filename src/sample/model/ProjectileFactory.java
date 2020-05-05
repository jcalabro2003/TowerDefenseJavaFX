package sample.model;


import javafx.scene.image.ImageView;

public class ProjectileFactory {

    public static Projectile getInstance(String type, PNJ target, Tower tower){
        ImageView imageView;
        Projectile res = null;
        switch (type){
            case "basic":
                imageView = LoadingImage.loadImage("billBalle.png", 25, 25);
                res = new Projectile(target, imageView, tower);
                break;
            case "slow":
                imageView = LoadingImage.loadImage("", 25, 25);
                res = new Projectile(target, imageView, tower);
            default: break;
        }
        return res;
    }
}
