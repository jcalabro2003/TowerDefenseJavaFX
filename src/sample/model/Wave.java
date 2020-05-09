package sample.model;
//
import sample.view.InfoPane;
import sample.view.Map;

import java.util.ArrayList;


public class Wave implements Runnable , ChangeMapObserver{
    private static int waveNumber;
    private static int duration = Settings.WAVE_MAX_DURATION;
    private static int maxDuration = Settings.WAVE_MAX_DURATION;
    private Thread t;
    private static boolean ready = true;
    private Map map = Map.getInstance();
    private static ArrayList<Building> buildings = new ArrayList<>();

    public Wave(int waveNumber){
        map.addObserver(this);
        Wave.waveNumber = waveNumber;
        InfoPane.update();
        initWave();
        t = new Thread(this);
        t.start();

    }

    public static boolean isReady() {
        return ready;
    }


    public static int getMaxDuration() {
        return maxDuration;
    }

    public static ArrayList<Building> getBuildings() {
        return buildings;
    }


    public void spawnPNJs(String type, int number){
        for (int i=0; i<number; i++){
            PNJFactory.getInstance(type);
        }
    }

    private void initWave(){
        switch (waveNumber % 6){
            case 1:
                spawnPNJs("basic", 2*waveNumber +2);
                break;
            case 2:
                spawnPNJs("fast", waveNumber +1);
                break;
            case 3:
                spawnPNJs("tank", waveNumber +1);
                break;
            case 4:
                spawnPNJs("basic", waveNumber +2);
                spawnPNJs("fast", waveNumber + 2);
                break;
            case 5:
                spawnPNJs("basic", waveNumber + 2);
                spawnPNJs("tank", waveNumber);
                break;
            case 0:
                spawnPNJs("basic", waveNumber*3/2 +2);
                spawnPNJs("fast", waveNumber/2);
                spawnPNJs("tank", waveNumber/2);
                break;
            default: break;
        }
    }

    public static int getDuration() {
        return duration;
    }

    public static void setDuration(int duration) {
        Wave.duration = duration;
    }

    public static int getWaveNumber() {
        return waveNumber;
    }

    @Override
    public void run() {
            ready = false;
            while (map.getGameObjects().size() > buildings.size() + map.getNbOfSpell()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ready = true;
            Preparation.prepare(10000);
    }

    @Override
    public void changeMap() {
        Map.getInstance();
    }
}