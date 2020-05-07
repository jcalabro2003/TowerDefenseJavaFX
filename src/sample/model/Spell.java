package sample.model;
//
public class Spell extends GameObject implements Movable {
    private String type;
    private int velocity;
    private int damage;
    private boolean ready = true;
    private SpellCreator spellCreator;

    public Spell(int velocity, SpellCreator spellCreator, int damage){
        super();
        this.velocity = velocity;
        this.spellCreator = spellCreator;
        this.type = spellCreator.getType();
        this.damage = damage;
        imageView = LoadingImage.loadImage("images/" + type + ".png");
        imageView.setVisible(false);
    }

    @Override
    public void move(Point point) {
        double theta = Math.atan2(point.getY() - this.getPosY(), point.getX() - this.getPosX());
        this.setPosX( (this.posX + velocity * Math.cos(theta)));
        this.setPosY( (this.posY + velocity * Math.sin(theta)));
    }

    @Override
    public void update() {
        imageView.setY(posY);
        imageView.setX(posX);
    }

    public void fire(){

    }
}
