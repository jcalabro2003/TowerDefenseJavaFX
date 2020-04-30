package sample.model;

import java.util.ArrayList;

public class Wave {
    private ArrayList<PNJ> pnjs = new ArrayList<>();
    private int waveNumber;
    private int duration = 40000;
    private String pnjType;

    public Wave(int waveNumber){
        this.waveNumber = waveNumber;
        spawnPNJs(pnjType);
    }

    public void spawnPNJs(String type){
        PNJ newPNJ = PNJFactory.getInstance(type);
        pnjs.add(newPNJ);
    }
}