package sample.model;

import sample.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoadingImage {
    public static ImageView loadImage(String path) {
        Image image = new Image(Main.class.getResourceAsStream(path));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);

        return imageView;
    }
}
