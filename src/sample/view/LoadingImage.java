package sample.view;

import sample.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoadingImage {
    public static ImageView loadImage(String path) {
        Image image = new Image(Main.class.getResourceAsStream("images/" + path));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);

        return imageView;
    }
    public static ImageView loadImage(String path,int width, int height) {
        Image image = new Image(Main.class.getResourceAsStream("images/" + path));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        return imageView;
    }
}
