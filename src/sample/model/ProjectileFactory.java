package sample.model;


import javafx.scene.image.ImageView;

public class ProjectileFactory {

    public static Projectile getInstance(String type, PNJ target, Tower tower){
        ImageView imageView;
        Projectile res = null;
        switch (type){
            case "basic":
                imageView = LoadingImage.loadImage("billBalle.png");
                res = new Projectile(target, 10, imageView, tower);
                break;
            default: break;
        }
        return res;
    }
}
