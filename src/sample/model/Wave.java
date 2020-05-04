package sample.model;

import java.util.ArrayList;

public class Wave implements Runnable{
    private static int waveNumber;
    private static int duration = 40000;
    private static int maxDuration = 40000;
    private Thread t;
    private static boolean ready = true;

    public Wave(int waveNumber){
        Wave.waveNumber = waveNumber;
        System.out.println(waveNumber);
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


    public void spawnPNJs(String type, int number){
        for (int i=0; i<number; i++){
            PNJFactory.getInstance(type);
        }
    }

    private void initWave(){
        switch (waveNumber % 6){
                case 1:
                    spawnPNJs("basic", 3*waveNumber +2);
                    break;
                case 2:
                    spawnPNJs("tank", waveNumber);
                    break;
                case 3:
                    spawnPNJs("fast", waveNumber + 1);
                    break;
                case 4:
                    spawnPNJs("basic", waveNumber*2 +2);
                    spawnPNJs("fast", waveNumber + 2);
                    break;
                case 5:
                    spawnPNJs("basic", waveNumber*2 + 2);
                    spawnPNJs("tank", waveNumber);
                    break;
                case 0:
                    spawnPNJs("basic", waveNumber*2 +2);
                    spawnPNJs("fast", waveNumber);
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

    @Override
    public void run() {
        ready = false;
        try {
            Thread.sleep(duration);
            ready = true;
            Preparation preparation = new Preparation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}