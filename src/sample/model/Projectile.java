package sample.model;

public class Projectile extends GameObject implements Movable, Stop {

    private Tower tower;
    private PNJ target;
    private int velocity;
    private int damage;


    public Projectile(PNJ target, int damage){
        super();
        this.target = target;
        this.damage = damage;

    }



    @Override
    public void move(Point p) {                                                // x et y sont les coordonées du PNJ target
        double theta = Math.atan2(p.getY() - this.getPosY(), p.getX() - this.getPosX());          //calcul l'angle entre la droite qui relie le projectile et le pnj et l'axe des x
        this.setPosX( (int) (this.posX + velocity * Math.cos(theta) ));             //déplacement du projectile vers le pnj
        this.setPosY( (int) (this.posY + velocity * Math.sin(theta) ));

    }

    @Override
    public void update() {
        imageView.setY(posY);
        imageView.setX(posX);
    }


    @Override
    public void notifyObserver() {

    }


    public int getDamage() {
        return damage;
    }
}