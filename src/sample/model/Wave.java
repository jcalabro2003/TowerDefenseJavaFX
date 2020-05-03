package sample.model;

import java.util.ArrayList;

public class Wave {
    private ArrayList<PNJ> pnjs = new ArrayList<>();
    private static int waveNumber;
    private int duration = 40000;
    private String pnjType;

    public Wave(int waveNumber){
        Wave.waveNumber = waveNumber;
        initWave();

    }

    public void spawnPNJs(String type, int number){
        for (int i=0; i<number; i++){
            PNJ newPNJ = PNJFactory.getInstance(type);
            pnjs.add(newPNJ);
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
}