package sample.model;


import javafx.scene.image.ImageView;

public class SpellCreator extends  Building{
    private Spell spell;
    private String type;


    public SpellCreator(ImageView imageView, double posX, double posY, String type){
        super();
        this.imageView = imageView;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        imageView.setX(posX);
        imageView.setY(posY);
        spell = new Spell(3, this, 20);
    }

    public String getType() {
        return type;
    }
}
