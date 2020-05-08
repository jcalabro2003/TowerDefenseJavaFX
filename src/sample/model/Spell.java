package sample.model;


public class Spell extends GameObject implements Movable {
    private String type;
    private int velocity;
    private int damage;
    private boolean ready = true;
    private SpellCreator spellCreator;
    private int sleeptime = 50;
    private int range = 300;

    public Spell(int velocity, SpellCreator spellCreator, int damage) {
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
        this.setPosX((this.posX + velocity * Math.cos(theta)));
        this.setPosY((this.posY + velocity * Math.sin(theta)));
    }

    @Override
    public void update() {
        imageView.setY(posY);
        imageView.setX(posX);
    }
    public void applyDamage(Point point){
        for (PNJ pnj : Tower.getPnjs()){
            if (pnj.getDistance(point) <= range){
                pnj.receiveDamage(damage);
            }
        }
    }

    public void fire(Point point) {
        imageView.setVisible(true);

        while (posX != point.getX() && posY != point.getY()) {
            move(point);
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        applyDamage(point);
        imageView.setVisible(false);
    }
}
