package sample.model;

import java.util.ArrayList;

public class Tower extends Building implements Runnable, Upgradable {

    private int damage;
    private int range;
    private int reloading;
    private Thread thread;
    private ArrayList<PNJ> pnjsInRange = new ArrayList<>();
    private int level;

    public Tower(int damage, int range, int reloading){
        super();
        this.damage = damage;
        this.range = range;
        this.reloading = reloading;
        this.thread.start();
        this.level = 1;

    }

    public void addPnjInRange(PNJ pnj){
        pnjsInRange.add(pnj);
    }

    public PNJ getNearest(){
        PNJ res = null;
        double distance = 0;
        for(int i=0; i<pnjsInRange.size(); i++){
            if (i == 0){
                distance = pnjsInRange.get(i).getDistance(this);
                res = pnjsInRange.get(i);
            } else {
                if (distance > pnjsInRange.get(i).getDistance(this)){
                    distance = pnjsInRange.get(i).getDistance(this);
                    res = pnjsInRange.get(i);
                }
            }
        }
        return res;
    }

    public void shoot(PNJ pnj){
        Projectile newProjectile = new Projectile(pnj, damage);
    }

    @Override
    public void run() {
        try {
            shoot(getNearest());
            Thread.sleep(reloading);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upgrade() {
        if (level == 1){
            level++;
            damage += damage/3;
        }
        else if(level == 2){
            level++;
            reloading -= reloading/3;
        }
        else {
            range += range/3;
        }
    }
}
